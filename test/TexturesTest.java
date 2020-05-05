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
        assertEquals("vG2/sBUqKsYGLWO+j+g+5Ta17ArTGntUJjBC55fnmji+5VD7Sq/Er4YLwqMeVoa+zbxigVoosGdBc/nxHLTW+KPX8F1rth/lQh72WnC5WtFlcC0cq52gcGnIIZpYeFdwiwComMULfuKxLQxFXsLTZF/P+pF1xMnp0gLLVwh07f2Rufg5+L6KGhDEOiKxyrlT6hPvS+hs3XAbJyf3eHV2N6siZM3r06NvYbXDs8WHit0i00LTEhxjPOHmUS6ldAHI/xWQLE5lEV+L5cVvUnAD4BFqJpmOmfHpNjdyOAF0Zkhii1ZGhXLyRp4lxsyvIQ8Ub1dV1IiBwUxokVeubAj2szfmH4d4DFiOwhPT0zwRoyDW/UaTBMPbHnbIyrYER0rnzqFPlkDB6kN+edAriixPl1D/GYEjyhPWyX8cJ7opxUN5mnzVR28474ogbis+6hh0v3WLf3dQaMdWaIZnvtykzvgzaCir7UHGlocDGC1V3qQ4wITVU95Z5ADJvnAusuaAZ5enCkcptrr7lbmVqIFgmv2srqeU3Oeu8I7gvLkoZaH6otcVAuqqdXpJKcME1OnaPWplKG/dUvSfVvKsEf70/T59mb+NctVHDR3oHpgP59gXzrZB+tSbdDtV41sBeHjK4t68ARN1K651BMDRLntztHw0aWzTrSbKVpwQzA5Y+oE=", this.mojangAPI.getPlayerInfoHandler().getRawPlayerProfile("GoldLord_").getProperties().get(0).getSignature());
    }
}
