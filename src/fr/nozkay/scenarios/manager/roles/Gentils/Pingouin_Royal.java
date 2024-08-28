package fr.nozkay.scenarios.manager.roles.Gentils;

import fr.nozkay.Main;
import fr.nozkay.scenarios.Enum.Camp;
import fr.nozkay.scenarios.KoupaingsGame;
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
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.*;

public class Pingouin_Royal extends RolesImpl {
    public Pingouin_Royal(UUID uuid, Camp camp) {
        super(uuid, camp);
    }

    @Override
    public String getName() {
        return "§aPingouin_Royal";
    }

    @Override
    public String getDescription() {
        return "§8» §7Vous êtes §aPingouin_Royal§7.\n §8» §7Vous devez gagner avec les §aGentils§7 et ce jusqu'à la fin de la partie.\n \n" +
                "  §5§lParticularités:§r§7\n§8» §7Vous possèdez de base 5% de faiblesse.\n §8» §7A la mort de §aSkyrouf§7, §aAlecsix§7 ou §aShigarashin§7 vous obtiendrez §c5% de force§7 et 1 coeur permanent supplémentaire.\n";
    }

    @Override
    public ItemStack getItems() {
        return null;
    }

    @Override
    public void initialize() {
        this.setForce(-5);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        Player p = e.getEntity();
        HashMap<UUID, Roles> roles = Main.getGame().roles;
        if(roles.get(p.getUniqueId()).getName().equalsIgnoreCase("§aSkyrouf") || roles.get(p.getUniqueId()).getName().equalsIgnoreCase("§aShigarashin") || roles.get(p.getUniqueId()).getName().equalsIgnoreCase("§aAlecsix")){
            this.setForce(this.getForce() + 5);
            Player pl = Bukkit.getPlayer(this.getUUID());
            pl.setMaxHealth(pl.getMaxHealth() + 2);
            pl.sendMessage(Main.prefix + "Un de vos amis est mort, vous obtenez donc §c5% de force §7ainsi qu'un coeur permanent supplémentaire.");
        }
    }

}
