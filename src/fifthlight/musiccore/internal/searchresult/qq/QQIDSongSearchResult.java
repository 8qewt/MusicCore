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
package fifthlight.musiccore.internal.searchresult.qq;

import fifthlight.musiccore.internal.song.QQSong;
import fifthlight.musiccore.search.searchresult.SearchResult;
import fifthlight.musiccore.song.Song;
import fifthlight.musiccore.util.qq.QQHTTPUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author liuyujie
 */
public class QQIDSongSearchResult extends SearchResult<Song> {

    ArrayList<Song> songs = new ArrayList<Song>();

    public QQIDSongSearchResult(List<String> IDs, boolean isMID) throws IOException {
        if (isMID) {
            for (String str : IDs) {
                songs.add(new QQSong(QQHTTPUtil.JSONHTTPRequest("http://c.y.qq.com/v8/fcg-bin/fcg_play_single_song.fcg?songmid="
                        + str + "&format=json"), QQSong.dataType.FROM_PLAY));
            }
        } else {
            for (String str : IDs) {
                if (!str.matches("^[0-9]*$")) {
                    throw new IllegalArgumentException("ID must be a number");
                }
                songs.add(new QQSong(QQHTTPUtil.JSONHTTPRequest("http://c.y.qq.com/v8/fcg-bin/fcg_play_single_song.fcg?songid="
                        + str + "&format=json"), QQSong.dataType.FROM_PLAY));
            }
        }
    }

    @Override
    public List getItems(int page) throws IOException {
        if (page == 0) {
            return songs;
        } else {
            return null;
        }
    }

    @Override
    public int length() {
        return songs.size();
    }

    @Override
    public int pageLength() {
        return 1;
    }

}
