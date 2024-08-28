package fr.nozkay.scenarios.manager.roles.Gentils;

import fr.nozkay.Main;
import fr.nozkay.scenarios.Enum.Camp;
import fr.nozkay.scenarios.KoupaingsGame;
import fr.nozkay.scenarios.Utils.UtilScenar;
import fr.nozkay.scenarios.manager.Roles;
import fr.nozkay.scenarios.manager.RolesImpl;
import fr.rolan.uhc.events.custom.GameEpisodeEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ZarmII extends RolesImpl {
    public ZarmII(UUID uuid, Camp camp) {
        super(uuid, camp);
    }

    @Override
    public String getName() {
        return "§aZarmII";
    }

    @Override
    public String getDescription() {
        return "§8» §7Vous êtes §aZarmII§7.\n §8» §7Vous devez gagner avec les §aGentils§7 et ce jusqu'à la fin de la partie.\n \n" +
                "  §5§lVos Commandes:§r§7\n§8» §7Via la commande §f/kp banane§7 vous jetterais une banane sur un joueur aléatoire à moins de 5 blocs de lui. Le joueur touché obtient Slowness I et 1 coeurs permanent en moins pendant 2 minutes, Attention les bananes peuvent vous touchés également. (5x dans la partie )\n\n" +
                "  §5§lParticularités:§r§7\n§8» §7Grâce à la force de tutel, vous donnerais 15% de dégâts supplémentaire à §6Azgar76§7.§r\n§8» §7Si vous tuez §6Azgar76§7 vous obtiendrez 10% de speed supplémentaire.\n\n";
    }

    @Override
    public ItemStack getItems() {
        return null;
    }

    @Override
    public void initialize() {
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Player && e.getEntity() instanceof Player){
            Player damager = (Player) e.getDamager();
            Player p = (Player) e.getEntity();
            if(damager.getUniqueId() == this.getUUID()){
                Roles role = Main.getGame().roles.get(p.getUniqueId());
                if(role.getName() == "§6Azgar76"){
                    Double damage = e.getDamage() * 1.05;
                    e.setCancelled(true);
                    p.damage(damage);
                    if(p.isDead() || p.getHealth() > 0.1){
                        this.setSpeed(this.getSpeed() + 10);
                    }
                }
            }
        }
    }

}
