package yusama125718_209282ihcuobust.man10manchiro;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import static yusama125718_209282ihcuobust.man10manchiro.Game.*;
import static yusama125718_209282ihcuobust.man10manchiro.Man10Manchiro.*;

public class Finish extends Thread
{
    @Override
    public void run()
    {
        for (Player player: Bukkit.getOnlinePlayers())
        {
            if (!disableplayers.contains(player.getUniqueId()))
            {
                player.sendMessage("§l[§e§lManchiro§f§l]§r" + Bukkit.getOfflinePlayer(parentname).getName() + "§lの部屋が終了しました");
            }
        }
        operation = false;
        sitperson = 0;
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        MySQLManager mysql = new MySQLManager(manchiro,"manchiro");
        mysql.execute("insert into mcr_data(endtime,betvalue,tax,playercount,parent,parentuuid,parentyaku,parentwin)values('"+dateFormat.format(date)+"','"+betvalue+"','"+taxprice+"','"+ playerperson +"','"+Bukkit.getOfflinePlayer(parentname).getName()+"','"+parentname+ "','"+parentyaku+"','"+parentprice+"');");
        for (int i = 0;i<sitperson;i++)
        {
            mysql.execute("insert into mcr_data(child"+i+",child"+i+"uuid,child"+i+"yaku,child"+i+"win)values('"+Bukkit.getOfflinePlayer(childplayer.get(i))+"','"+childplayer.get(i)+"','"+ childyaku[i] +"','"+ childprice[i] +"');");
        }
        parentname = null;
        childplayer.clear();
        sitperson = 0;
    }
}
