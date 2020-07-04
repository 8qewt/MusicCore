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
package fifthlight.musiccore.test.qq;

import fifthlight.musiccore.factory.QQMusicFactory;
import fifthlight.musiccore.search.MIDSearch;
import fifthlight.musiccore.search.searchresult.SearchResult;
import fifthlight.musiccore.song.Song;
import fifthlight.musiccore.song.lyric.Lyric;
import fifthlight.musiccore.song.lyric.TimeLine;
import fifthlight.musiccore.song.lyric.TimeLineLyric;
import java.io.IOException;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author liuyujie
 */
public class TestSongLyric {
    @Test
    public void test() throws IOException {
        MIDSearch search = new MIDSearch("001yS0N33yPm1B");
        SearchResult<Song> sr = QQMusicFactory.getInstance().getSongs(search);
        Song song = sr.getItems(0).get(0);
        vaildLyric(song.getLyric());
    }
    
    @Test
    public void testNull() throws IOException {
        MIDSearch search = new MIDSearch("000915uD19lawX");
        SearchResult<Song> sr = QQMusicFactory.getInstance().getSongs(search);
        Song song = sr.getItems(0).get(0);
        vaildLyric(song.getLyric());
        vaildLyric(song.getTranslatedLyric());
    }
    
    @Test
    public void testTranslate() throws IOException {
        MIDSearch search = new MIDSearch("0045yG4H4P0rMz");
        SearchResult<Song> sr = QQMusicFactory.getInstance().getSongs(search);
        Song song = sr.getItems(0).get(0);
        vaildLyric(song.getLyric());
        vaildLyric(song.getTranslatedLyric());
    }
    
    private void vaildLyric(Lyric lrc){
        if(lrc == null){
            System.out.println("Lyric is null.");
            return;
        }
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
