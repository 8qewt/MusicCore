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
import fifthlight.musiccore.artist.Artist;
import fifthlight.musiccore.internal.artist.NeteaseArtist;
import fifthlight.musiccore.search.NameSearch;
import fifthlight.musiccore.search.searchresult.SearchResult;
import fifthlight.musiccore.util.netease.NeteaseHTTPUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fifth_light
 */
public class NeteaseNameArtistSearchResult extends SearchResult<Artist> {

    private final JSONObject firstPage;
    private final int pageSize = 30;
    private final String name;

    public NeteaseNameArtistSearchResult(NameSearch search) throws IOException {
        name = search.getName();
        firstPage = requirePage(0);
    }

    private JSONObject requirePage(int page) throws IOException {
        return NeteaseHTTPUtil.getJSONLinuxForward("{\"method\":\"POST\",\"params\":{\"s\":\""
                + name.replace("\\", "\\\\")
                + "\",\"type\":100,\"total\":true,\"limit\":"
                + pageSize
                + ",\"offset\":"
                + page * pageSize
                + "},\"url\":\"http://music.163.com/api/cloudsearch/pc\"}");
    }

    @Override
    public List<Artist> getItems(int page) throws IOException {
        if (page < pageLength()) {
            JSONObject p;
            if (page == 0) {
                p = firstPage;
            } else {
                p = requirePage(page);
            }
            ArrayList<Artist> Artists = new ArrayList<Artist>();
            for (Object o : p.getJSONObject("result").getJSONArray("artists")) {
                Artists.add(new NeteaseArtist((JSONObject) o, 2));
            }
            return Artists;
        } else {
            return null;
        }
    }

    @Override
    public int length() {
        return firstPage.getJSONObject("result").getInteger("artistCount");
    }

    @Override
    public int pageLength() {
        return (int) Math.ceil((float) length() / pageSize);
    }

}
