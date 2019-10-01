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
import fifthlight.musiccore.internal.playlist.NeteasePlaylist;
import fifthlight.musiccore.playlist.Playlist;
import fifthlight.musiccore.search.searchresult.SearchResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fifth_light
 */
public class NeteasePlaylistSearchResult extends SearchResult<Playlist> {

    ArrayList<Playlist> playlists = new ArrayList<Playlist>();

    public NeteasePlaylistSearchResult(List<JSONObject> obj, int type) {
        switch (type) {
            case 0:
                for (JSONObject o : obj) {
                    playlists.add(new NeteasePlaylist(o.getJSONObject("playlist")));
                }
                break;
            default:
                throw new RuntimeException();
        }
    }

    @Override
    public List getItems(int page) throws IOException {
        if (page == 0) {
            return playlists;
        } else {
            return null;
        }
    }

    @Override
    public int length() {
        return playlists.size();
    }

    @Override
    public int pageLength() {
        return 1;
    }

}
