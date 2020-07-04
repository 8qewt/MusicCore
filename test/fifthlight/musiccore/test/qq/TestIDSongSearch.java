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

import fifthlight.musiccore.Picture;
import fifthlight.musiccore.artist.Artist;
import fifthlight.musiccore.factory.QQMusicFactory;
import fifthlight.musiccore.internal.song.QQSong;
import fifthlight.musiccore.search.MIDSearch;
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
public class TestIDSongSearch {

    @Test
    public void testSearchIDOnce() throws IOException {
        MIDSearch s = new MIDSearch("000915uD19lawX");
        SearchResult<Song> sr = QQMusicFactory.getInstance().getSongs(s);
        assertEquals(sr.length(), 1);
        assertEquals(sr.pageLength(), 1);
        vaildSong((QQSong) sr.getItems(0).get(0));
        s = new MIDSearch("0045yG4H4P0rMz");
        sr = QQMusicFactory.getInstance().getSongs(s);
        assertEquals(sr.length(), 1);
        assertEquals(sr.pageLength(), 1);
        vaildSong((QQSong) sr.getItems(0).get(0));
    }

    private void vaildSong(QQSong s) throws IOException {
        assertTrue("000915uD19lawX".equals(s.getMID()) || "0045yG4H4P0rMz".equals(s.getMID()));
        if ("000915uD19lawX".equals(s.getMID())) {
            assertEquals(s.getName(), "ちょちょちょ!ゆるゆり☆かぷりっちょ!!! (七森中☆生徒会ver.)");
            assertEquals(s.getName(), s.getTitle());
        } else if("0045yG4H4P0rMz".equals(s.getMID())){
            assertEquals(s.getName(), "daze");
            assertEquals(s.getTitle(), "daze (《目隐都市的演绎者》TV动画片头曲)");
        }
        System.out.println("---Vaild Song---");
        System.out.println("id: " + s.getID());
        System.out.println("name: " + s.getName());
        System.out.println("artists: ");
        List<Artist> as = s.getArtists();
        for (Artist a : as) {
            System.out.println("    name:" + a.getName());
            System.out.println("    title:" + a.getTitle());
            System.out.println("    description:" + a.getDescription());
            if (a.getPictures() != null) {
                for (Picture p : a.getPictures()) {
                    System.out.println("    picture:" + p.getURL(0, 0));
                }
            }
        }
        if (s.getAlbum() != null) {
            if (s.getAlbum().getPictures() != null) {
                for (Picture p : s.getAlbum().getPictures()) {
                    System.out.println("Album Picture: " + p.getURL(0, 0));
                }
            }
        }
        System.out.println("----------------");
    }
}
