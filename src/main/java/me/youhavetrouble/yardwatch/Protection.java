package me.youhavetrouble.yardwatch;

import org.bukkit.Location;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

/**
 * Interface for protection plugins
 */
public interface Protection {

    /**
     * Check if the implementing plugin is enabled
     * @return true if enabled, false if not
     */
    boolean isEnabled();

    /**
     * Check if location is protected. This is for plugins that place or break blocks without a player involved.
     * @param location location to check
     * @return true if location is protected, false if not
     */
    boolean isProtected(Location location);

    /**
     * Check if player can break specific block
     * @param player player
     * @param blockState block to check. This should always be a snapshot and not live object.
     * @return true if player can break block, false if not
     */
    boolean canBreakBlock(Player player, BlockState blockState);

    /**
     * Check if player can place block in specific location
     * @param player player
     * @param location location to check
     * @return true if player can place block, false if not
     */
    boolean canPlaceBlock(Player player, Location location);

    /**
     * Check if player can interact with specific block
     * @param player player
     * @param blockState block to check. This should always be a snapshot and not live object.
     * @return true if player can interact with block, false if not
     */
    boolean canInteract(Player player, BlockState blockState);

    /**
     * Check if player can interact with specific entity
     * @param player player
     * @param target target entity
     * @return true if player can interact with entity, false if not
     */
    boolean canInteract(Player player, Entity target);

    /**
     * Check if player can damage specific entity
     * @param damager entity that is to damage target
     * @param target target entity
     * @return true if player can damage entity, false if not
     */
    boolean canDamage(Entity damager, Entity target);

}
