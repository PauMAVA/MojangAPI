/*
 * MojangAPI
 * Copyright (c) 2019  Pau Machetti Vallverdú
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package me.PauMAVA.MojangAPI;

import java.util.HashMap;
import java.util.UUID;

public class MojangAPICache {

    private HashMap<String, UUID> UUIDcache;
    private HashMap<UUID, PlayerProfileJson> decodedProfileCache;
    private HashMap<UUID, RawPlayerProfileJson> rawProfileCache;

    MojangAPICache() {
        this.UUIDcache = new HashMap<>();
        this.decodedProfileCache = new HashMap<>();
        this.rawProfileCache = new HashMap<>();
    }

    void saveUUID(String playerName, UUID uuid) {
        this.UUIDcache.put(playerName, uuid);
    }

    <T> void saveProfile(UUID uuid, PlayerProfile profile, Class<T> classType) {
        if (classType.equals(PlayerProfileJson.class)) {
            this.decodedProfileCache.put(uuid, (PlayerProfileJson) profile);
        } else if (classType.equals(RawPlayerProfileJson.class)) {
            this.rawProfileCache.put(uuid, (RawPlayerProfileJson) profile);
        }
    }

    public HashMap<String, UUID> getUUIDcache() {
        return UUIDcache;
    }

    public HashMap<UUID, PlayerProfileJson> getDecodedProfileCache() {
        return decodedProfileCache;
    }

    public HashMap<UUID, RawPlayerProfileJson> getRawProfileCache() {
        return rawProfileCache;
    }

    public UUID getUUID(String playerName) {
        return UUIDcache.get(playerName);
    }

    public PlayerProfileJson getDecodedProfile(String playerName) {
        UUID uuid = checkUUID(playerName);
        return decodedProfileCache.get(uuid);
    }

    public RawPlayerProfileJson getRawProfile(String playerName) {
        UUID uuid = checkUUID(playerName);
        return rawProfileCache.get(uuid);
    }

    public boolean hasCachedUUID(String playerName) {
        return this.UUIDcache.containsKey(playerName);
    }

    public boolean hasCachedDecodedProfile(String playerName) {
        UUID uuid = checkUUID(playerName);
        return this.decodedProfileCache.containsKey(uuid);
    }

    public boolean hasCachedRawProfile(String playerName) {
        UUID uuid = checkUUID(playerName);
        return this.rawProfileCache.containsKey(uuid);
    }

    public void cleanCache(String playerName) {
        UUID uuid = checkUUID(playerName);
        this.UUIDcache.remove(playerName);
        this.decodedProfileCache.remove(uuid);
        this.rawProfileCache.remove(uuid);
    }

    public void cleanCache() {
        this.UUIDcache = new HashMap<>();
        this.decodedProfileCache = new HashMap<>();
        this.rawProfileCache = new HashMap<>();
    }

    private UUID checkUUID(String playerName) {
        UUID uuid = getUUID(playerName);
        if (uuid == null) {
            return MojangAPI.getInstance().getPlayerInfoHandler().fetchUUID(playerName);
        }
        return uuid;
    }
}
