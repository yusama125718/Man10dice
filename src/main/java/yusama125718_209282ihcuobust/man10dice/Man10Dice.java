package yusama125718_209282ihcuobust.man10dice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

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

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("mdice").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(sender instanceof Player)
        {
            Player playerid=(Player)sender;

            if (sender.hasPermission("mdice.player"))
            {
                switch (args.length)
                {
                    case 1:
                        if(args[0].length()>=10)
                        {
                            sender.sendMessage("§e§l[Man10Dice]§r§c§lダイスの面数は10億以上に設定できません");
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

                    case 2:
                        if (args[0].length()>=10||args[1].length()>=10)
                        {
                            sender.sendMessage("§e§l[Man10Dice]§r§c§lダイスの面数は10億以上に設定できません");
                            return true;
                        }

                        if (!operation)
                        {
                            boolean isNumeric2 = args[1].matches("-?\\d+");
                            if (isNumeric2) {
                                int numberofdice = parseInt(args[1]);

                                if (numberofdice >= 1) {
                                    if (numberofdice <= 12) {
                                        boolean isNumeric3 = args[0].matches("-?\\d+");
                                        if (isNumeric3) {
                                            int dicestakes1 = parseInt(args[0]);
                                            if (dicestakes1 >= 1) {
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
                                                        for (int loopcount = 1; loopcount <= numberofdice; loopcount++) {
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

                                            } else {
                                                sender.sendMessage("§e§l[Man10Dice]§r§c§lダイスの面数は1以上の整数にしてください");
                                                return true;
                                            }
                                        }
                                    } else {
                                        sender.sendMessage("§e§l[Man10Dice]§r§c§lダイスの数は1以上12以下の整数にしてください");
                                        return true;
                                    }
                                } else {
                                    sender.sendMessage("§e§l[Man10Dice]§r§c§lダイスの数は1以上12以下の整数にしてください");
                                    return true;
                                }

                            } else {
                                sender.sendMessage("§e§l[Man10Dice]§r§c§lダイスの面数は1以上の整数にしてください");
                                return true;
                            }
                        }
                        else
                        {
                            sender.sendMessage("§e§l[Man10Dice]§r§c§l現在実行中です");

                            return true;
                        }

                    default:
                        sender.sendMessage("§e§l[Man10Dice]§r§7§l/mdice [出目の数] [さいころの数]");

                        return true;
                }
            }
            else
            {
                sender.sendMessage("§c[Man10Dice]You don't have permission!");

                return true;
            }
        }
        else //以下console,コマブロ用処理
        {
            switch (args.length) {
                case 1:
                    if(args[0].length()>=10)
                    {
                        sender.sendMessage("§e§l[Man10Dice]§r§c§lダイスの面数は10億以上に設定できません");
                        return true;
                    }

                    if (!operation)
                    {
                        boolean isNumeric = args[0].matches("-?\\d+");
                        if (isNumeric)
                        {
                            int dicestakes = parseInt(args[0]);
                            if (dicestakes >= 1) {
                                operation = true;
                                for (Player player:Bukkit.getOnlinePlayers())
                                {
                                    if (!dissableplayers.contains(player.getUniqueId()))
                                    {
                                        player.sendMessage("§e§l[Man10Dice]§r" + sender.getName() + "§lははダイスを振っています...");
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

                case 2:
                    if (args[0].length()>=10||args[1].length()>=10)
                    {
                        sender.sendMessage("§e§l[Man10Dice]§r§c§lダイスの面数は10億以上に設定できません");
                        return true;
                    }

                    if (!operation)
                    {
                        boolean isNumeric2 = args[1].matches("-?\\d+");
                        if (isNumeric2) {
                            int numberofdice = parseInt(args[1]);

                            if (numberofdice >= 1) {
                                if (numberofdice <= 12) {
                                    boolean isNumeric3 = args[0].matches("-?\\d+");
                                    if (isNumeric3) {
                                        int dicestakes1 = parseInt(args[0]);
                                        if (dicestakes1 >= 1) {
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
                                                    for (int loopcount = 1; loopcount <= numberofdice; loopcount++) {
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

                                        } else {
                                            sender.sendMessage("§e§l[Man10Dice]§r§c§lダイスの面数は1以上の整数にしてください");
                                            return true;
                                        }
                                    }
                                } else {
                                    sender.sendMessage("§e§l[Man10Dice]§r§c§lダイスの数は1以上12以下の整数にしてください");
                                    return true;
                                }
                            } else {
                                sender.sendMessage("§e§l[Man10Dice]§r§c§lダイスの数は1以上12以下の整数にしてください");
                                return true;
                            }

                        } else {
                            sender.sendMessage("§e§l[Man10Dice]§r§c§lダイスの面数は1以上の整数にしてください");
                            return true;
                        }
                    }
                    else
                    {
                        sender.sendMessage("§e§l[Man10Dice]§r§c§l現在実行中です");

                        return true;
                    }

                default:
                    sender.sendMessage("§e§l[Man10Dice]§r§7§l/mdice [出目の数] [さいころの数]");

                    return true;
            }
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

