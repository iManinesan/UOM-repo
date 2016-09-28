using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using TankGL.AI;
using TankGL.Network;

namespace TankGL
{
    /*class Program
    {
        static void Main(string[] args)
        {
            Bot bot = new Bot();
            Thread gui = new Thread(new ThreadStart(startGame));
            Thread myBot = new Thread(new ThreadStart(bot.start));
            
            gui.Start();
            myBot.Start();

            while (true)
            {
                gui.Join(150);
                
            }
            
        }

        public static void startGame()
        {
            Game1 game = new Game1();
            game.Run();
        }
    }*/

    class Program
    {
        public static void Main(String[] args)
        {
            Server myServer = new Server();
            Client myClient = new Client();
            Game1 myGame = new Game1();
            
            myGame.Run();
            
            /*while (true)
            {
                myClient.sendMessage();
                long time = DateTime.Now.Ticks / TimeSpan.TicksPerMillisecond;
                while ((DateTime.Now.Ticks / TimeSpan.TicksPerMillisecond - time) < 1000)
                {
                    myServer.listen();
                }
                
            }*/
        }
    }
}
