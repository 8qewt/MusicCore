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
package fifthlight.musiccore.internal.playlist;

import com.alibaba.fastjson.JSONObject;
import fifthlight.musiccore.Picture;
import fifthlight.musiccore.internal.NeteasePicture;
import fifthlight.musiccore.internal.searchresult.netease.NeteaseSongSearchResult;
import fifthlight.musiccore.playlist.Playlist;
import fifthlight.musiccore.search.searchresult.SearchResult;
import fifthlight.musiccore.song.Song;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author fifth_light
 */
public class NeteasePlaylist extends Playlist {

    private final JSONObject o;

    public NeteasePlaylist(JSONObject o) {
        this.o = o.getJSONObject("playlist");
    }

    @Override
    public String getName() {
        return o.getString("name");
    }

    @Override
    public List<String> getSubNames() {
        return null;
    }

    @Override
    public Set<Picture> getPictures() throws IOException {
        Set set = new HashSet();
        set.add(new NeteasePicture(o.getLong("coverImgId")));
        return set;
    }

    @Override
    public SearchResult<Song> getSongs() throws IOException {
        return new NeteaseSongSearchResult(o, 1);
    }

    @Override
    public String getID() {
        return String.valueOf(o.getInteger("id"));
    }
}
