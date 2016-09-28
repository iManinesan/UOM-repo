using Microsoft.Xna.Framework;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using TankGL.AI;

namespace TankGL.GUI
{
    class Bullet
    {
        private Vector2 pos;
        float rotation;
        int direction;
        Point point;

        public Bullet(string p, int x)
        {
            // TODO: Complete member initialization
            Point pt = Bot.getPoint(p);
            this.pos = new Vector2(pt.X * 30 + 30 + 15,pt.Y * 30+ 30+15);
            this.rotation = x * (float)Math.PI/2.0f;
            this.direction = x;
            this.point = pt;
        }

        internal int getDirection()
        {
            return direction;
        }

        internal float getRotation()
        {
            return rotation;
        }

        internal Vector2 getPos()
        {
            return pos;
        }

        internal void update(Vector2 p)
        {
            this.pos = p;
        }

        internal Point getPoint()
        {
            return point;
        }
    }
}