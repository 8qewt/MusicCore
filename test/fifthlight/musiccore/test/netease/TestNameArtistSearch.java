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
import fifthlight.musiccore.album.Album;
import fifthlight.musiccore.artist.Artist;
import fifthlight.musiccore.factory.NeteaseMusicFactory;
import fifthlight.musiccore.search.NameSearch;
import fifthlight.musiccore.search.searchresult.SearchResult;
import fifthlight.musiccore.song.Song;
import java.io.IOException;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author fifth_light
 */
public class TestNameArtistSearch {

    @Test
    public void testSearchNameOnce() throws IOException {
        NameSearch s = new NameSearch("ClariS");
        SearchResult<Artist> sr = NeteaseMusicFactory.getInstance().getArtists(s);
        assertEquals(sr.length(), 54);
        assertEquals(sr.pageLength(), 2);
        vaildArtist(sr.getItems(0).get(0));
    }

    private void vaildArtist(Artist a) throws IOException {
        assertTrue("18961".equals(a.getID()));
        if ("18961".equals(a.getID())) {
            assertEquals(a.getName(), "ClariS");
            List<String> subNames = a.getSubNames();
            assertEquals(subNames.size(), 1);
            assertEquals(subNames.get(0), "クラリス");
        }
        System.out.println("---Vaild Artist---");
        System.out.println("id: " + a.getID());
        System.out.println("name: " + a.getName());
        System.out.println("subnames: " + String.join(", ", a.getSubNames()));
        System.out.println("description: " + a.getDescription());
        if (a.getPictures() != null) {
            for (Picture p : a.getPictures()) {
                System.out.println("picture: " + p.getURL(0, 0));
            }
        }
        System.out.println("----------------");
    }
}
