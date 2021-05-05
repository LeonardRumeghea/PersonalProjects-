namespace ArcadeMaze
{
    partial class GameForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(GameForm));
            this.timerMoveUp = new System.Windows.Forms.Timer(this.components);
            this.timerMoveDown = new System.Windows.Forms.Timer(this.components);
            this.timerMoveRight = new System.Windows.Forms.Timer(this.components);
            this.timerMoveLeft = new System.Windows.Forms.Timer(this.components);
            this.timerTime = new System.Windows.Forms.Timer(this.components);
            this.label_Time = new System.Windows.Forms.Label();
            this.panel_Game = new System.Windows.Forms.Panel();
            this.label_Level = new System.Windows.Forms.Label();
            this.Button_Meniu = new System.Windows.Forms.PictureBox();
            this.panel_Options = new System.Windows.Forms.Panel();
            this.panel_Levels = new System.Windows.Forms.Panel();
            this.label_Back = new System.Windows.Forms.Label();
            this.label_TextLevels = new System.Windows.Forms.Label();
            this.pictureBox4 = new System.Windows.Forms.PictureBox();
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            this.pictureBox2 = new System.Windows.Forms.PictureBox();
            this.pictureBox3 = new System.Windows.Forms.PictureBox();
            this.pictureBox8 = new System.Windows.Forms.PictureBox();
            this.pictureBox20 = new System.Windows.Forms.PictureBox();
            this.pictureBox14 = new System.Windows.Forms.PictureBox();
            this.pictureBox13 = new System.Windows.Forms.PictureBox();
            this.pictureBox19 = new System.Windows.Forms.PictureBox();
            this.pictureBox6 = new System.Windows.Forms.PictureBox();
            this.pictureBox7 = new System.Windows.Forms.PictureBox();
            this.pictureBox11 = new System.Windows.Forms.PictureBox();
            this.pictureBox16 = new System.Windows.Forms.PictureBox();
            this.pictureBox9 = new System.Windows.Forms.PictureBox();
            this.pictureBox10 = new System.Windows.Forms.PictureBox();
            this.pictureBox12 = new System.Windows.Forms.PictureBox();
            this.pictureBox18 = new System.Windows.Forms.PictureBox();
            this.pictureBox15 = new System.Windows.Forms.PictureBox();
            this.pictureBox17 = new System.Windows.Forms.PictureBox();
            this.pictureBox5 = new System.Windows.Forms.PictureBox();
            this.label_TextOption = new System.Windows.Forms.Label();
            this.Skip_Button = new System.Windows.Forms.PictureBox();
            this.Quit_Button = new System.Windows.Forms.PictureBox();
            this.LevelsList_Button = new System.Windows.Forms.PictureBox();
            this.Sound_Button = new System.Windows.Forms.PictureBox();
            this.Resune_Button = new System.Windows.Forms.PictureBox();
            this.notifyIcon = new System.Windows.Forms.NotifyIcon(this.components);
            this.contextMenuStrip_notifyIcon = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.helpToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripMenuItem1 = new System.Windows.Forms.ToolStripSeparator();
            this.exitToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.panel_Game.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.Button_Meniu)).BeginInit();
            this.panel_Options.SuspendLayout();
            this.panel_Levels.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox4)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox2)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox3)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox8)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox20)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox14)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox13)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox19)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox6)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox7)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox11)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox16)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox9)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox10)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox12)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox18)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox15)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox17)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox5)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.Skip_Button)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.Quit_Button)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.LevelsList_Button)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.Sound_Button)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.Resune_Button)).BeginInit();
            this.contextMenuStrip_notifyIcon.SuspendLayout();
            this.SuspendLayout();
            // 
            // timerMoveUp
            // 
            this.timerMoveUp.Interval = 25;
            this.timerMoveUp.Tick += new System.EventHandler(this.timerMoveUp_Tick);
            // 
            // timerMoveDown
            // 
            this.timerMoveDown.Interval = 25;
            this.timerMoveDown.Tick += new System.EventHandler(this.timerMoveDown_Tick);
            // 
            // timerMoveRight
            // 
            this.timerMoveRight.Interval = 25;
            this.timerMoveRight.Tick += new System.EventHandler(this.timerMoveRight_Tick);
            // 
            // timerMoveLeft
            // 
            this.timerMoveLeft.Interval = 25;
            this.timerMoveLeft.Tick += new System.EventHandler(this.timerMoveLeft_Tick);
            // 
            // timerTime
            // 
            this.timerTime.Interval = 1000;
            this.timerTime.Tick += new System.EventHandler(this.timerTime_Tick);
            // 
            // label_Time
            // 
            this.label_Time.AutoSize = true;
            this.label_Time.Font = new System.Drawing.Font("Microsoft Sans Serif", 30F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label_Time.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(9)))), ((int)(((byte)(98)))), ((int)(((byte)(138)))));
            this.label_Time.Location = new System.Drawing.Point(290, 75);
            this.label_Time.Name = "label_Time";
            this.label_Time.Size = new System.Drawing.Size(156, 58);
            this.label_Time.TabIndex = 0;
            this.label_Time.Text = "00:00";
            // 
            // panel_Game
            // 
            this.panel_Game.Controls.Add(this.label_Level);
            this.panel_Game.Controls.Add(this.label_Time);
            this.panel_Game.Controls.Add(this.Button_Meniu);
            this.panel_Game.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panel_Game.Location = new System.Drawing.Point(0, 0);
            this.panel_Game.Name = "panel_Game";
            this.panel_Game.Size = new System.Drawing.Size(480, 720);
            this.panel_Game.TabIndex = 2;
            // 
            // label_Level
            // 
            this.label_Level.AutoSize = true;
            this.label_Level.Font = new System.Drawing.Font("Microsoft Sans Serif", 20F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label_Level.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(9)))), ((int)(((byte)(98)))), ((int)(((byte)(138)))));
            this.label_Level.Location = new System.Drawing.Point(35, 91);
            this.label_Level.Name = "label_Level";
            this.label_Level.Size = new System.Drawing.Size(144, 39);
            this.label_Level.TabIndex = 41;
            this.label_Level.Text = "Level: 0";
            // 
            // Button_Meniu
            // 
            this.Button_Meniu.Image = global::ArcadeMaze.Properties.Resources.Options_Image;
            this.Button_Meniu.Location = new System.Drawing.Point(35, 10);
            this.Button_Meniu.Name = "Button_Meniu";
            this.Button_Meniu.Size = new System.Drawing.Size(65, 65);
            this.Button_Meniu.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.Button_Meniu.TabIndex = 1;
            this.Button_Meniu.TabStop = false;
            this.Button_Meniu.Click += new System.EventHandler(this.Button_Meniu_Click);
            // 
            // panel_Options
            // 
            this.panel_Options.Controls.Add(this.panel_Levels);
            this.panel_Options.Controls.Add(this.label_TextOption);
            this.panel_Options.Controls.Add(this.Skip_Button);
            this.panel_Options.Controls.Add(this.Quit_Button);
            this.panel_Options.Controls.Add(this.LevelsList_Button);
            this.panel_Options.Controls.Add(this.Sound_Button);
            this.panel_Options.Controls.Add(this.Resune_Button);
            this.panel_Options.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panel_Options.Location = new System.Drawing.Point(0, 0);
            this.panel_Options.Name = "panel_Options";
            this.panel_Options.Size = new System.Drawing.Size(480, 720);
            this.panel_Options.TabIndex = 3;
            this.panel_Options.Visible = false;
            // 
            // panel_Levels
            // 
            this.panel_Levels.Controls.Add(this.label_Back);
            this.panel_Levels.Controls.Add(this.label_TextLevels);
            this.panel_Levels.Controls.Add(this.pictureBox4);
            this.panel_Levels.Controls.Add(this.pictureBox1);
            this.panel_Levels.Controls.Add(this.pictureBox2);
            this.panel_Levels.Controls.Add(this.pictureBox3);
            this.panel_Levels.Controls.Add(this.pictureBox8);
            this.panel_Levels.Controls.Add(this.pictureBox20);
            this.panel_Levels.Controls.Add(this.pictureBox14);
            this.panel_Levels.Controls.Add(this.pictureBox13);
            this.panel_Levels.Controls.Add(this.pictureBox19);
            this.panel_Levels.Controls.Add(this.pictureBox6);
            this.panel_Levels.Controls.Add(this.pictureBox7);
            this.panel_Levels.Controls.Add(this.pictureBox11);
            this.panel_Levels.Controls.Add(this.pictureBox16);
            this.panel_Levels.Controls.Add(this.pictureBox9);
            this.panel_Levels.Controls.Add(this.pictureBox10);
            this.panel_Levels.Controls.Add(this.pictureBox12);
            this.panel_Levels.Controls.Add(this.pictureBox18);
            this.panel_Levels.Controls.Add(this.pictureBox15);
            this.panel_Levels.Controls.Add(this.pictureBox17);
            this.panel_Levels.Controls.Add(this.pictureBox5);
            this.panel_Levels.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panel_Levels.Location = new System.Drawing.Point(0, 0);
            this.panel_Levels.Name = "panel_Levels";
            this.panel_Levels.Size = new System.Drawing.Size(480, 720);
            this.panel_Levels.TabIndex = 14;
            this.panel_Levels.Visible = false;
            // 
            // label_Back
            // 
            this.label_Back.AutoSize = true;
            this.label_Back.Font = new System.Drawing.Font("Microsoft Sans Serif", 20F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label_Back.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(9)))), ((int)(((byte)(98)))), ((int)(((byte)(138)))));
            this.label_Back.Location = new System.Drawing.Point(25, 672);
            this.label_Back.Name = "label_Back";
            this.label_Back.Size = new System.Drawing.Size(123, 39);
            this.label_Back.TabIndex = 42;
            this.label_Back.Text = "⮜ Back";
            this.label_Back.Click += new System.EventHandler(this.label1_Click);
            // 
            // label_TextLevels
            // 
            this.label_TextLevels.AutoSize = true;
            this.label_TextLevels.Font = new System.Drawing.Font("Microsoft Sans Serif", 30F, ((System.Drawing.FontStyle)((System.Drawing.FontStyle.Bold | System.Drawing.FontStyle.Underline))), System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label_TextLevels.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(9)))), ((int)(((byte)(98)))), ((int)(((byte)(138)))));
            this.label_TextLevels.Location = new System.Drawing.Point(162, 10);
            this.label_TextLevels.Name = "label_TextLevels";
            this.label_TextLevels.Size = new System.Drawing.Size(176, 58);
            this.label_TextLevels.TabIndex = 40;
            this.label_TextLevels.Text = "Levels";
            // 
            // pictureBox4
            // 
            this.pictureBox4.Image = global::ArcadeMaze.Properties.Resources._4_Gray;
            this.pictureBox4.Location = new System.Drawing.Point(361, 92);
            this.pictureBox4.Name = "pictureBox4";
            this.pictureBox4.Size = new System.Drawing.Size(75, 75);
            this.pictureBox4.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pictureBox4.TabIndex = 39;
            this.pictureBox4.TabStop = false;
            this.pictureBox4.Click += new System.EventHandler(this.pictureBox4_Click);
            // 
            // pictureBox1
            // 
            this.pictureBox1.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(34)))), ((int)(((byte)(36)))), ((int)(((byte)(49)))));
            this.pictureBox1.Image = ((System.Drawing.Image)(resources.GetObject("pictureBox1.Image")));
            this.pictureBox1.Location = new System.Drawing.Point(28, 92);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(75, 75);
            this.pictureBox1.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pictureBox1.TabIndex = 38;
            this.pictureBox1.TabStop = false;
            this.pictureBox1.Tag = "";
            this.pictureBox1.Click += new System.EventHandler(this.pictureBox1_Click);
            // 
            // pictureBox2
            // 
            this.pictureBox2.Image = global::ArcadeMaze.Properties.Resources._2_Gray;
            this.pictureBox2.Location = new System.Drawing.Point(139, 92);
            this.pictureBox2.Name = "pictureBox2";
            this.pictureBox2.Size = new System.Drawing.Size(75, 75);
            this.pictureBox2.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pictureBox2.TabIndex = 37;
            this.pictureBox2.TabStop = false;
            this.pictureBox2.Click += new System.EventHandler(this.pictureBox2_Click);
            // 
            // pictureBox3
            // 
            this.pictureBox3.Image = global::ArcadeMaze.Properties.Resources._3_Gray;
            this.pictureBox3.Location = new System.Drawing.Point(250, 92);
            this.pictureBox3.Name = "pictureBox3";
            this.pictureBox3.Size = new System.Drawing.Size(75, 75);
            this.pictureBox3.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pictureBox3.TabIndex = 36;
            this.pictureBox3.TabStop = false;
            this.pictureBox3.Click += new System.EventHandler(this.pictureBox3_Click);
            // 
            // pictureBox8
            // 
            this.pictureBox8.Image = global::ArcadeMaze.Properties.Resources._8_Gray;
            this.pictureBox8.Location = new System.Drawing.Point(361, 216);
            this.pictureBox8.Name = "pictureBox8";
            this.pictureBox8.Size = new System.Drawing.Size(75, 75);
            this.pictureBox8.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pictureBox8.TabIndex = 35;
            this.pictureBox8.TabStop = false;
            this.pictureBox8.Click += new System.EventHandler(this.pictureBox8_Click);
            // 
            // pictureBox20
            // 
            this.pictureBox20.Image = global::ArcadeMaze.Properties.Resources._20_Gray;
            this.pictureBox20.Location = new System.Drawing.Point(361, 588);
            this.pictureBox20.Name = "pictureBox20";
            this.pictureBox20.Size = new System.Drawing.Size(75, 75);
            this.pictureBox20.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pictureBox20.TabIndex = 34;
            this.pictureBox20.TabStop = false;
            this.pictureBox20.Click += new System.EventHandler(this.pictureBox20_Click);
            // 
            // pictureBox14
            // 
            this.pictureBox14.Image = global::ArcadeMaze.Properties.Resources._14_Gray;
            this.pictureBox14.Location = new System.Drawing.Point(139, 464);
            this.pictureBox14.Name = "pictureBox14";
            this.pictureBox14.Size = new System.Drawing.Size(75, 75);
            this.pictureBox14.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pictureBox14.TabIndex = 33;
            this.pictureBox14.TabStop = false;
            this.pictureBox14.Click += new System.EventHandler(this.pictureBox14_Click);
            // 
            // pictureBox13
            // 
            this.pictureBox13.Image = global::ArcadeMaze.Properties.Resources._13_Gray;
            this.pictureBox13.Location = new System.Drawing.Point(28, 464);
            this.pictureBox13.Name = "pictureBox13";
            this.pictureBox13.Size = new System.Drawing.Size(75, 75);
            this.pictureBox13.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pictureBox13.TabIndex = 32;
            this.pictureBox13.TabStop = false;
            this.pictureBox13.Click += new System.EventHandler(this.pictureBox13_Click);
            // 
            // pictureBox19
            // 
            this.pictureBox19.Image = global::ArcadeMaze.Properties.Resources._19_Gray;
            this.pictureBox19.Location = new System.Drawing.Point(250, 588);
            this.pictureBox19.Name = "pictureBox19";
            this.pictureBox19.Size = new System.Drawing.Size(75, 75);
            this.pictureBox19.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pictureBox19.TabIndex = 31;
            this.pictureBox19.TabStop = false;
            this.pictureBox19.Click += new System.EventHandler(this.pictureBox19_Click);
            // 
            // pictureBox6
            // 
            this.pictureBox6.Image = global::ArcadeMaze.Properties.Resources._6_Gray;
            this.pictureBox6.Location = new System.Drawing.Point(139, 216);
            this.pictureBox6.Name = "pictureBox6";
            this.pictureBox6.Size = new System.Drawing.Size(75, 75);
            this.pictureBox6.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pictureBox6.TabIndex = 30;
            this.pictureBox6.TabStop = false;
            this.pictureBox6.Click += new System.EventHandler(this.pictureBox6_Click);
            // 
            // pictureBox7
            // 
            this.pictureBox7.Image = global::ArcadeMaze.Properties.Resources._7_Gray;
            this.pictureBox7.Location = new System.Drawing.Point(250, 216);
            this.pictureBox7.Name = "pictureBox7";
            this.pictureBox7.Size = new System.Drawing.Size(75, 75);
            this.pictureBox7.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pictureBox7.TabIndex = 29;
            this.pictureBox7.TabStop = false;
            this.pictureBox7.Click += new System.EventHandler(this.pictureBox7_Click);
            // 
            // pictureBox11
            // 
            this.pictureBox11.Image = global::ArcadeMaze.Properties.Resources._11_Gray;
            this.pictureBox11.Location = new System.Drawing.Point(250, 340);
            this.pictureBox11.Name = "pictureBox11";
            this.pictureBox11.Size = new System.Drawing.Size(75, 75);
            this.pictureBox11.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pictureBox11.TabIndex = 28;
            this.pictureBox11.TabStop = false;
            this.pictureBox11.Click += new System.EventHandler(this.pictureBox11_Click);
            // 
            // pictureBox16
            // 
            this.pictureBox16.Image = global::ArcadeMaze.Properties.Resources._16_Gray;
            this.pictureBox16.Location = new System.Drawing.Point(361, 464);
            this.pictureBox16.Name = "pictureBox16";
            this.pictureBox16.Size = new System.Drawing.Size(75, 75);
            this.pictureBox16.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pictureBox16.TabIndex = 27;
            this.pictureBox16.TabStop = false;
            this.pictureBox16.Click += new System.EventHandler(this.pictureBox16_Click);
            // 
            // pictureBox9
            // 
            this.pictureBox9.Image = global::ArcadeMaze.Properties.Resources._9_Gray;
            this.pictureBox9.Location = new System.Drawing.Point(28, 340);
            this.pictureBox9.Name = "pictureBox9";
            this.pictureBox9.Size = new System.Drawing.Size(75, 75);
            this.pictureBox9.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pictureBox9.TabIndex = 26;
            this.pictureBox9.TabStop = false;
            this.pictureBox9.Click += new System.EventHandler(this.pictureBox9_Click);
            // 
            // pictureBox10
            // 
            this.pictureBox10.Image = global::ArcadeMaze.Properties.Resources._10_Gray;
            this.pictureBox10.Location = new System.Drawing.Point(139, 340);
            this.pictureBox10.Name = "pictureBox10";
            this.pictureBox10.Size = new System.Drawing.Size(75, 75);
            this.pictureBox10.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pictureBox10.TabIndex = 25;
            this.pictureBox10.TabStop = false;
            this.pictureBox10.Click += new System.EventHandler(this.pictureBox10_Click);
            // 
            // pictureBox12
            // 
            this.pictureBox12.Image = global::ArcadeMaze.Properties.Resources._12_Gray;
            this.pictureBox12.Location = new System.Drawing.Point(361, 340);
            this.pictureBox12.Name = "pictureBox12";
            this.pictureBox12.Size = new System.Drawing.Size(75, 75);
            this.pictureBox12.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pictureBox12.TabIndex = 24;
            this.pictureBox12.TabStop = false;
            this.pictureBox12.Click += new System.EventHandler(this.pictureBox12_Click);
            // 
            // pictureBox18
            // 
            this.pictureBox18.Image = global::ArcadeMaze.Properties.Resources._18_Gray;
            this.pictureBox18.Location = new System.Drawing.Point(139, 588);
            this.pictureBox18.Name = "pictureBox18";
            this.pictureBox18.Size = new System.Drawing.Size(75, 75);
            this.pictureBox18.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pictureBox18.TabIndex = 23;
            this.pictureBox18.TabStop = false;
            this.pictureBox18.Click += new System.EventHandler(this.pictureBox18_Click);
            // 
            // pictureBox15
            // 
            this.pictureBox15.Image = global::ArcadeMaze.Properties.Resources._15_Gray;
            this.pictureBox15.Location = new System.Drawing.Point(250, 464);
            this.pictureBox15.Name = "pictureBox15";
            this.pictureBox15.Size = new System.Drawing.Size(75, 75);
            this.pictureBox15.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pictureBox15.TabIndex = 22;
            this.pictureBox15.TabStop = false;
            this.pictureBox15.Click += new System.EventHandler(this.pictureBox15_Click);
            // 
            // pictureBox17
            // 
            this.pictureBox17.Image = global::ArcadeMaze.Properties.Resources._17_Gray;
            this.pictureBox17.Location = new System.Drawing.Point(28, 588);
            this.pictureBox17.Name = "pictureBox17";
            this.pictureBox17.Size = new System.Drawing.Size(75, 75);
            this.pictureBox17.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pictureBox17.TabIndex = 21;
            this.pictureBox17.TabStop = false;
            this.pictureBox17.Click += new System.EventHandler(this.pictureBox17_Click);
            // 
            // pictureBox5
            // 
            this.pictureBox5.Image = global::ArcadeMaze.Properties.Resources._5_Gray;
            this.pictureBox5.Location = new System.Drawing.Point(28, 216);
            this.pictureBox5.Name = "pictureBox5";
            this.pictureBox5.Size = new System.Drawing.Size(75, 75);
            this.pictureBox5.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pictureBox5.TabIndex = 20;
            this.pictureBox5.TabStop = false;
            this.pictureBox5.Click += new System.EventHandler(this.pictureBox5_Click);
            // 
            // label_TextOption
            // 
            this.label_TextOption.AutoSize = true;
            this.label_TextOption.Font = new System.Drawing.Font("Microsoft Sans Serif", 25F, ((System.Drawing.FontStyle)((System.Drawing.FontStyle.Bold | System.Drawing.FontStyle.Underline))), System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label_TextOption.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(9)))), ((int)(((byte)(98)))), ((int)(((byte)(138)))));
            this.label_TextOption.Location = new System.Drawing.Point(95, 50);
            this.label_TextOption.Name = "label_TextOption";
            this.label_TextOption.Size = new System.Drawing.Size(291, 56);
            this.label_TextOption.TabIndex = 3;
            this.label_TextOption.Text = "Options Menu";
            this.label_TextOption.UseCompatibleTextRendering = true;
            // 
            // Skip_Button
            // 
            this.Skip_Button.Image = global::ArcadeMaze.Properties.Resources.Skip_Button;
            this.Skip_Button.Location = new System.Drawing.Point(103, 375);
            this.Skip_Button.Name = "Skip_Button";
            this.Skip_Button.Size = new System.Drawing.Size(270, 70);
            this.Skip_Button.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.Skip_Button.TabIndex = 10;
            this.Skip_Button.TabStop = false;
            this.Skip_Button.Click += new System.EventHandler(this.Skip_Button_Click);
            // 
            // Quit_Button
            // 
            this.Quit_Button.Image = global::ArcadeMaze.Properties.Resources.QuitGame_Button;
            this.Quit_Button.Location = new System.Drawing.Point(103, 575);
            this.Quit_Button.Name = "Quit_Button";
            this.Quit_Button.Size = new System.Drawing.Size(270, 70);
            this.Quit_Button.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.Quit_Button.TabIndex = 9;
            this.Quit_Button.TabStop = false;
            this.Quit_Button.Click += new System.EventHandler(this.Quit_Button_Click);
            // 
            // LevelsList_Button
            // 
            this.LevelsList_Button.ErrorImage = global::ArcadeMaze.Properties.Resources.LevelsList_Button;
            this.LevelsList_Button.Image = global::ArcadeMaze.Properties.Resources.LevelsList_Button;
            this.LevelsList_Button.Location = new System.Drawing.Point(103, 275);
            this.LevelsList_Button.Name = "LevelsList_Button";
            this.LevelsList_Button.Size = new System.Drawing.Size(270, 70);
            this.LevelsList_Button.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.LevelsList_Button.TabIndex = 8;
            this.LevelsList_Button.TabStop = false;
            this.LevelsList_Button.Click += new System.EventHandler(this.LevelsList_Button_Click);
            // 
            // Sound_Button
            // 
            this.Sound_Button.Image = global::ArcadeMaze.Properties.Resources.SoundsOn_Button;
            this.Sound_Button.Location = new System.Drawing.Point(103, 475);
            this.Sound_Button.Name = "Sound_Button";
            this.Sound_Button.Size = new System.Drawing.Size(270, 70);
            this.Sound_Button.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.Sound_Button.TabIndex = 7;
            this.Sound_Button.TabStop = false;
            this.Sound_Button.Click += new System.EventHandler(this.Sound_Button_Click);
            // 
            // Resune_Button
            // 
            this.Resune_Button.Image = ((System.Drawing.Image)(resources.GetObject("Resune_Button.Image")));
            this.Resune_Button.Location = new System.Drawing.Point(103, 175);
            this.Resune_Button.Name = "Resune_Button";
            this.Resune_Button.Size = new System.Drawing.Size(270, 70);
            this.Resune_Button.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.Resune_Button.TabIndex = 6;
            this.Resune_Button.TabStop = false;
            this.Resune_Button.Click += new System.EventHandler(this.Resune_Button_Click);
            // 
            // notifyIcon
            // 
            this.notifyIcon.ContextMenuStrip = this.contextMenuStrip_notifyIcon;
            this.notifyIcon.Icon = ((System.Drawing.Icon)(resources.GetObject("notifyIcon.Icon")));
            this.notifyIcon.Text = "Arcade Maze";
            this.notifyIcon.Visible = true;
            // 
            // contextMenuStrip_notifyIcon
            // 
            this.contextMenuStrip_notifyIcon.ImageScalingSize = new System.Drawing.Size(20, 20);
            this.contextMenuStrip_notifyIcon.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.helpToolStripMenuItem,
            this.toolStripMenuItem1,
            this.exitToolStripMenuItem});
            this.contextMenuStrip_notifyIcon.Name = "contextMenuStrip_notifyIcon";
            this.contextMenuStrip_notifyIcon.Size = new System.Drawing.Size(115, 62);
            // 
            // helpToolStripMenuItem
            // 
            this.helpToolStripMenuItem.Image = ((System.Drawing.Image)(resources.GetObject("helpToolStripMenuItem.Image")));
            this.helpToolStripMenuItem.Name = "helpToolStripMenuItem";
            this.helpToolStripMenuItem.Size = new System.Drawing.Size(114, 26);
            this.helpToolStripMenuItem.Text = "Help";
            this.helpToolStripMenuItem.Click += new System.EventHandler(this.helpToolStripMenuItem_Click);
            // 
            // toolStripMenuItem1
            // 
            this.toolStripMenuItem1.Name = "toolStripMenuItem1";
            this.toolStripMenuItem1.Size = new System.Drawing.Size(111, 6);
            // 
            // exitToolStripMenuItem
            // 
            this.exitToolStripMenuItem.Image = ((System.Drawing.Image)(resources.GetObject("exitToolStripMenuItem.Image")));
            this.exitToolStripMenuItem.Name = "exitToolStripMenuItem";
            this.exitToolStripMenuItem.Size = new System.Drawing.Size(114, 26);
            this.exitToolStripMenuItem.Text = "Exit";
            this.exitToolStripMenuItem.Click += new System.EventHandler(this.exitToolStripMenuItem_Click);
            // 
            // GameForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(34)))), ((int)(((byte)(36)))), ((int)(((byte)(49)))));
            this.ClientSize = new System.Drawing.Size(480, 720);
            this.Controls.Add(this.panel_Options);
            this.Controls.Add(this.panel_Game);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.MaximizeBox = false;
            this.Name = "GameForm";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Arcade Maze";
            this.KeyUp += new System.Windows.Forms.KeyEventHandler(this.GameForm_KeyUp);
            this.panel_Game.ResumeLayout(false);
            this.panel_Game.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.Button_Meniu)).EndInit();
            this.panel_Options.ResumeLayout(false);
            this.panel_Options.PerformLayout();
            this.panel_Levels.ResumeLayout(false);
            this.panel_Levels.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox4)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox2)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox3)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox8)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox20)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox14)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox13)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox19)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox6)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox7)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox11)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox16)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox9)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox10)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox12)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox18)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox15)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox17)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox5)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.Skip_Button)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.Quit_Button)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.LevelsList_Button)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.Sound_Button)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.Resune_Button)).EndInit();
            this.contextMenuStrip_notifyIcon.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Timer timerMoveUp;
        private System.Windows.Forms.Timer timerMoveDown;
        private System.Windows.Forms.Timer timerMoveRight;
        private System.Windows.Forms.Timer timerMoveLeft;
        private System.Windows.Forms.Timer timerTime;
        private System.Windows.Forms.Label label_Time;
        private System.Windows.Forms.PictureBox Button_Meniu;
        private System.Windows.Forms.Panel panel_Game;
        private System.Windows.Forms.Panel panel_Options;
        private System.Windows.Forms.PictureBox Skip_Button;
        private System.Windows.Forms.PictureBox Quit_Button;
        private System.Windows.Forms.PictureBox LevelsList_Button;
        private System.Windows.Forms.PictureBox Sound_Button;
        private System.Windows.Forms.PictureBox Resune_Button;
        private System.Windows.Forms.Label label_Level;
        private System.Windows.Forms.Label label_TextOption;
        private System.Windows.Forms.Panel panel_Levels;
        private System.Windows.Forms.Label label_Back;
        private System.Windows.Forms.Label label_TextLevels;
        private System.Windows.Forms.PictureBox pictureBox4;
        private System.Windows.Forms.PictureBox pictureBox1;
        private System.Windows.Forms.PictureBox pictureBox2;
        private System.Windows.Forms.PictureBox pictureBox3;
        private System.Windows.Forms.PictureBox pictureBox8;
        private System.Windows.Forms.PictureBox pictureBox20;
        private System.Windows.Forms.PictureBox pictureBox14;
        private System.Windows.Forms.PictureBox pictureBox13;
        private System.Windows.Forms.PictureBox pictureBox19;
        private System.Windows.Forms.PictureBox pictureBox6;
        private System.Windows.Forms.PictureBox pictureBox7;
        private System.Windows.Forms.PictureBox pictureBox11;
        private System.Windows.Forms.PictureBox pictureBox16;
        private System.Windows.Forms.PictureBox pictureBox9;
        private System.Windows.Forms.PictureBox pictureBox10;
        private System.Windows.Forms.PictureBox pictureBox12;
        private System.Windows.Forms.PictureBox pictureBox18;
        private System.Windows.Forms.PictureBox pictureBox15;
        private System.Windows.Forms.PictureBox pictureBox17;
        private System.Windows.Forms.PictureBox pictureBox5;
        private System.Windows.Forms.NotifyIcon notifyIcon;
        private System.Windows.Forms.ContextMenuStrip contextMenuStrip_notifyIcon;
        private System.Windows.Forms.ToolStripMenuItem helpToolStripMenuItem;
        private System.Windows.Forms.ToolStripSeparator toolStripMenuItem1;
        private System.Windows.Forms.ToolStripMenuItem exitToolStripMenuItem;
    }
}

