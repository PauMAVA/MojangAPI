/*
 * MojangAPI
 * Copyright (c) 2019  Pau Machetti Vallverd�
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

public class MojangAPI {

    private static MojangAPI instance;
    private final HTTPHandler httpHandler;
    private final PlayerInfoHandler playerInfoHandler;

    public MojangAPI() {
        instance = this;
        this.httpHandler = new HTTPHandler();
        this.playerInfoHandler = new PlayerInfoHandler();
    }

    public static MojangAPI getInstance() {
        return instance;
    }

    HTTPHandler getHttpHandler() {
        return this.httpHandler;
    }

    public PlayerInfoHandler getPlayerInfoHandler() {
        return this.playerInfoHandler;
    }
}
