package fr.nozkay.scenarios.manager.roles.Mechants;

import fr.nozkay.Main;
import fr.nozkay.scenarios.Enum.Camp;
import fr.nozkay.scenarios.Utils.Title;
import fr.nozkay.scenarios.manager.Roles;
import fr.nozkay.scenarios.manager.RolesImpl;
import fr.rolan.uhc.manager.players.UHCPlayerManager;
import fr.rolan.uhc.util.player.EffectUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class Neifen extends RolesImpl {
    public Neifen(UUID uuid, Camp camp) {
        super(uuid, camp);
    }

    @Override
    public String getName() {
        return "§cNeifen";
    }

    @Override
    public String getDescription() {
        return "§8» §7Vous êtes §cNeifen§7.\n §8» §7Vous devez gagner avec les §cMéchants§7 et ce jusqu'à la fin de la partie.\n \n" +
                "";
    }

    @Override
    public ItemStack getItems() {
        return null;
    }

    public Integer change = 0;

    @Override
    public void initialize() {
        Player p = Bukkit.getPlayer(getUUID());
        EffectUtil.addMaxHealth(p.getUniqueId(),2,true);
        HashMap<Roles, Integer> Force = new HashMap<>();
        for(Player pla: UHCPlayerManager.getPlayingsPlayer()){
            Roles rolee = Main.getGame().roles.get(pla.getUniqueId());
            if(rolee.isCamp(Camp.MECHANT)){
                Force.put(rolee,rolee.getForce());
            }
        }
        new BukkitRunnable(){
            @Override
            public void run(){
                if(p.getHealth() > 8){
                    if(change == 0){
                        Title.sendActionBar(p,"§7Forme: §cGrimm");
                        change = 1;
                    }else if(change == 2){
                        change = 0;
                    }
                    setForce(getForce() + 5);
                    for(Player pl: UHCPlayerManager.getPlayingsPlayer()){
                        Roles role = Main.getGame().roles.get(pl.getUniqueId());
                        if(role.isCamp(Camp.MECHANT)){
                            if(pl.getLocation().distance(p.getLocation()) < 6){
                                if(Force.get(role) < 5){
                                    role.setForce(Force.get(role) + 5);
                                    Force.put(role, Force.get(role) + 5);
                                }
                            }else{
                                if(Force.get(role) > 4){
                                    role.setForce(Force.get(role) - 5);
                                    Force.put(role, Force.get(role) - 5);
                                }
                            }
                        }
                    }
                } else {
                    if(change == 3){
                        Title.sendActionBar(p,"§7Forme: §cAjax");
                        change = 2;
                    }else if(change == 0){
                        change = 3;
                    }
                    EffectUtil.giveEffect(p, PotionEffectType.SPEED,4,2);
                    EffectUtil.giveEffect(p, PotionEffectType.DAMAGE_RESISTANCE,4,1);
                }
            }
        }.runTaskTimer(Main.instance,0,20);
    }


}
