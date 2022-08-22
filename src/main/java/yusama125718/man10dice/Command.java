package yusama125718.man10dice;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.*;

import static java.lang.Integer.min;
import static java.lang.Integer.parseInt;
import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.event.ClickEvent.runCommand;
import static net.kyori.adventure.text.event.ClickEvent.suggestCommand;
import static yusama125718.man10dice.Man10Dice.*;

public class Command implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args)
    {
        if (!(sender instanceof Player)){
            sender.sendMessage("§c[Man10Dice] コンソールからは実行できません");
            return true;
        }
        if (label.equalsIgnoreCase("mdice")) {
            if (!sender.hasPermission("mdice.player")) {
                sender.sendMessage("§c[Man10Dice]You don't have permission!");
                return true;
            }
            switch (args.length) {
                case 1: {
                    if (sender.hasPermission("mdice.op")) {
                        if ((args[0].equals("on"))) {
                            ondice = true;
                            sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§7§lONにしました");
                            return true;
                        }
                        if ((args[0].equals("off"))) {
                            ondice = false;
                            sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§7§lOFFにしました");
                            return true;
                        }
                    }
                    if (!ondice) {
                        sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§7§l現在Man10DiceはOFFです");
                        return true;
                    }
                    Player playerid = (Player) sender;
                    if (args[0].equals("hide")) {
                        if (dissableplayers.contains(playerid.getUniqueId())) {
                            return true;
                        } else {
                            dissableplayers.add(playerid.getUniqueId());
                            sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§7§l非表示にします");

                            return true;
                        }
                    }
                    if ((args[0].equals("show"))) {
                        if (dissableplayers.contains(playerid.getUniqueId())) {
                            dissableplayers.remove(playerid.getUniqueId());
                            sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§7§l表示します");
                            return true;
                        } else {
                            return true;
                        }
                    }

                    if (args[0].length() >= 10) {
                        sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§c§l数値は10億以上に設定できません");
                        return true;
                    }
                    if (operation) {
                        sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§c§l現在実行中です");
                        return true;
                    }
                    boolean isNumeric = args[0].matches("-?\\d+");
                    if (!isNumeric) {
                        sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§c§lダイスの面数は1以上の整数にしてください");
                        return true;
                    }
                    int dicestakes = parseInt(args[0]);
                    if (dicestakes <= 1) {
                        sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§c§lダイスの面数は1以上の整数にしてください");
                        return true;
                    }
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (!dissableplayers.contains(player.getUniqueId())) {
                            player.sendMessage("§l[§e§lMan10Dice§f§l]§r" + sender.getName() + "§lはダイスを振っています...");
                        }
                    }
                    dicer = (Player) sender;
                    maxstakes = dicestakes;
                    minstackes = 1;
                    dice = 1;
                    Dice dice = new Dice();
                    dice.start();
                    return true;
                }

                case 2: {
                    if (!ondice) {
                        sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§7§l現在Man10DiceはOFFです");
                        return true;
                    }
                    if (operation) {
                        sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§c§l現在実行中です");
                        return true;
                    }
                    if (args[0].length() >= 10 || args[1].length() >= 10) {
                        sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§c§l数値は10億以上に設定できません");
                        return true;
                    }
                    boolean isNumeric = args[0].matches("-?\\d+");
                    if (!isNumeric) {
                        sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§c§lダイスの面数は1以上の整数にしてください");
                        return true;
                    }
                    boolean isNumeric2 = args[1].matches("-?\\d+");
                    if (!isNumeric2) {
                        sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§c§lダイスの面数は1以上の整数にしてください");
                        return true;
                    }
                    int dicestakes = parseInt(args[0]);
                    if (dicestakes < 1) {
                        sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§c§lダイスの面数は1以上の整数にしてください");
                        return true;
                    }
                    int numberofdice = parseInt(args[1]);
                    if (numberofdice < 1) {
                        sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§c§lダイスの数は1以上12以下の整数にしてください");
                        return true;
                    }
                    if (numberofdice > maxdice) {
                        sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§c§lダイスの数は1以上" + maxdice + "以下の整数にしてください");
                        return true;
                    }

                    operation = true;
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (!dissableplayers.contains(player.getUniqueId())) {
                            player.sendMessage("§l[§e§lMan10Dice§f§l]§r" + sender.getName() + "§lはダイスを" + numberofdice + "個振っています...");
                        }
                    }
                    dicer = (Player) sender;
                    maxstakes = dicestakes;
                    minstackes = 1;
                    dice = numberofdice;
                    Dice dice = new Dice();
                    dice.start();
                    return true;
                }

                case 3: {
                    if (!ondice) {
                        sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§7§l現在Man10DiceはOFFです");
                        return true;
                    }
                    if (operation) {
                        sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§c§l現在実行中です");
                        return true;
                    }
                    if (args[0].length() >= 10 || args[1].length() >= 10 || args[2].length() >= 10) {
                        sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§c§l数値は10億以上に設定できません");
                        return true;
                    }
                    boolean isNumeric = args[0].matches("-?\\d+");
                    if (!isNumeric) {
                        sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§c§lダイスの面数は1以上の整数にしてください");
                        return true;
                    }
                    boolean isNumeric2 = args[1].matches("-?\\d+");
                    if (!isNumeric2) {
                        sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§c§lダイスの面数の最小数は1以上の整数にしてください");
                        return true;
                    }
                    boolean isNumeric3 = args[2].matches("-?\\d+");
                    if (!isNumeric3) {
                        sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§c§lダイスの面数は1以上の整数にしてください");
                        return true;
                    }
                    int dicestakes = parseInt(args[0]);
                    if (dicestakes < 1) {
                        sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§c§lダイスの面数は1以上の整数にしてください");
                        return true;
                    }
                    int minstakes = parseInt(args[1]);
                    if (minstakes < 1 || minstakes >= dicestakes) {
                        sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§c§lダイスの面数の最小数は1以上" + dicestakes + "以下の整数にしてください");
                        return true;
                    }
                    int numberofdice = parseInt(args[2]);
                    if (1 > numberofdice) {
                        sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§c§lダイスの数は1以上"+ maxdice +"以下の整数にしてください");
                        return true;
                    }
                    if (numberofdice > maxdice) {
                        sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§c§lダイスの数は1以上" + maxdice + "以下の整数にしてください");
                        return true;
                    }

                    operation = true;
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (!dissableplayers.contains(player.getUniqueId())) {
                            player.sendMessage("§l[§e§lMan10Dice§f§l]§r" + sender.getName() + "§lはダイスを" + numberofdice + "個振っています...");
                        }
                    }
                    dicer = (Player) sender;
                    maxstakes = dicestakes;
                    minstackes = minstakes;
                    dice = numberofdice;
                    Dice dice = new Dice();
                    dice.start();
                    return true;
                }

                default: {
                    sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§7§l/mdice [出目の数] [さいころの数]");
                    sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§7§l/mdice [出目の数] [出目の最小数] [さいころの数]");
                    if (sender.hasPermission("mdice.op")) {
                        sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§7§l/mdice on : Man10DiceをONにします");
                        sender.sendMessage("§l[§e§lMan10Dice§f§l]§r§7§l/mdice off : Man10DiceをOFFにします");
                    }
                    return true;
                }
            }
        }

        if (label.equalsIgnoreCase("mlot")) {
            if (!(sender.hasPermission("mlot.player"))) {
                sender.sendMessage("§c[Man10DiceLottery]You don't have permission!");
                return true;
            }
            switch (args.length) {
                case 1: {
                    if (sender.hasPermission("mlot.op")) {
                        if ((args[0].equals("on"))) {
                            ondice = true;
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§r§7§lONにしました");
                            return true;
                        }
                        if ((args[0].equals("off"))) {
                            ondice = false;
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§7§lOFFにしました");
                            return true;
                        }
                    }
                    if (!onlottery) {
                        sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§7§l現在Man10DiceLotteryはOFFです");
                        return true;
                    }
                    Player mlotplayerid = (Player) sender;

                    if (args[0].equals("hide")) {
                        if (mlotdissableplayers.contains(mlotplayerid.getUniqueId())) {
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§7§lすでに非表示にしています");
                        } else {
                            mlotdissableplayers.add(mlotplayerid.getUniqueId());
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§7§l非表示にします");

                        }
                        return true;
                    }

                    if (args[0].equals("show")) {
                        if (mlotdissableplayers.contains(mlotplayerid.getUniqueId())) {
                            mlotdissableplayers.remove(mlotplayerid.getUniqueId());
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§7§l表示します");

                        } else {
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§7§lすでに表示しています");
                        }
                        return true;
                    }

                    if (args[0].equals("random")){
                        if (!activegame) {
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§c§l現在応募は受け付けていません");
                            return true;
                        }
                        if (appliedplayers.containsValue(mlotplayerid.getUniqueId())) {
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§c§lあなたはすでに応募しています");
                            return true;
                        }
                        if (appliedplayers.size() == mlotstackes){
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§r§r§c§l全ての数字が埋まっています！");
                            return true;
                        }
                        Random dicerondom = new Random();
                        int addrand = dicerondom.nextInt(remaining.size() - 1) + 1;
                        if (appliedplayers.containsKey(remaining.get(addrand))){
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§r§r§c§l追加に失敗しました。もう一度お試しください。");
                            return true;
                        }
                        appliedplayers.put(remaining.get(addrand), mlotplayerid.getUniqueId());
                        int add = remaining.get(addrand);
                        remaining.remove(addrand);
                        sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§e§l" + add + "で応募しました！");
                        owner.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§c§l" + Objects.requireNonNull(((Player) sender).getPlayer()).getName() + "§e§lが" + add + "を応募しました");
                        return true;
                    }

                    if (!(args[0].length() <= 10)) {
                        sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§c§l応募する数字は10億以上に設定できません");
                        return true;
                    }
                    boolean isNumeric = args[0].matches("-?\\d+");
                    if (isNumeric) {
                        if (!activegame) {
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§c§l現在応募は受け付けていません");
                            return true;
                        }
                        int luckynumber = parseInt(args[0]);
                        if (mlotstackes < luckynumber) {
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§c§l" + mlotstackes + "以下の整数にしてください");
                            return true;
                        }
                        if (1 > luckynumber) {
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§c§l1以上の整数にしてください");
                            return true;
                        }

                        if (appliedplayers.containsValue(mlotplayerid.getUniqueId())) {
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§c§lあなたはすでに応募しています");
                            return true;
                        }
                        if (appliedplayers.containsKey(luckynumber)) {
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§c§lその数字はすでに使われています");
                            return true;
                        }
                        appliedplayers.put(luckynumber, mlotplayerid.getUniqueId());
                        remaining.remove((Integer) luckynumber);
                        sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§e§l応募しました！");
                        owner.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§c§l" + Objects.requireNonNull(((Player) sender).getPlayer()).getName() + "§e§lが" + luckynumber + "を応募しました");
                        return true;
                    } else {
                        if (sender.hasPermission("mlot.op")) {
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f §7/mlot [ダイスの面数] [時間(秒)] : §lゲームを開始します");
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f §7/mlot hide : §l非表示にします");
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f §7/mlot show : §l表示します");
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f §7/mlot [数字] : §l抽選に応募します(ゲーム中のみ)");
                            return true;
                        } else {
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f §7/mlot hide : §l非表示にします");
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f §7/mlot show : §l表示します");
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f §7/mlot [数字] : §l抽選に応募します(ゲーム中のみ)");
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f §7/mlot random : §lランダムな数字で抽選に応募します(ゲーム中のみ)");
                            return true;
                        }
                    }
                }

                case 2: {
                    if (!onlottery) {
                        sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§7§l現在Man10DiceLotteryはOFFです");
                        return true;
                    }
                    if (sender.hasPermission("mlot.op")) {
                        if (args[0].length() >= 10) {
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§c§lダイスの面数は10億以上に設定できません");
                            return true;
                        }
                        if (args[1].length() >= 10) {
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§c§l時間は10億秒以上に設定できません");
                            return true;
                        }
                        if (mlotoperation) {
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§c§lゲームが進行中です");
                            return true;
                        }
                        boolean isNumeric = args[0].matches("-?\\d+");
                        if (!isNumeric) {
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§c§lダイスの面数は1以上の整数にしてください");
                            return true;
                        }
                        boolean isNumeric1 = args[1].matches("-?\\d+");
                        if (!isNumeric1) {
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§c§l時間は1以上の整数にしてください");
                            return true;
                        }
                        int dicedelay = parseInt(args[1]);
                        if (dicedelay > maxtime) {
                            sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§c§l時間は" + maxtime + "以下の整数にしてください");
                            return true;
                        }
                        int stacks = parseInt(args[0]);
                        mlotoperation = true;
                        activegame = true;
                        for (int i = 1;i <= stacks;i++) remaining.add(i);
                        owner = ((Player) sender).getPlayer();
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            if (!mlotdissableplayers.contains(player.getUniqueId())) {
                                player.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§b§l" + sender.getName() + "§lが§e§l" + args[0] + "D§fを§l開始しました！ §e/mlot [数字] §r§lで§c§l参加しましょう！");
                                player.sendMessage(text("§e§l[ここをクリックで自動入力する]").clickEvent(suggestCommand("/mlot ")));
                                player.sendMessage(text("§e§l[ここをクリックでランダム応募する]").clickEvent(runCommand("/mlot random")));
                            }
                        }
                        mlotstackes = stacks;
                        mlottime = dicedelay;
                        Mlot lot = new Mlot();
                        lot.start();
                        return true;
                    } else {
                        sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f §7/mlot hide : §l非表示にします");
                        sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f §7/mlot show : §l表示します");
                        sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f §7/mlot [数字] : §l抽選に応募します(ゲーム中のみ)");
                        sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f §7/mlot random : §lランダムな数字で抽選に応募します(ゲーム中のみ)");
                        return true;
                    }
                }

                default: {
                    sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f §7/mlot hide : §l非表示にします");
                    sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f §7/mlot show : §l表示します");
                    sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f §7/mlot [数字] : §l抽選に応募します(ゲーム中のみ)");
                    if (sender.hasPermission("mlot.op")) {
                        sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f §7/mlot [ダイスの面数] [時間(秒)] : §lゲームを開始します");
                        sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§7§l/mdice on : Man10DiceをONにします");
                        sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§7§l/mdice off : Man10DiceをOFFにします");
                        sender.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f §7/mlot random : §lランダムな数字で抽選に応募します(ゲーム中のみ)");
                    }
                    return true;
                }
            }
        }
        return true;
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
                    return  Arrays.asList("[ダイスの数]","[面の最低値]");
                }
            }
            if (args.length == 3)
            {
                if (args[2].length() == 0)
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
                        return Arrays.asList("[応募する数字]","random");
                    }
                    if ("random".startsWith(args[0])){
                        return Collections.singletonList("random");
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
