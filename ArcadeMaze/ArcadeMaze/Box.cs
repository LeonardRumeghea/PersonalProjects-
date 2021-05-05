using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ArcadeMaze
{
    class Box:PictureBox
    {
        public int X = 0, Y = 0;
        public bool Block = false;
        public bool End = false;
        public bool Start = false;
        public bool Null = true;
        public bool pause = false;

        public Box()
        {
            Block = false;
            Start = false;
            End = false;
            pause = false;
            Null = true;
        }
    }
}
