using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Project
{
    class Buton:Button
    {
        public bool Bomba = false;
        public int NrVeciniBomba = 0;
        public bool Blocat = false;
        public int x, y;

        public void AfisareInformatii()
        {
            if (!this.Blocat)
            {
                if (this.Bomba)
                {
                    this.Text = "💣";
                }
                else
                {
                    this.Text = this.NrVeciniBomba.ToString();
                }
            }
            this.Enabled = false;
        }

        public void AfisareInformatiiOricum()
        {   
            if (this.Bomba)
            {
                this.Text = "💣";
            }
            else
            {
                this.Text = this.NrVeciniBomba.ToString();
            }
            this.Enabled = false;
        }

        public void BlocareDeblocare()
        {
            if (!this.Blocat)
            {
                this.Blocat = true;
                this.Text = "!";
            }
            else
            {
                this.Blocat = false;
                this.Text = "";
            }
        }
    }
}
