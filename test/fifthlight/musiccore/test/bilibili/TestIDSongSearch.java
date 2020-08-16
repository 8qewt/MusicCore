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
package fifthlight.musiccore.test.bilibili;

import fifthlight.musiccore.Picture;
import fifthlight.musiccore.artist.Artist;
import fifthlight.musiccore.factory.BilibiliFactory;
import fifthlight.musiccore.internal.song.BilibiliSong;
import fifthlight.musiccore.search.IDSearch;
import fifthlight.musiccore.search.searchresult.SearchResult;
import fifthlight.musiccore.song.Song;
import fifthlight.musiccore.util.DumpUtil;
import java.io.IOException;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author fifth_light
 */
public class TestIDSongSearch {

    @Test
    public void testSearchIDOnce() throws Exception {
        IDSearch s = new IDSearch("1520577");
        SearchResult<Song> sr = BilibiliFactory.getInstance().getSongs(s);
        assertEquals(sr.length(), 1);
        assertEquals(sr.pageLength(), 1);
        vaildSong((BilibiliSong) sr.getItems(0).get(0));
        s = new IDSearch("14130");
        sr = BilibiliFactory.getInstance().getSongs(s);
        assertEquals(sr.length(), 1);
        assertEquals(sr.pageLength(), 1);
        vaildSong((BilibiliSong) sr.getItems(0).get(0));
    }

    private void vaildSong(BilibiliSong s) throws Exception {
        if (s.getID().equals("14130")) {
            assertEquals("八辈子", s.getName());
            assertEquals(1250752, s.getAVID());
            assertEquals("BV1Sx411T7XB", s.getBVID());
        } else if (s.getID().equals("1520577")) {
            assertEquals("Hollow - Full Song Edit", s.getName());
            assertEquals(540345579, s.getAVID());
            assertEquals("BV14i4y1t7jY", s.getBVID());
        }
        System.out.println("---Vaild Song---");
        DumpUtil.dump(s, false, DumpUtil.getStandardOut());
    }
}
