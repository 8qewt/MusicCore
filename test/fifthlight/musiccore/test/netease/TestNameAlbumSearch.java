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

import fifthlight.musiccore.factory.NeteaseMusicFactory;
import fifthlight.musiccore.search.IDSearch;
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
public class TestNameAlbumSearch {
    
    @Test
    public void testSearchNameOnce() throws IOException {
        NameSearch s = new NameSearch("QUESTION");
        SearchResult<Song> sr = NeteaseMusicFactory.getInstance().getSongs(s);
        assertEquals(sr.length(), 1500);
        assertEquals(sr.pageLength(), 50);
        vaildSong(sr.getItems(0).get(0));
    }

    private void vaildSong(Song s) {
        assertTrue("404543013".equals(s.getID()) || "414118123".equals(s.getID()));
        if ("404543013".equals(s.getID())) {
            assertEquals(s.getName(), "QUESTION");
            List<String> subNames = s.getSubNames();
            assertEquals(subNames.size(), 1);
            assertEquals(subNames.get(0), "TV动画《暗杀教室2》片头曲；TVアニメ『暗殺教室』第2期オープニング・テーマ");
        }
        System.out.println("---Vaild Song---");
        System.out.println("id: " + s.getID());
        System.out.println("name: " + s.getName());
        System.out.println("----------------");
    }
}
