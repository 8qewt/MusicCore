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
package fifthlight.musiccore.internal.album;

import com.alibaba.fastjson.JSONObject;
import fifthlight.musiccore.Picture;
import fifthlight.musiccore.album.Album;
import fifthlight.musiccore.artist.Artist;
import fifthlight.musiccore.interfaces.MIDGetAble;
import fifthlight.musiccore.internal.artist.QQArtist;
import fifthlight.musiccore.internal.picture.qq.QQAlbumPicture;
import fifthlight.musiccore.internal.searchresult.OnePageSearchResult;
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
public class QQAlbum extends Album implements MIDGetAble {

    private JSONObject shortObj;
    private JSONObject songObj;
    private JSONObject infoObj;
    private String MID;
    private long ID;

    public enum DataType {
        SHORT,
        ID,
        ALBUM_SONG
    }

    @SuppressWarnings("DeadBranch")
    private void getInfo() throws IOException {
        if (true) {
            // get by ID
            infoObj = QQHTTPUtil.JSONHTTPRequest("https://c.y.qq.com/v8/fcg-bin/fcg_v8_album_info_cp.fcg?albumid="
                    + ID + "&format=json&inCharset=utf8&outCharset=utf-8&platform=yqq");
        } else {
            // by MID
            infoObj = QQHTTPUtil.JSONHTTPRequest("https://c.y.qq.com/v8/fcg-bin/fcg_v8_album_info_cp.fcg?albummid="
                    + MID + "&format=json&inCharset=utf8&outCharset=utf-8&platform=yqq");
        }
        infoObj = infoObj.getJSONObject("data");
    }

    public QQAlbum(Object o, DataType type) {
        switch (type) {
            case SHORT:
                shortObj = (JSONObject) o;
                MID = shortObj.getString("mid");
                ID = shortObj.getLongValue("id");
                break;
            case ID:
                infoObj = (JSONObject) o;
                break;
            case ALBUM_SONG:
                songObj = (JSONObject) o;
                break;
        }
    }

    @Override
    public String getName() {
        if (shortObj != null) {
            return shortObj.getString("name");
        } else if (infoObj == null) {
            return infoObj.getString("name");
        } else if (songObj != null) {
            return songObj.getString("albumname");
        }
        return null;
    }

    @Override
    public String getTitle() throws IOException {
        if (shortObj != null) {
            if (!"".equals(shortObj.getString("subtitle"))) {
                return shortObj.getString("name") + "（" + shortObj.getString("subtitle") + "）";
            } else {
                return shortObj.getString("name");
            }
        } else if (songObj != null) {
            if (!"".equals(songObj.getString("albumdesc"))) {
                return getName() + "（" + songObj.getString("albumdesc") + "）";
            } else {
                return getName();
            }
        } else {
            return null;
        }
    }

    @Override
    public List<Artist> getArtists() throws IOException {
        if (infoObj == null) {
            getInfo();
        }
        List<Artist> result = new ArrayList<Artist>();
        result.add(new QQArtist(infoObj, QQArtist.dataType.FROM_ALBUM));
        return result;
    }

    @Override
    public String getDescription() throws IOException {
        if (infoObj == null) {
            getInfo();
        }
        return infoObj.getString("desc");
    }

    @Override
    public List<Picture> getPictures() throws IOException {
        List<Picture> result = new ArrayList<Picture>();
        if(!"".equals(getMID())){
            result.add(new QQAlbumPicture(getMID()));
        }
        return result;
    }

    @Override
    public SearchResult<Song> getSongs() throws IOException {
        if (infoObj == null) {
            getInfo();
        }
        List<Song> artists = new ArrayList<Song>();
        for(Object o : infoObj.getJSONArray("list")) {
            artists.add(new QQSong(o, QQSong.DataType.FROM_ALBUM));
        }
        return new OnePageSearchResult<Song>(artists);
    }

    @Override
    public String getID() {
        return String.valueOf(ID);
    }
    
    @Override
    public String getMID(){
        return MID;
    }

}
