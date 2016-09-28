using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TankGL.AI
{

    enum Occupant
    {
        E,  //Empty
        G,  //Goal
        M,  //Me
        B,  //Brick
        W,  //Water
        S,  //Stone
        T,   //Tank
        H,  //HealthPack
        C   //Coin Pile
    };

    class Cell
    {
        Occupant occ = Occupant.E;
        
        public Occupant ContentCode
        {
            get { return occ; }
            set { occ = value; }
        }
       
        int distance = 10000;
        public int getDist
        {
            get { return distance; }
            set { distance = value; }
        }

        bool isOnPath = false;
        public bool IsPath
        {
            get { return isOnPath; }
            set { isOnPath = value; }
        }

        public bool isEmpty()
        {
            //Console.WriteLine(x + " " + y);
            // A square is open if it is not a wall.
            switch (this.ContentCode)
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
    }
}
