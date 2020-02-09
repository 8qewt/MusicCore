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

import fifthlight.musiccore.internal.searchresult.EmptySearchResult;
import com.alibaba.fastjson.JSONObject;
import fifthlight.musiccore.HotlistAble;
import fifthlight.musiccore.Picture;
import fifthlight.musiccore.artist.Artist;
import fifthlight.musiccore.internal.picture.qq.QQArtistPicture;
import fifthlight.musiccore.internal.searchresult.qq.QQArtistHotlistSearchResult;
import fifthlight.musiccore.search.searchresult.SearchResult;
import fifthlight.musiccore.song.Song;
import fifthlight.musiccore.util.qq.QQHTTPUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.dom4j.Document;

/**
 *
 * @author fifth_light
 */
public class QQArtist extends Artist implements HotlistAble<Song> {

    private JSONObject shortObj;
    private Document infoObj;
    private long id;
    private String mid;

    public enum dataType {
        FROM_PLAY
    }
    public QQArtist(Object o, dataType type) {
        switch (type) {
            case FROM_PLAY:
                shortObj = (JSONObject) o;
                id = shortObj.getLong("id");
                mid = shortObj.getString("mid");
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
            return null;
        }
    }

    @Override
    public String getTitle() {
        if (shortObj != null) {
            return shortObj.getString("title");
        } else {
            return null;
        }
    }

    private void getInfo() throws IOException {
        if (id != 0) {
            infoObj = QQHTTPUtil.XMLHTTPRequest("https://c.y.qq.com/splcloud/fcgi-bin/fcg_get_singer_desc.fcg?format=xml&singermid="
                    + mid + "&utf8=1&outCharset=utf-8&r=" + new Date().getTime());
        }
    }

    @Override
    public List<Picture> getPictures() throws IOException {
        List<Picture> list = new ArrayList<Picture>();
        if(id != 0){
            list.add(new QQArtistPicture(this.mid));
        }
        return list;
    }

    @Override
    public SearchResult<Song> getHotList() throws IOException {
        if (id == 0) {
            return new EmptySearchResult();
        }
        return new QQArtistHotlistSearchResult(id,
                QQHTTPUtil.JSONHTTPRequest("https://c.y.qq.com/v8/fcg-bin/fcg_v8_singer_track_cp.fcg?singerid="
                        + id + "begin=0&num=30&order=listen&newsong=1&platform=mac"));
    }

    @Override
    public String getDescription() throws IOException {
        if (id == 0) {
            return null;
        }
        if (infoObj == null) {
            getInfo();
        }
        return infoObj.selectSingleNode("//desc").getText();
    }

    @Override
    public String getID() {
        return String.valueOf(id);
    }

    public String getMID() {
        return mid;
    }

}
