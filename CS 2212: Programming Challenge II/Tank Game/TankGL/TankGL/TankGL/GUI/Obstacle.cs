using Microsoft.Xna.Framework;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using TankGL.AI;

namespace TankGL
{
    class Obstacle
    {
        protected Point pos;

        internal Point position()
        {
            return pos;
        }

        public Obstacle(String p)
        {
            pos = Bot.getPoint(p);
        }
    }
}
