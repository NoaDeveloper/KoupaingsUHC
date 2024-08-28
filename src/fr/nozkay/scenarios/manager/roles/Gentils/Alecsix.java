package fr.nozkay.scenarios.manager.roles.Gentils;

import fr.nozkay.Main;
import fr.nozkay.scenarios.Enum.Camp;
import fr.nozkay.scenarios.manager.Roles;
import fr.nozkay.scenarios.manager.RolesImpl;
import fr.rolan.uhc.manager.players.UHCPlayer;
import fr.rolan.uhc.manager.players.UHCPlayerManager;
import fr.rolan.uhc.util.player.EffectUtil;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.UUID;

public class Alecsix extends RolesImpl {
    public Alecsix(UUID uuid, Camp camp) {
        super(uuid, camp);
    }

    @Override
    public String getName() {
        return "§aAlecsix";
    }

    @Override
    public String getDescription() {
        return "§8» §7Vous êtes §aAlecsix§7.\n §8» §7Vous devez gagner avec les §aGentils§7 et ce jusqu'à la fin de la partie.\n \n" +
                "  §5§lParticularités:§r§7\n§8» §7Si vous ne portez aucune pièce d'armure vous obtenez §eVitesse I§7, Invisibilité et No Fall§7.§r\n§8» §7Votre effet ne disparaît pas si vous êtes tapé ou que vous tapé un joueur.\n§8» §7Si §cJawp34 §7est invisible en même temp que vous vous verrez des particules rouge.\n\n";
    }

    @Override
    public ItemStack getItems() {
        return null;
    }

    @Override
    public void initialize() {
        verif();
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
                        if(Main.getGame().roles.get(pl.getUniqueId()).getName().equalsIgnoreCase("§cJawp34")){
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
