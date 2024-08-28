package fr.nozkay.scenarios.manager.roles.Gentils;

import fr.nozkay.Main;
import fr.nozkay.scenarios.Enum.Camp;
import fr.nozkay.scenarios.Utils.UtilScenar;
import fr.nozkay.scenarios.manager.RolesImpl;
import fr.rolan.uhc.util.player.EffectUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class Roar extends RolesImpl {
    public Roar(UUID uuid, Camp camp) {
        super(uuid, camp);
    }

    @Override
    public String getName() {
        return "§aRoar";
    }

    @Override
    public String getDescription() {
        return "§8» §7Vous êtes §aRoar§7.\n §8» §7Vous devez gagner avec les §aGentils§7 et ce jusqu'à la fin de la partie.\n \n" +
                "  §5§lVos Commandes:§r§7\n§8» §7Via la commande §f/kp marry§7 vous demanderez skyrouf en mariage qui aura 5 minutes pour accepter. Si ce dernier accepte alors vous obtiendrez §e5% de vitesse§7 à moins de 10 blocs de §aSkyrouf§7 et §c5% de force§7 à chaque kill que ce dernier réalisera.\n\n" +
                "  §5§lParticularités:§r§7\n§8» §7A chaque kill que vous réaliserais vous obtiendrez 2 golden apple.\n\n";
    }

    @Override
    public ItemStack getItems() {
        return null;
    }

    @Override
    public void initialize() {
        verif();
    }

    public static Boolean married = false;

    public void verif(){
        new BukkitRunnable(){
            @Override
            public void run(){
                if(married){
                    Player pl = Bukkit.getPlayer(UtilScenar.getRole("§aSkyrouf"));
                    Player p= Bukkit.getPlayer(getUUID());
                    if(p.getLocation().distance(pl.getLocation()) < 10){
                        setSpeed(10);
                    }else{
                        setSpeed(0);
                    }
                }
            }
        }.runTaskTimer(Main.instance,0,20);
    }


    @EventHandler
    public void onDeath(PlayerDeathEvent e ){
        Player p = e.getEntity();
        if(married){
            if(Main.getGame().roles.get(p.getUniqueId()).getName().equalsIgnoreCase("§aSkyrouf")){
                EffectUtil.removeMaxHealth(getUUID(),2);
            }else{
                if(Main.getGame().fight.get(p.getUniqueId()).equals(UtilScenar.getRole("§aSkyrouf"))){
                    p.sendMessage(Main.prefix + "§aSkyrouf§7 venez de réalisé une élimination vous obtenez donc §c5% de force§7 supplementaire.");
                    this.setForce(this.getForce() + 5);
                }
            }
        }
    }

}
