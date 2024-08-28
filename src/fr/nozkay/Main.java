package fr.nozkay;

import fr.nozkay.scenarios.Commands.Kp;
import fr.nozkay.scenarios.KoupaingsGame;
import fr.nozkay.ui.CompoMenu;
import fr.rolan.uhc.commands.CommandRegister;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static Main instance;
    private static KoupaingsGame game;

    private static CompoMenu compomenu;

    @Override
    public void onEnable() {
        instance = this;
        game = new KoupaingsGame();
        compomenu = new CompoMenu();
        compomenu.createCompo();
        System.out.println("Activation du Koupaings UHC");
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(compomenu,this);
        pm.registerEvents(game,this);
        CommandRegister.registerNewCommand(new Kp());
    }

    public static String prefix = "§5Koupaings UHC §8» §7";
    public static String cooldown(String pv){
        return (prefix + "§cVotre pouvoir " + pv + " n'est pas utilisable pour le moment");
    }
    public static String use(String pv){
        return (prefix + "Vous utilisez votre pouvoir " + pv);
    }
    public static String plloin(){
        return (prefix + "Le joueur visé est trop loin !");
    }
    public static String sang(){
        return (prefix + "Vous n'avez pas assez de sang pour réaliser ceci.");
    }

    public static Main getInstance() {
        return instance;
    }
    public static CompoMenu getCompoMenu(){return compomenu;}
    public static KoupaingsGame getGame() {
        return game;
    }
}
