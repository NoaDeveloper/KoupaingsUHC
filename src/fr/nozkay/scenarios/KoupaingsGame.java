package fr.nozkay.scenarios;

import fr.nozkay.Main;
import fr.nozkay.scenarios.Commands.Kp;
import fr.nozkay.scenarios.Enum.Camp;
import fr.nozkay.scenarios.Utils.UtilScenar;
import fr.nozkay.scenarios.manager.Roles;
import fr.nozkay.scenarios.manager.roles.Gentils.*;
import fr.nozkay.scenarios.manager.roles.Mechants.*;
import fr.nozkay.scenarios.manager.roles.Solos.Azgar76;
import fr.nozkay.scenarios.manager.roles.Solos.Louloucario_;
import fr.nozkay.scenarios.manager.roles.Solos.Snow;
import fr.rolan.api.listeners.EventManager;
import fr.rolan.api.scoreboard.SidebarManager;
import fr.rolan.api.utils.player.CCPlayer;
import fr.rolan.api.utils.variable.list.VariableInteger;
import fr.rolan.uhc.commands.CommandRegister;
import fr.rolan.uhc.events.custom.GameEpisodeEvent;
import fr.rolan.uhc.manager.players.UHCPlayer;
import fr.rolan.uhc.manager.players.UHCPlayerManager;
import fr.rolan.uhc.manager.scenarios.Gamemode;
import fr.rolan.uhc.manager.scenarios.ScenarioCategorie;
import fr.rolan.uhc.manager.settings.Settings;
import fr.rolan.uhc.manager.settings.values.ValueCategorie;
import fr.rolan.uhc.manager.settings.values.Values;
import fr.rolan.uhc.tasks.runnable.GameTimer;
import fr.rolan.uhc.util.chat.ChatUtil;
import fr.rolan.uhc.util.player.EffectUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import javax.management.relation.Role;
import java.util.*;

public class KoupaingsGame extends Gamemode implements Listener {

    public HashMap<UUID, Roles> roles = new HashMap<>();
    private Settings rolesTimer;
    public HashMap<Location, UUID> death = new HashMap<>();
    public static HashMap<UUID, Collection<PotionEffect>> deatheffect = new HashMap<>();
    public List<Roles> mechants = new ArrayList<>();
    static public List<Roles> compo= new ArrayList<>();
    public final HashMap<UUID, ItemStack[]> playerInventories = new HashMap<>();
    public HashMap<UUID, UUID> fight = new HashMap<>();

    public KoupaingsGame() {
        super("Koupaings UHC", ScenarioCategorie.GAMEMODES, new ItemStack(Material.LAVA_BUCKET), true);
        this.rolesTimer = new Settings(ValueCategorie.SCENARIO, new VariableInteger("gamemode.koupaingsuhc.rolesTimer", new ItemStack(Material.WATCH), 1200, 60, 2147483647, 30, 60, 120));
    }

    int a = -1;
    @Override
    public void onStart(){
        List<Player> players = UHCPlayerManager.getOnlinePlayers();
        Collections.shuffle(players);
        PluginManager pm = Main.instance.getServer().getPluginManager();
        for(String r: Main.getCompoMenu().compo){
            Bukkit.getLogger().info(" " + players.size());
            if(!players.isEmpty()){
                Bukkit.getLogger().info(" " + players.get(0));
                switch(r){
                    case "§aNozkay":
                        Roles role = new Nozkay(players.get(0).getUniqueId(), Camp.GENTIL);
                        roles.put(players.get(0).getUniqueId(),role);
                        players.remove(0);
                        compo.add(role);
                        pm.registerEvents((Listener) role,Main.instance);
                        break;
                    case "§aZarmII":
                        Roles role1 = new ZarmII(players.get(0).getUniqueId(), Camp.GENTIL);
                        roles.put(players.get(0).getUniqueId(),role1);
                        players.remove(0);
                        compo.add(role1);
                        pm.registerEvents((Listener) role1,Main.instance);
                        break;
                    case "§aAlecsix":
                        Roles role2 = new Alecsix(players.get(0).getUniqueId(), Camp.GENTIL);
                        roles.put(players.get(0).getUniqueId(),role2);
                        players.remove(0);
                        compo.add(role2);
                        pm.registerEvents((Listener) role2,Main.instance);
                        break;
                    case "§aPingouin_Royal":
                        Roles role3 = new Pingouin_Royal(players.get(0).getUniqueId(), Camp.GENTIL);
                        roles.put(players.get(0).getUniqueId(),role3);
                        players.remove(0);
                        compo.add(role3);
                        pm.registerEvents((Listener) role3,Main.instance);
                        break;
                    case "§aTOD":
                        Roles role4 = new TOD(players.get(0).getUniqueId(), Camp.GENTIL);
                        roles.put(players.get(0).getUniqueId(),role4);
                        players.remove(0);
                        compo.add(role4);
                        pm.registerEvents((Listener) role4,Main.instance);
                        break;
                    case "§aShigarashin":
                        Roles role5 = new Shigarashin(players.get(0).getUniqueId(), Camp.GENTIL);
                        roles.put(players.get(0).getUniqueId(),role5);
                        players.remove(0);
                        compo.add(role5);
                        pm.registerEvents((Listener) role5,Main.instance);
                        break;
                    case "§aSkyrouf":
                        Roles role6 = new Skyrouf(players.get(0).getUniqueId(), Camp.GENTIL);
                        roles.put(players.get(0).getUniqueId(),role6);
                        players.remove(0);
                        compo.add(role6);
                        pm.registerEvents((Listener) role6,Main.instance);
                        break;
                    case "§aRoar":
                        Roles role7 = new Roar(players.get(0).getUniqueId(), Camp.GENTIL);
                        roles.put(players.get(0).getUniqueId(),role7);
                        players.remove(0);
                        compo.add(role7);
                        pm.registerEvents((Listener) role7,Main.instance);
                        break;
                    case "§aXelon":
                        Roles role8 = new Xelon(players.get(0).getUniqueId(), Camp.GENTIL);
                        roles.put(players.get(0).getUniqueId(),role8);
                        players.remove(0);
                        compo.add(role8);
                        pm.registerEvents((Listener) role8,Main.instance);
                        break;
                    case "§aLox":
                        Roles role9 = new Lox(players.get(0).getUniqueId(), Camp.GENTIL);
                        roles.put(players.get(0).getUniqueId(),role9);
                        players.remove(0);
                        compo.add(role9);
                        pm.registerEvents((Listener) role9,Main.instance);
                        break;
                    case "§cTijumi":
                        Roles role10 = new Tijumi(players.get(0).getUniqueId(), Camp.MECHANT);
                        roles.put(players.get(0).getUniqueId(),role10);
                        players.remove(0);
                        compo.add(role10);
                        mechants.add(role10);
                        pm.registerEvents((Listener) role10,Main.instance);
                        break;
                    case "§cBlack_Star":
                        Roles role11 = new Black_Star(players.get(0).getUniqueId(), Camp.MECHANT);
                        roles.put(players.get(0).getUniqueId(),role11);
                        players.remove(0);
                        compo.add(role11);
                        mechants.add(role11);
                        pm.registerEvents((Listener) role11,Main.instance);
                        break;
                    case "§cJawp34":
                        Roles role12 = new Jawp34(players.get(0).getUniqueId(), Camp.MECHANT);
                        roles.put(players.get(0).getUniqueId(),role12);
                        players.remove(0);
                        compo.add(role12);
                        mechants.add(role12);
                        pm.registerEvents((Listener) role12,Main.instance);
                        break;
                    case "§cNeifen":
                        Roles role13 = new Neifen(players.get(0).getUniqueId(), Camp.MECHANT);
                        roles.put(players.get(0).getUniqueId(),role13);
                        players.remove(0);
                        compo.add(role13);
                        mechants.add(role13);
                        pm.registerEvents((Listener) role13,Main.instance);
                        break;
                    case "§cLava_Fetzy":
                        Roles role14 = new Lava_Fetzy(players.get(0).getUniqueId(), Camp.MECHANT);
                        roles.put(players.get(0).getUniqueId(),role14);
                        players.remove(0);
                        compo.add(role14);
                        mechants.add(role14);
                        pm.registerEvents((Listener) role14,Main.instance);
                        break;
                    case "§6Azgar76":
                        Roles role15 = new Azgar76(players.get(0).getUniqueId(), Camp.DUO);
                        roles.put(players.get(0).getUniqueId(),role15);
                        players.remove(0);
                        compo.add(role15);
                        pm.registerEvents((Listener) role15,Main.instance);
                        break;
                    case "§6Snow":
                        Roles role16 = new Snow(players.get(0).getUniqueId(), Camp.DUO);
                        roles.put(players.get(0).getUniqueId(),role16);
                        players.remove(0);
                        compo.add(role16);
                        pm.registerEvents((Listener) role16,Main.instance);
                        break;
                    case "§5Louloucario_":
                        Roles role17 = new Louloucario_(players.get(0).getUniqueId(), Camp.SOLO);
                        roles.put(players.get(0).getUniqueId(),role17);
                        players.remove(0);
                        compo.add(role17);
                        pm.registerEvents((Listener) role17,Main.instance);
                        break;
                }
            }
        }
        for(Roles roles: compo){
            Player p = Bukkit.getPlayer(roles.getUUID());
            p.sendMessage("§7§m-------------------------------------------");
            p.sendMessage(roles.getDescription());
            p.sendMessage("§7§m-------------------------------------------");
            if(roles.getItems() != null){
                roles.getItems().setAmount(1);
                p.getInventory().addItem(roles.getItems());
            }
            if(roles.isCamp(Camp.MECHANT)){
                String b = " ";
                for(Roles ro: mechants){
                    b = b+ ", " + Bukkit.getPlayer(ro.getUUID()).getName();
                }if(Bukkit.getPlayer(UtilScenar.getRole("§6Snow")) != null){
                    b = b + Bukkit.getPlayer(UtilScenar.getRole("§6Snow")).getName();
                }
                p.sendMessage(Main.prefix + "§cVoici la liste des méchants" + b);
            }
            if(roles.isCamp(Camp.DUO)){
                if(roles.getName().equalsIgnoreCase("§6Azgar76")){
                    Player pla = Bukkit.getPlayer(UtilScenar.getRole("§6Snow"));
                    p.sendMessage(Main.prefix + "Votre duo est: §6" + pla.getName());
                }if(roles.getName().equalsIgnoreCase("§6Snow")){
                    Player pla = Bukkit.getPlayer(UtilScenar.getRole("§6Azgar76"));
                    p.sendMessage(Main.prefix + "Votre duo est: §6" + pla.getName());
                }
            }
            roles.initialize();
        }
        new BukkitRunnable(){
            @Override
            public void run(){
                for(Roles roles: compo){
                    Player p = Bukkit.getPlayer(roles.getUUID());
                    if(roles.getForce() >= 25){
                        EffectUtil.giveEffect(p,PotionEffectType.INCREASE_DAMAGE,3,1);
                    }if(roles.getResi() >= 20){
                        EffectUtil.giveEffect(p,PotionEffectType.DAMAGE_RESISTANCE,3,1);
                    }if(roles.getSpeed() > 0){
                        p.setWalkSpeed(p.getWalkSpeed() + ((float) roles.getSpeed() /200));
                    }
                }
            }
        }.runTaskTimer(Main.instance,0,20);
    }


    @EventHandler
    public void Damage(EntityDamageByEntityEvent e){
        if(e.getEntity() instanceof Player && e.getDamager() instanceof Player){
            Player damager = (Player) e.getDamager();
            Player p = (Player) e.getEntity();
            for(Roles roles: compo){
                Double damage = e.getDamage();
                if(damager.getUniqueId() == roles.getUUID()){
                    if(roles.getForce() > 0 && roles.getResi() < 25){
                        damage = (roles.getForce()/100 + 1) * damage;
                    }
                }if(p.getUniqueId() == roles.getUUID()){
                    if(roles.getResi() > 0 && roles.getResi() < 20){
                        damage = (roles.getResi()/100 + 1) / damage;
                    }
                }
                e.setCancelled(true);
                p.damage(damage);
            }
        }
    }

    @EventHandler
    public void onEpisode(GameEpisodeEvent e){
        if(e.getEpisode() == 2){
            for(Roles role: compo){
                Player p = Bukkit.getPlayer(role.getUUID());
                p.sendMessage("§7§m-------------------------------------------");
                p.sendMessage(role.getDescription());
                p.sendMessage("§7§m-------------------------------------------");
            }
        }if(e.getEpisode() > 3){
            for(Player p: UHCPlayerManager.getPlayingsPlayer()){
                if(Main.getGame().roles.get(p.getUniqueId()).getName().equalsIgnoreCase("§aLox")){
                    p.getInventory().addItem(UtilScenar.getItem(Material.REDSTONE,1,"§6Powerball §7| Clic Droit", Arrays.asList("","")));
                }
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Player && e.getEntity() instanceof Player){
            Player damager = (Player) e.getDamager();
            Player p = (Player) e.getEntity();
            fight.put(p.getUniqueId(),damager.getUniqueId());
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        e.setDeathMessage(" ");
        Player p = e.getEntity();
        Roles role = roles.get(p.getUniqueId());
        playerInventories.put(p.getUniqueId(), p.getInventory().getContents());
        death.put(p.getLocation(), p.getUniqueId());
        deatheffect.put(p.getUniqueId(),p.getActivePotionEffects());
        Bukkit.broadcastMessage("§7§m-------------------------------");
        Bukkit.broadcastMessage("§8» §f" + p.getName() + " §7est mort,");
        Bukkit.broadcastMessage("§8» §7Son rôle était: " + role.getName());
        Bukkit.broadcastMessage("§7§m-------------------------------");
    }

    @Override
    public void createScoreboard(CCPlayer p, SidebarManager.SidebarEditor e) {
        UHCPlayer player = UHCPlayerManager.getPlayer(p);
        e.clear();
        List<String> list = Arrays.asList("&8&m------------", "&5Host: &f<host_name>", "&5Players: &f<number_players>", "&8&m------------", "&5Timer: &f<game_timer>", "&5PvP: &f<timers.pvp>", "&5Meetup: &f<timers.meetup>", "&5R<o1>le: &f<gamemode.koupaingsuhc.rolesTimer>", "&8&m------------", "&5Kills: &f<kills>",
                "&8&m------------", "&5Border: &f<border>", "&5Episode: &f<episode>", "&8&m------------", "&6Made by Nozkay");
        for (String line : list) {
            String final_line = ChatUtil.convertText(line);
            if (final_line.contains("<episode>")) {
                if (!Values.TIMERS_EPISODE_ACTIVATION.getBoolean())
                    continue;
                final_line = final_line.replace("<episode>", "" + GameTimer.getEpisode());
            }
            if (final_line.contains("<kills>"))
                final_line = final_line.replace("<kills>", "" + player.getInfos().getCurrentKills());
            if (final_line.contains("<") && final_line.contains(">"))
                continue;
            e.add(final_line);
        }
    }



    public Settings getTimerRoles() {
        return rolesTimer;
    }
}
