using Microsoft.Xna.Framework;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using TankGL.AI;

namespace TankGL.AI
{
    class Pathfinder
    {
        Point[] directions;
        Cell[,] cell;
        Occupant lastOcc;

        public Pathfinder(Cell[,] grid)
        {
            InitMovements();
            cell = grid;
            ClearLogic();
        }

        public void ClearLogic()
        {
            foreach (Point point in AllSquares())
            {
                int x = point.X;
                int y = point.Y;
                cell[x, y].getDist = 10000;
            }
        }

        public void InitMovements()
        {
            directions = new Point[]
                {
                    new Point(0, -1), // UP
                    new Point(1, 0),    // RIGHT
                    new Point(0, 1),    //DOWN
                    new Point(-1, 0)    //LEFT
                };
        }

        public Stack<String> Pathfind(Point startingPoint, Point endPoint)
        {
            setGoal(startingPoint, endPoint);
            // Find path from hero to monster. First, get coordinates
            // of hero.
            int heroX = startingPoint.X;
            int heroY = startingPoint.Y;
            // Hero starts at distance of 0.
            cell[heroX, heroY].getDist = 0;

            while (true)
            {
                bool madeProgress = false;
                //Console.WriteLine("Path find");
                // Look at each square on the board.
                foreach (Point mainPoint in AllSquares())
                {
                    //Console.WriteLine(mainPoint.ToString());
                    int x = mainPoint.X;
                    int y = mainPoint.Y;

                    // If the square is open, look through valid moves given
                    // the coordinates of that square.
                    if (CellEmpty(x, y))
                    {
                        int passHere = cell[x, y].getDist;

                        foreach (Point movePoint in ValidMoves(x, y))
                        {
                            int newX = movePoint.X;
                            int newY = movePoint.Y;
                            int newPass = passHere + 1;

                            if (cell[newX, newY].getDist > newPass)
                            {
                                cell[newX, newY].getDist = newPass;
                                madeProgress = true;
                            }
                        }
                    }
                }
                if (!madeProgress)
                {
                    break;
                }
            }

            return getPath(endPoint);
        }

        static private bool ValidCoordinates(int x, int y)
        {
            // Our coordinates are constrained between 0 and 14.
            if (x < 0)
            {
                return false;
            }
            if (y < 0)
            {
                return false;
            }
            if (x >= Constants.GRID_SIZE)
            {
                return false;
            }
            if (y >= Constants.GRID_SIZE)
            {
                return false;
            }
            return true;
        }

        private bool CellEmpty(int x, int y)
        {
            //Console.WriteLine(x + " " + y);
            // A square is open if it is not a wall.
            switch (cell[x, y].ContentCode)
            {
                case Occupant.E:
                    return true;
                case Occupant.M:
                    return true;
                case Occupant.G:
                    return true;
                case Occupant.H:
                    return true;
                case Occupant.C:
                    return true;
                default:
                    return false;
            }
        }

        public Stack<String> getPath(Point endPoint)
        {
            long millis = DateTime.Now.Ticks / TimeSpan.TicksPerMillisecond;
            // Mark the path from monster to hero.
            int pointX = endPoint.X;
            int pointY = endPoint.Y;
            Point lastPoint = Point.Zero;
            Stack<String> path = new Stack<String>();

            display();
            while (true)
            {
                // Look through each direction and find the square
                // with the lowest number of steps marked.
                Point lowestPoint = Point.Zero;
                int lowest = 10000;

                foreach (Point movePoint in ValidMoves(pointX, pointY))
                {
                    int count = cell[movePoint.X, movePoint.Y].getDist;
                    if (count < lowest)
                    {
                        lowest = count;
                        lowestPoint.X = movePoint.X;
                        lowestPoint.Y = movePoint.Y;
                    }
                }
                
                if (lowest != 10000)
                {
                    // Mark the square as part of the path if it is the lowest
                    // number. Set the current position as the square with
                    // that number of steps.
                    if (path.Count == 0)
                    {
                        path.Push(getDirect(lowestPoint, endPoint));
                        Console.WriteLine(path.Peek());
                    }
                    else
                    {
                        if (!(path.Peek()).Equals(getDirect(lowestPoint, lastPoint)))
                        {
                            Console.WriteLine(getDirect(lowestPoint, lastPoint));
                            Console.WriteLine(getDirect(lowestPoint, lastPoint));
                            path.Push(getDirect(lowestPoint, lastPoint));
                            path.Push(getDirect(lowestPoint, lastPoint));
                        }
                        else
                        {
                            Console.WriteLine(path.Peek());
                            path.Push(getDirect(lowestPoint, lastPoint));
                        }
                    }
                    pointX = lowestPoint.X;
                    pointY = lowestPoint.Y;
                    lastPoint = lowestPoint;
                }
                else
                {
                    break;
                }

                if (cell[pointX, pointY].ContentCode == Occupant.M)
                {
                    // We went from monster to hero, so we're finished.
                    break;
                }
            }
            resetGoal();
            Console.WriteLine("Time to find path " + ((DateTime.Now.Ticks / TimeSpan.TicksPerMillisecond) - millis));
            return path;
        }

        private static IEnumerable<Point> AllSquares()
        {
            // Return every point on the board in order.
            for (int x = 0; x < Constants.GRID_SIZE; x++)
            {
                for (int y = 0; y < Constants.GRID_SIZE; y++)
                {
                    yield return new Point(x, y);
                }
            }
        }

        private IEnumerable<Point> ValidMoves(int x, int y)
        {
            // Return each valid square we can move to.
            foreach (Point movePoint in directions)
            {
                int newX = x + movePoint.X;
                int newY = y + movePoint.Y;

                if (ValidCoordinates(newX, newY))
                {
                    if (CellEmpty(newX, newY))
                    {
                        yield return new Point(newX, newY);
                    }
                }
            }
        }

        private void setGoal(Point start, Point end)
        {
            cell[start.X, start.Y].ContentCode = Occupant.M;
            lastOcc = cell[end.X, end.Y].ContentCode;
            cell[end.X, end.Y].ContentCode = Occupant.G;
        }

        private void resetGoal()
        {
            foreach (Cell c in cell)
            {
                if (c.ContentCode.Equals(Occupant.G))
                {
                    c.ContentCode = lastOcc;
                }
                if (c.ContentCode.Equals(Occupant.M))
                {
                    c.ContentCode = Occupant.T;
                }
            }
        }

        private String getDirect(Point startPoint, Point endPoint)
        {
            for (int i = 0; i < directions.Length; i++)
            {
                if (endPoint.X == startPoint.X + directions[i].X &&
                    endPoint.Y == startPoint.Y + directions[i].Y)
                {
                    switch (i)
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
                }
            }
            return null;
        }

        internal void display()
        {
            for (int i = 0; i < Constants.GRID_SIZE; i++)
            {
                for (int j = 0; j < Constants.GRID_SIZE; j++)
                {
                    Console.Write( (cell[j, i].getDist).ToString("X4") + " " + cell[j, i].ContentCode +" | ");
                }
                Console.WriteLine();
            }
        }
    }
}