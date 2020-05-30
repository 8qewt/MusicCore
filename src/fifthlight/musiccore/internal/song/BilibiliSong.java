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
package fifthlight.musiccore.internal.song;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import fifthlight.musiccore.DescriptionAble;
import fifthlight.musiccore.Picture;
import fifthlight.musiccore.album.Album;
import fifthlight.musiccore.artist.Artist;
import fifthlight.musiccore.exception.InvaildQualityException;
import fifthlight.musiccore.internal.artist.BilibiliUser;
import fifthlight.musiccore.internal.picture.BilibiliPicture;
import fifthlight.musiccore.song.Song;
import fifthlight.musiccore.song.lyric.LrcLyric;
import fifthlight.musiccore.song.lyric.Lyric;
import fifthlight.musiccore.song.lyric.TextLyric;
import fifthlight.musiccore.song.songquality.AACSongQuality;
import fifthlight.musiccore.song.songquality.SongQuality;
import fifthlight.musiccore.util.HTTPUtil;
import fifthlight.musiccore.util.bilibili.BilibiliHTTPUtil;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author liuyujie
 */
public class BilibiliSong extends Song implements DescriptionAble {
    
    private JSONObject infoObj;
    private int id;

    public BilibiliSong(JSONObject jo, int type) {
        switch(type){
            case 0:
                // INFO
                infoObj = jo;
                id = infoObj.getInteger("id");
                break;
            default:
                throw new AssertionError();
        }
    }
    
    private void getInfoObj() throws IOException{
        JSONObject jo = BilibiliHTTPUtil
                        .JSONHTTPRequest("https://www.bilibili.com/audio/music-service-c/web/song/info?sid=" + id);
    }

    @Override
    public String getName() {
        return infoObj.getString("title");
    }

    @Override
    public String getTitle() {
        return getName();
    }

    @Override
    public List<Artist> getArtists() {
        List<Artist> result = new ArrayList<Artist>();
        if(infoObj.getIntValue("uid") != 0){
            result.add(new BilibiliUser(infoObj.getIntValue("uid"), infoObj.getString("uname")));
        }
        return result;
    }

    @Override
    public Album getAlbum() {
        return null;
    }

    @Override
    public Set<SongQuality> getAvailableQualities() throws IOException {
        Set<SongQuality> qualities = new HashSet<SongQuality>();
        qualities.add(new AACSongQuality(192000));
        return qualities;
    }

    @Override
    public List<Picture> getPics() throws IOException {
        if(infoObj.containsKey("cover") && !infoObj.getString("cover").isEmpty()){
            ArrayList<Picture> list = new ArrayList<Picture>(1);
            list.add(new BilibiliPicture(infoObj.getString("cover")));
            return list;
        } else {
            return null;
        }
    }

    @Override
    public Lyric getLyric() throws IOException {
        if(infoObj.containsKey("lyric") && !infoObj.getString("lyric").isEmpty()){
            String lyric = HTTPUtil.HTTPRequest(infoObj.getString("lyric"));
            if(LrcLyric.isLRC(lyric)) {
                return new LrcLyric(lyric);
            } else {
                return new TextLyric(lyric);
            }
        } else {
            return null;
        }
    }

    @Override
    public Lyric getTranslatedLyric() throws IOException {
        // Bilibili无翻译
        return null;
    }

    @Override
    public URL getURL(SongQuality quality) throws InvaildQualityException, IOException {
        if(new AACSongQuality(192000).equals(quality)) {
            JSONObject resObject = BilibiliHTTPUtil.JSONHTTPRequest(
                    "https://www.bilibili.com/audio/music-service-c/web/url?sid=" + 
                    getID() + "&privilege=2&quality=3");
            JSONArray cdns = resObject.getJSONArray("cdns");
            if(cdns != null){
                String result = cdns.getString(0);
                if(!result.isEmpty()) {
                    return new URL(result);
                }
            }
            return null;
        }
        throw new InvaildQualityException();
    }

    @Override
    public String getID() {
        return String.valueOf(id);
    }
    
    public int getAVID(){
        return infoObj.getInteger("aid");
    }
    
    public String getBVID(){
        return infoObj.getString("bvid");
    }

    @Override
    public String getDescription() throws IOException {
        return infoObj.getString("intro");
    }
}
