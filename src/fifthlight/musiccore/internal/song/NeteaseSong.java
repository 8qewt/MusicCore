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
import fifthlight.musiccore.internal.album.NeteaseAlbum;
import fifthlight.musiccore.internal.artist.NeteaseArtist;
import fifthlight.musiccore.song.Song;
import fifthlight.musiccore.song.songquality.MP3SongQuality;
import fifthlight.musiccore.song.songquality.SongQuality;
import fifthlight.musiccore.util.netease.NeteaseHTTPUtil;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 网易云音乐中的一首歌曲
 * @author fifth_light
 */
public class NeteaseSong extends Song {
    
    private final JSONObject o;

    public NeteaseSong(JSONObject o){
        this.o = o;
    }
    
    @Override
    public String getID() {
        return String.valueOf(o.getLong("id"));
    }
    
    @Override
    public String getName() {
        return o.getString("name");
    }

    @Override
    public List<String> getSubNames() {
        List<String> result = new ArrayList<String>();
        for(Object name : o.getJSONArray("alia")){
            result.add((String) name);
        }
        return result;
    }

    @Override
    public List<Artist> getArtists() {
        List<Artist> result = new ArrayList<Artist>();
        for(Object obj : o.getJSONArray("ar")){
            result.add(new NeteaseArtist((JSONObject) obj, 0));
        }
        return result;
    }

    @Override
    public Album getAlbum() {
        return new NeteaseAlbum(o.getJSONObject("al"), 0);
    }

    @Override
    public Set<SongQuality> getAvailableQualities() throws IOException {
        Set<SongQuality> result = new HashSet<SongQuality>();
        if(o.containsKey("l")){
            result.add(new MP3SongQuality(128000));
        }
        if(o.containsKey("m")){
            result.add(new MP3SongQuality(192000));
        }
        if(o.containsKey("h")){
            result.add(new MP3SongQuality(320000));
        }
        return result;
    }

    @Override
    public Set<Picture> getPics() throws IOException {
        return null;
    }

    @Override
    public URL getURL(SongQuality quality) throws InvaildQualityException, IOException {
        if(quality instanceof MP3SongQuality){
            switch(quality.getBps()){
                case 128000:
                    if(o.containsKey("l")){
                        JSONObject result = NeteaseHTTPUtil.getJSONLinuxForward("{\"method\":\"POST\",\"params\":{\"ids\":[" + getID()
                        + "],\"br\":128000},\"url\":\"http://music.163.com/api/song/enhance/player/url\"}");
                        return new URL(result.getJSONArray("data").getJSONObject(0).getString("url"));
                    } else {
                        throw new InvaildQualityException();
                    }
                case 192000:
                    if(o.containsKey("m")){
                        JSONObject result = NeteaseHTTPUtil.getJSONLinuxForward("{\"method\":\"POST\",\"params\":{\"ids\":[" + getID()
                        + "],\"br\":192000},\"url\":\"http://music.163.com/api/song/enhance/player/url\"}");
                        return new URL(result.getJSONArray("data").getJSONObject(0).getString("url"));
                    } else {
                        throw new InvaildQualityException();
                    }
                case 320000:
                    if(o.containsKey("h")){
                        JSONObject result = NeteaseHTTPUtil.getJSONLinuxForward("{\"method\":\"POST\",\"params\":{\"ids\":[" + getID()
                        + "],\"br\":320000},\"url\":\"http://music.163.com/api/song/enhance/player/url\"}");
                        return new URL(result.getJSONArray("data").getJSONObject(0).getString("url"));
                    } else {
                        throw new InvaildQualityException();
                    }
                default:
                    throw new InvaildQualityException();
            }
        } else {
            throw new InvaildQualityException();
        }
    }
    
}
