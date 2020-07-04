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
import fifthlight.musiccore.factory.QQMusicFactory;
import fifthlight.musiccore.search.MIDSearch;
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
public class TestSongPic {
    @Test
    public void testSongPic() throws IOException {
        MIDSearch s = new MIDSearch("0045yG4H4P0rMz");
        SearchResult<Song> sr = QQMusicFactory.getInstance().getSongs(s);
        List<Picture> pics = sr.getItems(0).get(0).getAlbum().getPictures();
        assertEquals(pics.size(), 1);
        System.out.println("---Vaild Album Cover---");
        System.out.println("No Res: " + ((Picture) pics.toArray()[0]).getURL(0, 0));
        System.out.println("XRes: " + ((Picture) pics.toArray()[0]).getURL(100, 0));
        System.out.println("YRes: " + ((Picture) pics.toArray()[0]).getURL(0, 100));
        System.out.println("XYRes: " + ((Picture) pics.toArray()[0]).getURL(200, 100));
        System.out.println("----------------");
    }
}
