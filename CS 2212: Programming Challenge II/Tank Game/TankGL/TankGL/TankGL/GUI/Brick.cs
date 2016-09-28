using Microsoft.Xna.Framework;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using TankGL.AI;

namespace TankGL.GUI
{
    class Brick
    {
        int damage = 0;
        private Point pos;

        public Brick(Point p)
        {
            // TODO: Complete member initialization
            pos = p;
        }

        public Brick(string p)
        {
            // TODO: Complete member initialization
            String[] t = p.Split(',');
            pos = Bot.getPoint(t[0]+","+t[1]);
            damage = int.Parse(t[2]);
        }

        internal void setDamage(int damage)
        {
            this.damage = damage;
        }

        internal int getDamage()
        {
            return damage;
        }

        internal Point getPos()
        {
            return pos;
        }
    }
}
