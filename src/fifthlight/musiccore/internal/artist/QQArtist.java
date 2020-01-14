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
import fifthlight.musiccore.search.searchresult.SearchResult;
import fifthlight.musiccore.song.Song;
import fifthlight.musiccore.util.qq.QQHTTPUtil;
import java.io.IOException;
import java.util.Set;
import org.w3c.dom.Document;

/**
 *
 * @author fifth_light
 */
public class QQArtist extends Artist implements HotlistAble<Song> {

    private JSONObject shortObj;
    private JSONObject infoObj;
    private Document fullObj;
    private Long id;
    private String mid;

    public QQArtist(Object o, int type) {
        switch (type) {
            case 0:
                shortObj = (JSONObject) o;
                id = shortObj.getLong("id");
                mid = shortObj.getString("mid");
                break;
            case 1:
                fullObj = (Document) o;
                id = Long.parseLong(fullObj.getElementsByTagName("id").item(0).getTextContent());
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
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public String getTitle() {
        if (shortObj != null) {
            return shortObj.getString("title");
        }else {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    private void getInfo() throws IOException{
        infoObj = QQHTTPUtil.JSONHTTPRequest("https://c.y.qq.com/v8/fcg-bin/fcg_v8_singer_track_cp.fcg?singerid=" + id + "begin=0&num=0&order=listen&newsong=1&platform=mac");
    }

    @Override
    public Set<Picture> getPictures() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SearchResult<Song> getHotList() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDescription() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getID() {
        return String.valueOf(id);
    }
    
    public String getMID() {
        return mid;
    }

}
