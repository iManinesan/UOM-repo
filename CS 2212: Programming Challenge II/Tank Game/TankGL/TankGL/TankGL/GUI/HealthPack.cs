using Microsoft.Xna.Framework;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using TankGL.AI;

namespace TankGL.GUI
{
    class HealthPack
    {
        int LT;
        long birth;
        private Point pos;

        public HealthPack(Point point, int p)
        {
            birth = DateTime.Now.Ticks / TimeSpan.TicksPerMillisecond;
            this.pos = point;
            this.LT = p;
        }

        /// <summary>
        /// ///TEst
        /// </summary>
        /// <param name="p"></param>
        public HealthPack(string p)
        {
            // TODO: Complete member initialization
            this.pos = Bot.getPoint(p);
        }

        public long getTTL() //Time To Live
        {
            return LT - (DateTime.Now.Ticks / TimeSpan.TicksPerMillisecond - birth);
        }

        public Point getPos()
        {
            return pos;
        }
    }
}