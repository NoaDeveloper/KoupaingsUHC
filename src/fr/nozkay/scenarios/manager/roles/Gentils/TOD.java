package fr.nozkay.scenarios.manager.roles.Gentils;

import fr.nozkay.Main;
import fr.nozkay.scenarios.Enum.Camp;
import fr.nozkay.scenarios.Utils.UtilScenar;
import fr.nozkay.scenarios.manager.Roles;
import fr.nozkay.scenarios.manager.RolesImpl;
import fr.rolan.uhc.events.custom.world.DayEvent;
import fr.rolan.uhc.events.custom.world.NightEvent;
import fr.rolan.uhc.util.player.EffectUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class TOD extends RolesImpl {
    public TOD(UUID uuid, Camp camp) {
        super(uuid, camp);
    }

    @Override
    public String getName() {
        return "§aTOD";
    }

    @Override
    public String getDescription() {
        return "§8» §7Vous êtes §aTOD§7.\n §8» §7Vous devez gagner avec les §aGentils§7 mais vous pouvez changez de camp.\n \n" +
                "  §5§lParticularités:§r§7\n§8» §7Vous obteneez le pseudo d'§6Azgar76§7.\n §8» §7Vous devez tuer ou se trouver à moins de 10 blocs lors de la mort de §6Snow§7 pour pouvoir devenir en duo avec §6Azgar76§7.\n";
    }

    @Override
    public ItemStack getItems() {
        return null;
    }

    @Override
    public void initialize() {
        Player p = Bukkit.getPlayer(UtilScenar.getRole("§6Azgar76"));
        Player pl = Bukkit.getPlayer(this.getUUID());
        pl.sendMessage(Main.prefix + "§7Le pseudo d'§6Azgar76§7 est: " + p.getName());
    }

    @EventHandler
    public void onNight(NightEvent e){
        Player p = Bukkit.getPlayer(this.getUUID());
        EffectUtil.giveEffect(p, PotionEffectType.INCREASE_DAMAGE,60*5,1);
        this.setForce(this.getForce() + 25);
    }

    @EventHandler
    public void onDay(DayEvent e){
        Player p = Bukkit.getPlayer(this.getUUID());
        EffectUtil.removeEffect(p,PotionEffectType.INCREASE_DAMAGE);
        this.setForce(this.getForce() - 25);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        Player p = e.getEntity();
        HashMap<UUID, Roles> roles = Main.getGame().roles;
        if(roles.get(p.getUniqueId()).getName().equalsIgnoreCase("§6Snow")){
            Player pl = Bukkit.getPlayer(this.getUUID());
            if(pl.getLocation().distance(p.getLocation()) < 10){
                pl.sendMessage(Main.prefix+ "§6Snow§7 est mort à moins de 10 blocs de vous, désormais vous devez donc gagner avec §6Azgar76§7.");
                this.setCamp(Camp.DUO);
                Player pla = Bukkit.getPlayer(UtilScenar.getRole("§aAzgar76"));
                pla.sendMessage(Main.prefix + "§aTod §7était à moins de 10 blocs de §6Snow §7lors de sa mort alors il doit désormais gagner avec vous !");
            }
        }
    }

}
