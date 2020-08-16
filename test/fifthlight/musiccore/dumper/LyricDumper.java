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
package fifthlight.musiccore.dumper;

import fifthlight.musiccore.song.lyric.Lyric;
import fifthlight.musiccore.song.lyric.LyricMetadata;
import fifthlight.musiccore.song.lyric.TimeLine;
import fifthlight.musiccore.song.lyric.TimeLineLyric;
import fifthlight.musiccore.util.DumpWriter;

/**
 *
 * @author liuyujie
 */
public class LyricDumper extends Dumper {

    protected LyricDumper() {
    }

    @Override
    public boolean canDump(Object object) {
        return object instanceof Lyric;
    }

    @Override
    public void dump(Object lrc, DumpWriter out) throws Exception {
        if(lrc instanceof LyricMetadata) {
            LyricMetadata metadata = (LyricMetadata) lrc;
            out.println("Metadatas:");
            out.increaseTab();
            out.println("Title: ", metadata.getTitle());
            out.println("Author: ", metadata.getAuthor());
            out.println("Artist: ", metadata.getArtistName());
            out.println("Album: ", metadata.getAlbumName());
            out.reduceTab();
        }
        if(lrc instanceof TimeLineLyric) {
            for(TimeLine tl : ((TimeLineLyric) lrc).getTimeLinesList()){
                out.println("Time: ", tl.getTime(), " Text: ", tl.getText());
            }
        } else if(lrc != null) {
            out.println("Lyric: ", ((Lyric) lrc).getLyric());
        }
    }
}
