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
import fifthlight.musiccore.song.Song;
import fifthlight.musiccore.util.DumpUtil;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author fifth_light
 */
public class TestIDSongSearch {

    @Test
    public void testSearchIDOnce() throws Exception {
        IDSearch s = new IDSearch("566442486");
        SearchResult<Song> sr = NeteaseMusicFactory.getInstance().getSongs(s);
        assertEquals(sr.length(), 1);
        assertEquals(sr.pageLength(), 1);
        vaildSong(sr.getItems(0).get(0));
    }

    @Test
    public void testSearchIDMulti() throws Exception {
        IDSearch s = new IDSearch(new String[]{"566442486", "414118123", "106024"});
        SearchResult<Song> sr = NeteaseMusicFactory.getInstance().getSongs(s);
        assertEquals(sr.length(), 3);
        assertEquals(sr.pageLength(), 1);
        for (Song song : sr.getItems(0)) {
            vaildSong(song);
        }
    }

    private void vaildSong(Song s) throws Exception {
        assertTrue("566442486".equals(s.getID()) || "414118123".equals(s.getID()) || "106024".equals(s.getID()));
        if ("566442486".equals(s.getID())) {
            assertEquals(s.getName(), "Resonator");
            assertEquals(s.getName(), s.getTitle());
        } else if ("414118123".equals(s.getID())) {
            assertEquals(s.getName(), "バイバイ YESTERDAY");
            assertEquals(s.getTitle(), "バイバイ YESTERDAY（TV动画《暗杀教室2》OP2 ； TVアニメ「暗殺教室」第2期 OP2）");
        } else if ("106024".equals(s.getID())) {
            assertEquals(s.getName(), "国家");
            assertEquals(s.getName(), s.getTitle());
        }
        System.out.println("---Dump Song---");
        DumpUtil.dump(s, true, DumpUtil.getStandardOut());
    }
}
