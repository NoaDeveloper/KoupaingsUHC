package fr.nozkay.scenarios.manager.roles.Gentils;

import fr.nozkay.Main;
import fr.nozkay.scenarios.Enum.Camp;
import fr.nozkay.scenarios.Utils.UtilScenar;
import fr.nozkay.scenarios.manager.RolesImpl;
import fr.rolan.uhc.events.custom.GameEpisodeEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Nozkay extends RolesImpl {
    public Nozkay(UUID uuid, Camp camp) {
        super(uuid, camp);
    }

    @Override
    public String getName() {
        return "§aNozkay";
    }

    @Override
    public String getDescription() {
        return "§8» §7Vous êtes §aNozkay§7.\n §8» §7Vous devez gagner avec les §aGentils§7 et ce jusqu'à la fin de la partie.\n \n" +
                "  §5§lVos Effets:§r§7\n§8» §cForce I§7 de manière permanente.\n\n" +
                "  §5§lVos Objets:§r§7\n§8» §7Livre §lTranchant III§r.\n\n" +
                "  §5§lParticularités:§r§7\n§8» §7A chaque début d'épisode vous obtenez un message qui vous indique si vous avez croisé le joueur possèdant le rôle §aLox§7.§r\n\n";
    }

    @Override
    public ItemStack getItems() {
        ItemStack it = new ItemStack(Material.ENCHANTED_BOOK,1);
        ItemMeta im = it.getItemMeta();
        im.addEnchant(Enchantment.DAMAGE_ALL,3,true);
        it.setItemMeta(im);
        return it;
    }

    @Override
    public void initialize() {
        this.setForce(25);
        verif();
    }
    boolean lox = false;

    public void verif(){
        new BukkitRunnable(){
            @Override
            public void run(){
                Player p = Bukkit.getPlayer(getUUID());
                if(!(UtilScenar.getRole("§aLox") == null)){
                    Player Lox = Bukkit.getPlayer(UtilScenar.getRole("§aLox"));
                    if(p.getWorld() == Lox.getWorld()){
                        if(p.getLocation().distance(Lox.getLocation()) < 10){
                            lox = true;
                        }
                    }
                }
            }
        }.runTaskTimer(Main.instance, 0,20);
    }

    @EventHandler
    public void EpisodeEvent(GameEpisodeEvent e){
        Player p = Bukkit.getPlayer(this.getUUID());
        if(lox){
            p.sendMessage(Main.prefix + "Vous êtes passés à moins de 10 blocs de lox lors de cet épisode");
        }else{
            p.sendMessage(Main.prefix + "Vous n'êtes pas passés à moins de 10 blocs de lox lors de cet épisode");
        }
    }

}
