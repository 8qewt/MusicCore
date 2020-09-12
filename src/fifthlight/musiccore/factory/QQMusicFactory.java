/*
 * Copyright (C) 2019 liuyujie
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
package fifthlight.musiccore.factory;

import com.alibaba.fastjson.JSONObject;
import fifthlight.musiccore.album.Album;
import fifthlight.musiccore.artist.Artist;
import fifthlight.musiccore.exception.InvaildSearchException;
import fifthlight.musiccore.internal.album.QQAlbum;
import fifthlight.musiccore.internal.searchresult.OnePageSearchResult;
import fifthlight.musiccore.internal.searchresult.qq.QQNameSongSearchResult;
import fifthlight.musiccore.internal.song.QQSong;
import fifthlight.musiccore.playlist.Playlist;
import fifthlight.musiccore.search.IDSearch;
import fifthlight.musiccore.search.MIDSearch;
import fifthlight.musiccore.search.NameSearch;
import fifthlight.musiccore.search.Search;
import fifthlight.musiccore.search.searchresult.SearchResult;
import fifthlight.musiccore.song.Song;
import fifthlight.musiccore.util.qq.QQHTTPUtil;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author liuyujie
 */
public class QQMusicFactory extends MusicFactory {
    
    private static final QQMusicFactory instance = new QQMusicFactory();
    
    private QQMusicFactory(){
    }

    @Override
    public SearchResult<Song> getSongs(Search search) throws InvaildSearchException, IOException {
        if (search instanceof IDSearch) {
            ArrayList<Song> songs = new ArrayList<Song>();
            for (String str : ((IDSearch) search).getIDs()) {
                if (!str.matches("^[0-9]*$")) {
                    throw new IllegalArgumentException("ID must be a number");
                }
                JSONObject jsonData = QQHTTPUtil.JSONHTTPRequest("https://c.y.qq.com/v8/fcg-bin/fcg_play_single_song.fcg?songid="
                        + str + "&format=json");
                songs.add(new QQSong(jsonData, QQSong.DataType.FROM_PLAY));
            }
            return new OnePageSearchResult<Song>(songs);
        } else if (search instanceof MIDSearch) {
            ArrayList<Song> songs = new ArrayList<Song>();
            for (String str : ((MIDSearch) search).getMIDs()) {
                if (!str.matches("^[0-9]*$")) {
                    throw new IllegalArgumentException("ID must be a number");
                }
                JSONObject jsonData = QQHTTPUtil.JSONHTTPRequest("https://c.y.qq.com/v8/fcg-bin/fcg_play_single_song.fcg?songmid="
                        + str + "&format=json");
                songs.add(new QQSong(jsonData, QQSong.DataType.FROM_PLAY));
            }
            return new OnePageSearchResult<Song>(songs);
        } else if (search instanceof NameSearch) {
            return new QQNameSongSearchResult((NameSearch) search);
        } else {
            throw new InvaildSearchException();
        }
    }

    @Override
    public SearchResult<Artist> getArtists(Search search) throws InvaildSearchException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SearchResult<Album> getAlbums(Search search) throws InvaildSearchException, IOException {
        if (search instanceof IDSearch) {
            ArrayList<Album> albums = new ArrayList<Album>();
            for (String str : ((MIDSearch) search).getMIDs()) {
                if (!str.matches("^[0-9]*$")) {
                    throw new IllegalArgumentException("ID must be a number");
                }
                albums.add(new QQAlbum(Long.valueOf(str), QQAlbum.DataType.ID));
            }
            return new OnePageSearchResult<Album>(albums);
        } else {
            throw new InvaildSearchException();
        }
    }

    @Override
    public SearchResult<Playlist> getPlaylists(Search search) throws InvaildSearchException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static QQMusicFactory getInstance() {
        return instance;
    }
    
}
