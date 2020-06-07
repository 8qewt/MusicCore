/*
 * Copyright (C) 2020 fifth_light
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

import com.alibaba.fastjson.JSONObject;
import fifthlight.musiccore.internal.song.QQSong;
import fifthlight.musiccore.search.NameSearch;
import fifthlight.musiccore.search.searchresult.SearchResult;
import fifthlight.musiccore.song.Song;
import fifthlight.musiccore.util.qq.QQHTTPUtil;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author liuyujie
 */
public class QQNameSongSearchResult extends SearchResult<Song> {
    
    String searchString;
    JSONObject firstPage;
    private final int pageSize = 30;

    public QQNameSongSearchResult(NameSearch nameSearch) throws IOException {
        searchString = nameSearch.getName();
        firstPage = requirePage(0);
    }
    
    private JSONObject requirePage(int page) throws IOException{
        return QQHTTPUtil.JSONHTTPRequest("https://c.y.qq.com/soso/fcgi-bin/"
                + "client_search_cp?format=json&p=" + page + 1
                + "&n=" + String.valueOf(pageSize)
                + "&aggr=1&loseless=1&cr=1"
                + "&new_json=1&w=" + URLEncoder.encode(searchString, "utf-8")).getJSONObject("data");
    }
    
    @Override
    public List<Song> getItems(int page) throws IOException {
        if (0 <= page && page < pageLength()) {
            JSONObject p;
            if (page == 0) {
                p = firstPage;
            } else {
                p = requirePage(page);
            }
            if (length() > 0) {
                ArrayList<Song> songs = new ArrayList<Song>();
                for (Object o : p.getJSONObject("song").getJSONArray("list")) {
                    songs.add(new QQSong((JSONObject) o, QQSong.dataType.FROM_SEARCH));
                }
                return songs;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public int length() {
        return firstPage.getJSONObject("song").getInteger("totalnum");
    }

    @Override
    public int pageLength() {
        return (int) Math.ceil((float) length() / pageSize);
    }
}
