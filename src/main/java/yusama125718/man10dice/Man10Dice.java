package yusama125718.man10dice;

import java.awt.*;
import java.util.*;
import java.util.List;

import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import static java.lang.Integer.getInteger;
import static java.lang.Integer.parseInt;
import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.event.ClickEvent.suggestCommand;

public final class Man10Dice extends JavaPlugin
{
    boolean operation = false;
    boolean cooltime = false;
    public List<UUID> dissableplayers=new ArrayList<>();
    public List<UUID> mlotdissableplayers=new ArrayList<>();
    boolean mlotoperation = false;
    boolean activegame = false;
    int mlotdicestakes;
    int luckynumber;
    Player owner;

    HashMap<Integer,UUID> appliedplayers = new HashMap<>();
    boolean ondice;
    boolean onlottery;
    int counttime;
    String mlotowner;
    int mlotdice;
    int ct;
    private yusama125718.man10dice.Man10Dice Man10Dice;

    @Override
    public void onEnable()
    {
        // Plugin startup logic
        this.Man10Dice = this;
        saveDefaultConfig();
        ondice = Man10Dice.getConfig().getBoolean("OnDice");
        onlottery = Man10Dice.getConfig().getBoolean("OnLottery");
        ct = Man10Dice.getConfig().getInt("ct");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (label.equalsIgnoreCase("mdice"))
        {
            if (!sender.hasPermission("mdice.player"))
            {
                sender.sendMessage("§c[Man10Dice]You don't have permission!");
                return true;
            }
            switch (args.length)
            {
                case 1:
                {
                    if (sender.hasPermission("mdice.op"))
                    {
                        if ((args[0].equals("on")))
                        {
                            ondice = true;
                            sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§7§lONにしました");
                            return true;
                        }
                        if ((args[0].equals("off")))
                        {
                            ondice = false;
                            sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§7§lOFFにしました");
                            return true;
                        }
                    }
                    if (!ondice)
                    {
                        sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§7§l現在Man10DiceはOFFです");
                        return true;
                    }
                    if (sender instanceof Player)
                    {
                        Player playerid=(Player)sender;

                        if(args[0].length()>=10)
                        {
                            sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§c§lダイスの面数は10億以上に設定できません");
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
                                sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§7§l非表示にします");

                                return true;
                            }
                        }
                        if ((args[0].equals("show")))
                        {
                            if (dissableplayers.contains(playerid.getUniqueId()))
                            {
                                dissableplayers.remove(playerid.getUniqueId());
                                sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§7§l表示します");
                                return true;
                            }
                            else
                            {
                                return true;
                            }
                        }
                    }

                    if (operation)
                    {
                        sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§c§l現在実行中です");
                        return true;
                    }else if (cooltime){
                        sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§c§l現在クールタイムです");
                        return true;
                    }
                    boolean isNumeric = args[0].matches("-?\\d+");
                    if (!isNumeric)
                    {
                        sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§c§lダイスの面数は1以上の整数にしてください");
                        return true;
                    }
                    if (args[0].length()>=10)
                    {
                        sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§c§lダイスの面数は10億以上に設定できません");
                        return true;
                    }
                    int dicestakes = parseInt(args[0]);
                    if (dicestakes <= 1)
                    {
                        sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§c§lダイスの面数は1以上の整数にしてください");
                        return true;
                    }
                    operation = true;
                    for (Player player:Bukkit.getOnlinePlayers())
                    {
                        if (!dissableplayers.contains(player.getUniqueId()))
                        {
                            player.sendMessage("§l[§e§lMan10Dice§f§l]§r" + sender.getName() + "§lはダイスを振っています...");
                        }
                    }

                    Bukkit.getScheduler().runTaskLater(this, new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            Random dicerondom = new Random();
                            int outnumber = dicerondom.nextInt(dicestakes) + 1;
                            for (Player player:Bukkit.getOnlinePlayers())
                            {
                                if (!dissableplayers.contains(player.getUniqueId()))
                                {
                                    player.sendMessage("§l[§e§lMan10Dice§f§l]§r" + sender.getName() + "§lは" + dicestakes + "面ダイスを振って §e§l" + outnumber + "§r§l を出しました！");
                                }
                            }
                            cooltime = true;
                            operation = false;
                        }
                    }, 60);
                    Bukkit.getScheduler().runTaskLater(this, new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            cooltime = false;
                        }
                    }, ct * 20L + 60);
                    return true;
                }

                case 2:
                {
                    if (!ondice)
                    {
                        sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§7§l現在Man10DiceはOFFです");
                        return true;
                    }
                    int maxdice = Man10Dice.getConfig().getInt("mdice.maxdice");
                    if (!ondice)
                    {
                        sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§7§l現在Man10DiceはOFFです");
                        return true;
                    }
                    if (operation)
                    {
                        sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§c§l現在実行中です");
                        return true;
                    }else if (cooltime){
                        sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§c§l現在クールタイムです");
                        return true;
                    }
                    boolean isNumeric = args[0].matches("-?\\d+");
                    if (!isNumeric)
                    {
                        sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§c§lダイスの面数は1以上の整数にしてください");
                        return true;
                    }
                    boolean isNumeric2 = args[1].matches("-?\\d+");
                    if (!isNumeric2)
                    {
                        sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§c§lダイスの面数は1以上の整数にしてください");
                        return true;
                    }
                    if (args[0].length()>=10||args[1].length()>=10)
                    {
                        sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§c§lダイスの面数と数は10億以上に設定できません");
                        return true;
                    }
                    int dicestakes = parseInt(args[0]);
                    if (dicestakes < 1)
                    {
                        sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§c§lダイスの面数は1以上の整数にしてください");
                        return true;
                    }
                    int numberofdice = parseInt(args[1]);
                    if (numberofdice < 1)
                    {
                        sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§c§lダイスの数は1以上12以下の整数にしてください");
                        return true;
                    }
                    if (numberofdice > maxdice)
                    {
                        sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§c§lダイスの数は1以上"+maxdice+"以下の整数にしてください");
                        return true;
                    }

                    operation = true;
                    for (Player player:Bukkit.getOnlinePlayers())
                    {
                        if (!dissableplayers.contains(player.getUniqueId()))
                        {
                            player.sendMessage("§l[§e§lMan10Dice§f§l]§r" + sender.getName() + "§lはダイスを" + numberofdice + "個振っています...");
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
                                int outnumber1 = dicerondom1.nextInt(dicestakes) + 1;
                                for (Player player:Bukkit.getOnlinePlayers())
                                {
                                    if (!dissableplayers.contains(player.getUniqueId()))
                                    {
                                        player.sendMessage("§l[§e§lMan10Dice§f§l]§r" + sender.getName() + "§lは" + dicestakes + "面ダイスを振って §e§l" + outnumber1 + "§r§l を出しました！");
                                    }
                                }
                            }
                            cooltime = true;
                            operation = false;
                        }
                    }, 60);
                    Bukkit.getScheduler().runTaskLater(this, new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            cooltime = false;
                        }
                    }, ct * 20L + 60);
                    return true;
                }

                default:
                {
                    sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§7§l/mdice [出目の数] [さいころの数]");
                    if (sender.hasPermission("mdice.op"))
                    {
                        sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§7§l/mdice on : Man10DiceをONにします");
                        sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§7§l/mdice off : Man10DiceをOFFにします");
                    }
                    return true;
                }
            }
        }

        if (label.equalsIgnoreCase("mlot"))
        {
            if (!(sender.hasPermission("mlot.player")))
            {
                sender.sendMessage("§c[Man10DiceLottery]You don't have permission!");
                return true;
            }
            switch (args.length)
            {
                case 1:
                {
                    if (sender.hasPermission("mlot.op"))
                    {
                        if ((args[0].equals("on")))
                        {
                            ondice = true;
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§r§7§lONにしました");
                            return true;
                        }
                        if ((args[0].equals("off")))
                        {
                            ondice = false;
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§7§lOFFにしました");
                            return true;
                        }
                    }
                    if (!onlottery)
                    {
                        sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§7§l現在Man10DiceLotteryはOFFです");
                        return true;
                    }
                    if (!(sender instanceof Player))
                    {
                        return true;
                    }
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
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§7§l非表示にします");

                            return true;
                        }
                    }

                    if (args[0].equals("show"))
                    {
                        if (mlotdissableplayers.contains(mlotplayerid.getUniqueId()))
                        {
                            mlotdissableplayers.remove(mlotplayerid.getUniqueId());
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§7§l表示します");

                            return true;
                        }
                        else
                        {
                            return true;
                        }
                    }
                    if (!(args[0].length() <= 10))
                    {
                        sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§c§l応募する数字は10億以上に設定できません");
                        return true;
                    }
                    boolean isNumeric = args[0].matches("-?\\d+");
                    if (isNumeric)
                    {
                        if (!activegame)
                        {
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§c§l現在応募は受け付けていません");
                            return true;
                        }
                        luckynumber = parseInt(args[0]);
                        if (mlotdicestakes < luckynumber)
                        {
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§c§l" + mlotdicestakes + "以下の整数にしてください");
                            return true;
                        }
                        if (1 > luckynumber)
                        {
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§c§l1以上の整数にしてください");
                            return true;
                        }

                        if (appliedplayers.values().contains(mlotplayerid.getUniqueId()))
                        {
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§c§lあなたはすでに応募しています");
                            return true;
                        }
                        if (appliedplayers.keySet().contains(luckynumber))
                        {
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§c§lその数字はすでに使われています");
                            return true;
                        }
                        appliedplayers.put(luckynumber,mlotplayerid.getUniqueId());
                        sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§e§l応募しました！");
                        owner.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§c§l"+ Objects.requireNonNull(((Player) sender).getPlayer()).getName()+"§e§lが"+luckynumber+"を応募しました");
                        return true;
                    }
                    else
                    {
                        if (sender.hasPermission("mlot.op"))
                        {
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f §7/mlot [ダイスの面数] [時間(秒)] : §lゲームを開始します");
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f §7/mlot hide : §l非表示にします");
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f §7/mlot show : §l表示します");
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f §7/mlot [数字] : §l抽選に応募します(ゲーム中のみ)");
                            return true;
                        }
                        else
                        {
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f §7/mlot hide : §l非表示にします");
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f §7/mlot show : §l表示します");
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f §7/mlot [数字] : §l抽選に応募します(ゲーム中のみ)");
                            return true;
                        }
                    }
                }

                case 2:
                {
                    if (!onlottery)
                    {
                        sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§7§l現在Man10DiceLotteryはOFFです");
                        return true;
                    }
                    if (sender.hasPermission("mlot.op"))
                    {
                        if (args[0].length() >= 10)
                        {
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§c§lダイスの面数は10億以上に設定できません");
                            return true;
                        }
                        if (args[1].length() >= 10)
                        {
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§c§l時間は10億秒以上に設定できません");
                            return true;
                        }
                        if (mlotoperation)
                        {
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§c§lゲームが進行中です");
                            return true;
                        }
                        boolean isNumeric = args[0].matches("-?\\d+");
                        if (!isNumeric)
                        {
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§c§lダイスの面数は1以上の整数にしてください");
                            return true;
                        }
                        boolean isNumeric1 = args[1].matches("-?\\d+");
                        if (!isNumeric1)
                        {
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§c§l時間は1以上の整数にしてください");
                            return true;
                        }
                        int maxtime = Man10Dice.getConfig().getInt("mlot.maxtime");
                        int dicedelay = parseInt(args[1]);
                        if (dicedelay > maxtime)
                        {
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§c§l時間は"+maxtime+"以下の整数にしてください");
                            return true;
                        }
                        mlotdicestakes = parseInt(args[0]);
                        mlotoperation = true;
                        activegame = true;
                        owner = ((Player) sender).getPlayer();
                        for (Player player : Bukkit.getOnlinePlayers())
                        {
                            if (!mlotdissableplayers.contains(player.getUniqueId()))
                            {
                                player.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§b§l" + sender.getName() + "§lが§e§l" + args[0] + "D§fを§l開始しました！ §e/mlot [数字] §r§lで§c§l参加しましょう！");
                                player.sendMessage(text("§e§l[ここをクリックで自動入力する]").clickEvent(suggestCommand("/mlot ")));
                            }
                        }
                        int outnumber;

                        Random dicerondom = new Random();
                        outnumber = dicerondom.nextInt(mlotdicestakes) + 1;

                        mlotowner = sender.getName();
                        mlotdice = parseInt(args[0]);
                        counttime = dicedelay;
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
                                        player.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§lダイスを振っています...");
                                    }
                                }
                            }
                        }, dicedelay*20L);
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
                                            player.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r" + sender.getName() + "§lは" + mlotdicestakes + "面ダイスを振って §e§l" + outnumber + "§r§l を出しました！");
                                        }
                                    }
                                    int winner = 0;
                                    if (appliedplayers.keySet().contains(outnumber))
                                    {
                                        for (Player player : Bukkit.getOnlinePlayers())
                                        {
                                            if (!mlotdissableplayers.contains(player.getUniqueId()))
                                            {
                                                player.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§c§l" + Bukkit.getOfflinePlayer(appliedplayers.get(outnumber)).getName() + "§lは出目を §e§lピッタリ §f§l予想しました！");
                                            }
                                        }
                                        winner++;
                                    }
                                    if (appliedplayers.keySet().contains(outnumber + 1))
                                    {
                                        for (Player player : Bukkit.getOnlinePlayers())
                                        {
                                            if (!mlotdissableplayers.contains(player.getUniqueId()))
                                            {
                                                player.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§c§l" + Bukkit.getOfflinePlayer(appliedplayers.get(outnumber + 1)).getName() + "§lは出目を §e§l1つ多く §f§l予想しました！");
                                            }
                                        }
                                        winner++;
                                    }
                                    if (appliedplayers.keySet().contains(outnumber - 1))
                                    {
                                        for (Player player : Bukkit.getOnlinePlayers())
                                        {
                                            if (!mlotdissableplayers.contains(player.getUniqueId()))
                                            {
                                                player.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§c§l" + Bukkit.getOfflinePlayer(appliedplayers.get(outnumber - 1)).getName() + "§lは出目を §e§l1つ少なく §f§l予想しました！");
                                            }
                                        }
                                        winner++;
                                    }
                                    if (winner==0)
                                    {
                                        for (Player player : Bukkit.getOnlinePlayers())
                                        {
                                            if (!mlotdissableplayers.contains(player.getUniqueId()))
                                            {
                                                player.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§e§l当選者はいませんでした");
                                            }
                                        }
                                    }
                                    appliedplayers.clear();
                                    mlotoperation = false;
                                }
                            }
                        }, dicedelay*20L+60L);
                        return true;
                    }
                    else
                    {
                        sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f §7/mlot hide : §l非表示にします");
                        sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f §7/mlot show : §l表示します");
                        sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f §7/mlot [数字] : §l抽選に応募します(ゲーム中のみ)");
                        return true;
                    }
                }

                default:
                {
                    sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f §7/mlot hide : §l非表示にします");
                    sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f §7/mlot show : §l表示します");
                    sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f §7/mlot [数字] : §l抽選に応募します(ゲーム中のみ)");
                    if (sender.hasPermission("mlot.op"))
                    {
                        sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f §7/mlot [ダイスの面数] [時間(秒)] : §lゲームを開始します");
                        sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§7§l/mdice on : Man10DiceをONにします");
                        sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§7§l/mdice off : Man10DiceをOFFにします");
                    }
                    return true;
                }
            }
        }
        return true;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command command, String alias, String[] args)
    {
        if(command.getName().equalsIgnoreCase("mdice"))
        {
            if (!ondice)
            {
                return  null;
            }
            if (args.length == 1)
            {
                if (args[0].length() == 0)
                {
                    return Collections.singletonList("[面の数]");
                }
            }
            if (args.length == 2)
            {
                if (args[1].length() == 0)
                {
                    return Collections.singletonList("[ダイスの数]");
                }
            }
        }
        if(command.getName().equalsIgnoreCase("mlot"))
        {
            if (!onlottery)
            {
                return  null;
            }
            if (activegame)
            {
                if (args.length == 1)
                {
                    if (args[0].length() == 0)
                    {
                        return Collections.singletonList("[応募する数字]");
                    }
                }
            }
            else if (sender.hasPermission("mlot.op"))
            {
                if (args.length == 1)
                {
                    if (args[0].length() == 0)
                    {
                        return Collections.singletonList("[面の数]");
                    }
                }
                if (args.length == 2)
                {
                    if (args[1].length() == 0)
                    {
                        return Collections.singletonList("[時間(秒)]");
                    }
                }
            }
        }
        return null;
    }

}
