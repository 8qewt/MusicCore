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
import fifthlight.musiccore.search.searchresult.SearchResult;
import fifthlight.musiccore.song.Song;
import fifthlight.musiccore.song.songquality.SongQuality;
import java.io.IOException;
import java.util.Set;
import org.junit.Test;

/**
 *
 * @author fifth_light
 */
public class TestURLGet {

    @Test
    public void testURLGet() throws IOException {
        IDSearch s = new IDSearch("1311345944");
        SearchResult<Song> sr = NeteaseMusicFactory.getInstance().getSongs(s);
        Song song = sr.getItems(0).get(0);
        Set<SongQuality> sqs = song.getAvailableQualities();
        System.out.println("---Qualities---");
        for (SongQuality sq : sqs) {
            System.out.println("Format: " + sq.getType());
            System.out.println("BPS: " + sq.getBps());
            System.out.println("URL: " + song.getURL(sq));
        }
        System.out.println("---------------");
    }

    @Test
    public void testURLGetBest() throws IOException {
        IDSearch s = new IDSearch("1311345944");
        SearchResult<Song> sr = NeteaseMusicFactory.getInstance().getSongs(s);
        Song song = sr.getItems(0).get(0);
        Set<SongQuality> sqs = song.getAvailableQualities();
        SongQuality bsq = null;
        for (SongQuality sq : sqs) {
            if (bsq == null) {
                bsq = sq;
            } else {
                if (bsq.getBps() <= sq.getBps()) {
                    bsq = sq;
                }
            }
        }
        System.out.println("---Best Qualitiy---");
        System.out.println("Format: " + bsq.getType());
        System.out.println("BPS: " + bsq.getBps());
        System.out.println("URL: " + song.getURL(bsq));
        System.out.println("-------------------");
    }
}
