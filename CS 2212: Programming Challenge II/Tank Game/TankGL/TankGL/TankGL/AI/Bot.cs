using Microsoft.Xna.Framework.Input;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using TankGL.Network;
using TankGL.GUI;
using Microsoft.Xna.Framework;
using System.Collections;

namespace TankGL.AI
{
    class Bot
    {
        static int numOfPlayers;
        static Tank[] tanks = new Tank[5];
        static Client client = new Client();
        static int myId;
        static Cell[,] grid = new Cell[Constants.GRID_SIZE, Constants.GRID_SIZE];
        public static List<Brick> bricks;
        static String lastMsg, currentMsg = "SHOOT#";
        static Stack<String> path = new Stack<String>();
        public static List<CoinPile> coins = new List<CoinPile>();
        public static List<HealthPack> healthPack = new List<HealthPack>();
        public static List<Obstacle> stone = new List<Obstacle>();
        public static List<Obstacle> water = new List<Obstacle>();
        Server myServer = new Server();
        Client myClient = new Client();
        static bool joined = false, started = false;
        long time;
        private Object thisLock = new Object();

        //private Game myGame;

        public Bot(/*Game myGame*/)
        {
            // TODO: Complete member initialization
            join();
        }

        private void join()
        {
            while (!joined)
            {
                time = myClient.sendMessage();
                myClient.sendMessage("JOIN#");

                myServer.listen();
            }
            while (!started)
            {
                myServer.listen();
            }
        }

        internal static void playersFull()
        {
            Console.WriteLine("Players full. Press Escape to exit.");
            while (!Keyboard.GetState().IsKeyDown(Keys.Escape)) ;
            return;
        }

        internal static void alreadyAdded()
        {
            Console.WriteLine("Already Added.");
            joined = true;
        }

        internal static void globalUpdate(String msg)
        {
            //G:P0;0,0;0;0;100;0;0:P1;0,9;0;0;100;0;0:
            msg = msg.Substring(2, msg.Length - 3);
            String[] details = msg.Split(':');
            String message = "";
            for (int i = 0; i < numOfPlayers; i++)
            {
                String[] temp = details[i].Split(';');
                message += temp[0] + ";" + temp[1] + ";" + temp[2] + ":";
                if (int.Parse(temp[4]) == 0)
                {
                    grid[tanks[i].getX(), tanks[i].getY()].ContentCode = Occupant.E;
                }
            }
            setPlayers(message.Substring(0, message.Length - 1));
            for (int i = 0; i < numOfPlayers; i++)
            {
                String[] temp = details[i].Split(';');
                if (int.Parse(temp[4]) == 0)
                {
                    grid[tanks[i].getX(), tanks[i].getY()].ContentCode = Occupant.E;
                }
            }
            for (int i = 0; i < numOfPlayers; i++)
            {
                tanks[i].update(details[i]);
            }
            //:0,3,0;7,1,0;1,3,0;3,6,0;0,8,0;2,6,0#
            setBricks(details[numOfPlayers]);
            update(coins);
            update(healthPack); ;
            display();
            play();
        }

        public static void play()
        {
            if (!shoot(tanks[myId].getDirection(), Occupant.T))
            {
                move();
            }
            client.sendMessage(currentMsg);
            lastMsg = currentMsg;
        }

        private static void update(List<CoinPile> coins)
        {
            Console.WriteLine("Updating coin piles");
            if (coins.Count == 0)
                return;
            for (int i = 0; i < coins.Count; i++)
            {
                CoinPile coin = coins.ElementAt(i);
                if (coin.getTTL() <= 0 ||
                    coin.collected(tanks))
                {
                    coins.RemoveAt(i);
                    i--;
                }
            }
            foreach (CoinPile c in coins)
            {
                grid[c.getPos().X, c.getPos().Y].ContentCode = Occupant.C;
            }
        }

        private static void update(List<HealthPack> healthPack)
        {
            Console.WriteLine("Updating health Packs");
            if (healthPack.Count == 0)
                return;
            for (int i = 0; i < healthPack.Count; i++)
            {
                if (healthPack.ElementAt(i).getTTL() <= 0)
                {
                    healthPack.RemoveAt(i);
                    i--;
                }
            }
            foreach (HealthPack c in healthPack)
            {
                grid[c.getPos().X, c.getPos().Y].ContentCode = Occupant.H;
            }
        }

        internal static bool shoot(int direction, Occupant target)
        {
            bool done = false;
            Tank t = tanks[myId];
            switch (direction)
            {
                case 0:
                    for (int i = t.getY() - 1; i > 0; i--)
                    {
                        Occupant occ = grid[t.getX(), i].ContentCode;
                        if (occ.Equals(target))
                        {
                            currentMsg = "SHOOT#";
                            done = true;
                            break;
                        }
                        else if (occ.Equals(Occupant.S))
                        {
                            break;
                        }
                    }
                    break;
                case 1:
                    for (int i = t.getX() + 1; i < Constants.GRID_SIZE; i++)
                    {
                        Occupant occ = grid[i, t.getY()].ContentCode;
                        if (occ.Equals(target))
                        {
                            currentMsg = "SHOOT#";
                            done = true;
                            break;
                        }
                        else if (occ.Equals(Occupant.S))
                        {
                            break;
                        }
                    }
                    break;
                case 2:
                    for (int i = t.getY() + 1; i < Constants.GRID_SIZE; i++)
                    {
                        Occupant occ = grid[t.getX(), i].ContentCode;
                        if (occ.Equals(target))
                        {
                            currentMsg = "SHOOT#";
                            done = true;
                            break;
                        }
                        else if (occ.Equals(Occupant.S))
                        {
                            break;
                        }
                    }
                    break;
                case 3:
                    for (int i = t.getX() - 1; i > 0; i--)
                    {
                        Occupant occ = grid[i, t.getY()].ContentCode;
                        if (occ.Equals(target))
                        {
                            currentMsg = "SHOOT#";
                            done = true;
                            break;
                        }
                        else if (occ.Equals(Occupant.S))
                        {
                            break;
                        }
                    }
                    break;
            }
            return done;
        }

        internal static void move()
        {
            Tank t = tanks[myId];
            Stack<String> tempPath = new Stack<string>();
            if (coins.Count != 0)
            {
                //foreach (CoinPile c in coins)
                //{
                Pathfinder myPath = new Pathfinder(grid);
                path = myPath.Pathfind(t.getPoint(), coins.ElementAt(0).getPos());

                /*if (tempPath.Count == 0)
                {
                    tempPath = path;
                }
                else if (path.Count < tempPath.Count)
                {
                    tempPath = path;
                }*/
                //}
                if (path.Count != 0)
                {
                    currentMsg = path.Pop();
                }
                else
                {
                    moveRand(t.getDirection(), 0);
                }
            }
            /*else if (healthPack.Count != 0)
            {
                foreach (HealthPack h in healthPack)
                {
                    Pathfinder myPath = new Pathfinder(grid);
                    path = myPath.Pathfind(t.getPoint(), h.getPos());

                    if (tempPath.Count == 0)
                    {
                        tempPath = path;
                    }
                    else if (path.Count < tempPath.Count)
                    {
                        tempPath = path;
                    }
                }
                currentMsg = tempPath.Pop();
            }*/
            else
            {
                moveRand(t.getDirection(), 0);
            }
        }

        internal static void moveRand(int direction, int call)
        {
            if (!shoot(tanks[myId].getDirection(), Occupant.B))
            {
                Tank t = tanks[myId];
                call++;
                if (call > 4)
                {
                    Console.WriteLine("Pass 1");
                    return;
                }

                switch (direction)
                {
                    case 0:
                        if (t.getY() - 1 < 0)
                        {
                            moveRand(1, call);
                            return;
                        }
                        else
                        {
                            if (grid[t.getX(), t.getY() - 1].isEmpty())
                            {
                                currentMsg = "UP#";
                            }
                            else
                            {
                                moveRand(1, call);
                                return;
                            }
                        }
                        break;
                    case 1:
                        if (t.getX() + 1 >= Constants.GRID_SIZE)
                        {
                            moveRand(2, call);
                            return;
                        }
                        else
                        {
                            if (grid[t.getX() + 1, t.getY()].isEmpty())
                            {
                                currentMsg = "RIGHT#";
                            }
                            else
                            {
                                moveRand(2, call);
                                return;
                            }
                        }
                        break;
                    case 2:
                        if (t.getY() + 1 >= Constants.GRID_SIZE)
                        {
                            moveRand(3, call);
                            return;
                        }
                        else
                        {
                            if (grid[t.getX(), t.getY() + 1].isEmpty())
                            {
                                currentMsg = "DOWN#";
                            }
                            else
                            {
                                moveRand(3, call);
                                return;
                            }
                        }
                        break;
                    case 3:
                        if (t.getX() - 1 < 0)
                        {
                            moveRand(0, call);
                            return;
                        }
                        else
                        {
                            if (grid[t.getX() - 1, t.getY()].isEmpty())
                            {
                                currentMsg = "LEFT#";
                            }
                            else
                            {
                                moveRand(0, call);
                                return;
                            }
                        }
                        break;
                }
            }
        }

        internal static void intiatePlayer(string msg)
        {
            started = true;
            msg = msg.Substring(2, msg.Length - 3);
            setPlayers(msg);
            display();
        }

        internal static void setBricks(String msg)
        {
            String[] temp = msg.Split(';');
            bricks = new List<Brick>();
            for (int i = 0; i < temp.Length; i++)
            {
                int damage = int.Parse(temp[i].Substring(temp[i].Length - 1, 1));
                if (damage == 4)
                {
                    String[] t = temp[i].Split(',');
                    grid[int.Parse(t[0]), int.Parse(t[1])].ContentCode = Occupant.E;
                }
                else
                {
                    bricks.Add(new Brick(temp[i]));
                }
            }
        }

        private static void setPlayers(string msg)
        {
            String[] players = msg.Split(':');
            numOfPlayers = players.Length;
            for (int i = 0; i < players.Length; i++)
            {
                String[] details = players[i].Split(';');
                Point p = getPoint(details[1]);
                if (tanks[i] != null)
                    grid[tanks[i].getX(), tanks[i].getY()].ContentCode = Occupant.E; //x,y corrected
                tanks[i] = new Tank(int.Parse(details[0].Substring(1, 1)), p,
                    int.Parse(details[2]));
                grid[p.X, p.Y].ContentCode = Occupant.T;
            }
        }

        public static void initiateGame(String message)
        {
            Console.WriteLine("Setting Game...");
            grid = new Cell[Constants.GRID_SIZE, Constants.GRID_SIZE];
            message = message.Substring(2, message.Length - 3);
            String[] temp = message.Split(':');
            myId = int.Parse(temp[0].Substring(1, 1));
            String[] temp2 = temp[1].Split(';');
            for (int i = 0; i < Constants.GRID_SIZE; i++)
            {
                for (int j = 0; j < Constants.GRID_SIZE; j++)
                {
                    grid[i, j] = new Cell();
                }
            }
            bricks = new List<Brick>();
            for (int i = 0; i < temp2.Length; i++)
            {
                String[] temp3 = temp2[i].Split(',');
                bricks.Add(new Brick(getPoint(temp2[i])));
                grid[int.Parse(temp3[0]), int.Parse(temp3[1])].ContentCode = Occupant.B;
            }
            temp2 = temp[2].Split(';');
            for (int i = 0; i < temp2.Length; i++)
            {
                String[] temp3 = temp2[i].Split(',');
                grid[int.Parse(temp3[0]), int.Parse(temp3[1])].ContentCode = Occupant.S;
                stone.Add(new Obstacle(temp2[i]));
            }
            temp2 = temp[3].Split(';');
            for (int i = 0; i < temp2.Length; i++)
            {
                String[] temp3 = temp2[i].Split(',');
                grid[int.Parse(temp3[0]), int.Parse(temp3[1])].ContentCode = Occupant.W;
                water.Add(new Obstacle(temp2[i]));
            }
            display();
        }

        internal static void display()
        {
            for (int i = 0; i < Constants.GRID_SIZE; i++)
            {
                for (int j = 0; j < Constants.GRID_SIZE; j++)
                {
                    Console.Write(grid[j, i].ContentCode + " | ");
                }
                Console.WriteLine();
            }
        }

        public static void handleException(String msg)
        {
            switch (msg)
            {
                case "OBSTACLE#":
                    //think update your data
                    Console.WriteLine("Blind bot. You hit on something.");
                    break;
                case "CELL_OCCUPIED#":
                    //update man...
                    Console.WriteLine("Someone in, mr.bot.");
                    break;
                case "DEAD#":
                    Console.WriteLine("You are done. Watch genius play.");
                    while (!Keyboard.GetState().IsKeyDown(Keys.Escape)) ;
                    return;
                case "TOO_QUICK#":
                    // never mind for now :) Consider resending last action
                    client.sendMessage(lastMsg);
                    break;
                case "INVALID_CELL#":
                    Console.WriteLine("Invalid cell.");
                    break;
                case "NOT_A_VALID_CONTESTANT#":
                    Console.WriteLine("He doesn't know you bot...");
                    break;
                default:
                    Console.WriteLine("Something happened. Carry on.");
                    break;
            }
        }

        internal static void gameIssue(string msg)
        {
            if (msg.Equals("GAME_ALREADY_STARTED#"))
            {
                Console.WriteLine("Already Begun.");
                //               while (!Keyboard.GetState().IsKeyDown(Keys.Escape)) ;
                return;
            }
            else if (msg.Equals("GAME_HAS_FINISHED#"))
            {
                Console.WriteLine("Game Has Finished. Press Escape to exit.");
                while (!Keyboard.GetState().IsKeyDown(Keys.Escape)) ;
                return;
            }
            else if (msg.Equals("GAME_NOT_STARTED YET#"))
            {
                Console.WriteLine("Game not started yet.");
            }
        }

        internal static void tryCoins(string message)
        {
            Console.WriteLine("Coin Pile...");
            message = message.Substring(2, message.Length - 3);
            string[] temp = message.Split(':');
            coins.Add(new CoinPile(getPoint(temp[0]), int.Parse(temp[1]), int.Parse(temp[2])));
        }

        internal static void tryLifePack(string message)
        {
            Console.WriteLine("Life Pack...");
            message = message.Substring(2, message.Length - 3);
            string[] temp = message.Split(':');
            healthPack.Add(new HealthPack(getPoint(temp[0]), int.Parse(temp[1])));
        }

        internal static Point getPoint(String p)
        {
            int[] point = new int[2];
            String[] t = p.Split(',');
            point[0] = int.Parse(t[0]);
            point[1] = int.Parse(t[1]);
            return new Point(point[0], point[1]);
        }

        private static String getDirect(int x)
        {
            switch (x)
            {
                case 0:
                    return "UP#";
                case 1:
                    return "RIGHT#";
                case 2:
                    return "DOWN#";
                case 3:
                    return "LEFT#";
            }
            return null;
        }

        public static Tank getMyTank()
        {
            return tanks[myId];
        }

        internal static Cell[,] getCell()
        {
            return grid;
        }

        internal void start()
        {
            while (true)
            {
                lock (thisLock)
                {
                    if ((DateTime.Now.Ticks / TimeSpan.TicksPerMillisecond - time) > 1000)
                    {
                        time = myClient.sendMessage();
                    }
                    myServer.listen();
                }
            }
        }
    }
}