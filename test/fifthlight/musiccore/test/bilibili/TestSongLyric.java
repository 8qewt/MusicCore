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
package fifthlight.musiccore.test.bilibili;

import fifthlight.musiccore.factory.BilibiliFactory;
import fifthlight.musiccore.factory.NeteaseMusicFactory;
import fifthlight.musiccore.internal.song.BilibiliSong;
import fifthlight.musiccore.search.IDSearch;
import fifthlight.musiccore.search.searchresult.SearchResult;
import fifthlight.musiccore.song.Song;
import fifthlight.musiccore.song.lyric.Lyric;
import fifthlight.musiccore.song.lyric.TimeLine;
import fifthlight.musiccore.song.lyric.TimeLineLyric;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author liuyujie
 */
public class TestSongLyric {
    @Test
    public void test() throws IOException {
        IDSearch s = new IDSearch("14130");
        SearchResult<Song> sr = BilibiliFactory.getInstance().getSongs(s);
        assertEquals(sr.length(), 1);
        assertEquals(sr.pageLength(), 1);
        vaildLyric(((BilibiliSong) sr.getItems(0).get(0)).getLyric());
    }
    
    @Test
    public void testNoTime() throws IOException {
        IDSearch s = new IDSearch("1520577");
        SearchResult<Song> sr = BilibiliFactory.getInstance().getSongs(s);
        assertEquals(sr.length(), 1);
        assertEquals(sr.pageLength(), 1);
        vaildLyric(((BilibiliSong) sr.getItems(0).get(0)).getLyric());
    }
    
    private void vaildLyric(Lyric lrc){
        assertNotNull(lrc);
        System.out.println("---Vaild Lyric---");
        if(lrc instanceof TimeLineLyric){
            for(TimeLine tl : ((TimeLineLyric) lrc).getTimeLinesList()){
                System.out.println("Time: " + tl.getTime() + " Lyric: " + tl.getText());
            }
        } else {
            System.out.println("Lyric: " + lrc.getLyric());
        }
        System.out.println("-----------------");
    }
}
