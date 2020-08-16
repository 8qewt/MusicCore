/*
 * Copyright (C) 2019 liuyujie
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
import fifthlight.musiccore.song.lyric.Lyric;
import fifthlight.musiccore.song.lyric.TimeLine;
import fifthlight.musiccore.song.lyric.TimeLineLyric;
import fifthlight.musiccore.util.DumpUtil;
import java.io.IOException;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author liuyujie
 */
public class TestSongLyric {
    @Test
    public void test() throws Exception {
        IDSearch search = new IDSearch("1317110262");
        SearchResult<Song> sr = NeteaseMusicFactory.getInstance().getSongs(search);
        Song song = sr.getItems(0).get(0);
        vaildLyric(song.getLyric());
    }
    
    @Test
    public void testTranslate() throws Exception {
        IDSearch search = new IDSearch("401249251");
        SearchResult<Song> sr = NeteaseMusicFactory.getInstance().getSongs(search);
        Song song = sr.getItems(0).get(0);
        vaildLyric(song.getLyric());
        vaildLyric(song.getTranslatedLyric());
    }
    
    private void vaildLyric(Lyric lrc) throws Exception{
        assertNotNull(lrc);
        System.out.println("---Vaild Lyric---");
        DumpUtil.dump(lrc, true, DumpUtil.getStandardOut());
    }
}
