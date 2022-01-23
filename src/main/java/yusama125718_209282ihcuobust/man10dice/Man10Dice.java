package yusama125718_209282ihcuobust.man10dice;

import java.util.*;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import static java.lang.Integer.parseInt;

public final class Man10Dice extends JavaPlugin
{
    static boolean operation = false;
    static public List<UUID> dissableplayers=new ArrayList<>();
    static public List<UUID> mlotdissableplayers=new ArrayList<>();
    static boolean mlotoperation = false;
    static boolean activegame = false;
    static int mlotdicestakes;
    static int luckynumber;
    HashMap<Integer,UUID> appliedplayers = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (label.equalsIgnoreCase("mdice"))
        {
            if (sender.hasPermission("mdice.player"))
            {
                switch (args.length)
                {
                    case 1:
                    {
                        if (sender instanceof Player)
                        {
                            Player playerid=(Player)sender;

                            if(args[0].length()>=10)
                            {
                                sender.sendMessage("§l[§eMan10Dice§f]§r§c§lダイスの面数は10億以上に設定できません");
                                return true;
                            }

                            if (args[0].equals("hide"))
                            {
                                if (dissableplayers.contains(playerid.getUniqueId()))
                                {
                                    return true;
                                }
                                else
                                {
                                    dissableplayers.add(playerid.getUniqueId());
                                    sender.sendMessage("§e§l[Man10Dice]§r§7§l非表示にします");

                                    return true;
                                }
                            }

                            if ((args[0].equals("show")))
                            {
                                if (dissableplayers.contains(playerid.getUniqueId()))
                                {
                                    dissableplayers.remove(playerid.getUniqueId());
                                    sender.sendMessage("§e§l[Man10Dice]§r§7§l表示します");

                                    return true;
                                }
                                else
                                {
                                    return true;
                                }
                            }
                        }

                        if (!operation)
                        {
                            boolean isNumeric = args[0].matches("-?\\d+");
                            if (isNumeric)
                            {
                                int dicestakes = parseInt(args[0]);
                                if (dicestakes >= 1)
                                {
                                    operation = true;
                                    for (Player player:Bukkit.getOnlinePlayers())
                                    {
                                        if (!dissableplayers.contains(player.getUniqueId()))
                                        {
                                            player.sendMessage("§e§l[Man10Dice]§r" + sender.getName() + "§lはダイスを振っています...");
                                        }
                                    }

                                    Bukkit.getScheduler().runTaskLater(this, new Runnable() {
                                        @Override
                                        public void run() {
                                            Random dicerondom = new Random();
                                            int outnumber = dicerondom.nextInt(dicestakes) + 1;
                                            for (Player player:Bukkit.getOnlinePlayers())
                                            {
                                                if (!dissableplayers.contains(player.getUniqueId()))
                                                {
                                                    player.sendMessage("§e§l[Man10Dice]§r" + sender.getName() + "§lは" + dicestakes + "面ダイスを振って §e§l" + outnumber + "§r§l を出しました！");
                                                }
                                            }
                                            operation = false;
                                        }
                                    }, 60);
                                    return true;
                                }
                                else
                                {
                                    sender.sendMessage("§e§l[Man10Dice]§r§c§lダイスの面数は1以上の整数にしてください");
                                    return true;
                                }
                            }
                            else
                            {
                                sender.sendMessage("§e§l[Man10Dice]§r§c§lダイスの面数は1以上の整数にしてください");
                                return true;
                            }
                        }
                        else
                        {
                            sender.sendMessage("§e§l[Man10Dice]§r§c§l現在実行中です");

                            return true;
                        }
                    }

                    case 2:
                    {
                        if (args[0].length()>=10||args[1].length()>=10)
                        {
                            sender.sendMessage("§e§l[Man10Dice]§r§c§lダイスの面数は10億以上に設定できません");
                            return true;
                        }

                        if (!operation)
                        {
                            boolean isNumeric2 = args[1].matches("-?\\d+");
                            if (isNumeric2)
                            {
                                int numberofdice = parseInt(args[1]);

                                if (numberofdice >= 1)
                                {
                                    if (numberofdice <= 12)
                                    {
                                        boolean isNumeric3 = args[0].matches("-?\\d+");
                                        if (isNumeric3)
                                        {
                                            int dicestakes1 = parseInt(args[0]);
                                            if (dicestakes1 >= 1)
                                            {
                                                operation = true;
                                                for (Player player:Bukkit.getOnlinePlayers())
                                                {
                                                    if (!dissableplayers.contains(player.getUniqueId()))
                                                    {
                                                        player.sendMessage("§e§l[Man10Dice]§r" + sender.getName() + "§lはダイスを振っています...");
                                                    }
                                                }
                                                Bukkit.getScheduler().runTaskLater(this, new Runnable()
                                                {
                                                    @Override
                                                    public void run()
                                                    {
                                                        for (int loopcount = 1; loopcount <= numberofdice; loopcount++)
                                                        {
                                                            Random dicerondom1 = new Random();
                                                            int outnumber1 = dicerondom1.nextInt(dicestakes1) + 1;
                                                            for (Player player:Bukkit.getOnlinePlayers())
                                                            {
                                                                if (!dissableplayers.contains(player.getUniqueId()))
                                                                {
                                                                    player.sendMessage("§e§l[Man10Dice]§r" + sender.getName() + "§lは" + dicestakes1 + "面ダイスを振って §e§l" + outnumber1 + "§r§l を出しました！");
                                                                }
                                                            }

                                                        }
                                                        operation = false;
                                                    }
                                                }, 60);
                                                return true;

                                            }
                                            else
                                            {
                                                sender.sendMessage("§e§l[Man10Dice]§r§c§lダイスの面数は1以上の整数にしてください");
                                                return true;
                                            }
                                        }
                                    }
                                    else
                                    {
                                        sender.sendMessage("§e§l[Man10Dice]§r§c§lダイスの数は1以上12以下の整数にしてください");
                                        return true;
                                    }
                                }
                                else
                                {
                                    sender.sendMessage("§e§l[Man10Dice]§r§c§lダイスの数は1以上12以下の整数にしてください");
                                    return true;
                                }

                            }
                            else
                            {
                                sender.sendMessage("§e§l[Man10Dice]§r§c§lダイスの面数は1以上の整数にしてください");
                                return true;
                            }
                        }
                        else
                        {
                            sender.sendMessage("§e§l[Man10Dice]§r§c§l現在実行中です");

                            return true;
                        }
                    }

                    default:
                    {
                        sender.sendMessage("§e§l[Man10Dice]§r§7§l/mdice [出目の数] [さいころの数]");

                        return true;
                    }
                }
            }
            else
            {
                sender.sendMessage("§c[Man10Dice]You don't have permission!");
                return true;
            }
        }

        if (label.equalsIgnoreCase("mlot"))
        {
            if (sender.hasPermission("mlot.player"))
            {
                switch (args.length)
                {
                    case 1:
                    {
                        if (sender instanceof Player)
                        {
                            Player mlotplayerid = (Player) sender;

                            if (args[0].equals("hide"))
                            {
                                if (mlotdissableplayers.contains(mlotplayerid.getUniqueId()))
                                {
                                    return true;
                                }
                                else
                                {
                                    mlotdissableplayers.add(mlotplayerid.getUniqueId());
                                    sender.sendMessage("§l[§e§lMan10DiceLottery§f§l]§r§7§l非表示にします");

                                    return true;
                                }
                            }

                            if (args[0].equals("show"))
                            {
                                if (mlotdissableplayers.contains(mlotplayerid.getUniqueId()))
                                {
                                    mlotdissableplayers.remove(mlotplayerid.getUniqueId());
                                    sender.sendMessage("§l[§e§lMan10DiceLottery§f§l]§r§7§l表示します");

                                    return true;
                                }
                                else
                                {
                                    return true;
                                }
                            }
                            if (args[0].length() <= 10)
                            {
                                boolean isNumeric = args[0].matches("-?\\d+");
                                if (isNumeric)
                                {
                                    if (activegame)
                                    {
                                        luckynumber = parseInt(args[0]);

                                        if (mlotdicestakes < luckynumber)
                                        {
                                            sender.sendMessage("§l[§e§lMan10DiceLottery§f§l]§r§c§l" + mlotdicestakes + "以下の整数にしてください");
                                            return true;
                                        }
                                        if (1 > luckynumber)
                                        {
                                            sender.sendMessage("§l[§e§lMan10DiceLottery§f§l]§r§c§l1以上の整数にしてください");
                                            return true;
                                        }

                                        if (!appliedplayers.values().contains(mlotplayerid.getUniqueId()))
                                        {
                                            if (!appliedplayers.keySet().contains(luckynumber))
                                            {
                                                appliedplayers.put(luckynumber,mlotplayerid.getUniqueId());
                                                sender.sendMessage("§l[§e§lMan10DiceLottery§f§l]§r§e§l応募しました！");
                                                return true;
                                            }
                                            else
                                            {
                                                sender.sendMessage("§l[§e§lMan10DiceLottery§f§l]§r§c§lその数字はすでに使われています");
                                                return true;
                                            }
                                        }
                                        else
                                        {
                                            sender.sendMessage("§l[§e§lMan10DiceLottery§f§l]§r§c§lあなたはすでに応募しています");
                                            return true;
                                        }
                                    }
                                    else
                                    {
                                        sender.sendMessage("§l[§e§lMan10DiceLottery§f§l]§r§c§l現在応募は受け付けていません");
                                        return true;
                                    }
                                }
                                else
                                {
                                    if (sender.hasPermission("mlot.op"))
                                    {
                                        sender.sendMessage("§l[§e§lMan10DiceLottery§f§l] §7/mlot [ダイスの面数] [時間(秒)] : §lゲームを開始します");
                                        sender.sendMessage("§l[§e§lMan10DiceLottery§f§l] §7/mlot hide : §l非表示にします");
                                        sender.sendMessage("§l[§e§lMan10DiceLottery§f§l] §7/mlot show : §l表示します");
                                        sender.sendMessage("§l[§e§lMan10DiceLottery§f§l] §7/mlot [数字] : §l抽選に応募します(ゲーム中のみ)");
                                        return true;
                                    }
                                    else
                                    {
                                        sender.sendMessage("§l[§e§lMan10DiceLottery§f§l] §7/mlot hide : §l非表示にします");
                                        sender.sendMessage("§l[§e§lMan10DiceLottery§f§l] §7/mlot show : §l表示します");
                                        sender.sendMessage("§l[§e§lMan10DiceLottery§f§l] §7/mlot [数字] : §l抽選に応募します(ゲーム中のみ)");
                                        return true;
                                    }
                                }
                            }
                            else
                            {
                                sender.sendMessage("§l[§e§lMan10DiceLottery§f§l]§r§c§l応募する数字は10億以上に設定できません");
                                return true;
                            }
                        }
                        return true;
                    }

                    case 2:
                    {
                        if (sender.hasPermission("mlot.op"))
                        {
                            if (args[0].length() >= 10)
                            {
                                sender.sendMessage("§l[§e§lMan10DiceLottery§f§l]§r§c§lダイスの面数は10億以上に設定できません");
                                return true;
                            }

                            if (args[1].length() >= 3)
                            {
                                sender.sendMessage("§l[§e§lMan10DiceLottery§f§l]§r§c§l時間は100秒以上に設定できません");
                                return true;
                            }

                            if (!mlotoperation)
                            {
                                boolean isNumeric = args[0].matches("-?\\d+");
                                if (isNumeric)
                                {
                                    boolean isNumeric1 = args[1].matches("-?\\d+");
                                    if (isNumeric1)
                                    {
                                        mlotdicestakes = parseInt(args[0]);
                                        int dicedelay = parseInt(args[1]);

                                        mlotoperation = true;
                                        activegame = true;
                                        for (Player player : Bukkit.getOnlinePlayers())
                                        {
                                            if (!mlotdissableplayers.contains(player.getUniqueId()))
                                            {
                                                player.sendMessage("§l[§e§lMan10DiceLottery§f§l]§r" + sender.getName() + "§lが" + args[0] + "§lDを開始しました！ §e/mlot [数字] §r§lで参加しましょう！");
                                            }
                                        }
                                        int outnumber;

                                        Random dicerondom = new Random();
                                        outnumber = dicerondom.nextInt(mlotdicestakes) + 1;

                                        Bukkit.getScheduler().runTaskLater(this, new Runnable()
                                        {
                                            @Override
                                            public void run()
                                            {
                                                activegame = false;

                                                for (Player player : Bukkit.getOnlinePlayers())
                                                {
                                                    if (!mlotdissableplayers.contains(player.getUniqueId()))
                                                    {
                                                        player.sendMessage("§l[§e§lMan10DiceLottery§f§l]§r§lダイスを振っています...");
                                                    }
                                                }
                                            }
                                        }, dicedelay * 20L);

                                        Bukkit.getScheduler().runTaskLater(this, new Runnable()
                                        {
                                            @Override
                                            public void run()
                                            {
                                                {
                                                    for (Player player : Bukkit.getOnlinePlayers())
                                                    {
                                                        if (!mlotdissableplayers.contains(player.getUniqueId()))
                                                        {
                                                            player.sendMessage("§l[§e§lMan10DiceLottery§f§l]§r" + sender.getName() + "§lは" + mlotdicestakes + "面ダイスを振って §e§l" + outnumber + "§r§l を出しました！");
                                                        }
                                                    }

                                                    if (appliedplayers.keySet().contains(outnumber))
                                                    {
                                                        for (Player player : Bukkit.getOnlinePlayers())
                                                        {
                                                            if (!mlotdissableplayers.contains(player.getUniqueId()))
                                                            {
                                                                player.sendMessage("§l[§e§lMan10DiceLottery§f§l]§r" + Bukkit.getOfflinePlayer(appliedplayers.get(outnumber)).getName() + "§lは出目を §e§lピッタリ §f§l予想しました！");
                                                            }
                                                        }
                                                    }
                                                    if (appliedplayers.keySet().contains(outnumber + 1))
                                                    {
                                                        for (Player player : Bukkit.getOnlinePlayers())
                                                        {
                                                            if (!mlotdissableplayers.contains(player.getUniqueId()))
                                                            {
                                                                player.sendMessage("§l[§e§lMan10DiceLottery§f§l]§r" + Bukkit.getOfflinePlayer(appliedplayers.get(outnumber + 1)).getName() + "§lは出目を §e§l1つ多く §f§l予想しました！");
                                                            }
                                                        }
                                                    }
                                                    if (appliedplayers.keySet().contains(outnumber - 1))
                                                    {
                                                        for (Player player : Bukkit.getOnlinePlayers())
                                                        {
                                                            if (!mlotdissableplayers.contains(player.getUniqueId()))
                                                            {
                                                                player.sendMessage("§l[§e§lMan10DiceLottery§f§l]§r" + Bukkit.getOfflinePlayer(appliedplayers.get(outnumber - 1)).getName() + "§lは出目を §e§l1つ少なく §f§l予想しました！");
                                                            }
                                                        }
                                                    }
                                                    appliedplayers.clear();
                                                    mlotoperation = false;
                                                }
                                            }
                                        }, dicedelay * 20L + 60L);
                                        return true;
                                    }
                                    else
                                    {
                                        sender.sendMessage("§l[§e§lMan10DiceLottery§f§l]§r§c§l時間は1以上の整数にしてください");
                                        return true;
                                    }
                                }
                                sender.sendMessage("§l[§e§lMan10DiceLottery§f§l]§r§c§lダイスの面数は1以上の整数にしてください");
                                return true;
                            }
                            else
                            {
                                sender.sendMessage("§l[§e§lMan10DiceLottery§f§l]§r§c§lゲームが進行中です");
                            }
                        }
                        else
                        {
                            sender.sendMessage("§l[§e§lMan10DiceLottery§f§l] §7/mlot hide : §l非表示にします");
                            sender.sendMessage("§l[§e§lMan10DiceLottery§f§l] §7/mlot show : §l表示します");
                            sender.sendMessage("§l[§e§lMan10DiceLottery§f§l] §7/mlot [数字] : §l抽選に応募します(ゲーム中のみ)");
                            return true;
                        }
                    }

                    default:
                    {
                        if (sender.hasPermission("mlot.op"))
                        {
                            sender.sendMessage("§l[§e§lMan10DiceLottery§f§l] §7/mlot [ダイスの面数] [時間(秒)] : §lゲームを開始します");
                            sender.sendMessage("§l[§e§lMan10DiceLottery§f§l] §7/mlot hide : §l非表示にします");
                            sender.sendMessage("§l[§e§lMan10DiceLottery§f§l] §7/mlot show : §l表示します");
                            sender.sendMessage("§l[§e§lMan10DiceLottery§f§l] §7/mlot [数字] : §l抽選に応募します(ゲーム中のみ)");
                            return true;
                        }
                        else
                        {
                            sender.sendMessage("§l[§e§lMan10DiceLottery§f§l] §7/mlot hide : §l非表示にします");
                            sender.sendMessage("§l[§e§lMan10DiceLottery§f§l] §7/mlot show : §l表示します");
                            sender.sendMessage("§l[§e§lMan10DiceLottery§f§l] §7/mlot [数字] : §l抽選に応募します(ゲーム中のみ)");
                            return true;
                        }
                    }
                }
            }
            else
            {
                sender.sendMessage("§c[Man10DiceLottery]You don't have permission!");
                return true;
            }
        }
        return true;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

