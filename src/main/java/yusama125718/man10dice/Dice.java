package yusama125718.man10dice;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Random;

import static yusama125718.man10dice.Man10Dice.dissableplayers;
import static yusama125718.man10dice.Man10Dice.*;

public class Dice extends Thread{
    @Override
    public void run() {
        operation = true;
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
        if (minstackes == 1 && dice == 1){
            int outnumber = dicerondom.nextInt(maxstakes) + 1;
            for (Player player:Bukkit.getOnlinePlayers())
            {
                if (!dissableplayers.contains(player.getUniqueId()))
                {
                    player.sendMessage("§l[§e§lMan10Dice§f§l]§r" + dicer.getName() + "§lは" + maxstakes + "面ダイスを振って §e§l" + outnumber + "§r§l を出しました！");
                }
            }
        }else if (minstackes != 1 && dice == 1){
            int outnumber = dicerondom.nextInt(maxstakes - minstackes + 1) + minstackes;
            for (Player player:Bukkit.getOnlinePlayers())
            {
                if (!dissableplayers.contains(player.getUniqueId()))
                {
                    player.sendMessage("§l[§e§lMan10Dice§f§l]§r" + dicer.getName() + "§lは"+ minstackes + "以上" + maxstakes + "以下ダイスを振って §e§l" + outnumber + "§r§l を出しました！");
                }
            }
        }else if (minstackes == 1){
            for (int i = 0 ; i < dice ; i++) {
                int outnumber = dicerondom.nextInt(maxstakes) + 1;
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (!dissableplayers.contains(player.getUniqueId())) {
                        player.sendMessage("§l[§e§lMan10Dice§f§l]§r" + dicer.getName() + "§lは" + maxstakes + "面ダイスを振って §e§l" + outnumber + "§r§l を出しました！");
                    }
                }
            }
        }else{
            for (int i = 0 ; i < dice ; i++) {
                int outnumber = dicerondom.nextInt(maxstakes - minstackes + 1) + minstackes;
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (!dissableplayers.contains(player.getUniqueId())) {
                        player.sendMessage("§l[§e§lMan10Dice§f§l]§r" + dicer.getName() + "§lは" + minstackes + "以上" + maxstakes + "以下ダイスを振って §e§l" + outnumber + "§r§l を出しました！");
                    }
                }
            }
        }
        operation = false;
    }
}
