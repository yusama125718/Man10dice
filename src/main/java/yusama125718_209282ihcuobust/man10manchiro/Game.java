package yusama125718_209282ihcuobust.man10manchiro;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

import static yusama125718_209282ihcuobust.man10manchiro.Man10Manchiro.*;

public class Game extends Thread
{
    static int yaku = 0;
    public static String yakuname;
    public static SpinnerListModel getInstance()
    {
        return null;
    }
    static ArrayList<Integer> outnumber = new ArrayList<>();
    public static int k = 0;
    public static int[] childyaku = new int[5];
    static int parentyaku;
    static double parentprice = 0;
    static double[] childprice = new double[5];
    double jackpot;
    double tax = manchiro.getConfig().getDouble("tax");
    static  double taxprice = 0;

    @Override
    public void run()
    {
        Random dicerondom = new Random();
        int shonben = dicerondom.nextInt(1000);
        if (shonben == 0)
        {
            parentyaku = 2;
            parentprice = (betvalue / 5 * 4);
            for (int l=0;l<playerperson;l++)
            {
                childprice[l] = (betvalue / 5 + betvalue - tax * betvalue);
            }
            for (Player player: Bukkit.getOnlinePlayers())
            {
                if (!disableplayers.contains(player.getUniqueId()))
                {
                    player.sendMessage("§l[§e§lManchiro§f§l]§r§e§l親のターン");
                    player.sendMessage("§l[§e§lManchiro§f§l]§r§e§lダイスを振っています...");
                    try
                    {
                        Thread.sleep(2000);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    player.sendMessage("§l[§e§lManchiro§f§l]§r§7お...おい...茶碗から出たぞ...");
                    player.sendMessage("§l[§e§lManchiro§f§l]§r§7これって...");
                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    player.sendMessage("§l[§e§lManchiro§f§l]§r§e§l子の勝ち！§f§l(倍率:1倍)");
                    player.sendMessage("§l[§e§lManchiro§f§l]§r§l" + Bukkit.getOfflinePlayer(parentname).getName() + "が§c§lションベン§f§lをして子が" + childprice[0] + "円獲得しました！§7(手数料"+tax * betvalue+"円)");
                }
            }
            vaultapi.deposit(parentname,parentprice);
            for (int i = 0;i < playerperson;i++)
            {
                vaultapi.deposit((childplayer.get(i)),childprice[i]);
            }
            taxprice = tax * betvalue;
            Finish finish = new Finish();
            finish.start();
            return;
        }
        Dice dicethread = new Dice();
        dicethread.start();
        try
        {
            dicethread.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        parentyaku = yaku;
        yaku = 0;
        for (Player player: Bukkit.getOnlinePlayers())
        {
            if (!disableplayers.contains(player.getUniqueId()))
            {
                player.sendMessage("§l[§e§lManchiro§f§l]§r§e§l親のターン");
                player.sendMessage("§l[§e§lManchiro§f§l]§r§e§lダイスを振っています...");
            }
        }
        try
        {
            Thread.sleep(3000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        for (Player player: Bukkit.getOnlinePlayers())
        {
            if (!disableplayers.contains(player.getUniqueId()))
            {
                player.sendMessage("§l[§e§lManchiro§f§l]§r§e§lチンチロリン♪§f§l" + outnumber.get(0) + " , " + outnumber.get(1) + " , " + outnumber.get(2) + "！");
                player.sendMessage("§l[§e§lManchiro§f§l]§r§e§l" + yakuname + "！！！");
            }
        }
        outnumber.clear();
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        switch (parentyaku)
        {
            case 111:
            {
                jackpot = manchiro.getConfig().getDouble("jackpot");
                if (betvalue * 10 >= jackpot)
                {
                    parentprice = jackpot - tax * betvalue;
                }
                else
                {
                    parentprice = betvalue * 10 - tax * betvalue;
                }
                for (int l=0;l<5;l++)
                {
                    childprice[l] = 0;
                }
                for (Player player: Bukkit.getOnlinePlayers())
                {
                    if (!disableplayers.contains(player.getUniqueId()))
                    {
                        player.sendMessage("§l[§e§lManchiro§f§l]§r§e§l親の勝ち！§f§l(ジャックポット)");
                        player.sendMessage("§l[§e§lManchiro§f§l]§r§l" + Bukkit.getOfflinePlayer(parentname).getName() + "が§c§l"+yakuname+"§f§lを出して" + parentprice + "円獲得しました！§7(手数料"+(tax * betvalue)+"円)");
                    }
                }
                vaultapi.deposit(parentname,parentprice);
                jackpot = jackpot - parentprice - tax * betvalue;
                taxprice = tax * betvalue;
                manchiro.getConfig().set("jackpot",jackpot);
                manchiro.saveConfig();
                Finish finish = new Finish();
                finish.start();
                return;
            }
            case 106:
            {
                for (Player player: Bukkit.getOnlinePlayers())
                {
                    if (!disableplayers.contains(player.getUniqueId()))
                    {
                        player.sendMessage("§l[§e§lManchiro§f§l]§r§e§l全員没収");
                        player.sendMessage("§l[§e§lManchiro§f§l]§r§l" + Bukkit.getOfflinePlayer(parentname).getName() + "が§c§l"+yakuname+"§f§lを出して没収されました！");
                    }
                }
                parentprice = 0;
                for (int l=0;l<playerperson;l++)
                {
                    childprice[l] = 0;
                }
                jackpot = manchiro.getConfig().getDouble("jackpot");
                jackpot = jackpot + betvalue * sitperson * 2;
                manchiro.getConfig().set("jackpot",jackpot);
                manchiro.saveConfig();
                Finish finish = new Finish();
                finish.start();
                return;
            }
            case 100:
            case 45:
            {
                parentprice = (betvalue / 5 * 2 * sitperson + betvalue - tax * betvalue);
                for (int l=0;l<5;l++)
                {
                    childprice[l] = (betvalue / 5 * 3);
                }
                for (Player player: Bukkit.getOnlinePlayers())
                {
                    if (!disableplayers.contains(player.getUniqueId()))
                    {
                        player.sendMessage("§l[§e§lManchiro§f§l]§r§e§l親の勝ち！§f§l(倍率:2倍)");
                        player.sendMessage("§l[§e§lManchiro§f§l]§r§l" + Bukkit.getOfflinePlayer(parentname).getName() + "が§c§l"+yakuname+"§f§lを出して" + (betvalue / 5 * 2 * playerperson + betvalue) + "円獲得しました！§7(手数料"+tax * betvalue+"円)");
                    }
                }
                vaultapi.deposit(parentname,parentprice);
                for (int i = 0;i < playerperson;i++)
                {
                    vaultapi.deposit((childplayer.get(i)),betvalue / 5 * 3);
                }
                taxprice = tax * betvalue;
                Finish finish = new Finish();
                finish.start();
                return;
            }
            case 3:
            {
                parentprice = (betvalue / 5 * 4 * sitperson);
                for (int l=0;l<5;l++)
                {
                    childprice[l] = (betvalue / 5 * 1 + betvalue - tax * betvalue);
                }
                for (Player player: Bukkit.getOnlinePlayers())
                {
                    if (!disableplayers.contains(player.getUniqueId()))
                    {
                        player.sendMessage("§l[§e§lManchiro§f§l]§r§e§l子の勝ち！§f§l(倍率:1倍)");
                        player.sendMessage("§l[§e§lManchiro§f§l]§r§l" + Bukkit.getOfflinePlayer(parentname).getName() + "が§c§l"+yakuname+"§f§lを出して子が" + childprice[0] + "円獲得しました！§7(手数料"+tax * betvalue+"円)");
                    }
                }
                vaultapi.deposit(parentname,parentprice);
                for (int i = 0;i < playerperson;i++)
                {
                    vaultapi.deposit((childplayer.get(i)),(childprice[i]));
                }
                taxprice = tax * betvalue * sitperson;
                Finish finish = new Finish();
                finish.start();
                return;
            }
            case 5:
            case 1:
            {
                parentprice = (betvalue / 5 * 3 * sitperson);
                for (int l=0;l<5;l++)
                {
                    childprice[l] = (betvalue / 5 * 2 + betvalue - tax * betvalue);
                }
                for (Player player: Bukkit.getOnlinePlayers())
                {
                    if (!disableplayers.contains(player.getUniqueId()))
                    {
                        player.sendMessage("§l[§e§lManchiro§f§l]§r§e§l子の勝ち！§f§l(倍率:2倍)");
                        player.sendMessage("§l[§e§lManchiro§f§l]§r§l" + Bukkit.getOfflinePlayer(parentname).getName() + "が§c§l"+yakuname+"§f§lを出して子が" + childprice[0] + "円獲得しました！§7(手数料"+(tax * betvalue)+"円)");
                    }
                }
                taxprice = tax * betvalue * sitperson;
                vaultapi.deposit(parentname,parentprice);
                for (int i = 0;i < playerperson;i++)
                {
                    vaultapi.deposit((childplayer.get(i)),(childprice[i]));
                }
                Finish finish = new Finish();
                finish.start();
                return;
            }
        }
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        String parentyakuname = yakuname;
        for (Player player: Bukkit.getOnlinePlayers())
        {
            if (!disableplayers.contains(player.getUniqueId()))
            {
                player.sendMessage("§l[§e§lManchiro§f§l]§r§e§l子のターン");
            }
        }
        for (k = 0;k < playerperson;k++)         //子の処理
        {
            Random dicerondom1 = new Random();
            int shonben1 = dicerondom1.nextInt(1000);
            if (shonben1 == 0)
            {
                childyaku[k] = 2;
                parentprice = (parentprice + betvalue / 5 + betvalue - tax * betvalue);
                childprice[k] = (betvalue / 5 * 4);
                for (Player player: Bukkit.getOnlinePlayers())
                {
                    if (!disableplayers.contains(player.getUniqueId()))
                    {
                        player.sendMessage("§l[§e§lManchiro§f§l]§r§e§l親のターン");
                        player.sendMessage("§l[§e§lManchiro§f§l]§r§e§lダイスを振っています...");
                        try
                        {
                            Thread.sleep(2000);
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                        player.sendMessage("§l[§e§lManchiro§f§l]§r§7お...おい...茶碗から出たぞ...");
                        player.sendMessage("§l[§e§lManchiro§f§l]§r§7これって...");
                        try
                        {
                            Thread.sleep(1000);
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                        player.sendMessage("§l[§e§lManchiro§f§l]§r§e§l親の勝ち！§f§l(倍率:1倍)");
                        player.sendMessage("§l[§e§lManchiro§f§l]§r§l" + Bukkit.getOfflinePlayer(childplayer.get(k)).getName() + "が§c§lションベン§f§lをして子が" + childprice[0] + "円獲得しました！§7(手数料"+(tax * betvalue)+"円)");
                    }
                }
                vaultapi.deposit((childplayer.get(k)),(childprice[k]));
                vaultapi.deposit(parentname,betvalue / 5 + betvalue - tax * betvalue);
                taxprice = taxprice + tax * betvalue;
                Finish finish = new Finish();
                finish.start();
                return;
            }
            outnumber.clear();
            Dice dicethread1 = new Dice();
            dicethread1.start();
            try
            {
                dicethread1.join();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            childyaku[k] = yaku;
            yaku = 0;
            for (Player player: Bukkit.getOnlinePlayers())
            {
                if (!disableplayers.contains(player.getUniqueId()))
                {
                    player.sendMessage("§l[§e§lManchiro§f§l]§r§e§l" + Bukkit.getOfflinePlayer(childplayer.get(k)).getName() + "のターン");
                    player.sendMessage("§l[§e§lManchiro§f§l]§r§e§lダイスを振っています...");
                }
            }
            try
            {
                Thread.sleep(3000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            for (Player player: Bukkit.getOnlinePlayers())
            {
                if (!disableplayers.contains(player.getUniqueId()))
                {
                    player.sendMessage("§l[§e§lManchiro§f§l]§r§e§lチンチロリン♪§f§l" + outnumber.get(0) + " , " + outnumber.get(1) + " , " + outnumber.get(2) + "！");
                    player.sendMessage("§l[§e§lManchiro§f§l]§r§e§l" + yakuname + "！！！");
                }
            }
            outnumber.clear();
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            switch (childyaku[k])
            {
                case 111:
                {
                    jackpot = manchiro.getConfig().getDouble("jackpot");
                    parentprice = 0;
                    if (betvalue * 10 >= jackpot)
                    {
                        childprice[k] = jackpot - tax * betvalue;
                    }
                    else
                    {
                        childprice[k] = betvalue * 10 - tax * betvalue;
                    }
                    childprice[k] = betvalue * 2;
                    for (Player player: Bukkit.getOnlinePlayers())
                    {
                        if (!disableplayers.contains(player.getUniqueId()))
                        {
                            player.sendMessage("§l[§e§lManchiro§f§l]§r§e§l子の勝ち！§f§l(ジャックポット)");
                            player.sendMessage("§l[§e§lManchiro§f§l]§r§l" + Bukkit.getOfflinePlayer(childplayer.get(k)).getName() + "が§c§l"+yakuname+"§f§lを出して" + childprice[k] + "円獲得しました！§7(手数料"+(tax * betvalue)+"円)");
                        }
                    }
                    vaultapi.deposit((childplayer.get(k)),(childprice[k]));
                    jackpot = jackpot - childprice[k] - tax * betvalue;
                    taxprice = taxprice + tax * betvalue;
                    manchiro.getConfig().set("jackpot",jackpot);
                    manchiro.saveConfig();
                    break;
                }
                case 106:
                {
                    for (Player player: Bukkit.getOnlinePlayers())
                    {
                        if (!disableplayers.contains(player.getUniqueId()))
                        {
                            player.sendMessage("§l[§e§lManchiro§f§l]§r§e§l全員没収");
                            player.sendMessage("§l[§e§lManchiro§f§l]§r§l" + Bukkit.getOfflinePlayer(parentname).getName() + "が§c§l"+yakuname+"§f§lを出して没収されました！");
                        }
                    }
                    childprice[k] = 0;
                    jackpot = manchiro.getConfig().getDouble("jackpot");
                    jackpot = jackpot + betvalue * 2;
                    manchiro.getConfig().set("jackpot",jackpot);
                    manchiro.saveConfig();
                    break;
                }
                case 100:
                case 45:
                {
                    parentprice = (betvalue / 5 * 3 + parentprice);
                    childprice[k] = (betvalue + betvalue / 5 * 2 - tax * betvalue);
                    for (Player player: Bukkit.getOnlinePlayers())
                    {
                        if (!disableplayers.contains(player.getUniqueId()))
                        {
                            player.sendMessage("§l[§e§lManchiro§f§l]§r§e§l子の勝ち！§f§l(倍率:2倍)");
                            player.sendMessage("§l[§e§lManchiro§f§l]§r§l" + Bukkit.getOfflinePlayer(childplayer.get(k)).getName() + "が§c§l"+yakuname+"§f§lを出して" + childprice[k] + "円獲得しました！§7(手数料"+(tax * betvalue)+"円)");
                        }
                    }
                    vaultapi.deposit((childplayer.get(k)),childprice[k]);
                    vaultapi.deposit(parentname,betvalue / 5 * 3);
                    taxprice = taxprice + tax * betvalue;
                    break;
                }
                case 3:
                {
                    parentprice = (betvalue / 5 * 1 + betvalue + parentprice - tax * betvalue);
                    childprice[k] = (betvalue / 5 * 4);
                    for (Player player: Bukkit.getOnlinePlayers())
                    {
                        if (!disableplayers.contains(player.getUniqueId()))
                        {
                            player.sendMessage("§l[§e§lManchiro§f§l]§r§e§l親の勝ち！§f§l(倍率:1倍)");
                            player.sendMessage("§l[§e§lManchiro§f§l]§r§l" + Bukkit.getOfflinePlayer(childplayer.get(k)).getName() + "が§c§l"+yakuname+"§f§lを出して親が" + (betvalue / 5 * 1 + betvalue) + "円獲得しました！§7(手数料"+tax * betvalue+"円)");
                        }
                    }
                    vaultapi.deposit((childplayer.get(k)),childprice[k]);
                    vaultapi.deposit(parentname,betvalue / 5 * 1 + betvalue - tax * betvalue);
                    taxprice = taxprice + tax * betvalue;
                    break;
                }
                case 5:
                case 1:
                {
                    parentprice = (betvalue / 5 * 2 + betvalue + parentprice - tax * betvalue);
                    childprice[k] = (betvalue / 5 * 3);
                    for (Player player: Bukkit.getOnlinePlayers())
                    {
                        if (!disableplayers.contains(player.getUniqueId()))
                        {
                            player.sendMessage("§l[§e§lManchiro§f§l]§r§e§l親の勝ち！§f§l(倍率:2倍)");
                            player.sendMessage("§l[§e§lManchiro§f§l]§r§l" + Bukkit.getOfflinePlayer(childplayer.get(k)).getName() + "が§c§l"+yakuname+"§f§lを出して親が" + (betvalue / 5 * 2 + betvalue) + "円獲得しました！§7(手数料"+tax * betvalue+"円)");
                        }
                    }
                    vaultapi.deposit((childplayer.get(k)),childprice[k]);
                    vaultapi.deposit(parentname,betvalue / 5 * 2 + betvalue - tax * betvalue);
                    taxprice = taxprice + tax * betvalue;
                    break;
                }
                default:
                {
                    if (parentyaku == childyaku[k])
                    {
                        parentprice = parentprice + betvalue;
                        childprice[k] = betvalue;
                        for (Player player: Bukkit.getOnlinePlayers())
                        {
                            if (!disableplayers.contains(player.getUniqueId()))
                            {
                                player.sendMessage("§l[§e§lManchiro§f§l]§r§e§l引き分け！");
                            }
                        }
                        vaultapi.deposit((childplayer.get(k)),betvalue);
                        vaultapi.deposit(parentname,childprice[k]);
                    }
                    if (parentyaku < childyaku[k])
                    {
                        if (childyaku[k] > 20)
                        {
                            parentprice = (betvalue / 5 * 2 + parentprice);
                            childprice[k] = (betvalue / 5 * 3 + betvalue - tax * betvalue);
                            for (Player player: Bukkit.getOnlinePlayers())
                            {
                                if (!disableplayers.contains(player.getUniqueId()))
                                {
                                    player.sendMessage("§l[§e§lManchiro§f§l]§r§e§l子の勝ち！§f§l(倍率:3倍)");
                                    player.sendMessage("§l[§e§lManchiro§f§l]§r§l" + Bukkit.getOfflinePlayer(parentname).getName() + "が§c§l" + yakuname + "§f§lを出して子が" + childprice[k] + "円獲得しました！§7(手数料"+tax * betvalue+"円)");
                                }
                            }
                            vaultapi.deposit(parentname,betvalue / 5 * 2);
                            vaultapi.deposit((childplayer.get(k)),childprice[k]);
                            taxprice = taxprice + tax * betvalue;
                        }
                        else
                        {
                            parentprice = (betvalue / 5 * 4 + parentprice);
                            childprice[k] = (betvalue / 5 * 1 + betvalue - tax * betvalue);
                            for (Player player: Bukkit.getOnlinePlayers())
                            {
                                if (!disableplayers.contains(player.getUniqueId()))
                                {
                                    player.sendMessage("§l[§e§lManchiro§f§l]§r§e§l子の勝ち！§f§l(倍率:1倍)");
                                    player.sendMessage("§l[§e§lManchiro§f§l]§r§l" + Bukkit.getOfflinePlayer(parentname).getName() + "が§c§l" + yakuname + "§f§lを出して子が" + (betvalue / 5 + betvalue) + "円獲得しました！§7(手数料"+tax * betvalue+"円)");
                                }
                            }
                            vaultapi.deposit(parentname,betvalue / 5 * 4);
                            vaultapi.deposit((childplayer.get(k)),childprice[k]);
                            taxprice = taxprice + tax * betvalue;
                        }
                    }
                    else
                    {
                        if (parentyaku > 20)
                        {
                            parentprice = (betvalue / 5 * 3 + betvalue + parentprice - tax * betvalue);
                            childprice[k] = (betvalue / 5 * 2);
                            for (Player player: Bukkit.getOnlinePlayers())
                            {
                                if (!disableplayers.contains(player.getUniqueId()))
                                {
                                    player.sendMessage("§l[§e§lManchiro§f§l]§r§e§l親の勝ち！§f§l(倍率:3倍)");
                                    player.sendMessage("§l[§e§lManchiro§f§l]§r§l" + Bukkit.getOfflinePlayer(childplayer.get(k)).getName() + "が§c§l" + parentyakuname + "§f§lを出して親が" + (betvalue / 5 * 3 + betvalue) + "円獲得しました！§7(手数料"+tax * betvalue+"円)");
                                }
                            }
                            vaultapi.deposit((childplayer.get(k)),childprice[k]);
                            vaultapi.deposit(parentname,betvalue / 5 * 3 + betvalue - tax * betvalue);
                            taxprice = taxprice + tax * betvalue;
                        }
                        else
                        {
                            parentprice = (betvalue / 5 + betvalue + parentprice - tax * betvalue);
                            childprice[k] = (betvalue / 5 * 4);
                            for (Player player: Bukkit.getOnlinePlayers())
                            {
                                if (!disableplayers.contains(player.getUniqueId()))
                                {
                                    player.sendMessage("§l[§e§lManchiro§f§l]§r§e§l親の勝ち！§f§l(倍率:1倍)");
                                    player.sendMessage("§l[§e§lManchiro§f§l]§r§l" + Bukkit.getOfflinePlayer(childplayer.get(k)).getName() + "が§c§l" + parentyakuname + "§f§lを出して親が" + (betvalue / 5 + betvalue) + "円獲得しました！§7(手数料"+tax * betvalue+"円)");
                                }
                            }
                            vaultapi.deposit((childplayer.get(k)),childprice[k]);
                            vaultapi.deposit(parentname,betvalue / 5 + betvalue - tax * betvalue);
                            taxprice = taxprice + tax * betvalue;
                        }
                    }
                    break;
                }
            }
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        Finish finish = new Finish();
        finish.start();
        return;
    }
}
