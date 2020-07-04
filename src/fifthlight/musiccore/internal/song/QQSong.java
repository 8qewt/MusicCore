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
package fifthlight.musiccore.internal.song;

import com.alibaba.fastjson.JSONObject;
import fifthlight.musiccore.Picture;
import fifthlight.musiccore.album.Album;
import fifthlight.musiccore.artist.Artist;
import fifthlight.musiccore.exception.InvaildQualityException;
import fifthlight.musiccore.exception.ParseException;
import fifthlight.musiccore.interfaces.MIDGetAble;
import fifthlight.musiccore.internal.album.QQAlbum;
import fifthlight.musiccore.internal.artist.QQArtist;
import fifthlight.musiccore.song.Song;
import fifthlight.musiccore.song.lyric.LrcLyric;
import fifthlight.musiccore.song.lyric.Lyric;
import fifthlight.musiccore.song.lyric.TextLyric;
import fifthlight.musiccore.song.songquality.SongQuality;
import fifthlight.musiccore.util.Base64;
import fifthlight.musiccore.util.qq.QQHTTPUtil;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * QQ音乐中的一首歌曲
 *
 * @author fifth_light
 */
public class QQSong extends Song implements MIDGetAble {

    private final JSONObject obj;
    private Long ID;
    private String MID;
    private dataType dataType;
    private JSONObject lyricObject;

    public enum dataType {
        FROM_PLAY,
        FROM_ARTIST_HOTLIST,
        FROM_ALBUM,
        FROM_SEARCH
    }

    /**
     * 创建一个QQSong。 请不要使用这个方法创建QQSong！ 正确的方法是使用MusicFactory创建。
     *
     * @param data 初始化QQSong的数据
     * @param type 初始化数据的类型
     */
    public QQSong(Object data, dataType type) {
        JSONObject jsonData;
        switch (type) {
            case FROM_PLAY:
                jsonData = (JSONObject) data;
                obj = jsonData.getJSONArray("data").getJSONObject(0);
                ID = Long.valueOf(obj.getString("id"));
                MID = obj.getString("mid");
                break;

            case FROM_ALBUM:
                obj = (JSONObject) data;
                ID = Long.valueOf(obj.getString("id"));
                MID = obj.getString("mid");
                break;

            case FROM_ARTIST_HOTLIST:
                jsonData = (JSONObject) data;
                obj = jsonData.getJSONObject("musicData");
                ID = obj.getLong("id");
                MID = obj.getString("mid");
                break;

            case FROM_SEARCH:
                obj = (JSONObject) data;
                ID = obj.getLongValue("id");
                MID = obj.getString("mid");
                break;

            default:
                throw new RuntimeException();
        }
    }

    @Override
    public String getID() {
        return String.valueOf(ID);
    }

    @Override
    public String getMID() {
        return MID;
    }

    @Override
    public String getName() {
        return obj.getString("name");
    }

    @Override
    public String getTitle() {
        if (!"".equals(obj.getString("subtitle"))) {
            return obj.getString("title") + " (" + obj.getString("subtitle") + ")";
        }
        return obj.getString("title");
    }

    @Override
    public List<Artist> getArtists() {
        if (obj != null) {
            ArrayList<Artist> result = new ArrayList<Artist>();
            for (Object o : obj.getJSONArray("singer")) {
                result.add(new QQArtist(o, QQArtist.dataType.FROM_SEARCH));
            }
            return result;
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public Album getAlbum() {
        return new QQAlbum(obj.getJSONObject("album"), QQAlbum.DataType.SHORT);
    }

    @Override
    public Set<SongQuality> getAvailableQualities() throws IOException {
        return new HashSet<SongQuality>();
    }

    @Override
    public List<Picture> getPics() throws IOException {
        return null;
    }

    @Override
    public Lyric getLyric() throws IOException {
        if (lyricObject == null) {
            getLyricObject();
        }
        if (lyricObject == null) {
            return null;
        }
        if (lyricObject.containsKey("lyric")) {
            String lrc = lyricObject.getString("lyric");
            if (lrc != null && !lrc.isEmpty()) {
                lrc = new String(Base64.decode(lrc, Base64.DEFAULT), Charset.forName("utf-8"));
                if (LrcLyric.isLRC(lrc)) {
                    return new LrcLyric(lrc);
                } else {
                    return new TextLyric(lrc);
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public Lyric getTranslatedLyric() throws IOException {
        if (lyricObject == null) {
            getLyricObject();
        }
        if (lyricObject == null) {
            return null;
        }
        if (lyricObject.containsKey("trans")) {
            String lrc = lyricObject.getString("trans");
            if (lrc != null && !lrc.isEmpty()) {
                lrc = new String(Base64.decode(lrc, Base64.DEFAULT), Charset.forName("utf-8"));
                if (LrcLyric.isLRC(lrc)) {
                    return new LrcLyric(lrc);
                } else {
                    return new TextLyric(lrc);
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    private void getLyricObject() throws IOException {
        lyricObject = QQHTTPUtil.JSONHTTPRequest("https://c.y.qq.com/lyric/fcgi-bin/fcg_query_lyric_new.fcg"
                + "?songmid=" + MID + "&g_tk=5381&format=json");
        if (lyricObject.getInteger("code") != 0) {
            lyricObject = null;
        }
    }

    @Override
    public URL getURL(SongQuality quality) throws InvaildQualityException, IOException {
        throw new InvaildQualityException();
    }

}
