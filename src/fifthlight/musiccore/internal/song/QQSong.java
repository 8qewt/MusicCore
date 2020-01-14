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
import fifthlight.musiccore.song.Song;
import fifthlight.musiccore.song.lyric.Lyric;
import fifthlight.musiccore.song.songquality.SongQuality;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Set;

/**
 * QQ音乐中的一首歌曲
 * @author fifth_light
 */
public class QQSong extends Song {
    
    private final JSONObject playObj;
    private Long ID;
    private String MID;

    public QQSong(JSONObject o, int type) {
        switch(type){
            case 0:
                playObj = o;
                ID = Long.valueOf(o.getJSONArray("data").getJSONObject(0).getString("id"));
                MID = o.getJSONArray("data").getJSONObject(0).getString("mid");
                break;
            
            default:
                throw new RuntimeException();
        }
    }
    
    @Override
    public String getID() {
        return String.valueOf(ID);
    }
    
    public String getMID() {
        return String.valueOf(MID);
    }
    
    @Override
    public String getName() {
        if(playObj != null){
            return playObj.getJSONArray("data").getJSONObject(0).getString("name");
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public String getTitle() {
        if(playObj != null){
            return playObj.getJSONArray("data").getJSONObject(0).getString("title");
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public List<Artist> getArtists() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Album getAlbum() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<SongQuality> getAvailableQualities() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Picture> getPics() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Lyric getLyric() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Lyric getTranslatedLyric() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public URL getURL(SongQuality quality) throws InvaildQualityException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
