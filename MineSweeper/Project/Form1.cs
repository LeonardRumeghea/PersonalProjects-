using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Project
{
    public partial class Game : Form
    {
        public Game()
        {
            InitializeComponent();
        }

        private int secunde = 0, minute = 0;

        public int dimensiune = 4;
        private Buton[,] btn;
        public int nrBome = 0, width = 0, nrNuBombe = 0;

        private int win = 0, lose = 0;

        private Label time;

        private void SetTime()
        {
            if(minute < 10)
            {
                if(secunde < 10)
                {
                    time.Text = "0" + minute.ToString() + ":" + "0" + secunde.ToString();
                }
                else
                {
                    time.Text = "0" + minute.ToString() + ":" + secunde.ToString();
                }

            }
            else
            {
                if (secunde < 10)
                {
                    time.Text = minute.ToString() + ":" + "0" + secunde.ToString();
                }
                else
                {
                    time.Text = minute.ToString() + ":" + secunde.ToString();
                }
            }
        }

        private void buttonStart_Click(object sender, EventArgs e)
        {
            time = new Label();
            time.Location = new Point(20, 20);
            Controls.Add(time);
            time.Font = new Font(time.Font.FontFamily, 16);

            label.Visible = false;
            buttonStart.Visible = false;
            numericUpDown.Visible = false;

            dimensiune = (int)numericUpDown.Value;

            this.Text = "MineSweeper " + dimensiune.ToString() + "x" + dimensiune.ToString();

            btn = new Buton[dimensiune, dimensiune];

            this.Size = new Size(65 + 40 * dimensiune, 75 + 40 * (dimensiune + 1));

            width = 65 + 40 * dimensiune;

            for (int i = 0; i < dimensiune; i++)
            {
                for (int j = 0; j < dimensiune; j++)
                {
                    btn[i, j] = new Buton();

                    btn[i, j].Font = new Font(btn[i, j].Font.FontFamily, 20);

                    btn[i, j].Size = new Size(40, 40);
                    btn[i, j].Location = new Point(25 + i * 40, 50 + j * 40);
                    this.Controls.Add(btn[i, j]);

                    btn[i, j].x = i;
                    btn[i, j].y = j;

                    btn[i, j].MouseDown += new MouseEventHandler(ApasClick);
                }
            }
            nrBome =  (dimensiune * dimensiune) / 2;

            nrNuBombe = (dimensiune * dimensiune) - nrBome;

            nrNuBombeAfisate = 0;

            Random rnd = new Random();

            int x, y;

            for (int i = 0; i < nrBome; i++)
            {
                do
                {
                    x = rnd.Next(dimensiune);
                    y = rnd.Next(dimensiune);
                } while (btn[x, y].Bomba);

                btn[x, y].Bomba = true;

            }

            for (int i = 0; i < dimensiune; i++)
            {
                for (int j = 0; j < dimensiune; j++)
                {
                    NumaraBombe(i, j);
                }
            }

            minute = 0;
            secunde = 0;

            SetTime();

            timer.Start();        
        }

        private void EndShow()
        {
            for (int i = 0; i < dimensiune; i++)
            {
                for (int j = 0; j < dimensiune; j++)
                {
                    btn[i, j].AfisareInformatiiOricum();
                }
            }
        }

        private Label Scor;

        private void ApasClick(object sender, MouseEventArgs e)
        {
            Buton x = (Buton)sender;

            if (e.Button == MouseButtons.Left)
            {
                if (!x.Blocat)
                {
                    if (!x.Bomba)
                    {
                        Show(x.x, x.y);

                        if (nrNuBombeAfisate == nrNuBombe)
                        {
                            WinForm winForm = new WinForm();

                            win++;

                            winForm.Text = "WIN " + win.ToString() + " - " + lose.ToString(); //WIN 1 - 0

                            timer.Stop();

                            EndShow();

                            DialogResult dialogResult = winForm.ShowDialog();
                            
                            if(dialogResult == DialogResult.OK)
                            {
                                startAgainToolStripMenuItem_Click(this, e);
                            }

                            if(dialogResult == DialogResult.No)
                            {
                                Application.Exit();
                            }
                        }
                    }
                    else
                    {
                        MessageBoxForm messageBoxForm = new MessageBoxForm();

                        lose++;

                        messageBoxForm.Text = "GAME OVER " + win.ToString() + " - " + lose.ToString(); //GAME OVER 0 - 1


                        timer.Stop();

                        EndShow();
                        
                        DialogResult dialogResult = messageBoxForm.ShowDialog();

                        if (dialogResult == DialogResult.OK)
                        {
                            startAgainToolStripMenuItem_Click(this, e);
                        }

                        if(dialogResult == DialogResult.No)
                        {
                            Application.Exit();
                        }
                    }
                }
            }

            if (e.Button == MouseButtons.Right)
            {
                x.BlocareDeblocare();
            }
        }

        private int nrNuBombeAfisate = 0;

        private void Show(int i, int j)
        {
            if (i < 0 || j < 0 || i >= dimensiune || j >= dimensiune) return;

            if (btn[i, j].Bomba) return;

            if (btn[i, j].Enabled == false) return;

            if (btn[i, j].Blocat) return;

            nrNuBombeAfisate++;
            btn[i, j].AfisareInformatii();

            Show(i - 1, j);
            Show(i + 1, j);
            Show(i, j - 1);
            Show(i, j + 1);
        }

        private void NumaraBombe(int i, int j)
        {
            int c = 0;

            if (i > 0 && j > 0) if(btn[i - 1, j - 1].Bomba) c++;

            if (i > 0) if (btn[i - 1, j].Bomba) c++;

            if (i > 0 && j < dimensiune - 1) if (btn[i - 1, j + 1].Bomba) c++;

            if (j > 0) if (btn[i, j - 1].Bomba) c++;

            if (j < dimensiune - 1) if (btn[i, j + 1].Bomba) c++;

            if (i < dimensiune - 1 && j > 0) if (btn[i + 1, j - 1].Bomba) c++;

            if (i < dimensiune - 1) if (btn[i + 1, j].Bomba) c++;

            if (i < dimensiune - 1 && j < dimensiune - 1) if (btn[i + 1, j + 1].Bomba) c++;

            btn[i, j].NrVeciniBomba = c;
        }

        private void quitToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }

        public void startAgainToolStripMenuItem_Click(object sender, EventArgs e)
        {
            timer.Stop();

            time.Dispose();

            for (int i = 0; i < dimensiune; i++)
            {
                for (int j = 0; j < dimensiune; j++)
                {
                    Controls.Remove(btn[i, j]);
                }
            }

            buttonStart_Click(this, e);
        }

        private void quitToolStripMenuItem1_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }

        private void resizeToolStripMenuItem_Click(object sender, EventArgs e)
        {
            time.Visible = false;

            timer.Stop();

            for (int i = 0; i < dimensiune; i++)
            {
                for (int j = 0; j < dimensiune; j++)
                {
                    Controls.Remove(btn[i, j]);
                }
            }

            Size = new Size(290, 200);

            label.Visible = true;
            buttonStart.Visible = true;
            numericUpDown.Visible = true;

            Text = "MineSweeper";
        }

        private void timer_Tick(object sender, EventArgs e)
        {
            secunde++;

            if(secunde == 60)
            {
                secunde = 0;
                minute++;
            }

            SetTime();
        }
    }
}
