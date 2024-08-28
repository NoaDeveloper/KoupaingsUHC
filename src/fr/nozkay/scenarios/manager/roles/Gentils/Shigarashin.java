package fr.nozkay.scenarios.manager.roles.Gentils;

import fr.nozkay.Main;
import fr.nozkay.scenarios.Enum.Camp;
import fr.nozkay.scenarios.Utils.Title;
import fr.nozkay.scenarios.Utils.UtilScenar;
import fr.nozkay.scenarios.manager.RolesImpl;
import fr.rolan.uhc.manager.players.UHCPlayerManager;
import fr.rolan.uhc.util.player.EffectUtil;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import net.minecraft.server.v1_8_R3.PlayerInteractManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Shigarashin extends RolesImpl {
    public Shigarashin(UUID uuid, Camp camp) {
        super(uuid, camp);
    }

    @Override
    public String getName() {
        return "§aShigarashin";
    }

    @Override
    public String getDescription() {
        return "§8» §7Vous êtes §aShigarashin§7.\n §8» §7Vous devez gagner avec les §aGentils§7 et ce jusqu'à la fin de la partie.\n \n" +
                "  §5§lVos Objets:§r§7\n§8» §fVent:§7 Vous réaliserez un dash de 10 blocs en avant. ( 1x toutes les 3 min )\n\n" +
                "  §5§lParticularités:§r§7\n§8» §7Si vous êtes à cotés de §aAlecsix §7ou §aSkyrouf §7vous obtenez 10% de résistance cumulable.\n\n";
    }

    @Override
    public ItemStack getItems() {
        return UtilScenar.getItem(Material.FEATHER,1,"§6Vent §7| Clic Droit", Arrays.asList("",""));
    }

    @Override
    public void initialize() {
        verif();
    }
    int cd =0;

    public void verif(){
        new BukkitRunnable(){
            @Override
            public void run(){
                Player p = Bukkit.getPlayer(getUUID());
                for(Player pl: UHCPlayerManager.getPlayingsPlayer()){
                    if(p.getLocation().distance(pl.getLocation()) < 20) {
                        if (Main.getGame().roles.get(pl.getUniqueId()).getName().equalsIgnoreCase("§aSkyrouf") && Main.getGame().roles.get(pl.getUniqueId()).getName().equalsIgnoreCase("§aAlecsix")) {
                            setResi(getResi() + 20);
                        } else if (Main.getGame().roles.get(pl.getUniqueId()).getName().equalsIgnoreCase("§aSkyrouf") || Main.getGame().roles.get(pl.getUniqueId()).getName().equalsIgnoreCase("§aAlecsix")) {
                            setResi(getResi() + 10);
                        } else{
                            setResi(0);
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
    public void onItem(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(p.getUniqueId() == getUUID() && e.getItem() != null && e.getItem().getItemMeta().hasDisplayName() && e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Vent §7| Clic Droit")){
            if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)){
                if(cd == 0){
                    cd = 180;
                    Vector direction = p.getLocation().getDirection().normalize();
                    Vector dash = direction.multiply(3);
                    p.setVelocity(dash);
                    p.sendMessage(Main.use("Vent"));
                }else{
                    p.sendMessage(Main.cooldown("Vent"));
                    Title.sendActionBar(p,"§cIl vous reste " + cd + " seconde(s) à attendre.");
                }
            }
        }
    }

}
