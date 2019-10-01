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
package fifthlight.musiccore.internal.album;

import com.alibaba.fastjson.JSONObject;
import fifthlight.musiccore.Picture;
import fifthlight.musiccore.album.Album;
import fifthlight.musiccore.internal.NeteasePicture;
import fifthlight.musiccore.internal.searchresult.netease.NeteaseSongSearchResult;
import fifthlight.musiccore.search.searchresult.SearchResult;
import fifthlight.musiccore.song.Song;
import fifthlight.musiccore.util.netease.NeteaseHTTPUtil;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author fifth_light
 */
public class NeteaseAlbum extends Album {

    private JSONObject shortObj;
    private JSONObject fullObj;
    private Long id;

    public NeteaseAlbum(JSONObject o, int type) {
        switch (type) {
            case 0:
                shortObj = o;
                id = o.getLong("id");
                break;
            case 1:
                fullObj = o;
                id = o.getLong("id");
                break;
            default:
                throw new RuntimeException();
        }
    }

    @Override
    public String getName() {
        if (shortObj != null) {
            return shortObj.getString("name");
        } else {
            return fullObj.getString("name");
        }
    }

    @Override
    public List<String> getSubNames() {
        return null;
    }

    @Override
    public Set<Picture> getPictures() throws IOException {
        Set set = new HashSet();
        if (shortObj != null) {
            set.add(new NeteasePicture(shortObj.getLong("pic")));
        } else {
            set.add(new NeteasePicture(fullObj.getLong("picId")));
        }
        return set;
    }

    @Override
    public SearchResult<Song> getSongs() throws IOException {
        if (fullObj == null) {
            fullObj = NeteaseHTTPUtil.getJSONLinuxForward("{\"method\":\"GET\",\"params\":{\"id\":" + id
                    + "},\"url\":\"http://music.163.com/api/v1/album/" + id + "\"}").getJSONObject("album");
        }
        return new NeteaseSongSearchResult(fullObj, 0);
    }

}
