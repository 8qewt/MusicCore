package fifthlight.musiccore.factory;

import com.alibaba.fastjson.JSONObject;
import fifthlight.musiccore.album.Album;
import fifthlight.musiccore.artist.Artist;
import fifthlight.musiccore.exception.InvaildSearchException;
import fifthlight.musiccore.factory.MusicFactory;
import fifthlight.musiccore.internal.searchresult.OnePageSearchResult;
import fifthlight.musiccore.internal.song.BilibiliSong;
import fifthlight.musiccore.playlist.Playlist;
import fifthlight.musiccore.search.IDSearch;
import fifthlight.musiccore.search.Search;
import fifthlight.musiccore.search.searchresult.SearchResult;
import fifthlight.musiccore.song.Song;
import fifthlight.musiccore.util.bilibili.BilibiliHTTPUtil;
import java.io.IOException;
import java.util.ArrayList;

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
/**
 *
 * @author liuyujie
 */
public class BilibiliFactory extends MusicFactory {

    private static final BilibiliFactory instance = new BilibiliFactory();

    public static BilibiliFactory getInstance() {
        return instance;
    }

    private BilibiliFactory() {
    }

    @Override
    public SearchResult<Song> getSongs(Search search) throws InvaildSearchException, IOException {
        if (search instanceof IDSearch) {
            IDSearch idSearch = (IDSearch) search;
            ArrayList<Song> list = new ArrayList<Song>();
            for (String id : idSearch.getIDs()) {
                JSONObject jo = BilibiliHTTPUtil
                        .JSONHTTPRequest("https://www.bilibili.com/audio/music-service-c/web/song/info?sid=" + id);
                list.add(new BilibiliSong(jo, 0));
            }
            return new OnePageSearchResult(list);

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

}
