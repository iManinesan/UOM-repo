using Microsoft.Xna.Framework;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using TankGL.AI;

namespace TankGL.GUI
{
    class Tank
    {
        int direcion,ID;
        int points,coins,health, shot;
        Vector2 position;
        Point pos;
        float rotation;

        public Tank(int id, Point pt, int d)
        {
            this.rotation = d * (float)Math.PI / 2.0f;
            this.position = new Vector2(pt.X * 30 + 30, pt.Y * 30 + 30);
            this.direcion = d;
            this.pos = pt;
            this.ID = id;
        }

        public int getId()
        {
            return ID;
        }

        public int getX()
        {
            return pos.X;
        }

        public int getY()
        {
            return pos.Y;
        }

        public Vector2 getPos()
        {
            return position;
        }

        public float getRotation(){
            return rotation;
        }

        public int getDirection()
        {
            return direcion;
        }

        public Point getPoint()
        {
            return pos;
        }

        public int getCoins()
        {
            return coins;
        }

        public int getPoints()
        {
            return points;
        }

        public int getHealth()
        {
            return health;
        }

        internal void update(string p)
        {
            //P0;0,0;0;0;100;0;0:P1;0,9;0;0;100;0;0:
            String[] temp = p.Split(';');
            this.pos = Bot.getPoint(temp[1]);
            this.direcion = int.Parse(temp[2]);
            this.shot += int.Parse(temp[3]);
            this.health = int.Parse(temp[4]);
            this.coins = int.Parse(temp[5]);
            this.points = int.Parse(temp[6]);
        }


        internal Vector2 locate()
        {
            switch (this.direcion)
            {
                case 0:
                    return new Vector2(-5,-5);
                case 1:
                    return new Vector2(-5, 55);
                case 2:
                    return new Vector2(55, 55);
                case 3:
                    return new Vector2(55, -5);
            }
            return new Vector2(0, 0);
        }
    }
}
