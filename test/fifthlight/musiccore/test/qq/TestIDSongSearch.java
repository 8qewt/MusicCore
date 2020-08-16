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
package fifthlight.musiccore.test.qq;

import fifthlight.musiccore.factory.QQMusicFactory;
import fifthlight.musiccore.internal.song.QQSong;
import fifthlight.musiccore.search.IDSearch;
import fifthlight.musiccore.search.MIDSearch;
import fifthlight.musiccore.search.searchresult.SearchResult;
import fifthlight.musiccore.song.Song;
import fifthlight.musiccore.util.DumpUtil;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author fifth_light
 */
public class TestIDSongSearch {

    @Test
    public void testSearchIDOnce() throws Exception {
        MIDSearch s = new MIDSearch("000915uD19lawX");
        SearchResult<Song> sr = QQMusicFactory.getInstance().getSongs(s);
        assertEquals(sr.length(), 1);
        assertEquals(sr.pageLength(), 1);
        vaildSong((QQSong) sr.getItems(0).get(0));
        IDSearch ids = new IDSearch("233032206");
        sr = QQMusicFactory.getInstance().getSongs(ids);
        assertEquals(sr.length(), 1);
        assertEquals(sr.pageLength(), 1);
        vaildSong((QQSong) sr.getItems(0).get(0));
    }

    private void vaildSong(QQSong s) throws Exception {
        assertTrue("000915uD19lawX".equals(s.getMID()) || "0045yG4H4P0rMz".equals(s.getMID()));
        if ("000915uD19lawX".equals(s.getMID())) {
            assertEquals(s.getName(), "ちょちょちょ!ゆるゆり☆かぷりっちょ!!! (七森中☆生徒会ver.)");
            assertEquals(s.getName(), s.getTitle());
        } else if("0045yG4H4P0rMz".equals(s.getMID())){
            assertEquals(s.getName(), "daze");
            assertEquals(s.getTitle(), "daze (《目隐都市的演绎者》TV动画片头曲)");
        }
        System.out.println("---Vaild Song---");
        DumpUtil.dump(s, true, DumpUtil.getStandardOut());
    }
}
