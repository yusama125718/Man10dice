package tsubouchi_pl.tsubouchi_man10dice;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import static java.lang.Integer.parseInt;

public final class Tsubouchi_man10dice extends JavaPlugin 
{
    boolean operation;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("mdice").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(operation = false)
        {
            switch (args.length)
            {
                case 1:
                    boolean isNumeric =  args[0].matches("-?\\d+");
                    if(isNumeric)
                    {
                        int dicestakes = parseInt(args[0]);
                        if(dicestakes >= 1)
                        {
                            operation = true;
                            Bukkit.broadcast("ダイスを振っています...", Server.BROADCAST_CHANNEL_USERS);

                            Bukkit.getScheduler().runTaskLater(this, new Runnable() 
                            {
                                @Override
                                public void run() 
                                {
                                    Random dicerondom = new Random();
                                    int outnumber = dicerondom.nextInt(dicestakes) + 1;
                                    Bukkit.broadcast(sender.getName()+"は"+dicestakes+"面 ダイスを振って "+outnumber+" を出しました！", Server.BROADCAST_CHANNEL_USERS);
                                    operation =false;
                                }
                            },60);
                        }
                        else
                        {
                            sender.sendMessage("ダイスの数は1以上の整数にしてください");
                            return true;
                        }
                    }
                    else
                    {
                        sender.sendMessage("ダイスの数は1以上の整数にしてください");
                        return true;
                    }
    
                case 2:
                    boolean isNumeric2 =  args[1].matches("-?\\d+");
                    if(isNumeric2)
                    {
                        int numberofdice = parseInt(args[1]);
                        {
                            if (numberofdice >= 1) {
                            }
                            boolean isNumeric3 = args[0].matches("-?\\d+");
                            if (isNumeric3)
                            {
                                int dicestakes1 = parseInt(args[0]);
                                if (dicestakes1 >= 1)
                                {
                                    operation = true;
                                    Bukkit.broadcast("ダイスを振っています...", Server.BROADCAST_CHANNEL_USERS);

                                    Bukkit.getScheduler().runTaskLater(this, new Runnable() 
                                    {
                                        @Override
                                        public void run() 
                                        {
                                            for (int loopcount = 1; loopcount <= numberofdice; loopcount++)
                                            {
                                                Random dicerondom1 = new Random();
                                                int outnumber1 = dicerondom1.nextInt(dicestakes1) + 1;
                                                Bukkit.broadcast(sender.getName() + "は" + dicestakes1 + "面 ダイスを振って " + outnumber1 + " を出しました！", Server.BROADCAST_CHANNEL_USERS);
            
                                            }
                                            operation = false;
                                        }
                                    },60);

                                } else {
                                    sender.sendMessage("ダイスの数は1以上の整数にしてください");
                                    return true;
                                }
                            }
                            else
                            {
                                sender.sendMessage("ダイスの数は1以上の整数にしてください");
                                return true;
                            }
                        }
                    }
                    else
    
                    {
                        sender.sendMessage("ダイスの数は1以上の整数にしてください");
                        return true;
                    }
    
                default:
                    sender.sendMessage("/mdice [出目の数] [さいころの数]");
    
                    return true;
            }
        }
        else
        {
            sender.sendMessage("しばらくお待ちください");

            return true;
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
