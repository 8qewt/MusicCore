/*
 * Copyright (C) 2020 liuyujie
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
import fifthlight.musiccore.search.searchresult.SearchResult;
import fifthlight.musiccore.song.Song;
import fifthlight.musiccore.util.qq.QQHTTPUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author liuyujie
 */
public class QQArtistHotlistSearchResult extends SearchResult<Song> {

    private final JSONObject firstObj;
    private final long id;

    public QQArtistHotlistSearchResult(long id, JSONObject o) {
        this.id = id;
        this.firstObj = o;
    }

    @Override
    public List<Song> getItems(int page) throws IOException {
        if (page >= 0 && page < pageLength()) {
            JSONObject o;
            if (page == 0) {
                o = firstObj;
            } else {
                o = QQHTTPUtil.JSONHTTPRequest("https://c.y.qq.com/v8/fcg-bin/fcg_v8_singer_track_cp.fcg?singerid=" + id
                        + "begin=0&num=30&order=listen&newsong=1&platform=mac");
            }
            ArrayList<Song> result = new ArrayList<Song>();
            for (Object dataObj : o.getJSONObject("data").getJSONArray("list")) {
                if (dataObj != null) {
                    result.add(new QQSong(dataObj, QQSong.dataType.FROM_PLAY));
                }
            }
            return result;
        } else {
            return null;
        }
    }

    @Override
    public int length() {
        return firstObj.getJSONObject("data").getInteger("total");
    }

    @Override
    public int pageLength() {
        return (int) Math.ceil(firstObj.getJSONObject("data").getFloat("total") / 30);
    }

}
