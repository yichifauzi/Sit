package one.oth3r.sit.file;

import net.minecraft.entity.decoration.DisplayEntity;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.HashMap;

public class Data {
    /**
     * Sit! config file
     */
    private static ServerConfig serverConfig = new ServerConfig();

    public static ServerConfig getServerConfig() {
        return new ServerConfig(serverConfig);
    }

    public static void setServerConfig(ServerConfig newServerConfig) {
        serverConfig = new ServerConfig(newServerConfig);
    }

    /**
     * The default hand config for all new players
     */
    private static HandConfig handConfig = new HandConfig();

    public static HandConfig getHandConfig() {
        return new HandConfig(handConfig);
    }

    public static void setHandConfig(HandConfig newHandConfig) {
        handConfig = new HandConfig(newHandConfig);
    }

    /**
     * the hand config stored per player on the server
     */
    private static final HashMap<ServerPlayerEntity, HandConfig> playerSettings = new HashMap<>();

    public static void clearPlayerSettings() {
        playerSettings.clear();
    }

    public static void setPlayerSetting(ServerPlayerEntity player, HandConfig config) {
        playerSettings.put(player, config);
    }

    public static void removePlayerSetting(ServerPlayerEntity player) {
        playerSettings.remove(player);
    }

    public static HandConfig getPlayerSetting(ServerPlayerEntity player) {
        return playerSettings.getOrDefault(player,handConfig);
    }

    /**
     * a list of every Sit! entity in the server, bound to the player
     */
    private static final HashMap<ServerPlayerEntity, DisplayEntity.TextDisplayEntity> sitEntities = new HashMap<>();

    public static void addSitEntity(ServerPlayerEntity player, DisplayEntity.TextDisplayEntity entity) {
        sitEntities.put(player, entity);
    }

    public static void removeSitEntity(DisplayEntity.TextDisplayEntity entity) {
        sitEntities.values().remove(entity);
    }

    public static DisplayEntity.TextDisplayEntity getSitEntity(ServerPlayerEntity player) {
        return sitEntities.get(player);
    }

    public static HashMap<ServerPlayerEntity, DisplayEntity.TextDisplayEntity> getSitEntities() {
        return new HashMap<>(sitEntities);
    }

    /**
     * a list of players who just joined, to check if they are mounted to a Sit! entity
     */
    private static final HashMap<ServerPlayerEntity, Integer> checkPlayers = new HashMap<>();

    public static void setCheckPlayer(ServerPlayerEntity player, Integer time) {
        checkPlayers.put(player, time);
    }

    public static void removeCheckPlayer(ServerPlayerEntity player) {
        checkPlayers.remove(player);
    }

    public static HashMap<ServerPlayerEntity, Integer> getCheckPlayers() {
        return new HashMap<>(checkPlayers);
    }

    /**
     * loads all config files to memory
     * @param tryLegacy try to load the legacy file, usually only used on server startup
     */
    public static void loadFiles(boolean tryLegacy) {
        ServerConfig.load(tryLegacy);
        HandConfig.load();
    }
}
