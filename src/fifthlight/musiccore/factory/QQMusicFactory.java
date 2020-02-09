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

import fifthlight.musiccore.album.Album;
import fifthlight.musiccore.artist.Artist;
import fifthlight.musiccore.exception.InvaildSearchException;
import fifthlight.musiccore.internal.searchresult.qq.QQIDSongSearchResult;
import fifthlight.musiccore.playlist.Playlist;
import fifthlight.musiccore.search.IDSearch;
import fifthlight.musiccore.search.MIDSearch;
import fifthlight.musiccore.search.Search;
import fifthlight.musiccore.search.searchresult.SearchResult;
import fifthlight.musiccore.song.Song;
import java.io.IOException;

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
            return new QQIDSongSearchResult(((IDSearch) search).getIDs(), false);
        } else if (search instanceof MIDSearch) {
            return new QQIDSongSearchResult(((MIDSearch) search).getMIDs(), true);
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SearchResult<Playlist> getPlaylists(Search search) throws InvaildSearchException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static QQMusicFactory getInstance() {
        return instance;
    }
    
}
