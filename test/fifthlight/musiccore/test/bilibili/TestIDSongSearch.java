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
    public void testSearchIDOnce() throws IOException {
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

    private void vaildSong(BilibiliSong s) throws IOException {
        System.out.println("---Vaild Song---");
        System.out.println("id: " + s.getID());
        System.out.println("name: " + s.getName());
        System.out.println("aid: " + s.getAVID());
        System.out.println("bvid: " + s.getBVID());
        System.out.println("description:" + s.getDescription());
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
