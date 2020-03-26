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
import me.PauMAVA.MojangAPI.PlayerProfileJson;
import me.PauMAVA.MojangAPI.RawPlayerProfileJson;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

public class CacheTest {

    @Test
    void internalCache() {
        MojangAPI api = new MojangAPI();
        UUID apiUUID = api.getPlayerInfoHandler().fetchUUID("GoldLord_");
        PlayerProfileJson apiDecodedProfile = api.getPlayerInfoHandler().getPlayerProfile("GoldLord_");
        RawPlayerProfileJson apiRawPlayerProfile = api.getPlayerInfoHandler().getRawPlayerProfile("GoldLord_");
        assertEquals(apiUUID, api.getMojangAPICache().getUUID("GoldLord_"));
        assertNull(api.getMojangAPICache().getUUID("player"));
        assertEquals(apiDecodedProfile, api.getMojangAPICache().getDecodedProfile("GoldLord_"));
        assertNull(api.getMojangAPICache().getDecodedProfile("player"));
        assertEquals(apiRawPlayerProfile, api.getMojangAPICache().getRawProfile("GoldLord_"));
        assertNull(api.getMojangAPICache().getRawProfile("player"));
        assertTrue(api.getMojangAPICache().hasCachedUUID("GoldLord_"));
        assertFalse(api.getMojangAPICache().hasCachedUUID("player"));
        assertTrue(api.getMojangAPICache().hasCachedDecodedProfile("GoldLord_"));
        assertFalse(api.getMojangAPICache().hasCachedDecodedProfile("player"));
        assertTrue(api.getMojangAPICache().hasCachedRawProfile("GoldLord_"));
        assertFalse(api.getMojangAPICache().hasCachedRawProfile("player"));
    }

    @Test
    void cleanCache() {
        MojangAPI api = new MojangAPI();
        /* UUID */
        api.getPlayerInfoHandler().fetchUUID("ElRichMC");
        api.getPlayerInfoHandler().fetchUUID("GoldLord_");
        api.getPlayerInfoHandler().fetchUUID("killercreeper_55");
        assertTrue(api.getMojangAPICache().hasCachedUUID("ElRichMC"));
        assertTrue(api.getMojangAPICache().hasCachedUUID("GoldLord_"));
        assertTrue(api.getMojangAPICache().hasCachedUUID("killercreeper_55"));
        api.getMojangAPICache().cleanCache("ElRichMC");
        assertFalse(api.getMojangAPICache().hasCachedUUID("ElRichMC"));
        api.getMojangAPICache().cleanCache();
        assertFalse(api.getMojangAPICache().hasCachedUUID("GoldLord_"));
        assertFalse(api.getMojangAPICache().hasCachedUUID("killercreeper_55"));

        /* Decoded profile */
        api.getPlayerInfoHandler().getPlayerProfile("ElRichMC");
        api.getPlayerInfoHandler().getPlayerProfile("GoldLord_");
        api.getPlayerInfoHandler().getPlayerProfile("killercreeper_55");
        assertTrue(api.getMojangAPICache().hasCachedDecodedProfile("ElRichMC"));
        assertTrue(api.getMojangAPICache().hasCachedDecodedProfile("GoldLord_"));
        assertTrue(api.getMojangAPICache().hasCachedDecodedProfile("killercreeper_55"));
        api.getMojangAPICache().cleanCache("ElRichMC");
        assertFalse(api.getMojangAPICache().hasCachedDecodedProfile("ElRichMC"));
        api.getMojangAPICache().cleanCache();
        assertFalse(api.getMojangAPICache().hasCachedDecodedProfile("GoldLord_"));
        assertFalse(api.getMojangAPICache().hasCachedDecodedProfile("killercreeper_55"));

        /* Raw profiles */
        api.getPlayerInfoHandler().getRawPlayerProfile("ElRichMC");
        api.getPlayerInfoHandler().getRawPlayerProfile("GoldLord_");
        api.getPlayerInfoHandler().getRawPlayerProfile("killercreeper_55");
        assertTrue(api.getMojangAPICache().hasCachedRawProfile("ElRichMC"));
        assertTrue(api.getMojangAPICache().hasCachedRawProfile("GoldLord_"));
        assertTrue(api.getMojangAPICache().hasCachedRawProfile("killercreeper_55"));
        api.getMojangAPICache().cleanCache("ElRichMC");
        assertFalse(api.getMojangAPICache().hasCachedRawProfile("ElRichMC"));
        api.getMojangAPICache().cleanCache();
        assertFalse(api.getMojangAPICache().hasCachedRawProfile("GoldLord_"));
        assertFalse(api.getMojangAPICache().hasCachedRawProfile("killercreeper_55"));
    }

    @Test
    void staticCache() {
        MojangAPI.getStaticCache().cleanCache();
        MojangAPI api1 = new MojangAPI();
        MojangAPI api2 = new MojangAPI();
        /* UUID */
        api1.getPlayerInfoHandler().fetchUUID("GoldLord_");
        api2.getPlayerInfoHandler().fetchUUID("ElRichMC");
        assertTrue(api1.getMojangAPICache().hasCachedUUID("GoldLord_"));
        assertTrue(MojangAPI.getStaticCache().hasCachedUUID("GoldLord_"));
        assertFalse(api1.getMojangAPICache().hasCachedUUID("ElRichMC"));
        assertTrue(api2.getMojangAPICache().hasCachedUUID("ElRichMC"));
        assertTrue(MojangAPI.getStaticCache().hasCachedUUID("ElRichMC"));
        assertFalse(api2.getMojangAPICache().hasCachedUUID("GoldLord_"));

        /* Decoded profiles */
        api1.getPlayerInfoHandler().getPlayerProfile("GoldLord_");
        api2.getPlayerInfoHandler().getPlayerProfile("ElRichMC");
        assertTrue(api1.getMojangAPICache().hasCachedDecodedProfile("GoldLord_"));
        assertTrue(MojangAPI.getStaticCache().hasCachedDecodedProfile("GoldLord_"));
        assertFalse(api1.getMojangAPICache().hasCachedDecodedProfile("ElRichMC"));
        assertTrue(api2.getMojangAPICache().hasCachedDecodedProfile("ElRichMC"));
        assertTrue(MojangAPI.getStaticCache().hasCachedDecodedProfile("ElRichMC"));
        assertFalse(api2.getMojangAPICache().hasCachedDecodedProfile("GoldLord_"));

        /* Raw profiles */
        api1.getPlayerInfoHandler().getRawPlayerProfile("GoldLord_");
        api2.getPlayerInfoHandler().getRawPlayerProfile("ElRichMC");
        assertTrue(api1.getMojangAPICache().hasCachedRawProfile("GoldLord_"));
        assertTrue(MojangAPI.getStaticCache().hasCachedRawProfile("GoldLord_"));
        assertFalse(api1.getMojangAPICache().hasCachedRawProfile("ElRichMC"));
        assertTrue(api2.getMojangAPICache().hasCachedRawProfile("ElRichMC"));
        assertTrue(MojangAPI.getStaticCache().hasCachedRawProfile("ElRichMC"));
        assertFalse(api2.getMojangAPICache().hasCachedRawProfile("GoldLord_"));
    }
}
