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

public class PlayerProfileJson {

    String profileId;
    String profileName;

    Textures textures;

    public String getProfileId() {
        return profileId;
    }

    public String getProfileName() {
        return profileName;
    }

    public Textures getTextures() {
        return textures;
    }

    @Override
    public String toString() {
        return "{\n" +
                "profileId: " + profileId + "\n" +
                "profileName: " + profileName + "\n" +
                "textures.SKIN.url: " + textures.getSkin().getUrl() + "\n" +
                "}";
    }
}

class Textures {
    private Skin SKIN;

    public Skin getSkin() {
        return SKIN;
    }
}

class Skin {
    private String url;

    public String getUrl() {
        return url;
    }
}

class Cape {
    private String url;

    public String getUrl() {
        return url;
    }
}


