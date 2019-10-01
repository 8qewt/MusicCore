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
package fifthlight.musiccore.internal.searchresult.netease;

import com.alibaba.fastjson.JSONObject;
import fifthlight.musiccore.internal.song.NeteaseSong;
import fifthlight.musiccore.search.searchresult.SearchResult;
import fifthlight.musiccore.song.Song;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fifth_light
 */
public class NeteaseArtistHotlistSearchResult extends SearchResult<Song> {

    private final ArrayList<Song> songs = new ArrayList<Song>();

    public NeteaseArtistHotlistSearchResult(JSONObject obj) {
        for (Object o : obj.getJSONObject("artist").getJSONArray("hotSongs")) {
            songs.add(new NeteaseSong((JSONObject) o));
        }
    }

    @Override
    public List<Song> getItems(int page) throws IOException {
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
