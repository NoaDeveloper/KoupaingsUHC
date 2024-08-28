package fr.nozkay.scenarios.manager.roles.Mechants;

import fr.nozkay.Main;
import fr.nozkay.scenarios.Enum.Camp;
import fr.nozkay.scenarios.manager.RolesImpl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class Lava_Fetzy extends RolesImpl {
    public Lava_Fetzy(UUID uuid, Camp camp) {
        super(uuid, camp);
    }

    @Override
    public String getName() {
        return "§cLava_Fetzy";
    }

    @Override
    public String getDescription() {
        return "§8» §7Vous êtes §cLava_Fetzy§7.\n §8» §7Vous devez gagner avec les §cMéchants§7 et ce jusqu'à la fin de la partie.\n \n" +
                "";
    }

    @Override
    public ItemStack getItems() {
        return null;
    }

    @Override
    public void initialize() {
    }

    public static Boolean shindo = false;

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Player && e.getEntity() instanceof Player){
            Player damager = (Player) e.getDamager();
            Player p = (Player) e.getEntity();
            if(damager.getUniqueId() == getUUID()){
                if(shindo){
                    p.setFireTicks(40);
                }
            }
        }
    }


}
