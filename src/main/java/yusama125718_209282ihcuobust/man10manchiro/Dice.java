package yusama125718_209282ihcuobust.man10manchiro;

import java.util.Random;

import static yusama125718_209282ihcuobust.man10manchiro.Game.outnumber;
import static yusama125718_209282ihcuobust.man10manchiro.Game.yaku;

public class Dice extends Thread
{
    @Override
    public synchronized void run()
    {
        loop: for (int i = 0; i < 3; i++)
        {
            for (int i2 = 0; i2 < 3; i2++)
            {
                Random dicerondom = new Random();
                int diceoutnumber = dicerondom.nextInt(6) + 1;
                switch (outnumber.size())
                {
                    case 0:
                    {
                        outnumber.add(diceoutnumber);
                        break;
                    }
                    case 1:
                    {
                        if (diceoutnumber > outnumber.get(0))
                        {
                            outnumber.add(diceoutnumber);
                        }
                        else
                        {
                            outnumber.add(0,diceoutnumber);
                        }
                        break;
                    }
                    case 2:
                    {
                        if (diceoutnumber > outnumber.get(0))
                        {
                            if (diceoutnumber > outnumber.get(1))
                            {
                                outnumber.add(diceoutnumber);
                            }
                            else
                            {
                                outnumber.add(1,diceoutnumber);
                            }
                        }
                        else
                        {
                            outnumber.add(0,diceoutnumber);
                        }
                        break;
                    }
                }
            }
            yaku = 0;
            Yaku yakuthread = new Yaku();
            yakuthread.start();
            try
            {
                yakuthread.join();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            if (!(yaku == 0))
            {
                if (!(yaku == 2))
                {
                    break loop;
                }
            }
        }
    }
}
