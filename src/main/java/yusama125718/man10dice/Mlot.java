package yusama125718.man10dice;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Random;

import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.event.ClickEvent.runCommand;
import static net.kyori.adventure.text.event.ClickEvent.suggestCommand;
import static yusama125718.man10dice.Man10Dice.*;

public class Mlot extends Thread{
    @Override
    public void run() {
        try{
            sleep(mlottime % 20 * 1000);
        }catch (InterruptedException e) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!dissableplayers.contains(player.getUniqueId())) {
                    player.sendMessage("§l[§e§lMan10Dice§f§l]§r(エラー)ダイスが行方不明になりました...");
                }
            }
            e.printStackTrace();
            return;
        }
        mlottime -= mlottime % 20;
        for (int i = 0;i < mlottime / 20;i++){
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!mlotdissableplayers.contains(player.getUniqueId())) {
                    player.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§b§l" + owner.getName() + "§lが§e§l" + mlotstackes + "D§fを§l開始しました！ §e/mlot [数字] §r§lで§c§l参加しましょう！");
                    player.sendMessage(text("§e§l[ここをクリックで自動入力する]").clickEvent(suggestCommand("/mlot ")));
                    player.sendMessage(text("§e§l[ここをクリックでランダム応募する]").clickEvent(runCommand("/mlot random")));
                }
            }
            try{
                sleep(20000);
            }catch (InterruptedException e) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (!dissableplayers.contains(player.getUniqueId())) {
                        player.sendMessage("§l[§e§lMan10Dice§f§l]§r(エラー)ダイスが行方不明になりました...");
                    }
                }
                e.printStackTrace();
                return;
            }
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!mlotdissableplayers.contains(player.getUniqueId())) {
                player.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§lダイスを振っています...");
            }
        }
        try{
            sleep(3000);
        }catch (InterruptedException e) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!dissableplayers.contains(player.getUniqueId())) {
                    player.sendMessage("§l[§e§lMan10Dice§f§l]§r(エラー)ダイスが行方不明になりました...");
                }
            }
            e.printStackTrace();
            return;
        }
        Random dicerondom = new Random();
        int outnumber = dicerondom.nextInt(mlotstackes) + 1;
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!mlotdissableplayers.contains(player.getUniqueId())) {
                player.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r" + owner.getName() + "§lは" + mlotstackes + "面ダイスを振って §e§l" + outnumber + "§r§l を出しました！");
            }
        }
        int winner = 0;
        if (appliedplayers.containsKey(outnumber)) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!mlotdissableplayers.contains(player.getUniqueId())) {
                    player.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§c§l" + Bukkit.getOfflinePlayer(appliedplayers.get(outnumber)).getName() + "§lは出目を §e§lピッタリ §f§l予想しました！");
                }
            }
            winner++;
        }
        if (appliedplayers.containsKey(outnumber + 1)) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!mlotdissableplayers.contains(player.getUniqueId())) {
                    player.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§c§l" + Bukkit.getOfflinePlayer(appliedplayers.get(outnumber + 1)).getName() + "§lは出目を §e§l1つ多く §f§l予想しました！");
                }
            }
            winner++;
        }
        if (appliedplayers.containsKey(outnumber - 1)) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!mlotdissableplayers.contains(player.getUniqueId())) {
                    player.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§c§l" + Bukkit.getOfflinePlayer(appliedplayers.get(outnumber - 1)).getName() + "§lは出目を §e§l1つ少なく §f§l予想しました！");
                }
            }
            winner++;
        }
        if (winner == 0) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!mlotdissableplayers.contains(player.getUniqueId())) {
                    player.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§e§l当選者はいませんでした");
                }
            }
        }
        appliedplayers.clear();
        mlotoperation = false;
    }
}
