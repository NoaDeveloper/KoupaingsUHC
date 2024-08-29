package fr.nozkay.scenarios.Commands;

import fr.nozkay.Main;
import fr.nozkay.scenarios.Enum.Camp;
import fr.nozkay.scenarios.Utils.UtilScenar;
import fr.nozkay.scenarios.manager.Roles;
import fr.nozkay.scenarios.manager.RolesImpl;
import fr.nozkay.scenarios.manager.roles.Gentils.Roar;
import fr.nozkay.scenarios.manager.roles.Gentils.Skyrouf;
import fr.nozkay.scenarios.manager.roles.Mechants.Black_Star;
import fr.nozkay.scenarios.manager.roles.Mechants.Lava_Fetzy;
import fr.rolan.api.annotations.commands.Command;
import fr.rolan.api.annotations.commands.CommandArgs;
import fr.rolan.api.utils.player.CCPlayer;
import fr.rolan.uhc.UHC;
import fr.rolan.uhc.manager.players.UHCPlayer;
import fr.rolan.uhc.manager.players.UHCPlayerManager;
import fr.rolan.uhc.util.player.EffectUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Kp {
    int bananeuse = 0;
    int drapall = 0;
    boolean drap = true;
    boolean marry = false;
    boolean married = true;
    public static boolean boost = true;
    public int mess = 0;
    public int pouf = 0;
    public boolean cdmalus= true;
    @Command(name = "kp", description = "Commandes pour tous le mode de jeu Koupaing", usage = "Usage: /kp ", aliases = {"koupaings","Kp"})
    public void onCompoCommand(CommandArgs args) {
        String[] arg = args.getArgs();
        if (arg[0].equalsIgnoreCase("revive")) {
            if (Bukkit.getPlayer(arg[1]) != null) {
                if (!Black_Star.res) {
                    Player pl = Bukkit.getPlayer(arg[1]);
                    pl.spigot().respawn();
                    UHCPlayerManager.addPlayer(UHCPlayerManager.getPlayer(pl));
                    for (ItemStack it : Main.getGame().playerInventories.get(pl.getUniqueId())) {
                        pl.getInventory().addItem(it);
                    }
                    Black_Star.res = true;
                }
            }
        } else if (args.getPlayer() == null) {
            return;
        }
        Player p = args.getPlayer();
        if(arg[0].equalsIgnoreCase("compo")){
            Main.getCompoMenu().openCompo(p);
        }if(arg[0].equalsIgnoreCase("effet") ||arg[0].equalsIgnoreCase("effets")){
            Roles role = Main.getGame().roles.get(p.getUniqueId());
            p.sendMessage("§m-------------------------");
            p.sendMessage("§8» §cForce§f: " + role.getForce() + " %");
            p.sendMessage("§8» §1Résistance§f: " + role.getResi() + " %");
            p.sendMessage("§8» §eVitesse§f: " + role.getSpeed() + " %");
            p.sendMessage("§m-------------------------");
        }if(arg[0].equalsIgnoreCase("pouf")){
            if(pouf < 3){
                if(Main.getGame().roles.get(p.getUniqueId()).getName().equalsIgnoreCase("§6Azgar76")){
                    if (arg[1].isEmpty()) {
                        p.sendMessage(Main.prefix + "§cMauvaise utilisation de la commande: /kp pouf [Joueur]");
                    }
                    Player pl = Bukkit.getPlayer(arg[1]);
                    Random rd = new Random();
                    pl.teleport(new Location(pl.getWorld(),rd.nextInt(400), 80,rd.nextInt(400)));
                    pouf ++;
                }
            }

        }
        if(arg[0].equalsIgnoreCase("shindo")){
            if(Main.getGame().roles.get(p.getUniqueId()).getName().equalsIgnoreCase("§cLava_Fetzy")){
                if(Lava_Fetzy.shindo){
                    Lava_Fetzy.shindo = false;
                    EffectUtil.removeEffect(p,PotionEffectType.FIRE_RESISTANCE);
                    p.sendMessage(Main.prefix + "§cShindo est maintenant désactivé");
                }else{
                    EffectUtil.giveEffect(p,PotionEffectType.FIRE_RESISTANCE,99999,1);
                    Lava_Fetzy.shindo = true;
                    p.sendMessage(Main.prefix + "§aShindo est maintenant activé");
                }
            }
        }
        if(arg[0].equalsIgnoreCase("marry")){
            if(Main.getGame().roles.get(p.getUniqueId()).getName().equalsIgnoreCase("§aRoar") && married){
                Player pl = Bukkit.getPlayer(UtilScenar.getRole("§aSkyrouf"));
                pl.sendMessage(Main.prefix + "§aRoar §7vous à demander en marriage, faîtes la commande §f/kp maryaccept§7 pour accepter cette demande.");
                p.sendMessage(Main.prefix + "Vous avez bien soumis votre demande en marriage.");
                marry = true;
                married = false;
            }
        }if(arg[0].equalsIgnoreCase("mess")) {
            if(Main.getGame().roles.get(p.getUniqueId()).getName().equalsIgnoreCase("§cTijumi") && mess < 5){
                String b = " ";
                for(String c: arg){
                    if(c != "mess"){
                        b = b + " " + c;
                    }
                }
                for(Roles role: Main.getGame().mechants){
                    Player pl = Bukkit.getPlayer(role.getUUID());
                    pl.sendMessage(Main.prefix + "Un nouveau message de §cTijumi §7est arrivé:");
                    pl.sendMessage(b);
                }
                mess++;
            }
        }if(arg[0].equalsIgnoreCase("malus")){
            if(Main.getGame().roles.get(p.getUniqueId()).getName().equalsIgnoreCase("§cTijumi") && cdmalus){
                Random rd = new Random();
                int a = rd.nextInt(3);
                if (arg[1].isEmpty()) {
                    p.sendMessage(Main.prefix + "§cMauvaise utilisation de la commande: /kp boost [Joueur]");
                }
                Player pl = Bukkit.getPlayer(arg[1]);
                p.sendMessage(Main.prefix + "Vous avez bien envoyer un malus au joueur.");
                pl.sendMessage(Main.prefix + "Vous avez obtenu un malus de §cTijumi§7.");
                cdmalus = false;
                switch (a){
                    case 1:
                        EffectUtil.removeMaxHealth(pl.getUniqueId(),2);
                        new BukkitRunnable(){
                            @Override
                            public void run(){
                                EffectUtil.addMaxHealth(pl.getUniqueId(),2,true);
                            }
                        }.runTaskLater(Main.instance,20*60*5);
                        break;
                    case 2:
                        EffectUtil.giveEffect(pl,PotionEffectType.WEAKNESS,60*10,1);
                        break;
                    case 3:
                        EffectUtil.giveEffect(pl,PotionEffectType.SLOW,20,1);
                        EffectUtil.giveEffect(pl, PotionEffectType.BLINDNESS,20,1);
                        break;
                }
                new BukkitRunnable(){
                    @Override
                    public void run(){
                        cdmalus = true;
                    }
                }.runTaskLater(Main.instance,20*60*20);
            }else{
                p.sendMessage(Main.prefix + "§cVous ne pouvez pas utilisez cet commande pour le moment.");
            }
        }
        if(arg[0].equalsIgnoreCase("boost")) {
                if (Main.getGame().roles.get(p.getUniqueId()).getName().equalsIgnoreCase("§aXelon")) {
                    Random rdm = new Random();
                    if (arg[1].isEmpty()) {
                        p.sendMessage(Main.prefix + "§cMauvaise utilisation de la commande: /kp boost [Joueur]");
                    }
                    if (boost) {
                        boost = false;
                        Player pl = Bukkit.getPlayer(arg[1]);
                        int a = rdm.nextInt(6);
                        p.sendMessage(Main.prefix + "Vous avez bien boost le joueur.");
                        pl.sendMessage(Main.prefix + "Vous avez était boost par §aXelon§7.");
                        switch (a) {
                            case 1:
                                EffectUtil.addMaxHealth(pl.getUniqueId(), 3, true);
                                new BukkitRunnable() {
                                    @EventHandler
                                    public void run() {
                                        EffectUtil.removeMaxHealth(pl.getUniqueId(), 3);
                                        pl.sendMessage(Main.prefix + "Fin du boost de §aXelon§7.");
                                    }
                                }.runTaskLater(Main.instance, 20 * 60 * 15);
                                break;
                            case 2:
                                EffectUtil.giveEffect(pl,PotionEffectType.INCREASE_DAMAGE,60*15,1,false);
                                break;
                            case 3:
                                EffectUtil.giveEffect(pl,PotionEffectType.DAMAGE_RESISTANCE,60*15,1,false);
                                break;
                            case 4:
                                EffectUtil.giveEffect(pl,PotionEffectType.SPEED,60*15,1,false);
                                break;
                            case 5:
                                EffectUtil.giveEffect(pl,PotionEffectType.WEAKNESS,60*15,1,false);
                                break;
                            case 6:
                                EffectUtil.giveEffect(pl,PotionEffectType.FIRE_RESISTANCE,60*15,1,false);
                                break;
                        }
                    } else {
                        p.sendMessage(Main.prefix + "§cPouvoir en cooldown");
                    }
                }
        }
        if(arg[0].equalsIgnoreCase("maryaccept")){
            if(marry){
                if(Main.getGame().roles.get(p.getUniqueId()).getName().equalsIgnoreCase("§aSkyrouf")){
                    marry = false;
                    p.sendMessage(Main.prefix + "Vous êtes désormais marrié à §aRoar");
                    Player pl = Bukkit.getPlayer(UtilScenar.getRole("§aRoar"));
                    pl.sendMessage(Main.prefix + "§aSkyrouf§7 à accepter votre demande en mariage vous êtes désormais marriés !");
                    Roar.married = true;
                    Skyrouf.married = true;
                }
            }
        }
        if(arg[0].equalsIgnoreCase("drapall")){
            if(drap && drapall< 4){
                int mechant = 0;
                for(Player pl: UHCPlayerManager.getPlayingsPlayer()){
                    if(p.getLocation().distance(pl.getLocation()) < 15){
                        if(Main.getGame().roles.get(pl.getUniqueId()).isCamp(Camp.MECHANT) || Main.getGame().roles.get(pl.getUniqueId()).getName().equalsIgnoreCase("§6Snow")){
                            mechant++;
                        }
                    }
                }
                p.sendMessage(Main.prefix + "Il y a §c" + mechant + " méchants §7autour de vous !");
                drapall++;
                drap = false;
                new BukkitRunnable(){
                    @Override
                    public void run(){
                        drap = true;
                    }
                }.runTaskLater(Main.instance,20*60*10);
            }else{
                p.sendMessage(Main.prefix + "§cVous ne pouvez pas utilisez votre commande pour le moment.");
            }
        }
        if(arg[0].equalsIgnoreCase("banane")){
            if(Main.getGame().roles.get(p.getUniqueId()).getName().equalsIgnoreCase("§aZarmII") && bananeuse < 6){
                List<Player> pla = new ArrayList<>();
                for(Player pl: UHCPlayerManager.getPlayingsPlayer()){
                    if(p.getLocation().distance(pl.getLocation()) < 5){
                        pla.add(pl);
                    }
                }
                if(pla != null){
                    Collections.shuffle(pla);
                    Player banane = pla.get(0);
                    p.sendMessage(Main.use("Banane"));
                    banane.sendMessage(Main.prefix + "Vous venez d'être toucher par une banane de ZarmII");
                    EffectUtil.giveEffect(banane, PotionEffectType.SLOW,120,1);
                    EffectUtil.removeMaxHealth(banane.getUniqueId(),1);
                    new BukkitRunnable(){
                        @Override
                        public void run(){
                            EffectUtil.addMaxHealth(banane.getUniqueId(),1,true);
                        }
                    }.runTaskLater(Main.instance,20*120);
                    bananeuse++;
                }
            }
        }
    }
}
