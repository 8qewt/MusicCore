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
import fifthlight.musiccore.search.IDSearch;
import fifthlight.musiccore.search.searchresult.SearchResult;
import fifthlight.musiccore.util.DumpUtil;
import java.io.IOException;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author fifth_light
 */
public class TestIDArtistSearch {

    @Test
    public void testSearchIDOnce() throws Exception {
        IDSearch s = new IDSearch("90056");
        SearchResult<Artist> sr = NeteaseMusicFactory.getInstance().getArtists(s);
        assertEquals(sr.length(), 1);
        assertEquals(sr.pageLength(), 1);
        vaildArtist(sr.getItems(0).get(0));
    }

    @Test
    public void testSearchIDMulti() throws Exception {
        IDSearch s = new IDSearch(new String[]{"90056", "18961", "1142042"});
        SearchResult<Artist> sr = NeteaseMusicFactory.getInstance().getArtists(s);
        assertEquals(sr.length(), 3);
        assertEquals(sr.pageLength(), 1);
        for (Artist a : sr.getItems(0)) {
            vaildArtist(a);
        }
    }

    private void vaildArtist(Artist a) throws Exception {
        assertTrue("90056".equals(a.getID()) || "18961".equals(a.getID()) || "1142042".equals(a.getID()));
        if ("90056".equals(a.getID())) {
            assertEquals(a.getName(), "C418");
            assertEquals(a.getTitle(), "C418（丹尼尔·罗森菲尔德、Daniel Rosenfeld）");
        } else if ("414118123".equals(a.getID())) {
            assertEquals(a.getName(), "ClariS");
            assertEquals(a.getTitle(), "ClariS（ クラリス）");
        } else if ("1142042".equals(a.getID())) {
            assertEquals(a.getName(), "徐梦圆");
            assertEquals(a.getName(), a.getTitle());
        }
        System.out.println("---Dump Artist---");
        DumpUtil.dump(a, true, DumpUtil.getStandardOut());
    }
}
