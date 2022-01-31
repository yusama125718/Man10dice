package yusama125718_209282ihcuobust.man10manchiro;

import static yusama125718_209282ihcuobust.man10manchiro.Game.*;

public class Yaku extends Thread
{
    @Override
    public synchronized void run()
    {
        assert Game.getInstance() != null;
        yakuhanntei: switch (outnumber.get(0))
        {
            case 1:
            {
                switch (outnumber.get(1))
                {
                    case 1:
                    {
                        switch (outnumber.get(2))
                        {
                            case 1:
                            {
                                yaku = 111;
                                yakuname = "ピンゾロ";
                                break yakuhanntei;
                            }
                            case 2:
                            {
                                yaku = 12;
                                yakuname = "イチのニ";
                                break yakuhanntei;
                            }
                            case 3:
                            {
                                yaku = 5;
                                yakuname = "dan5";
                                break yakuhanntei;
                            }
                            case 4:
                            {
                                yaku = 14;
                                yakuname = "イチのヨン";
                                break yakuhanntei;
                            }
                            case 5:
                            {
                                yaku = 15;
                                yakuname = "イチのゴ";
                                break yakuhanntei;
                            }
                            case 6:
                            {
                                yaku = 16;
                                yakuname = "イチのロク";
                                break yakuhanntei;
                            }
                        }
                    }
                    case 2:
                    {
                        switch (outnumber.get(2))
                        {
                            case 2:
                            {
                                yaku = 5;
                                yakuname = "dan5";
                                break yakuhanntei;
                            }
                            case 3:
                            {
                                yaku = 1;
                                yakuname = "ヒフミ";
                                break yakuhanntei;
                            }
                            default:
                            {
                                yaku = 3;
                                yakuname = "役無し";
                                break yakuhanntei;
                            }
                        }
                    }
                    case 3:
                    {
                        switch (outnumber.get(2))
                        {
                            case 3:
                            {
                                yaku = 11;
                                yakuname = "サンのイチ";
                                break yakuhanntei;
                            }
                            case 6:
                            {
                                yaku = 100;
                                yakuname = "man10";
                                break yakuhanntei;
                            }
                            default:
                            {
                                yaku = 3;
                                yakuname = "役無し";
                                break yakuhanntei;
                            }
                        }
                    }
                    case 4:
                    {
                        switch (outnumber.get(2))
                        {
                            case 4:
                            {
                                yaku = 11;
                                yakuname = "ヨンのイチ";
                                break yakuhanntei;
                            }
                            case 5:
                            {
                                yaku = 100;
                                yakuname = "man10";
                                break yakuhanntei;
                            }
                            default:
                            {
                                yaku = 3;
                                yakuname = "役無し";
                                break yakuhanntei;
                            }
                        }
                    }
                    case 5:
                    {
                        switch (outnumber.get(2))
                        {
                            case 5:
                            {
                                yaku = 11;
                                yakuname = "ゴのイチ";
                                break yakuhanntei;
                            }
                            case 6:
                            {
                                yaku = 3;
                                yakuname = "役無し";
                                break yakuhanntei;
                            }
                        }
                    }
                    case 6:
                    {
                        yaku = 11;
                        yakuname = "ロクのイチ";
                        break yakuhanntei;
                    }
                }
            }
            case 2:
            {
                switch (outnumber.get(1))
                {
                    case 2:
                    {
                        switch (outnumber.get(2))
                        {
                            case 2:
                            {
                                yaku = 110;
                                yakuname = "ニのゾロ目";
                                break yakuhanntei;
                            }
                            case 3:
                            {
                                yaku = 13;
                                yakuname = "ニのサン";
                                break yakuhanntei;
                            }
                            case 4:
                            {
                                yaku = 14;
                                yakuname = "ニのヨン";
                                break yakuhanntei;
                            }
                            case 5:
                            {
                                yaku = 15;
                                yakuname = "ニのゴ";
                                break yakuhanntei;
                            }
                            case 6:
                            {
                                yaku = 100;
                                yakuname = "man10";
                                break yakuhanntei;
                            }
                        }
                    }
                    case 3:
                    {
                        switch (outnumber.get(2))
                        {
                            case 3:
                            {
                                yaku = 12;
                                yakuname = "サンのニ";
                                break yakuhanntei;
                            }
                            case 5:
                            {
                                yaku = 100;
                                yakuname = "man10";
                                break yakuhanntei;
                            }
                            default:
                            {
                                yaku = 3;
                                yakuname = "役無し";
                                break yakuhanntei;
                            }
                        }
                    }
                    case 4:
                    {
                        switch (outnumber.get(2))
                        {
                            case 4:
                            {
                                yaku = 100;
                                yakuname = "man10";
                                break yakuhanntei;
                            }
                            default:
                            {
                                yaku = 3;
                                yakuname = "役無し";
                                break yakuhanntei;
                            }
                        }
                    }
                    case 5:
                    {
                        switch (outnumber.get(2))
                        {
                            case 5:
                            {
                                yaku = 12;
                                yakuname = "ゴのニ";
                                break yakuhanntei;
                            }
                            case 6:
                            {
                                yaku = 3;
                                yakuname = "役無し";
                                break yakuhanntei;
                            }
                        }
                    }
                    case 6:
                    {
                        yaku = 12;
                        yakuname = "ロクのニ";
                        break yakuhanntei;
                    }
                }
            }
            case 3:
            {
                switch (outnumber.get(1))
                {
                    case 3:
                    {
                        switch (outnumber.get(2))
                        {
                            case 3:
                            {
                                yaku = 109;
                                yakuname = "サンのゾロ目";
                                break yakuhanntei;
                            }
                            case 4:
                            {
                                yaku = 100;
                                yakuname = "man10";
                                break yakuhanntei;
                            }
                            case 5:
                            {
                                yaku = 15;
                                yakuname = "サンのゴ";
                                break yakuhanntei;
                            }
                            case 6:
                            {
                                yaku = 16;
                                yakuname = "サンのロク";
                                break yakuhanntei;
                            }
                        }
                    }
                    case 4:
                    {
                        switch (outnumber.get(2))
                        {
                            case 4:
                            {
                                yaku = 13;
                                yakuname = "ヨンのサン";
                                break yakuhanntei;
                            }
                            default:
                            {
                                yaku = 3;
                                yakuname = "役無し";
                                break yakuhanntei;
                            }
                        }
                    }
                    case 5:
                    {
                        switch (outnumber.get(2))
                        {
                            case 5:
                            {
                                yaku = 13;
                                yakuname = "ゴのサン";
                                break yakuhanntei;
                            }
                            case 6:
                            {
                                yaku = 3;
                                yakuname = "役無し";
                                break yakuhanntei;
                            }
                        }
                    }
                    case 6:
                    {
                        yaku = 13;
                        yakuname = "ロクのサン";
                        break yakuhanntei;
                    }
                }
            }
            case 4:
            {
                switch (outnumber.get(1))
                {
                    case 4:
                    {
                        switch (outnumber.get(2))
                        {
                            case 4:
                            {
                                yaku = 108;
                                yakuname = "ヨンのゾロ目";
                                break yakuhanntei;
                            }
                            case 5:
                            {
                                yaku = 15;
                                yakuname = "ヨンのゴ";
                                break yakuhanntei;
                            }
                            case 6:
                            {
                                yaku = 16;
                                yakuname = "ヨンのロク";
                                break yakuhanntei;
                            }
                        }
                    }
                    case 5:
                    {
                        switch (outnumber.get(2))
                        {
                            case 5:
                            {
                                yaku = 14;
                                yakuname = "ゴのヨン";
                                break yakuhanntei;
                            }
                            case 6:
                            {
                                yaku = 45;
                                yakuname = "シゴロ";
                                break yakuhanntei;
                            }
                        }
                    }
                    case 6:
                    {
                        yaku = 14;
                        yakuname = "ロクのヨン";
                        break yakuhanntei;
                    }
                }
            }
            case 5:
            {
                switch (outnumber.get(1))
                {
                    case 5:
                    {
                        switch (outnumber.get(2))
                        {
                            case 5:
                            {
                                yaku = 107;
                                yakuname = "ゴのゾロ目";
                                break yakuhanntei;
                            }
                            case 6:
                            {
                                yaku = 16;
                                yakuname = "ゴのロク";
                                break yakuhanntei;
                            }
                        }
                    }
                    case 6:
                    {
                        yaku = 15;
                        yakuname = "ロクのゴ";
                        break yakuhanntei;
                    }
                }
            }
            case 6:
            {
                yaku = 106;
                yakuname = "マンコロリン";
                break yakuhanntei;
            }
        }
    }
}
