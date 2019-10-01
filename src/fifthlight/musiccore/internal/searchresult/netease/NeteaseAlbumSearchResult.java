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
import fifthlight.musiccore.album.Album;
import fifthlight.musiccore.internal.album.NeteaseAlbum;
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
public class NeteaseAlbumSearchResult extends SearchResult<Album> {
    ArrayList<Album> albums = new ArrayList<Album>();

    public NeteaseAlbumSearchResult(List<JSONObject> obj, int type) {
        switch (type) {
            case 0:
                for (JSONObject o : obj) {
                    albums.add(new NeteaseAlbum(o.getJSONObject("album"), 1));
                }
                break;
            default:
                throw new RuntimeException();
        }
    }

    @Override
    public List<Album> getItems(int page) throws IOException {
        if (page == 0) {
            return albums;
        } else {
            return null;
        }
    }

    @Override
    public int length() {
        return albums.size();
    }

    @Override
    public int pageLength() {
        return 1;
    }
}
