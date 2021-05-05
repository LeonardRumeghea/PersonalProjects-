using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ArcadeMaze
{
    class Time
    {
        public int seconds, minutes;

        public Time()
        {
            seconds = 0;
            minutes = 0;
        }

        public void AddSeconds()
        {
            seconds++;

            if (seconds == 60)
            {
                minutes++;
                seconds = 0;
            }
        }

        public void AddMinutes()
        {
            minutes++;
        }

        public string Write()
        {
            string cache = "";

            if (minutes < 10)
            {
                if (seconds < 10)
                {
                    cache = "0" + minutes.ToString() + ":0" + seconds.ToString();
                }
                else
                {
                    cache = "0" + minutes.ToString() + ":" + seconds.ToString();
                }
            }
            else
            {
                if (seconds < 10)
                {
                    cache = minutes.ToString() + ":0" + seconds.ToString();
                }
                else
                {
                    cache = minutes.ToString() + ":" + seconds.ToString();
                }
            }

            return cache;
        }
    }
}
