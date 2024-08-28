package fr.nozkay.scenarios.manager.roles.Mechants;

import fr.nozkay.Main;
import fr.nozkay.scenarios.Commands.Kp;
import fr.nozkay.scenarios.Enum.Camp;
import fr.nozkay.scenarios.manager.RolesImpl;
import fr.rolan.uhc.events.custom.GameEpisodeEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class Tijumi extends RolesImpl {
    public Tijumi(UUID uuid, Camp camp) {
        super(uuid, camp);
    }

    @Override
    public String getName() {
        return "§cTijumi";
    }

    @Override
    public String getDescription() {
        return "§8» §7Vous êtes §cTijumi§7.\n §8» §7Vous devez gagner avec les §cMéchants§7 et ce jusqu'à la fin de la partie.\n \n" +
                "";
    }

    @Override
    public ItemStack getItems() {
        return null;
    }

    @Override
    public void initialize() {
    }


}
