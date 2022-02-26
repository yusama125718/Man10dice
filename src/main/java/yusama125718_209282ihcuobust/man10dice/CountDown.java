package yusama125718_209282ihcuobust.man10dice;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.event.ClickEvent.suggestCommand;
import static yusama125718_209282ihcuobust.man10dice.Man10Dice.*;

public class CountDown extends Thread
{
    public void start()
    {
        int drawtime = 0;
        for (int i = 0;i*20<counttime;i++)
        {
            drawtime = drawtime+20;
        }
        drawtime = drawtime - 20;
        try
        {
            sleep((counttime-drawtime)* 1000L);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        counttime = drawtime;
        aaa: for (int i = 0;i*20<counttime;i++)
        {
            for (Player player : Bukkit.getOnlinePlayers())
            {
                if (!mlotdissableplayers.contains(player.getUniqueId()))
                {
                    player.sendMessage("§l[§d§lM§f§la§a§ln§f§l10§5§lDice§f§l]§f§r§b§l" + mlotowner + "§lが§e§l" + mlotdice + "D§fを§l開催中！ §e/mlot [数字] §r§lで§c§l参加しましょう！§e残り"+(counttime-i*20)+"秒");
                    player.sendMessage(text("§e§lここをクリックで自動入力する").clickEvent(suggestCommand("/mlot ")));
                }
            }
            try
            {
                sleep(20000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            if (i*20-counttime<0)
            {
                break aaa;
            }
        }
    }
}
