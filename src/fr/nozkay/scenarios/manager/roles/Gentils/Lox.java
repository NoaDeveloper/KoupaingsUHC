package fr.nozkay.scenarios.manager.roles.Gentils;

import fr.nozkay.Main;
import fr.nozkay.scenarios.Enum.Camp;
import fr.nozkay.scenarios.KoupaingsGame;
import fr.nozkay.scenarios.Utils.Title;
import fr.nozkay.scenarios.Utils.UtilScenar;
import fr.nozkay.scenarios.manager.RolesImpl;
import fr.rolan.uhc.manager.players.UHCPlayerManager;
import fr.rolan.uhc.util.player.EffectUtil;
import org.bukkit.*;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class Lox extends RolesImpl {

    public HashMap<UUID, Collection<PotionEffect>> powerball = new HashMap<UUID, Collection<PotionEffect>>();
    public HashMap<UUID, UUID> premier = new HashMap<>();
    public Lox(UUID uuid, Camp camp) {
        super(uuid, camp);
    }

    @Override
    public String getName() {
        return "§aLox";
    }

    @Override
    public String getDescription() {
        return "§8» §7Vous êtes §aLox§7.\n §8» §7Vous devez gagner avec les §aGentils§7 et ce jusqu'à la fin de la partie.\n \n" +
                "  §5§lVos Objets:§r§7\n§8» §fPowerBall:§7 Via un clic droit sur cette item vous devrez rester 15 secondes à coté de la position d'un joueur mort, si vous réussissez alors les effets du joueur seront stockés dans cette power ball et tant qu'elle est dans votre inventaire vous obtiendrez les effets en question.\n\n" +
                "  §5§lParticularités:§r§7\n§8» §7Vous obtiendrez une PowerBall lors de chaque épisode, de plus vous ne pouv.\n\n";
    }

    @Override
    public ItemStack getItems() {
        return UtilScenar.getItem(Material.REDSTONE,1,"§6Powerball §7| Clic Droit", Arrays.asList("",""));
    }

    @Override
    public void initialize() {
        verif();
    }

    public void verif(){
        new BukkitRunnable(){
            @Override
            public void run(){
                for(Player p : UHCPlayerManager.getPlayingsPlayer()){
                    if(p.getInventory().contains(Material.REDSTONE)){
                        for (ItemStack item : p.getInventory().getContents()) {
                            if (item != null && item.getType().equals(Material.REDSTONE) && item.getItemMeta().hasDisplayName() && !(item.getItemMeta().getDisplayName().equalsIgnoreCase("§6Powerball §7| Clic Droit"))) {
                                ItemMeta im = item.getItemMeta();
                                if(Bukkit.getPlayer(im.getDisplayName()) != null){
                                    OfflinePlayer pl = Bukkit.getPlayer(im.getDisplayName());
                                    if(premier.get(p.getUniqueId()) == null){
                                        premier.put(p.getUniqueId(),pl.getUniqueId());
                                    }if(premier.get(p.getUniqueId()) == pl.getUniqueId() || !(Main.getGame().roles.get(p.getUniqueId()).getName().equalsIgnoreCase("§aLox"))){
                                        Collection<PotionEffect> potions = powerball.get(premier.get(p.getUniqueId()));
                                        if(potions != null){
                                            for(PotionEffect potion: potions){
                                                EffectUtil.giveEffect(p,potion.getType(),3,1,false);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(Main.instance,0,20);
    }

    UUID death = null;
    Location deathloc = null;
    @EventHandler
    public void onItem(PlayerInteractEvent e){
        if(e.getItem().getType().equals(Material.REDSTONE) && e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName()){
            e.setCancelled(true);
        }
        Player p = e.getPlayer();
        if(p.getUniqueId() == getUUID() && e.getItem() != null && e.getItem().getItemMeta().hasDisplayName() && e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Powerball §7| Clic Droit")){
            if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)){
                Location center = p.getLocation();
                World world = center.getWorld();
                int radius = 15;
                for (Map.Entry<Location, UUID> entry : Main.getGame().death.entrySet()) {
                    Location location = entry.getKey();
                    UUID uuid = entry.getValue();
                    if(location.distance(center) < 15){
                        death = uuid;
                        deathloc = location;
                        Main.getGame().death.remove(location);
                    }
                }
                if(death == null && deathloc == null){
                    p.sendMessage(Main.prefix + "Il n'y a aucun cadavre à coté de vous.");
                }else{
                    Location finalDeathloc = deathloc;
                    UUID finalDeath = death;
                    new BukkitRunnable(){
                        int timer = 15;
                        @Override
                        public void run(){
                            p.getInventory().remove(UtilScenar.getItem(Material.REDSTONE,1,"§6Powerball §7| Clic Droit", Arrays.asList("","")));
                            Title.sendActionBar(p,"§7Temp Restant à coté du cadavre: §c" + timer);
                            timer--;
                            if(finalDeathloc.distance(p.getLocation()) > 15){
                                this.cancel();
                                p.sendMessage(Main.prefix + "Vous vous êtes trop éloignez du cadavre, fin de la capture.");
                                p.getInventory().addItem(UtilScenar.getItem(Material.REDSTONE,1,"§6Powerball §7| Clic Droit", Arrays.asList("","")));
                            }else if(timer == 0){
                                OfflinePlayer pl = Bukkit.getPlayer(finalDeath);
                                powerball.put(pl.getUniqueId(), KoupaingsGame.deatheffect.get(pl.getUniqueId()));
                                if(premier.get(p.getUniqueId()) == null){
                                    premier.put(p.getUniqueId(),pl.getUniqueId());
                                }
                                p.sendMessage(Main.prefix + "Vous avez bien copié les effets du joueur dans votre PowerBall");
                                p.getInventory().addItem(UtilScenar.getItem(Material.REDSTONE,1,pl.getName(), Arrays.asList("","")));
                                this.cancel();
                            }
                        }
                    }.runTaskTimer(Main.instance,0,20);
                }
            }
        }
    }

}
