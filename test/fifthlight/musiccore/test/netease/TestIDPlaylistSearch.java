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
import fifthlight.musiccore.factory.NeteaseMusicFactory;
import fifthlight.musiccore.playlist.Playlist;
import fifthlight.musiccore.search.IDSearch;
import fifthlight.musiccore.search.searchresult.SearchResult;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author fifth_light
 */
public class TestIDPlaylistSearch {

    @Test
    public void testSearchIDOnce() throws IOException {
        IDSearch s = new IDSearch("1");
        SearchResult<Playlist> sr = NeteaseMusicFactory.getInstance().getPlaylists(s);
        assertEquals(sr.length(), 1);
        assertEquals(sr.pageLength(), 1);
        vaildPlaylist(sr.getItems(0).get(0));
    }

    @Test
    public void testSearchIDMulti() throws IOException {
        IDSearch s = new IDSearch(new String[]{"1", "164521381", "2490931595"});
        SearchResult<Playlist> sr = NeteaseMusicFactory.getInstance().getPlaylists(s);
        assertEquals(sr.length(), 3);
        assertEquals(sr.pageLength(), 1);
        for (Playlist playlist : sr.getItems(0)) {
            vaildPlaylist(playlist);
        }
    }

    private void vaildPlaylist(Playlist pl) throws IOException {
        assertTrue("1".equals(pl.getID()) || "164521381".equals(pl.getID()) || "2490931595".equals(pl.getID()));
        assertEquals(pl.getSubNames(), null);
        if ("1".equals(pl.getID())) {
            assertEquals(pl.getName(), "网易云音乐喜欢的音乐");
        } else if ("164521381".equals(pl.getID())) {
            assertEquals(pl.getName(), "C418 - Minecraft【59首MC全曲】");
        } else if ("2490931595".equals(pl.getID())) {
            assertEquals(pl.getName(), "《摇曳百合》音乐全收录");
        }
        System.out.println("---Vaild Playlist---");
        System.out.println("id: " + pl.getID());
        System.out.println("name: " + pl.getName());
        System.out.println("songsLength: " + pl.getSongs().length());
        if (pl.getPictures() != null) {
            for (Picture p : pl.getPictures()) {
                System.out.println("Picture: " + p.getURL(0, 0));
            }
        }
        System.out.println("----------------");
    }
}
