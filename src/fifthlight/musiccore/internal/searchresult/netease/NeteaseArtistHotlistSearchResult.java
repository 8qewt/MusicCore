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
import fifthlight.musiccore.util.netease.NeteaseHTTPUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fifth_light
 */
public class NeteaseArtistHotlistSearchResult extends SearchResult<Song> {

    private final int allSize = 50;
    private final long artistID;
    private final ArrayList<Song> songs = new ArrayList<Song>();

    public NeteaseArtistHotlistSearchResult(long artistID) throws IOException {
        this.artistID = artistID;
        fetchSongs();
    }
    
    private void fetchSongs() throws IOException {
        if(songs.isEmpty()){
            JSONObject o = NeteaseHTTPUtil.getJSONLinuxForward("{\"method\":\"GET\",\"params\":{\"id\":" + artistID
                + ",\"ext\":true,\"top\":" + allSize + "},\"url\":\"https://music.163.com/api/v1/artist/" + artistID + "\"}");
            for(Object song : o.getJSONArray("hotSongs")){
                songs.add(new NeteaseSong((JSONObject) song));
            }
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
        return -1;
    }

    @Override
    public int pageLength() {
        return 1;
    }

}
