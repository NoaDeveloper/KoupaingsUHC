package fr.nozkay.scenarios.manager.roles.Gentils;

import fr.nozkay.Main;
import fr.nozkay.scenarios.Commands.Kp;
import fr.nozkay.scenarios.Enum.Camp;
import fr.nozkay.scenarios.Utils.UtilScenar;
import fr.nozkay.scenarios.manager.RolesImpl;
import fr.rolan.uhc.events.custom.GameEpisodeEvent;
import fr.rolan.uhc.util.player.EffectUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class Xelon extends RolesImpl {
    public Xelon(UUID uuid, Camp camp) {
        super(uuid, camp);
    }

    @Override
    public String getName() {
        return "§aXelon";
    }

    @Override
    public String getDescription() {
        return "§8» §7Vous êtes §aXelon§7.\n §8» §7Vous devez gagner avec les §aGentils§7 et ce jusqu'à la fin de la partie.\n \n" +
                "  §5§lVos Commandes:§r§7\n§8» §7À chaque début d'épisode, à l'aide de la commande §f/kp boost [Joueur]§7, Qui vous permet de sélectionné un joueur dans un rayon de 15 blocs auquel il sera donné l'un des effets suivant pendant 15 minutes:\n" +
                "- 3 coeurs en plus\n -§cForce I§7\n - §1Résistance I§7\n - §eVitesse I§7\n - §5Weakness I§7\n - §5Résistance au feu I§7\nVous ne pouvez pas choisir la même personne 2 fois d'affilés.";
    }

    @Override
    public ItemStack getItems() {
        return null;
    }

    @Override
    public void initialize() {

    }

    @EventHandler
    public void onEpisode(GameEpisodeEvent e){
        Player p = Bukkit.getPlayer(getUUID());
        Kp.boost = true;
        p.sendMessage(Main.prefix + "Vous pouvez désormais boost un joueur via la commande §f/kp boost [Joueur]§7.");
    }

}
