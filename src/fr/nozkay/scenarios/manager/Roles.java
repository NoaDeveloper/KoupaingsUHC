package fr.nozkay.scenarios.manager;

import fr.nozkay.scenarios.Enum.Camp;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;

public interface Roles {

    UUID getUUID();
    String getName();
    String getDescription();
    Camp getCamp();
    void setCamp(Camp camp);
    boolean isCamp(Camp camp);
    ItemStack getItems();
    Integer getForce();
    Integer getResi();
    Integer getSpeed();
    void setForce(Integer force);
    void setResi(Integer resi);
    void setSpeed(Integer speed);

    void initialize();

}
