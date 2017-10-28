package org.rs2server.rs2.domain.service.impl;

import org.rs2server.rs2.domain.service.api.PermissionService;
import org.rs2server.rs2.model.player.Player;

import javax.annotation.Nonnull;
import java.util.EnumSet;

/**
 * An implementation of a permission service.
 * @author Twelve
 */
public final class PermissionServiceImpl implements PermissionService {

    /**
     * Enum values which indicate a player has some sort of special permission.
     */
    public static final EnumSet<PlayerPermissions> SPECIAL_PERMISSIONS = EnumSet.of(
            PlayerPermissions.ADMINISTRATOR, PlayerPermissions.MODERATOR,
            PlayerPermissions.HELPER
    );


    @Override
    public PlayerPermissions getHighestPermission(@Nonnull Player player) {

        if (player.getDetails().getForumRights() == 2 || is(player, PlayerPermissions.ADMINISTRATOR)) {
            return PlayerPermissions.ADMINISTRATOR;
        }
        if (player.getDetails().getForumRights() == 1 || is(player, PlayerPermissions.MODERATOR)) {
            return PlayerPermissions.MODERATOR;
        }
        if (player.getDetails().getForumRights() == 10 || is(player, PlayerPermissions.HELPER)) {
            return PlayerPermissions.HELPER;
        }
        
        if (is(player, PlayerPermissions.HARDCORE_IRON_MAN)) {
			return PlayerPermissions.HARDCORE_IRON_MAN;
		}

		if (is(player, PlayerPermissions.ULTIMATE_IRON_MAN)) {
			return PlayerPermissions.ULTIMATE_IRON_MAN;
		}

		if (is(player, PlayerPermissions.IRON_MAN)) {
			return PlayerPermissions.IRON_MAN;
		}


//        if (is(player, PlayerPermissions.ADMINISTRATOR)) {
//            return PlayerPermissions.ADMINISTRATOR;
//        }
//
//        if (is(player, PlayerPermissions.MODERATOR)) {
//            return PlayerPermissions.MODERATOR;
//        }
//
//        if (is(player, PlayerPermissions.HELPER)) {
//            return PlayerPermissions.HELPER;
//        }
//
//        if (is(player, PlayerPermissions.ULTIMATE_IRON_MAN)) {
//            return PlayerPermissions.ULTIMATE_IRON_MAN;
//        }
//
//        if (is(player, PlayerPermissions.IRON_MAN)) {
//            return PlayerPermissions.IRON_MAN;
//        }
//
//        if (is(player, PlayerPermissions.DONATOR)) {
//            return PlayerPermissions.DONATOR;
//        }

        return PlayerPermissions.PLAYER;
        
    }

    @Override
    public void give(@Nonnull Player player, @Nonnull PlayerPermissions permission) {
        player.getDatabaseEntity().getPermissions().add(permission);
    }

    @Override
    public void remove(@Nonnull Player player, @Nonnull PlayerPermissions permission) {
        player.getDatabaseEntity().getPermissions().remove(permission);
    }

    @Override
    public boolean is(@Nonnull Player player, @Nonnull PlayerPermissions permission) {
        return player.getDatabaseEntity().getPermissions().contains(permission);
    }

    @Override
    public boolean isAny(@Nonnull Player player, @Nonnull PlayerPermissions... permissions) {
        for (PlayerPermissions permission : permissions) {
            if (is(player, permission)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isAny(@Nonnull Player player, @Nonnull EnumSet<PlayerPermissions> permissions) {
        for (PlayerPermissions permission : permissions) {
            if (is(player, permission)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isNot(@Nonnull Player player, @Nonnull PlayerPermissions permissions) {
        return !is(player, permissions);
    }

    @Override
    public boolean isNotAny(@Nonnull Player player, @Nonnull PlayerPermissions... permissions) {
        return !isAny(player, permissions);
    }

    @Override
   public boolean isNotAny(@Nonnull Player player, @Nonnull EnumSet<PlayerPermissions> permissions) {
        return !isAny(player, permissions);
    }
}
