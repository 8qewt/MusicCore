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
package fifthlight.musiccore.internal.artist;

import com.alibaba.fastjson.JSONObject;
import fifthlight.musiccore.HotlistAble;
import fifthlight.musiccore.Picture;
import fifthlight.musiccore.artist.Artist;
import fifthlight.musiccore.internal.NeteasePicture;
import fifthlight.musiccore.internal.searchresult.netease.NeteaseArtistHotlistSearchResult;
import fifthlight.musiccore.search.searchresult.SearchResult;
import fifthlight.musiccore.song.Song;
import fifthlight.musiccore.util.netease.NeteaseHTTPUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author fifth_light
 */
public class NeteaseArtist extends Artist implements HotlistAble<Song> {

    private JSONObject shortObj;
    private JSONObject fullObj;
    private JSONObject halfFullObj;
    private Long id;

    public NeteaseArtist(JSONObject o, int type) {
        switch (type) {
            case 0:
                shortObj = o;
                id = o.getLong("id");
                break;
            case 1:
                fullObj = o;
                id = o.getJSONObject("artist").getLong("id");
                break;
            case 2:
                halfFullObj = o;
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
        } else if (halfFullObj != null) {
            return halfFullObj.getString("name");
        } else {
            return fullObj.getJSONObject("artist").getString("name");
        }
    }

    @Override
    public List<String> getSubNames() throws IOException {
        if (fullObj == null && halfFullObj == null) {
            getFullObj();
        }
        List<String> result = new ArrayList<String>();
        if (fullObj == null) {
            for (Object name : halfFullObj.getJSONArray("alias")) {
                result.add((String) name);
            }
        } else {
            for (Object name : fullObj.getJSONObject("artist").getJSONArray("alias")) {
                result.add((String) name);
            }
        }
        return result;
    }

    @Override
    public Set<Picture> getPictures() throws IOException {
        Set set = new HashSet();
        if (shortObj != null) {
            set.add(new NeteasePicture(shortObj.getLong("pic")));
        } else if (fullObj != null) {
            set.add(new NeteasePicture(fullObj.getJSONObject("artist").getLong("picId")));
        } else {
            set.add(new NeteasePicture(halfFullObj.getLong("picId")));
        }
        return set;
    }

    @Override
    public SearchResult<Song> getHotList() throws IOException {
        if (fullObj == null) {
            getFullObj();
        }
        return new NeteaseArtistHotlistSearchResult(fullObj);
    }

    @Override
    public String getDescription() throws IOException {
        if (fullObj == null && halfFullObj == null) {
            getFullObj();
        }
        return halfFullObj == null ? fullObj.getJSONObject("artist").getString("briefDesc") : halfFullObj.getString("briefDesc");
    }

    private void getFullObj() throws IOException {
        fullObj = NeteaseHTTPUtil.getJSONLinuxForward("{\"method\":\"GET\",\"params\":{\"id\":" + id
                + ",\"ext\":true,\"top\":0},\"url\":\"http://music.163.com/api/v1/artist/" + id + "\"}");
    }

    @Override
    public String getID() {
        return String.valueOf(id);
    }

}
