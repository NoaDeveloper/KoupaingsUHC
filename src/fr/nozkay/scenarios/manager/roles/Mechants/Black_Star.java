package fr.nozkay.scenarios.manager.roles.Mechants;

import com.sun.org.apache.xpath.internal.operations.Bool;
import fr.nozkay.Main;
import fr.nozkay.scenarios.Enum.Camp;
import fr.nozkay.scenarios.manager.Roles;
import fr.nozkay.scenarios.manager.RolesImpl;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class Black_Star extends RolesImpl {
    public Black_Star(UUID uuid, Camp camp) {
        super(uuid, camp);
    }

    @Override
    public String getName() {
        return "§cBlack_Star";
    }

    @Override
    public String getDescription() {
        return "§8» §7Vous êtes §cBlack_Star§7.\n §8» §7Vous devez gagner avec les §cMéchants§7 et ce jusqu'à la fin de la partie.\n \n" +
                "";
    }

    @Override
    public ItemStack getItems() {
        return null;
    }

    @Override
    public void initialize() {
        this.setForce(25);
    }

    public static Boolean res = false;

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        Player p = e.getEntity();
        Roles role = Main.getGame().roles.get(p.getUniqueId());
        if(role.isCamp(Camp.MECHANT)){
            if(!res){
                Player pl = Bukkit.getPlayer(this.getUUID());
                TextComponent message = new TextComponent("§cPour réssusciter le joueur mort cliquez sur ce message.");
                message.setBold(true);
                message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/kp revive " + p.getName()));
                pl.spigot().sendMessage(message);
            }
        }
    }


}
