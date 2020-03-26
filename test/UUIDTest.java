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

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class UUIDTest {

    private MojangAPI mojangAPI;

    public UUIDTest() {
        this.mojangAPI = new MojangAPI();
    }

    @Test
    public void testUUID() {
        assertEquals(mojangAPI.getPlayerInfoHandler().fetchUUID("GoldLord_"), UUID.fromString("0006f9f3-bde9-4d12-9271-18502d8053dd"));
    }
}
