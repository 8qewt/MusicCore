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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import fifthlight.musiccore.Picture;
import fifthlight.musiccore.album.Album;
import fifthlight.musiccore.artist.Artist;
import fifthlight.musiccore.internal.picture.netease.NeteasePicture;
import fifthlight.musiccore.internal.artist.NeteaseArtist;
import fifthlight.musiccore.internal.searchresult.netease.NeteaseSongSearchResult;
import fifthlight.musiccore.search.searchresult.SearchResult;
import fifthlight.musiccore.song.Song;
import fifthlight.musiccore.util.netease.NeteaseHTTPUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
            return fullObj.getJSONObject("album").getString("name");
        }
    }

    @Override
    public List<String> getSubNames() throws IOException {
        if (fullObj == null) {
            getLongObj();
        }
        JSONArray ja = fullObj.getJSONObject("album").getJSONArray("alias");
        List<String> l = new ArrayList<String>();
        for(Object o : ja){
            l.add((String) o);
        }
        return l;
    }

    @Override
    public List<Picture> getPictures() throws IOException {
        List<Picture> list = new ArrayList<Picture>();
        if (shortObj != null) {
            list.add(new NeteasePicture(shortObj.getLong("pic")));
        } else {
            list.add(new NeteasePicture(fullObj.getLong("picId")));
        }
        return list;
    }
    
    private void getLongObj() throws IOException{
        fullObj = NeteaseHTTPUtil.getJSONLinuxForward("{\"method\":\"GET\",\"params\":{\"id\":" + id
                    + "},\"url\":\"https://music.163.com/api/v1/album/" + id + "\"}");
    }

    @Override
    public SearchResult<Song> getSongs() throws IOException {
        if (fullObj == null) {
            getLongObj();
        }
        return new NeteaseSongSearchResult(fullObj, 0);
    }

    @Override
    public String getID() {
        return String.valueOf(id);
    }

    @Override
    public List<Artist> getArtists() throws IOException {
        if (fullObj == null) {
            getLongObj();
        }
        List<Artist> result = new ArrayList<Artist>();
        for(Object obj : fullObj.getJSONObject("album").getJSONArray("ar")){
            result.add(new NeteaseArtist((JSONObject) obj, 0));
        }
        return result;
    }

    @Override
    public String getDescription() throws IOException {
        if (fullObj == null) {
            getLongObj();
        }
        return fullObj.getJSONObject("album").getString("description");
    }

}
