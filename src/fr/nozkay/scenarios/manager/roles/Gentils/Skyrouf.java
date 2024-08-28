package fr.nozkay.scenarios.manager.roles.Gentils;

import fr.nozkay.Main;
import fr.nozkay.scenarios.Enum.Camp;
import fr.nozkay.scenarios.Utils.UtilScenar;
import fr.nozkay.scenarios.manager.Roles;
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

public class Skyrouf extends RolesImpl {
    public Skyrouf(UUID uuid, Camp camp) {
        super(uuid, camp);
    }

    @Override
    public String getName() {
        return "§aSkyrouf";
    }

    @Override
    public String getDescription() {
        return "§8» §7Vous êtes §aSkyrouf§7.\n §8» §7Vous devez gagner avec les §aGentils§7 et ce jusqu'à la fin de la partie.\n \n" +
                "  §5§lVos Commandes:§r§7\n§8» §7Via la commande §f/kp drapall§7 vous obtiendez le nombre de méchants présent à moins de 15 blocs de vous au moment de la commande. ( 3x par partie avec un cooldown de 10 minutes )\n" +
                "§8» §7Si vous venez à recevoir une demande de mariage vous pouvez l'acceptez via la commande §f/kp marryaccept§7 alors vous obtiendrez §e10% de speed§7 en restant à moins de 10 blocs de §aRoar§7et §c5% de force§7 supplémentaire pour chaque kills qu'elle fait.\nSi §aSkyrouf§7 meurt alors vous perdrez 2 coeurs de manière permanente.\n\n";
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
                    Player pl = Bukkit.getPlayer(UtilScenar.getRole("§aRoar"));
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
            if(Main.getGame().roles.get(p.getUniqueId()).getName().equalsIgnoreCase("§aRoar")){
                EffectUtil.removeMaxHealth(getUUID(),2);
            }else{
                if(Main.getGame().fight.get(p.getUniqueId()).equals(UtilScenar.getRole("§aSkyrouf"))){
                    p.sendMessage(Main.prefix + "§aRoar§7 venez de réalisé une élimination vous obtenez donc §c5% de force§7 supplementaire.");
                    this.setForce(this.getForce() + 5);
                }
            }
        }
    }

}
