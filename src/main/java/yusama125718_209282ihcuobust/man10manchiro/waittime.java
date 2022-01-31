package yusama125718_209282ihcuobust.man10manchiro;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static yusama125718_209282ihcuobust.man10manchiro.Man10Manchiro.*;

public class waittime extends Thread
{
    @Override
    public void run()
    {
        loop: for (int i = 0; i < 6; i++)
        {
            for (int j = 0; j < 20; j++)
            {
                if (playerperson == sitperson)
                {
                    activegame = false;
                    break loop;
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
            if (!(120 - (i +1) * 20 == 0))
            {
                for (Player player: Bukkit.getOnlinePlayers())
                {
                    if (!disableplayers.contains(player.getUniqueId()))
                    {
                        player.sendMessage("§l[§e§lManchiro§f§l]§r" + Bukkit.getOfflinePlayer(parentname).getName() + "§lが一人あたり" + betvalue + "§l円で§e§lマンチロ§f§lを子" + playerperson + "人で募集中！ /mcr join で参加しましょう！§7残り" + (120 - (i +1) * 20) + "秒");
                    }
                }
            }
        }
        MySQLManager mysql = new MySQLManager(manchiro,"manchiro");
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (playerperson == sitperson)
        {
            Game gamethread = new Game();
            gamethread.start();
            mysql.execute("insert into mcr_data(starttime,betvalue,playercount,parent,parentuuid)values('"+dateFormat.format(date)+"','"+betvalue+"','"+sitperson+"','"+Bukkit.getOfflinePlayer(parentname).getName()+"','"+parentname+"');");
            for (int i = 0;i<sitperson;i++)
            {
                mysql.execute("insert into mcr_data(child"+i+",child"+i+"uuid)values('"+Bukkit.getOfflinePlayer(childplayer.get(i))+"','"+(childplayer.get(i))+"');");
            }
        }
        else
        {
            mysql.execute("insert into mcr_data(starttime,endtime,betvalue,playercount,parent,parentuuid)values('"+dateFormat.format(date)+"','"+dateFormat.format(date)+"','"+betvalue+"','"+playerperson+"','"+Bukkit.getOfflinePlayer(parentname).getName()+"','"+parentname+"');");
            for (int i = 0;i < sitperson;i++)
            {
                mysql.execute("insert into mcr_data(child"+i+",child"+i+"uuid)values('"+Bukkit.getOfflinePlayer(childplayer.get(i))+"','"+(childplayer.get(i))+"');");
            }
            operation = false;
            activegame = false;
            vaultapi.deposit(parentname,betvalue * playerperson);
            for (int i = 0;i < sitperson;i++)
            {
                vaultapi.deposit((childplayer.get(i)),betvalue);
            }
            for (Player player: Bukkit.getOnlinePlayers())
            {
                if (!disableplayers.contains(player.getUniqueId()))
                {
                    player.sendMessage("§l[§e§lManchiro§f§l]§r§f§l" + Bukkit.getOfflinePlayer(parentname).getName() + "の部屋は人が集まらなかったので解散しました");
                }
            }
            parentname = null;
            childplayer.clear();
            sitperson = 0;
        }
    }
}
