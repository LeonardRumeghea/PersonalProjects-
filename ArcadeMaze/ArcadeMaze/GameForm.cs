using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.IO;
using System.Media;

namespace ArcadeMaze
{
    public partial class GameForm : Form
    {
        Time time;

        private SoundPlayer soundPlayer_Move = new SoundPlayer(Properties.Resources.Move);
        private SoundPlayer soundPlayer_Block = new SoundPlayer(Properties.Resources.Block);
        private SoundPlayer soundPlayer_Win = new SoundPlayer(Properties.Resources.Win);
        private SoundPlayer soundPlayer_Click = new SoundPlayer(Properties.Resources.Click);

        bool Sounds = true;

        private int startI, startJ;

        private int height = 9, width = 6;

        private int levelNumber = 0;

        private Box[,] box;

        private bool Read = true;

        private List<int[,]> Nivele;

        private List<int> Marime;

        public GameForm()
        {
            InitializeComponent();

            Read = false;

            PlayGame();
        }

        private void StartTimer(object sender, FormClosingEventArgs e)
        {
            timerTime.Start();
        }

        private void ReadData_Level()
        {
            Nivele = new List<int[,]>();

            Marime = new List<int>();

            string[] levels = Properties.Resources.Data_Level.ToString().Split('*');

            //string[] levels = File.ReadAllText(@"C:\Users\Leonard\Desktop\ArcadeMaze\Resorces\Data_Level.txt").Split('*');

            foreach (string objects in levels)
            {
                string[] cache = objects.Split('\n');

                int cnt = 0;

                int[,] nivel = new int[3, 16];

                foreach (string cc in cache)
                {
                    if (cc.Trim() != "")
                    {
                        string[] ccc = cc.Split(' ');

                        if (cnt == 0)
                        {
                            Marime.Add(int.Parse(ccc[0]));
                            cnt++;
                        }

                        else if (cnt == 1)
                        {
                            nivel[1, cnt] = int.Parse(ccc[0]);
                            nivel[2, cnt] = int.Parse(ccc[1]);

                            cnt++;
                        }

                        else if (cnt == 2)
                        {
                            nivel[1, cnt] = int.Parse(ccc[0]);
                            nivel[2, cnt] = int.Parse(ccc[1]);

                            cnt++;
                        }
                        else
                        {
                            nivel[1, cnt] = int.Parse(ccc[0]);
                            nivel[2, cnt] = int.Parse(ccc[1]);

                            cnt++;
                        }
                    }
                }

                Nivele.Add(nivel);
            }
        }

        private void LoadTable()
        {
            box = new Box[height, width];

            for (int i = 0; i < height; i++)
            {
                for (int j = 0; j < width; j++)
                {
                    box[i, j] = new Box
                    {
                        X = i,
                        Y = j,

                        Size = new Size(50, 50),

                        Location = new Point(25 + 50 * j + 5, 115 + 50 * i + 5)
                    };


                    Controls.Add(box[i, j]);

                    panel_Game.Controls.Add(box[i, j]);

                    box[i, j].Image = Properties.Resources.Box_Null;

                    box[i, j].SizeMode = PictureBoxSizeMode.StretchImage;
                }
            }
        }

        private void LoadObjects()
        {
            for (int i = 1; i <= Marime[levelNumber]; i++)
            {
                int x = Nivele[levelNumber][1, i];
                int y = Nivele[levelNumber][2, i];

                //MessageBox.Show(x.ToString() + " " + y.ToString());

                if (i == 1)
                {
                    box[x, y].Start = true;
                    box[x, y].Image = Properties.Resources.Box_Start;
                    startI = x;
                    startJ = y;
                }

                else if (i == 2)
                {
                    box[x, y].Null = false;
                    box[x, y].End = true;
                    box[x, y].Image = Properties.Resources.Box_End;
                }

                else
                {
                    box[x, y].Null = false;
                    box[x, y].Block = true;
                    box[x, y].Image = Properties.Resources.Box_Block;
                }
            }


        }

        private void pictureBox_PlayButton_Click(object sender, EventArgs e)
        {
            PlayGame();
        }

        public void PlayGame()
        {
            label_Time.Text = "00:00";

            label_Time.Visible = true;

            label_Level.Text = "Level: " + (levelNumber + 1).ToString();

            ReadData_Level();

            LoadTable();

            LoadObjects();

            time = new Time();

            Read = true;

            timerTime.Start();
        }

        private void GameForm_KeyUp(object sender, KeyEventArgs e)
        {
            if (Read)
            {
                if (e.KeyData == Keys.Up)
                {
                    if (startI - 1 >= 0)
                    {
                        if (!box[startI - 1, startJ].Block)
                        {
                            timerMoveUp.Start();
                            Read = false;
                            if (Sounds) soundPlayer_Move.Play();
                        }
                        else
                        {
                            if (Sounds) soundPlayer_Block.Play();
                        }
                    }
                    else
                    {
                        if (Sounds) soundPlayer_Block.Play();
                    }
                }

                if (e.KeyData == Keys.Down)
                {
                    if (startI + 1 < height)
                    {
                        if (!box[startI + 1, startJ].Block)
                        {
                            timerMoveDown.Start();
                            Read = false;
                            if (Sounds) soundPlayer_Move.Play();
                        }
                        else
                        {
                            if (Sounds) soundPlayer_Block.Play();
                        }
                    }
                    else
                    {
                        if (Sounds) soundPlayer_Block.Play();
                    }
                }

                if (e.KeyData == Keys.Right)
                {
                    if (startJ + 1 < width)
                    {
                        if (!box[startI, startJ + 1].Block)
                        {
                            timerMoveRight.Start();
                            Read = false;
                            if (Sounds) soundPlayer_Move.Play();
                        }
                        else
                        {
                            if (Sounds) soundPlayer_Block.Play();
                        }
                    }
                    else
                    {
                        if (Sounds) soundPlayer_Block.Play();
                    }
                }

                if (e.KeyData == Keys.Left)
                {
                    if (startJ - 1 >= 0)
                    {
                        if (!box[startI, startJ - 1].Block)
                        {
                            timerMoveLeft.Start();
                            Read = false;
                            if (Sounds) soundPlayer_Move.Play();
                        }
                        else
                        {
                            if (Sounds) soundPlayer_Block.Play();
                        }
                    }
                    else
                    {
                        if (Sounds) soundPlayer_Block.Play();
                    }
                }
            }
        }

        private void timerMoveDown_Tick(object sender, EventArgs e)
        {
            if (startI == 8)
            {
                timerMoveDown.Stop();
                Read = true;
            }
            else
            {
                if (box[startI + 1, startJ].End)
                {
                    box[startI, startJ].Image = Properties.Resources.Box_Start;
                    box[startI, startJ].Image = Properties.Resources.Box_Null;

                    Win();
                }

                else if (box[startI + 1, startJ].Null)
                {
                    startI++;
                    box[startI, startJ].Image = Properties.Resources.Box_Start;
                    box[startI - 1, startJ].Image = Properties.Resources.Box_Null;
                }
                else
                {
                    timerMoveDown.Stop();
                    Read = true;
                }
            }
        }

        private void timerMoveRight_Tick(object sender, EventArgs e)
        {
            if (startJ == 5)
            {
                timerMoveRight.Stop();
                Read = true;
            }
            else
            {
                if (box[startI, startJ + 1].End)
                {
                    box[startI, startJ + 1].Image = Properties.Resources.Box_Start;
                    box[startI, startJ].Image = Properties.Resources.Box_Null;

                    Win();
                }

                else if (box[startI, startJ + 1].Null)
                {
                    startJ++;
                    box[startI, startJ].Image = Properties.Resources.Box_Start;
                    box[startI, startJ - 1].Image = Properties.Resources.Box_Null;
                }
                else
                {
                    timerMoveRight.Stop();
                    Read = true;
                }
            }
        }

        private void timerTime_Tick(object sender, EventArgs e)
        {
            time.AddSeconds();

            label_Time.Text = time.Write();
        }

        private void timerMoveLeft_Tick(object sender, EventArgs e)
        {
            if (startJ == 0)
            {
                timerMoveLeft.Stop();
                Read = true;
            }
            else
            {
                if (box[startI, startJ - 1].End)
                {
                    box[startI, startJ - 1].Image = Properties.Resources.Box_Start;
                    box[startI, startJ].Image = Properties.Resources.Box_Null;

                    Win();
                }
                else if (box[startI, startJ - 1].Null)
                {
                    startJ--;
                    box[startI, startJ].Image = Properties.Resources.Box_Start;
                    box[startI, startJ + 1].Image = Properties.Resources.Box_Null;
                }
                else
                {
                    timerMoveLeft.Stop();
                    Read = true;
                }
            }
        }

        private void timerMoveUp_Tick(object sender, EventArgs e)
        {
            if (startI == 0)
            {
                timerMoveUp.Stop();
                Read = true;
            }
            else
            {
                if (box[startI - 1, startJ].End)
                {
                    box[startI - 1, startJ].Image = Properties.Resources.Box_Start;
                    box[startI, startJ].Image = Properties.Resources.Box_Null;

                    Win();
                }

                else if (box[startI - 1, startJ].Null == true)
                {
                    startI--;
                    box[startI, startJ].Image = Properties.Resources.Box_Start;
                    box[startI + 1, startJ].Image = Properties.Resources.Box_Null;
                }
                else
                {
                    timerMoveUp.Stop();
                    Read = true;
                }
            }
        }

        private void Button_Meniu_Click(object sender, EventArgs e)
        {
            if (Sounds) soundPlayer_Click.Play();

            label_Time.Visible = false;
            panel_Options.Visible = true;
            timerTime.Stop();
        }

        private void Resune_Button_Click(object sender, EventArgs e)
        {
            if (Sounds) soundPlayer_Click.Play();
            label_Time.Visible = true;
            timerTime.Start();
            panel_Options.Visible = false;
        }

        private void Quit_Button_Click(object sender, EventArgs e)
        {
            if (Sounds) soundPlayer_Click.Play();

            Application.Exit();
        }

        private void Sound_Button_Click(object sender, EventArgs e)
        {
            if(Sounds == true)
            {
                Sounds = false;

                Sound_Button.Image = Properties.Resources.SoundsOff_Button;
            }
            else
            {
                soundPlayer_Click.Play();

                Sounds = true;

                Sound_Button.Image = Properties.Resources.SoundsOn_Button;
            }
        }

        private void LevelsList_Button_Click(object sender, EventArgs e)
        {
            if (Sounds) soundPlayer_Click.Play();

            panel_Levels.Visible = true;

        }

        private void pictureBox21_Click(object sender, EventArgs e)
        {
            if (Sounds) soundPlayer_Click.Play();
            label_Time.Visible = true;
            timerTime.Start();
            panel_Options.Visible = false;
            panel_Levels.Visible = false;
        }

        private void pictureBox1_Click(object sender, EventArgs e)
        {
            if (Sounds) soundPlayer_Click.Play();

            levelNumber = 0;

            Clear();

            PlayGame();

            panel_Options.Visible = false;
            panel_Levels.Visible = false;
        }

        private void Skip_Button_Click(object sender, EventArgs e)
        {
            if (Sounds) soundPlayer_Click.Play();

            if (levelNumber < 19)
            {
                label_Time.Text = "00:00";

                levelNumber++;

                for (int i = 0; i < height; i++)
                {
                    for (int j = 0; j < width; j++)
                    {
                        box[i, j].Dispose();
                    }
                }

                label_Time.Visible = true;
                timerTime.Start();
                panel_Options.Visible = false;

                PlayGame();
            }
        }

        private void pictureBox2_Click(object sender, EventArgs e)
        {
            if (Sounds) soundPlayer_Click.Play();

            levelNumber = 1;

            Clear();

            PlayGame();

            panel_Options.Visible = false;
            panel_Levels.Visible = false;
        }

        private void pictureBox3_Click(object sender, EventArgs e)
        {
            if (Sounds) soundPlayer_Click.Play();

            levelNumber = 2;

            Clear();

            PlayGame();

            panel_Options.Visible = false;
            panel_Levels.Visible = false;
        }

        private void pictureBox4_Click(object sender, EventArgs e)
        {
            if (Sounds) soundPlayer_Click.Play();

            levelNumber = 3;

            Clear();

            PlayGame();

            panel_Options.Visible = false;
            panel_Levels.Visible = false;
        }

        private void pictureBox5_Click(object sender, EventArgs e)
        {
            if (Sounds) soundPlayer_Click.Play();

            levelNumber = 4;

            Clear();

            PlayGame();

            panel_Options.Visible = false;
            panel_Levels.Visible = false;
        }

        private void pictureBox6_Click(object sender, EventArgs e)
        {
            if (Sounds) soundPlayer_Click.Play();

            levelNumber = 5;

            Clear();

            PlayGame();

            panel_Options.Visible = false;
            panel_Levels.Visible = false;
        }

        private void pictureBox7_Click(object sender, EventArgs e)
        {
            if (Sounds) soundPlayer_Click.Play();

            levelNumber = 6;

            Clear();

            PlayGame();

            panel_Options.Visible = false;
            panel_Levels.Visible = false;
        }

        private void pictureBox8_Click(object sender, EventArgs e)
        {
            if (Sounds) soundPlayer_Click.Play();

            levelNumber = 7;

            Clear();

            PlayGame();

            panel_Options.Visible = false;
            panel_Levels.Visible = false;
        }

        private void pictureBox9_Click(object sender, EventArgs e)
        {
            if (Sounds) soundPlayer_Click.Play();

            levelNumber = 8;

            Clear();

            PlayGame();

            panel_Options.Visible = false;
            panel_Levels.Visible = false;
        }

        private void pictureBox10_Click(object sender, EventArgs e)
        {
            if (Sounds) soundPlayer_Click.Play();

            levelNumber = 9;

            Clear();

            PlayGame();

            panel_Options.Visible = false;
            panel_Levels.Visible = false;
        }

        private void pictureBox11_Click(object sender, EventArgs e)
        {
            if (Sounds) soundPlayer_Click.Play();

            levelNumber = 10;

            Clear();

            PlayGame();

            panel_Options.Visible = false;
            panel_Levels.Visible = false;
        }

        private void pictureBox12_Click(object sender, EventArgs e)
        {
            if (Sounds) soundPlayer_Click.Play();

            levelNumber = 11;

            Clear();

            PlayGame();

            panel_Options.Visible = false;
            panel_Levels.Visible = false;
        }

        private void pictureBox13_Click(object sender, EventArgs e)
        {
            if (Sounds) soundPlayer_Click.Play();

            levelNumber = 12;

            Clear();

            PlayGame();

            panel_Options.Visible = false;
            panel_Levels.Visible = false;
        }

        private void pictureBox14_Click(object sender, EventArgs e)
        {
            if (Sounds) soundPlayer_Click.Play();

            levelNumber = 13;

            Clear();

            PlayGame();

            panel_Options.Visible = false;
            panel_Levels.Visible = false;
        }

        private void pictureBox15_Click(object sender, EventArgs e)
        {
            if (Sounds) soundPlayer_Click.Play();

            levelNumber = 14;

            Clear();

            PlayGame();

            panel_Options.Visible = false;
            panel_Levels.Visible = false;
        }

        private void pictureBox16_Click(object sender, EventArgs e)
        {
            if (Sounds) soundPlayer_Click.Play();

            levelNumber = 15;

            Clear();

            PlayGame();

            panel_Options.Visible = false;
            panel_Levels.Visible = false;
        }

        private void pictureBox17_Click(object sender, EventArgs e)
        {
            if (Sounds) soundPlayer_Click.Play();

            levelNumber = 16;

            Clear();

            PlayGame();

            panel_Options.Visible = false;
            panel_Levels.Visible = false;
        }

        private void pictureBox18_Click(object sender, EventArgs e)
        {
            if (Sounds) soundPlayer_Click.Play();

            levelNumber = 17;

            Clear();

            PlayGame();

            panel_Options.Visible = false;
            panel_Levels.Visible = false;
        }

        private void pictureBox19_Click(object sender, EventArgs e)
        {
            if (Sounds) soundPlayer_Click.Play();

            levelNumber = 18;

            Clear();

            PlayGame();

            panel_Options.Visible = false;
            panel_Levels.Visible = false;
        }

        private void pictureBox20_Click(object sender, EventArgs e)
        {
            if (Sounds) soundPlayer_Click.Play();

            levelNumber = 19;

            Clear();

            PlayGame();

            panel_Options.Visible = false;
            panel_Levels.Visible = false;
        }

        private void label1_Click(object sender, EventArgs e)
        {
            if (Sounds) soundPlayer_Click.Play();
            panel_Levels.Visible = false;
        }

        private void exitToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }

        private void helpToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Form_Help form_Help = new Form_Help();

            form_Help.Show();
        }

        public void Win()
        {
            Read = false;

            timerMoveRight.Stop();
            timerMoveLeft.Stop();
            timerMoveUp.Stop();
            timerMoveDown.Stop();

            if (Sounds) soundPlayer_Win.Play();

            timerTime.Stop();

            if (levelNumber < 19)
            {
                label_Time.Text = "00:00";

                levelNumber++;

                for (int i = 0; i < height; i++)
                {
                    for (int j = 0; j < width; j++)
                    {
                        box[i, j].Dispose();
                    }
                }

                PlayGame();
            }
            else
            {
                panel_Options.Visible = true;
                panel_Levels.Visible = true;
            }
        }

        private void Clear()
        {
            for (int i = 0; i < height; i++)
            {
                for (int j = 0; j < width; j++)
                {
                    box[i, j].Dispose();
                }
            }
        }
    }
}