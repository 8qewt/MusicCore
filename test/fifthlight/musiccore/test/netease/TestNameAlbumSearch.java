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

import fifthlight.musiccore.album.Album;
import fifthlight.musiccore.factory.NeteaseMusicFactory;
import fifthlight.musiccore.search.NameSearch;
import fifthlight.musiccore.search.searchresult.SearchResult;
import fifthlight.musiccore.song.Song;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author fifth_light
 */
public class TestNameAlbumSearch {

    @Test
    public void testSearchNameOnce() throws IOException {
        NameSearch s = new NameSearch("YURUYURI");
        SearchResult<Album> sr = NeteaseMusicFactory.getInstance().getAlbums(s);
        assertEquals(sr.length(), 7);
        assertEquals(sr.pageLength(), 1);
        vaildAlbum(sr.getItems(0).get(0));
        vaildAlbum(sr.getItems(0).get(3));
    }

    private void vaildAlbum(Album a) throws IOException {
        assertTrue("2512603".equals(a.getID()) || "39391231".equals(a.getID()));
        if ("2512603".equals(a.getID())) {
            assertEquals(a.getName(), "YURUYURI♪1st.Series Best Album“ゆるゆりずむ♪”");
            assertEquals(a.getSongs().length(), 25);
        } else if ("39391231".equals(a.getID())) {
            assertEquals(a.getName(), "ゆるゆりのおんがく♪ YURUYURI ORIGINALSOUNDTRACK");
            assertEquals(a.getTitle(), "ゆるゆりのおんがく♪ YURUYURI ORIGINALSOUNDTRACK（摇曳百合 原声带）");
            assertEquals(a.getSongs().length(), 34);
        }
        System.out.println("---Vaild Album---");
        System.out.println("id: " + a.getID());
        System.out.println("name: " + a.getName());
        System.out.println("title: " + a.getTitle());
        System.out.println("songs: ");
        int i = 0;
        for (Song s : a.getSongs().getItems()) {
            System.out.println("    name:" + s.getName());
            System.out.println("    id:" + s.getID());
        }
        System.out.println("----------------");
    }
}
