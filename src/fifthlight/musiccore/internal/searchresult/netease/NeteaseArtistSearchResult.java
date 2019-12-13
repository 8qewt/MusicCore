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
import fifthlight.musiccore.search.searchresult.SearchResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fifth_light
 */
public class NeteaseArtistSearchResult extends SearchResult<Artist> {

    ArrayList<Artist> artists = new ArrayList<Artist>();

    public NeteaseArtistSearchResult(List<JSONObject> obj) {
        for (JSONObject o : obj) {
            artists.add(new NeteaseArtist(o, 1));
        }
    }

    @Override
    public List getItems(int page) throws IOException {
        if (page == 0) {
            return artists;
        } else {
            return null;
        }
    }

    @Override
    public int length() {
        return artists.size();
    }

    @Override
    public int pageLength() {
        return 1;
    }

}
