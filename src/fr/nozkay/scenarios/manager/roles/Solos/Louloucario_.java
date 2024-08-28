package fr.nozkay.scenarios.manager.roles.Solos;

import fr.nozkay.Main;
import fr.nozkay.scenarios.Enum.Camp;
import fr.nozkay.scenarios.Utils.Targeter;
import fr.nozkay.scenarios.Utils.Title;
import fr.nozkay.scenarios.Utils.UtilScenar;
import fr.nozkay.scenarios.manager.RolesImpl;
import fr.rolan.uhc.events.player.food.ConsumeEvent;
import fr.rolan.uhc.util.player.EffectUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Louloucario_ extends RolesImpl {
    public Louloucario_(UUID uuid, Camp camp) {
        super(uuid, camp);
    }

    @Override
    public String getName() {
        return "§5Louloucario_";
    }


    @Override
    public String getDescription() {
        return "§8» §7Vous êtes §5Louloucario_§7.\n §8» §7Vous devez gagner §5SEUL§7 en duo et ce jusqu'à la fin de la partie.\n \n" +
                "";
    }

    @Override
    public ItemStack getItems() {
        return UtilScenar.getItem(Material.BOWL,1,"§6Sub Twitch §7| Clic Droit", Arrays.asList("",""));
    }

    @Override
    public void initialize() {
        Player p = Bukkit.getPlayer(getUUID());
        EffectUtil.addMaxHealth(p.getUniqueId(),3,true);
        p.getInventory().addItem(UtilScenar.getItem(Material.SUGAR,1,"§6Vitesse Extreme §7| Clic Droit", Arrays.asList("","")));
    }
    int cd = 0;
    boolean abso = false;
    boolean nofall = false;

    Inventory inv;
    @EventHandler
    public void onItem(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(p.getUniqueId() == getUUID() && e.getItem() != null && e.getItem().getItemMeta().hasDisplayName() && e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Vitesse Extreme §7| Clic Droit")){
            if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)){
                if(cd == 0){
                    cd = 300;
                    Player pl = Targeter.getTargetPlayer(p);
                    p.teleport(pl.getLocation());
                    p.sendMessage(Main.use("Vitesse Extreme"));
                    new BukkitRunnable(){
                        @Override
                        public void run(){
                            if(cd == 0){
                                this.cancel();
                            }else{
                                cd--;
                            }
                        }
                    }.runTaskTimer(Main.instance,0,20);
                }else{
                    p.sendMessage(Main.cooldown("Vitesse Extreme"));
                    Title.sendActionBar(p,"§cIl vous reste " + cd + " seconde(s) à attendre.");
                }
            }
        }if(p.getUniqueId() == getUUID() && e.getItem() != null && e.getItem().getItemMeta().hasDisplayName() && e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Sub Twitch §7| Clic Droit")){
            if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)){
                inv = Bukkit.createInventory(p,45,"§5Sub Twitch");
                inv.setItem(19,UtilScenar.getItem(Material.FEATHER,1,"§5Premier Echange", Arrays.asList("§7Coût: §c1 Coeur","§7Bonus: §cForce I§7 et §eVitesse I§7 pendant 30 minutes")));
                inv.setItem(21,UtilScenar.getItem(Material.SPIDER_EYE,1,"§5Second Echange", Arrays.asList("§7Coût: §c2 Coeurs","§7Bonus: 3 coeurs d'absorption supplémentaire lorsque vous mangez une pomme d'or pendant 30 minutes")));
                inv.setItem(23,UtilScenar.getItem(Material.NETHER_STAR,1,"§5Troisième Echange", Arrays.asList("§7Coût: §c4 Coeurs","§7Bonus: §1Résistance I§7 pendant 20 minutes")));
                inv.setItem(25,UtilScenar.getItem(Material.DIAMOND_SWORD,1,"§5Quatrième Echange", Arrays.asList("§7Coût: §c6 Coeurs","§7Bonus: §eVitesse II §7permanent.")));
                p.openInventory(inv);
            }
        }
    }

    @EventHandler
    public void onFall(EntityDamageEvent e){
        if(e.getEntity() instanceof Player){
            Player p = (Player) e.getEntity();
            if(p.getUniqueId() == this.getUUID()){
                if(e.getCause().equals(EntityDamageEvent.DamageCause.FALL)){
                    if(nofall){
                        e.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        Player p = e.getEntity();
        if(Main.getGame().fight.get(p.getUniqueId()) == getUUID()){
            Player pl = Bukkit.getPlayer(getUUID());
            EffectUtil.addMaxHealth(pl.getUniqueId(),1,true);
            if(Main.getGame().roles.get(p.getUniqueId()).getName().equalsIgnoreCase("§aSkyrouf")){
                EffectUtil.addMaxHealth(pl.getUniqueId(),2,true);
            }if(Main.getGame().roles.get(p.getUniqueId()).getName().equalsIgnoreCase("§aAlecsix")){
                nofall = true;
            }if(Main.getGame().roles.get(p.getUniqueId()).getName().equalsIgnoreCase("§cJawp34")){
                pl.getInventory().addItem(UtilScenar.getItem(Material.STICK,1,"§6Ratio §7| Clic Droit", Arrays.asList("","")));
            }
        }
    }

    @EventHandler
    public void onConsume(PlayerItemConsumeEvent e){
        if(e.getPlayer().getUniqueId() == this.getUUID() && abso){
            Player p = e.getPlayer();
            EffectUtil.removeEffect(p,PotionEffectType.ABSORPTION);
            EffectUtil.giveEffect(p,PotionEffectType.ABSORPTION,120,2);
        }
    }

    @EventHandler
    public void onInventory(InventoryClickEvent e){
        if(e.getInventory().getName().equalsIgnoreCase("§5Sub Twitch")){
            e.setCancelled(true);
            Player p = Bukkit.getPlayer(this.getUUID());
            if(e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§5Premier Echange")){
                if(p.getMaxHealth() > 1.0){
                    EffectUtil.removeMaxHealth(p.getUniqueId(),1);
                    EffectUtil.giveEffect(p,PotionEffectType.SPEED,60*20,1);
                    EffectUtil.giveEffect(p,PotionEffectType.INCREASE_DAMAGE,60*20,1);
                }
            }if(e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§5Second Echange")){
                if(p.getMaxHealth() > 2.0){
                    EffectUtil.removeMaxHealth(p.getUniqueId(),2);
                    abso = true;
                    new BukkitRunnable(){
                        @Override
                        public void run(){
                            abso = false;
                        }
                    }.runTaskLater(Main.instance,20*60*30);
                }
            }if(e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§5Troisième Echange")){
                if(p.getMaxHealth() > 4.0){
                    EffectUtil.removeMaxHealth(p.getUniqueId(),4);
                    EffectUtil.giveEffect(p,PotionEffectType.DAMAGE_RESISTANCE,60*20,1);
                }
            }if(e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§5Quatrième Echange")){
                if(p.getMaxHealth() > 6.0){
                    EffectUtil.removeMaxHealth(p.getUniqueId(),6);
                    EffectUtil.giveInfiniteEffect(p,PotionEffectType.SPEED,2);
                }
            }
            p.closeInventory();
        }
    }
}
