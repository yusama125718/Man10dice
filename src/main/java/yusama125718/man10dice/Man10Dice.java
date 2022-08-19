package yusama125718.man10dice;

import java.util.*;
import java.util.List;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Man10Dice extends JavaPlugin implements Listener, CommandExecutor, TabCompleter {
    public static boolean operation = false;
    public static List<UUID> dissableplayers=new ArrayList<>();
    public static List<UUID> mlotdissableplayers=new ArrayList<>();
    public static boolean mlotoperation = false;
    public static boolean activegame = false;
    public static Player owner;
    public static int mlottime;
    public static int mlotstackes;
    public static HashMap<Integer,UUID> appliedplayers = new HashMap<>();
    public static List<Integer> remaining = new ArrayList<>();
    public static boolean ondice;
    public static boolean onlottery;
    public static int ct;
    public static yusama125718.man10dice.Man10Dice Man10Dice;
    public static int maxdice;
    public static int maxstakes;
    public static int minstackes;
    public static int dice;
    public static Player player;
    public static int maxtime;

    @Override
    public void onEnable()
    {
        getCommand("mdice").setExecutor(new Command());
        getCommand("mlot").setExecutor(new Command());
        Man10Dice = this;
        saveDefaultConfig();
        ondice = Man10Dice.getConfig().getBoolean("OnDice");
        onlottery = Man10Dice.getConfig().getBoolean("OnLottery");
        ct = Man10Dice.getConfig().getInt("ct");
        maxdice = Man10Dice.getConfig().getInt("maxdice");
        maxtime = Man10Dice.getConfig().getInt("maxtime");
    }
}

