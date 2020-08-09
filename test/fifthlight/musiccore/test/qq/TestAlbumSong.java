/*
 * Copyright (C) 2020 fifth_light
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
import fifthlight.musiccore.search.IDSearch;
import fifthlight.musiccore.search.searchresult.SearchResult;
import fifthlight.musiccore.song.Song;
import java.io.IOException;
import org.junit.Test;

/**
 *
 * @author liuyujie
 */
public class TestAlbumSong {
    @Test
    public void test() throws IOException {
        IDSearch search = new IDSearch("225981689");
        SearchResult<Song> sr = QQMusicFactory.getInstance().getSongs(search);
        Song song = sr.getItems(0).get(0);
        System.out.println(song.getAlbum().getSongs().getItems(0));
    }
}
