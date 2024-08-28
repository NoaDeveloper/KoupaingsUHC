package fr.nozkay.scenarios.Utils;

import fr.nozkay.scenarios.KoupaingsGame;
import fr.nozkay.scenarios.manager.Roles;
import fr.nozkay.scenarios.manager.RolesImpl;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class UtilScenar {

    public static ItemStack getItem(Material material, Integer nb, String name, List<String> lore) {
        ItemStack it = new ItemStack(material, nb);
        ItemMeta itM = it.getItemMeta();
        itM.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        if(name != null) itM.setDisplayName(name);
        if (lore != null) itM.setLore(lore);
        it.setItemMeta(itM);
        return it;
    }
    public static ItemStack getItemEnchant(Material material,Enchantment enchant, Integer puissance, Integer nb, String name, List<String> lore) {
        ItemStack it = new ItemStack(material, 1);
        ItemMeta itM = it.getItemMeta();
        if (enchant != null && puissance != null) itM.addEnchant(enchant, puissance, true);
        if (name != null) itM.setDisplayName(name);
        if (lore != null) itM.setLore(lore);
        it.setItemMeta(itM);
        return it;
    }

    public static ItemStack getPlayerHead(OfflinePlayer player) {
        ItemStack playerHead = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta meta = (SkullMeta) playerHead.getItemMeta();

        meta.setOwner(player.getName());
        meta.setLore(Arrays.asList(" ", "§cCliquez ici pour vous transformer en ce joueur."));
        playerHead.setItemMeta(meta);

        return playerHead;
    }

    public static ItemStack getColoredGlassPane(boolean isGreen, String name) {
        ItemStack glassPane = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) (isGreen ? 5 : 14));
        ItemMeta meta = glassPane.getItemMeta();
        if (meta != null) {
            if(name != null){meta.setDisplayName(name);}
            if(isGreen){
                meta.setLore(Arrays.asList(" ","§7Status: §aActivé", "§7Cliquez ici pour désactiver le rôle."));
            }else{
                meta.setLore(Arrays.asList(" ","§7Status: §cDésactivé", "§7Cliquez ici pour activé le rôle."));
            }
        }
        glassPane.setItemMeta(meta);
        return glassPane;
    }

    public static UUID getRole(String r){
        List<Roles> compo = KoupaingsGame.compo;
        for(Roles role: compo){
            if(role.getName().equalsIgnoreCase(r)){
                return role.getUUID();
            }
        }
        return null;
    }
}
