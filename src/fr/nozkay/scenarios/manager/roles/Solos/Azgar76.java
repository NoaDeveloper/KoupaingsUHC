package fr.nozkay.scenarios.manager.roles.Solos;

import fr.nozkay.Main;
import fr.nozkay.scenarios.Enum.Camp;
import fr.nozkay.scenarios.Utils.Title;
import fr.nozkay.scenarios.Utils.UtilScenar;
import fr.nozkay.scenarios.manager.Roles;
import fr.nozkay.scenarios.manager.RolesImpl;
import fr.rolan.uhc.manager.players.UHCPlayerManager;
import fr.rolan.uhc.util.player.EffectUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.UUID;

public class Azgar76 extends RolesImpl {
    public Azgar76(UUID uuid, Camp camp) {
        super(uuid, camp);
    }

    @Override
    public String getName() {
        return "§6Azgar76";
    }

    @Override
    public String getDescription() {
        return "§8» §7Vous êtes §6Azgar76§7.\n §8» §7Vous devez gagner avec §6Snow§7 en duo et ce jusqu'à la fin de la partie.\n \n" +
                "";
    }

    @Override
    public ItemStack getItems() {
        return UtilScenar.getItem(Material.INK_SACK,1,"§6Poop §7| Clic Droit", Arrays.asList("",""));
    }

    @Override
    public void initialize() {
        Player p = Bukkit.getPlayer(getUUID());
        EffectUtil.giveInfiniteEffect(p, PotionEffectType.DAMAGE_RESISTANCE,1);
        new BukkitRunnable(){
            @Override
            public void run(){
                Player pl = Bukkit.getPlayer(UtilScenar.getRole("§6Snow"));
                if(pl.getLocation().distance(p.getLocation()) < 15){
                    setSpeed(7);
                }else{
                    if(getSpeed() > 6){
                        setSpeed(getSpeed() - 7);
                    }
                }
            }
        }.runTaskTimer(Main.instance,0,20);
    }
    int cd = 0;
    @EventHandler
    public void onItem(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(p.getUniqueId() == getUUID() && e.getItem() != null && e.getItem().getItemMeta().hasDisplayName() && e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Poop §7| Clic Droit")){
            if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)){
                if(cd == 0){
                    cd = 300;
                    for(Player pl: UHCPlayerManager.getPlayingsPlayer()){
                        if(pl.getLocation().distance(p.getLocation()) < 15){
                            EffectUtil.giveEffect(pl,PotionEffectType.BLINDNESS,5,1);
                        }
                    }
                    p.sendMessage(Main.use("Poop"));
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
                    p.sendMessage(Main.cooldown("Poop"));
                    Title.sendActionBar(p,"§cIl vous reste " + cd + " seconde(s) à attendre.");
                }
            }
        }
    }


}
