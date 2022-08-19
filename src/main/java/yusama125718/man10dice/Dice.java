package yusama125718.man10dice;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Random;

import static java.lang.Thread.sleep;
import static yusama125718.man10dice.Man10Dice.dissableplayers;
import static yusama125718.man10dice.Man10Dice.*;

public class Dice {
    public void MdiceDice(Player p,Integer maxstakes,Integer minstakes,Integer dicecount) {
        operation = true;
        try{
            sleep(30000);
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
        if (minstakes == 1 && dicecount == 1){
            int outnumber = dicerondom.nextInt(maxstakes) + 1;
            for (Player player:Bukkit.getOnlinePlayers())
            {
                if (!dissableplayers.contains(player.getUniqueId()))
                {
                    player.sendMessage("§l[§e§lMan10Dice§f§l]§r" + p.getName() + "§lは" + maxstakes + "面ダイスを振って §e§l" + outnumber + "§r§l を出しました！");
                }
            }
        }else if (minstakes != 1 && dicecount == 1){
            int outnumber = dicerondom.nextInt(maxstakes - minstakes) + minstakes - 1;
            for (Player player:Bukkit.getOnlinePlayers())
            {
                if (!dissableplayers.contains(player.getUniqueId()))
                {
                    player.sendMessage("§l[§e§lMan10Dice§f§l]§r" + p.getName() + "§lは"+ minstakes + "面以上" + maxstakes + "面以下ダイスを振って §e§l" + outnumber + "§r§l を出しました！");
                }
            }
        }else if (minstakes == 1){
            for (int i = 0 ; i < dicecount ; i++) {
                int outnumber = dicerondom.nextInt(maxstakes) + 1;
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (!dissableplayers.contains(player.getUniqueId())) {
                        player.sendMessage("§l[§e§lMan10Dice§f§l]§r" + p.getName() + "§lは" + maxstakes + "面ダイスを振って §e§l" + outnumber + "§r§l を出しました！");
                    }
                }
            }
        }else{
            for (int i = 0 ; i < dicecount ; i++) {
                int outnumber = dicerondom.nextInt(maxstakes - minstakes) + minstakes - 1;
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (!dissableplayers.contains(player.getUniqueId())) {
                        player.sendMessage("§l[§e§lMan10Dice§f§l]§r" + p.getName() + "§lは" + maxstakes + "面以上" + minstakes + "面以下ダイスを振って §e§l" + outnumber + "§r§l を出しました！");
                    }
                }
            }
        }
        operation = false;
    }
}
