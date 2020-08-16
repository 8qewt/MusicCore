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
import fifthlight.musiccore.playlist.Playlist;
import fifthlight.musiccore.search.NameSearch;
import fifthlight.musiccore.search.searchresult.SearchResult;
import fifthlight.musiccore.song.Song;
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
public class TestNamePlaylistSearch {
    @Test
    public void testSearchNameOnce() throws Exception {
        NameSearch s = new NameSearch("网易云音乐喜欢的音乐");
        SearchResult<Playlist> sr = NeteaseMusicFactory.getInstance().getPlaylists(s);
        vaildPlaylist(sr.getItems(0).get(0));
    }

    private void vaildPlaylist(Playlist pl) throws Exception {
        assertTrue("4866675870".equals(pl.getID()));
        assertEquals(pl.getSubNames(), null);
        if ("4866675870".equals(pl.getID())) {
            assertEquals(pl.getName(), "网易云音乐喜欢的音乐");
        }
        System.out.println("---Dump Playlist---");
        DumpUtil.dump(pl, true, DumpUtil.getStandardOut());
    }
}
