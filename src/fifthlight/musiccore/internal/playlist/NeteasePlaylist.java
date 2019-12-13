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
import fifthlight.musiccore.util.netease.NeteaseHTTPUtil;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author fifth_light
 */
public class NeteasePlaylist extends Playlist {

    private JSONObject fullObj = null;
    private JSONObject shortObj = null;

    public NeteasePlaylist(JSONObject o, int type) {
        switch(type){
            case 0:
                this.fullObj = o;
                break;
            case 1:
                this.shortObj = o;
                break;
        }
    }

    @Override
    public String getName() {
        return fullObj != null ? fullObj.getJSONObject("playlist").getString("name") : shortObj.getString("name");
    }

    @Override
    public List<String> getSubNames() {
        return null;
    }

    @Override
    public Set<Picture> getPictures() throws IOException {
        Set set = new HashSet();
        set.add(new NeteasePicture(fullObj != null ? fullObj.getJSONObject("playlist").getLong("coverImgId") : shortObj.getLong("coverImgId")));
        return set;
    }

    @Override
    public SearchResult<Song> getSongs() throws IOException {
        if(fullObj == null){
            fullObj = NeteaseHTTPUtil.getJSONLinuxForward("{\"method\":\"POST\",\"params\":{\"id\":" + getID()
                        + ",\"n\":65536},\"url\":\"http://music.163.com/api/v3/playlist/detail\"}");
        }
        return new NeteaseSongSearchResult(fullObj.getJSONObject("playlist"), 1);
    }

    @Override
    public String getID() {
        return fullObj != null ? fullObj.getJSONObject("playlist").getString("id") : shortObj.getString("id");
    }
}
