using Microsoft.Xna.Framework;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using TankGL.AI;

namespace TankGL.GUI
{
    class CoinPile 
    {
        long birth;
        private Point pos;
        private int LT;
        private int val;

        public CoinPile(Point point, int p1, int p2)
        {
            // TODO: Complete member initialization
            birth = DateTime.Now.Ticks / TimeSpan.TicksPerMillisecond;
            this.pos = point;
            this.LT = p1;
            this.val = p2;
        }

        /// <summary>
        /// test
        /// </summary>
        /// <returns></returns>
        public CoinPile(String p)
        {
            this.pos = Bot.getPoint(p);
        }


        public long getTTL() //Time To Live
        {
            return LT - (DateTime.Now.Ticks / TimeSpan.TicksPerMillisecond - birth);
        }

        public int getVal()
        {
            return val;
        }

        public Point getPos()
        {
            return pos;
        }

        public bool collected(Tank[] tanks)
        {
            foreach (Tank t in tanks)
            {
                if (t != null && t.getPoint().Equals(this.getPos()))
                    return true;
            }
            return false;
        }
    }
}