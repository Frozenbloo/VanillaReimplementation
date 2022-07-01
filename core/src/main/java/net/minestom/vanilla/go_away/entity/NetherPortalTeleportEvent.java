package net.minestom.vanilla.go_away.entity;

import net.minestom.server.coordinate.Point;
import net.minestom.server.entity.Entity;
import net.minestom.server.event.Event;
import net.minestom.server.event.trait.CancellableEvent;
import net.minestom.server.event.trait.EntityEvent;
import net.minestom.server.instance.Instance;
import net.minestom.vanilla.go_away.system.NetherPortal;
import org.jetbrains.annotations.NotNull;

/**
 * Triggered when a nether portal attempts to teleport entities between dimensions
 */
public class NetherPortalTeleportEvent implements Event, CancellableEvent, EntityEvent {

    private boolean cancelled = false;
    private final Entity entity;
    private final Point portalBlockPosition;
    private final NetherPortal portal;
    private final long ticksSpentInPortal;
    private Instance targetInstance;
    private Point targetPosition;
    private NetherPortal targetPortal;
    private boolean createsNewPortal;

    public NetherPortalTeleportEvent(
            Entity entity,
            Point portalBlockPosition,
            NetherPortal portal,
            long ticksSpentInPortal,
            Instance targetInstance,
            Point targetPosition,
            NetherPortal targetPortal,
            boolean createsNewPortal
    ) {
        this.entity = entity;
        this.portalBlockPosition = portalBlockPosition;
        this.portal = portal;
        this.ticksSpentInPortal = ticksSpentInPortal;
        this.targetInstance = targetInstance;
        this.targetPosition = targetPosition;
        this.targetPortal = targetPortal;
        this.createsNewPortal = createsNewPortal;
    }

    /**
     * Teleporting entity
     */
    public @NotNull Entity getEntity() {
        return entity;
    }

    /**
     * Position of the portal block which triggered the teleportation
     */
    public Point getPortalBlockPosition() {
        return portalBlockPosition;
    }

    /**
     * CAN BE NULL. The Nether portal trying to teleport an entity. Can be null if the portal block is not part of a nether portal frame
     * (for instance, placed with /setblock)
     */
    public NetherPortal getPortal() {
        return portal;
    }

    /**
     * Number of ticks the entity spent in portal before this event
     */
    public long getTicksSpentInPortal() {
        return ticksSpentInPortal;
    }

    /**
     * Instance to teleport the entity to
     */
    public Instance getTargetInstance() {
        return targetInstance;
    }

    /**
     * Instance to teleport the entity to
     */
    public void setTargetDimension(Instance targetInstance) {
        this.targetInstance = targetInstance;
    }

    /**
     * Position to teleport the entity to. Set to the center of the linked portal, if available
     */
    public Point getTargetPosition() {
        return targetPosition;
    }

    /**
     * Position to teleport the entity to. Set by default to the center of the linked portal, if available
     */
    public void setTargetPosition(Point targetPosition) {
        this.targetPosition = targetPosition;
    }

    /**
     * The linked Nether Portal to teleport to, if any
     */
    public NetherPortal getTargetPortal() {
        return targetPortal;
    }

    /**
     * Set the portal to teleport to. Warning: the position to teleport the entity to is defined by {@link #getTargetPosition()}
     */
    public void setTargetPortal(NetherPortal targetPortal) {
        this.targetPortal = targetPortal;
    }

    /**
     * Should the teleportation create a new portal on the other side?
     */
    public boolean createsNewPortal() {
        return createsNewPortal;
    }

    /**
     * @see #createsNewPortal
     */
    public void createsNewPortal(boolean createNewPortal) {
        this.createsNewPortal = createNewPortal;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }
}
