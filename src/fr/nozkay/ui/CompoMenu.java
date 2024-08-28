package fr.nozkay.ui;

import fr.nozkay.scenarios.Utils.UtilScenar;
import fr.nozkay.scenarios.manager.Roles;
import fr.rolan.api.utils.inventory.CustomInventory;
import fr.rolan.api.utils.item.Item;
import fr.rolan.api.utils.player.CCPlayer;
import fr.rolan.uhc.manager.settings.config.ConfigFile;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CompoMenu implements Listener {

    public Inventory inv;
    public List<String> compo = new ArrayList<>();
    public List<String> gentil = new ArrayList<>();
    public List<String> mechant = new ArrayList<>();
    public List<String> solo = new ArrayList<>();

    public void createCompo(){
        inv = Bukkit.createInventory(null,54,"§5Composition");
        inv.setItem(3, UtilScenar.getItem(Material.DIAMOND_SWORD,1,"§aGentils", Arrays.asList("§7Cliquez ici pour obtenir la composition des gentils")));
        inv.setItem(4, UtilScenar.getItem(Material.NETHERRACK,1,"§cMéchants", Arrays.asList("§7Cliquez ici pour obtenir la composition des méchants")));
        inv.setItem(5, UtilScenar.getItem(Material.NETHER_STAR,1,"§6Solitaires", Arrays.asList("§7Cliquez ici pour obtenir la composition des solitaires")));
        initialize();
        int a = 20;
        for(String r: gentil){
            if(a > 24 && a < 29){
                a =29;
            }
            if(a > 33 && a < 38) {
                a = 38;
            }if(compo.contains(r)){
                inv.setItem(a,UtilScenar.getColoredGlassPane(true,r));
            }else{
                inv.setItem(a,UtilScenar.getColoredGlassPane(false,r));
            }a++;
        }
    }

    public void openCompo(Player p){
        p.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        Player p =(Player) e.getWhoClicked();
        if(e.getInventory().getName().equalsIgnoreCase("§5Composition")){
            e.setCancelled(true);
            if(e.getCurrentItem() == null){
                return;
            }
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aGentils")){
                inv = Bukkit.createInventory(null,54,"§5Composition");
                inv.setItem(3, UtilScenar.getItem(Material.DIAMOND_SWORD,1,"§aGentils", Arrays.asList("§7Cliquez ici pour obtenir la composition des gentils")));
                inv.setItem(4, UtilScenar.getItem(Material.NETHERRACK,1,"§cMéchants", Arrays.asList("§7Cliquez ici pour obtenir la composition des méchants")));
                inv.setItem(5, UtilScenar.getItem(Material.NETHER_STAR,1,"§6Solitaires", Arrays.asList("§7Cliquez ici pour obtenir la composition des solitaires")));
                int a = 20;
                for(String r: gentil){
                    if(a > 24 && a < 29){
                        a =29;
                    }
                    if(a > 33 && a < 38) {
                        a = 38;
                    }if(compo.contains(r)){
                            inv.setItem(a,UtilScenar.getColoredGlassPane(true,r));
                    }else{
                            inv.setItem(a,UtilScenar.getColoredGlassPane(false,r));
                    }a++;
                }
            }if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cMéchants")){
                inv = Bukkit.createInventory(null,54,"§5Composition");
                inv.setItem(3, UtilScenar.getItem(Material.DIAMOND_SWORD,1,"§aGentils", Arrays.asList("§7Cliquez ici pour obtenir la composition des gentils")));
                inv.setItem(4, UtilScenar.getItem(Material.NETHERRACK,1,"§cMéchants", Arrays.asList("§7Cliquez ici pour obtenir la composition des méchants")));
                inv.setItem(5, UtilScenar.getItem(Material.NETHER_STAR,1,"§6Solitaires", Arrays.asList("§7Cliquez ici pour obtenir la composition des solitaires")));
                int a = 20;
                for(String r: mechant){
                    if(a > 24 && a < 29){
                        a = 29;
                    }else if(a > 33 && a < 38){
                        a = 38;
                    }else{
                        if(compo.contains(r)){
                            inv.setItem(a,UtilScenar.getColoredGlassPane(true,r));
                        }else{
                            inv.setItem(a,UtilScenar.getColoredGlassPane(false,r));
                        }a++;
                    }
                }
            }if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Solitaires")){
                inv = Bukkit.createInventory(null,54,"§5Composition");
                inv.setItem(3, UtilScenar.getItem(Material.DIAMOND_SWORD,1,"§aGentils", Arrays.asList("§7Cliquez ici pour obtenir la composition des gentils")));
                inv.setItem(4, UtilScenar.getItem(Material.NETHERRACK,1,"§cMéchants", Arrays.asList("§7Cliquez ici pour obtenir la composition des méchants")));
                inv.setItem(5, UtilScenar.getItem(Material.NETHER_STAR,1,"§6Solitaires", Arrays.asList("§7Cliquez ici pour obtenir la composition des solitaires")));
                int a = 20;
                for(String r: solo){
                    if(a > 24 && a < 29){
                        a =29;
                    }else if(a > 33 && a < 38){
                        a = 38;
                    }else{
                        if(compo.contains(r)){
                            inv.setItem(a,UtilScenar.getColoredGlassPane(true,r));
                        }else{
                            inv.setItem(a,UtilScenar.getColoredGlassPane(false,r));
                        }a++;
                    }
                }
            }else if(e.getSlot() > 9 && e.getCurrentItem() != null) {
                String name = e.getCurrentItem().getItemMeta().getDisplayName();
                if(compo.contains(name)){
                    inv.setItem(e.getSlot(),UtilScenar.getColoredGlassPane(false,name));
                    compo.remove(name);
                }else{
                    inv.setItem(e.getSlot(),UtilScenar.getColoredGlassPane(true,name));
                    compo.add(name);
                }
            }
            p.openInventory(inv);
        }
    }

    public void initialize(){
        gentil.add("§aNozkay");
        gentil.add("§aSkyrouf");
        gentil.add("§aRoar");
        gentil.add("§aZarmII");
        gentil.add("§aAlecsix");
        gentil.add("§aCapitaine_Max");
        gentil.add("§aShigarashin");
        gentil.add("§aPingouin_Royal");
        gentil.add("§aTOD");
        gentil.add("§aXelon");
        gentil.add("§aLox");
        mechant.add("§cTijumi");
        mechant.add("§cBlack_Star");
        mechant.add("§cJawp34");
        mechant.add("§cNeifen");
        mechant.add("§cLava_Fetzy");
        solo.add("§6Azgar76");
        solo.add("§6Snow");
        solo.add("§5Louloucario_");
    }
}
