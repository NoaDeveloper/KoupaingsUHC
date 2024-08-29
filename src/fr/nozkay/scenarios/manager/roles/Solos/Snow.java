package fr.nozkay.scenarios.manager.roles.Solos;

import fr.nozkay.Main;
import fr.nozkay.scenarios.Enum.Camp;
import fr.nozkay.scenarios.Utils.Title;
import fr.nozkay.scenarios.Utils.UtilScenar;
import fr.nozkay.scenarios.manager.RolesImpl;
import fr.rolan.uhc.manager.players.UHCPlayerManager;
import fr.rolan.uhc.util.player.EffectUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Snow extends RolesImpl {
    public Snow(UUID uuid, Camp camp) {
        super(uuid, camp);
    }

    @Override
    public String getName() {
        return "§6Snow";
    }
    private Map<Location, Material> originalBlocks = new HashMap<>();



    @Override
    public String getDescription() {
        return "§8» §7Vous êtes §6Snow§7.\n §8» §7Vous devez gagner avec §6Snow§7 en duo et ce jusqu'à la fin de la partie.\n \n" +
                "";
    }

    @Override
    public ItemStack getItems() {
        return UtilScenar.getItem(Material.NETHER_STAR,1,"§6Oeria §7| Clic Droit", Arrays.asList("",""));
    }

    @Override
    public void initialize() {
        Player p = Bukkit.getPlayer(getUUID());
        EffectUtil.giveInfiniteEffect(p, PotionEffectType.SPEED,1);
        new BukkitRunnable(){
            @Override
            public void run(){
                Player pl = Bukkit.getPlayer(UtilScenar.getRole("§6Snow"));
                if(pl.getLocation().distance(p.getLocation()) < 15){
                    setResi(7);
                }else{
                    if(getResi() > 6){
                        setResi(getResi() - 7);
                    }
                }
            }
        }.runTaskTimer(Main.instance,0,20);
    }
    int cd = 0;
    @EventHandler
    public void onItem(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(p.getUniqueId() == getUUID() && e.getItem() != null && e.getItem().getItemMeta().hasDisplayName() && e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Oeria §7| Clic Droit")){
            if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)){
                if(cd == 0){
                    cd = 600;
                    createSphere(p.getLocation(),25,Material.GLASS);
                    EffectUtil.giveEffect(p,PotionEffectType.DAMAGE_RESISTANCE,60,1);
                    EffectUtil.giveEffect(p,PotionEffectType.INCREASE_DAMAGE,60,1);
                    p.sendMessage(Main.use("Oeria"));
                    new BukkitRunnable(){
                        @Override
                        public void run(){
                            restoreOriginalBlocks();
                        }
                    }.runTaskLater(Main.instance,20*60);
                    new BukkitRunnable(){
                        @Override
                        public void run(){
                            if(cd == 0){
                                this.cancel();
                            }else{
                                cd--;
                            }
                        }
                    }.runTaskTimer(Main.instance,0,20);
                }else{
                    p.sendMessage(Main.cooldown("Oeria"));
                    Title.sendActionBar(p,"§cIl vous reste " + cd + " seconde(s) à attendre.");
                }
            }
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        if(e.getBlock().getType().equals(Material.GLASS)){
            e.setCancelled(true);
        }
    }

    public void createSphere(Location center, int radius, Material material) {
        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    Location loc = center.clone().add(new Vector(x, y, z));
                    double distance = loc.distance(center);
                    if (distance <= radius && distance > radius - 1) {
                        Block block = loc.getBlock();
                        originalBlocks.put(loc, block.getType());
                        block.setType(material);
                    }
                }
            }
        }

    }

    public void restoreOriginalBlocks() {
        for (Map.Entry<Location, Material> entry : originalBlocks.entrySet()) {
            Location loc = entry.getKey();
            Material originalMaterial = entry.getValue();
            loc.getBlock().setType(originalMaterial);
        }
        originalBlocks.clear();
    }
}
