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

import me.PauMAVA.MojangAPI.MojangAPI;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TexturesTest {

    private MojangAPI mojangAPI;

    public TexturesTest() {
        this.mojangAPI = new MojangAPI();
    }


    @Test
    void testTextures() {
        assertEquals("http://textures.minecraft.net/texture/a4776c280c2aebad1d5f18c0738ebc079551df1c1e6ea94298b2e863e58f24b0", this.mojangAPI.getPlayerInfoHandler().getPlayerProfile("GoldLord_").getTextures().getSkin().getUrl());
        assertEquals("YqhtB13Eyo62pinGeEv27RJKzVZNGCbyVP5wfoV+12gjrfHwrZqOv32dAHl4z7WljvS62dvMpnWEQR4bSSTQsZFpseNtmMav45AsSkB5rtC/NPUtUtqCKrwXtLB/IwedUHMc+s5SdC0rgPxdiGLdYD1b2aGtq6wVeS6g5FWE/KFNZW/MC7ZtJW515/6WC6gUdhWpHdFhsyorO0+CkFXiw0DlXnhTkYd1J7r2HT79MJB3u5VtISBvLmrMEGWaLs5QFhmJ1fuQoYIZUJvjS3m6sudVcZIZb1fHOdP8o845R1a5hzWItD36mJMTtnwBd7zJyrqEzEmt6xqUQFhLMeSiw1V/KEv7jxVzSim1eHiESDicKqcnM2xqaP37UDBXfA3rPs3uO4tKIT81m+BQfJLm0klvfTFrdhRvQcqJRVRkfWs0o3Hie6W3ffk1Vywgw/xr2ICfDz/b8qRF+eOFPyT6tdILEhLzngxHuybfx+pFb7yG0jUiYQC3W0NKmnCXl2/X3+SWvxtzgactQI066U3EVUGafPs7d8g6sOl7lMRlLKrEoOmR1nE0h5f7M9EXmGGR3u0Pv+AdZEtKA8qR05cAwDDBABDsJS2FtoYbSt5rjMLV4KPYQXOIge84+GWaNG77L0ua7CWSkV4iOC9cRXn3VVC6nMh6BQQM0EFjNwsqDZk=", this.mojangAPI.getPlayerInfoHandler().getRawPlayerProfile("GoldLord_").getProperties().get(0).getSignature());
    }
}
