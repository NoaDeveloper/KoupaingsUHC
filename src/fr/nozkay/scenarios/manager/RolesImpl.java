package fr.nozkay.scenarios.manager;

import fr.nozkay.scenarios.Enum.Camp;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;

public abstract class RolesImpl implements Roles, Listener {

    public Integer force = 0;
    public Integer resi = 0;
    public Integer speed = 0;
    private final UUID uuid;
    private Camp camp;

    public RolesImpl(UUID uuid, Camp camp) {
        this.uuid = uuid;
        this.camp = camp;
    }
    @Override
    public UUID getUUID() {
        return uuid;
    }

    @Override
    public Camp getCamp() {
        return camp;
    }

    @Override
    public void setCamp(Camp camp) {
        this.camp = camp;
    }

    @Override
    public boolean isCamp(Camp camp) {
        return camp.equals(this.camp);
    }

    @Override
    public Integer getForce() {
        return force;
    }

    @Override
    public Integer getResi() {
        return resi;
    }

    @Override
    public Integer getSpeed() {
        return speed;
    }

    @Override
    public void setForce(Integer force) {
        this.force = force;
    }

    @Override
    public void setResi(Integer resi) {
        this.resi = resi;
    }

    @Override
    public void setSpeed(Integer speed) {
        this.speed = speed;
    }
}
