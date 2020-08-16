/*
 * Copyright (C) 2019 fifth_light
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
package fifthlight.musiccore.test.netease;

import fifthlight.musiccore.Picture;
import fifthlight.musiccore.artist.Artist;
import fifthlight.musiccore.factory.NeteaseMusicFactory;
import fifthlight.musiccore.search.NameSearch;
import fifthlight.musiccore.search.searchresult.SearchResult;
import fifthlight.musiccore.util.DumpUtil;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author fifth_light
 */
public class TestNameArtistSearch {

    @Test
    public void testSearchNameOnce() throws Exception {
        NameSearch s = new NameSearch("ClariS");
        SearchResult<Artist> sr = NeteaseMusicFactory.getInstance().getArtists(s);
        assertTrue(sr.length() > 54);
        assertEquals(sr.pageLength(), 2);
        vaildArtist(sr.getItems(0).get(0));
    }

    private void vaildArtist(Artist a) throws Exception {
        assertTrue("18961".equals(a.getID()));
        if ("18961".equals(a.getID())) {
            assertEquals(a.getName(), "ClariS");
            assertEquals(a.getTitle(), "ClariS（クラリス）");
        }
        System.out.println("---Dump Artist---");
        DumpUtil.dump(a, true, DumpUtil.getStandardOut());
    }
}
