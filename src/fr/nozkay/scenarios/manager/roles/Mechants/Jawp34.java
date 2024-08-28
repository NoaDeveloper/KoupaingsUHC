package fr.nozkay.scenarios.manager.roles.Mechants;

import fr.nozkay.Main;
import fr.nozkay.scenarios.Enum.Camp;
import fr.nozkay.scenarios.Utils.Title;
import fr.nozkay.scenarios.Utils.UtilScenar;
import fr.nozkay.scenarios.manager.Roles;
import fr.nozkay.scenarios.manager.RolesImpl;
import fr.rolan.uhc.manager.players.UHCPlayerManager;
import fr.rolan.uhc.util.player.EffectUtil;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.UUID;

public class Jawp34 extends RolesImpl {
    public Jawp34(UUID uuid, Camp camp) {
        super(uuid, camp);
    }

    @Override
    public String getName() {
        return "§cJawp34";
    }

    @Override
    public String getDescription() {
        return "§8» §7Vous êtes §cJawp34§7.\n §8» §7Vous devez gagner avec les §cMéchants§7 et ce jusqu'à la fin de la partie.\n \n" +
                "";
    }

    @Override
    public ItemStack getItems() {
        return UtilScenar.getItem(Material.STICK,1,"§6Ratio §7| Clic Droit", Arrays.asList("",""));

    }

    @Override
    public void initialize() {
        verif();
    }

    int cd = 0;

    @EventHandler
    public void onItem(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(e.getItem() != null && e.getItem().getItemMeta().hasDisplayName() && e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Ratio §7| Clic Droit")){
            if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)){
                if(cd == 0){
                    cd = 180;
                    for(Player pl: UHCPlayerManager.getPlayingsPlayer()){
                       Roles role = Main.getGame().roles.get(pl.getUniqueId());
                       if(!role.isCamp(Camp.MECHANT)){
                           if(pl.getLocation().distance(p.getLocation()) < 15){
                               Vector direction = p.getLocation().getDirection().normalize();
                               Vector dash = direction.multiply(30);
                               pl.setVelocity(dash);
                           }
                       }
                    }
                    p.sendMessage(Main.use("Ratio"));
                }else{
                    p.sendMessage(Main.cooldown("Ratio"));
                    Title.sendActionBar(p,"§cIl vous reste " + cd + " seconde(s) à attendre.");
                }
            }
        }
    }

    boolean fall = false;


    public void verif(){
        new BukkitRunnable(){
            @Override
            public void run(){
                Player p = Bukkit.getPlayer(getUUID());
                if(p.getInventory().getChestplate() == null && p.getInventory().getBoots() == null && p.getInventory().getHelmet() == null && p.getInventory().getLeggings() == null){
                    EffectUtil.giveEffect(p, PotionEffectType.SPEED,4,1);
                    EffectUtil.giveEffect(p, PotionEffectType.INVISIBILITY,4,1);
                    fall = true;
                }else{
                    if(p.getActivePotionEffects().contains(PotionEffectType.INVISIBILITY)){
                        EffectUtil.removeEffect(p,PotionEffectType.INVISIBILITY);
                        fall = false;
                    }
                }
                for(Player pl: UHCPlayerManager.getPlayingsPlayer()){
                    if(p.getLocation().distance(pl.getLocation()) < 20){
                        if(Main.getGame().roles.get(pl.getUniqueId()).getName().equalsIgnoreCase("§aAlecsix")){
                            Location location = pl.getLocation();
                            PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(
                                    EnumParticle.REDSTONE,
                                    true,
                                    (float) location.getX(),
                                    (float) location.getY(),
                                    (float) location.getZ(),
                                    0, 0, 0, 0,
                                    3
                            );

                            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
                        }
                    }
                }
                if(cd != 0){
                    cd--;
                }
            }
        }.runTaskTimer(Main.instance,0,20);
    }

    @EventHandler
    public void onFall(EntityDamageEvent e){
        if(e.getEntity() instanceof Player){
            Player p = (Player) e.getEntity();
            if(p.getUniqueId() == this.getUUID()){
                if(e.getCause().equals(EntityDamageEvent.DamageCause.FALL)){
                    if(fall){
                        e.setCancelled(true);
                    }
                }
            }
        }
    }


}
