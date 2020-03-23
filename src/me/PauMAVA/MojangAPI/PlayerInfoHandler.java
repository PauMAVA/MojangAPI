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

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.UUID;

public class PlayerInfoHandler {

    private final MojangAPI api = MojangAPI.getInstance();
    private final HTTPHandler httpHandler = api.getHttpHandler();

    public UUID fetchUUID(String playerName) throws NullPointerException {
        if(!httpHandler.checkService(MojangService.MOJANG_API)) {
            System.out.println("Unavaiable service: " + MojangService.MOJANG_API.getKey());
            return null;
        }
        try {
            HttpURLConnection conn = httpHandler.getHTTPConnection(MojangService.MOJANG_API, "uuid", playerName);
            assert conn != null;
            UsernameToUUIDJson data = (UsernameToUUIDJson) httpHandler.fetchJSON(conn, UsernameToUUIDJson.class);
            String stringUUID = (String) data.getClass().getDeclaredField("id").get(data);
            stringUUID = String.format("%s-%s-%s-%s-%s", stringUUID.substring(0,8), stringUUID.substring(8,12), stringUUID.substring(12,16), stringUUID.substring(16,20), stringUUID.substring(20));
            return UUID.fromString(stringUUID);
        } catch (IOException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public PlayerProfileJson getPlayerProfile(UUID uuid) {
        if(!httpHandler.checkService(MojangService.MOJANG_API)) {
            System.out.println("Unavaiable service: " + MojangService.MOJANG_API.getKey());
            return new PlayerProfileJson();
        }
        try {
            HttpURLConnection conn = httpHandler.getHTTPConnection(MojangService.MOJANG_API, "profile", uuid.toString().replace("-", ""));
            assert conn != null;
            PlayerProfileJson data = (PlayerProfileJson) httpHandler.fetchJSON(conn, PlayerProfileJson.class);
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return new PlayerProfileJson();
        }
    }

    public PlayerProfileJson getPlayerProfile(String playerName) {
        return getPlayerProfile(fetchUUID(playerName));
    }

}
