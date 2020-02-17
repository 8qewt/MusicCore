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
package fifthlight.musiccore.factory;

import com.alibaba.fastjson.JSONObject;
import fifthlight.musiccore.album.Album;
import fifthlight.musiccore.artist.Artist;
import fifthlight.musiccore.exception.InvaildSearchException;
import fifthlight.musiccore.internal.searchresult.netease.NeteaseAlbumSearchResult;
import fifthlight.musiccore.internal.searchresult.netease.NeteaseArtistSearchResult;
import fifthlight.musiccore.internal.searchresult.netease.NeteaseNameAlbumSearchResult;
import fifthlight.musiccore.internal.searchresult.netease.NeteaseNameArtistSearchResult;
import fifthlight.musiccore.internal.searchresult.netease.NeteaseNamePlaylistSearchResult;
import fifthlight.musiccore.internal.searchresult.netease.NeteaseNameSongSearchResult;
import fifthlight.musiccore.internal.searchresult.netease.NeteasePlaylistSearchResult;
import fifthlight.musiccore.playlist.Playlist;
import fifthlight.musiccore.search.IDSearch;
import fifthlight.musiccore.search.Search;
import fifthlight.musiccore.search.searchresult.SearchResult;
import fifthlight.musiccore.internal.searchresult.netease.NeteaseSongSearchResult;
import fifthlight.musiccore.search.NameSearch;
import fifthlight.musiccore.song.Song;
import fifthlight.musiccore.util.netease.NeteaseHTTPUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 网易云音乐
 *
 * @author fifth_light
 */
public class NeteaseMusicFactory extends MusicFactory {

    private static final NeteaseMusicFactory instance = new NeteaseMusicFactory();

    private NeteaseMusicFactory() {
    }

    @Override
    public SearchResult<Song> getSongs(Search search) throws InvaildSearchException, IOException {
        if (search instanceof IDSearch) {
            IDSearch idSearch = (IDSearch) search;
            List<String> ids = ((IDSearch) search).getIDs();
            String sList = "";
            for (int i = 0; i < ids.size(); i++) {
                String id = ids.get(i);
                id = id.replace("{", "\\{");
                id = id.replace("}", "\\}");
                sList += "{\"id\":" + id + "}";
                if (i != ids.size() - 1) {
                    sList += ",";
                }
            }
            return new NeteaseSongSearchResult(NeteaseHTTPUtil.getJSONLinuxForward("{\"method\":\"POST\",\"params\":{\"c\":\""
                    + ("[" + sList + "]").replace("\"", "\\\"")
                    + "\"},\"url\":\"https://music.163.com/api/v3/song/detail\"}"), 0);
        } else if (search instanceof NameSearch) {
            return new NeteaseNameSongSearchResult((NameSearch) search);
        } else {
            throw new InvaildSearchException();
        }
    }

    @Override
    public SearchResult<Artist> getArtists(Search search) throws InvaildSearchException, IOException {
        if (search instanceof IDSearch) {
            IDSearch idSearch = (IDSearch) search;
            List<String> ids = ((IDSearch) search).getIDs();
            List<JSONObject> results = new ArrayList<JSONObject>();
            for (int i = 0; i < ids.size(); i++) {
                results.add(NeteaseHTTPUtil.getJSONLinuxForward("{\"method\":\"GET\",\"params\":{\"id\":" + ids.get(i)
                    + ",\"ext\":true,\"top\":0},\"url\":\"https://music.163.com/api/v1/artist/" + ids.get(i) + "\"}"));
            }
            return new NeteaseArtistSearchResult(results);
        } else if (search instanceof NameSearch) {
            return new NeteaseNameArtistSearchResult((NameSearch) search);
        } else {
            throw new InvaildSearchException();
        }
    }

    @Override
    public SearchResult<Album> getAlbums(Search search) throws InvaildSearchException, IOException {
        if (search instanceof IDSearch) {
            IDSearch idSearch = (IDSearch) search;
            List<String> ids = ((IDSearch) search).getIDs();
            List<JSONObject> results = new ArrayList<JSONObject>();
            for (int i = 0; i < ids.size(); i++) {
                results.add(NeteaseHTTPUtil.getJSONLinuxForward("{\"method\":\"GET\",\"params\":{\"id\":" + ids.get(i)
                        + "},\"url\":\"https://music.163.com/api/v1/album/" + ids.get(i) + "\"}"));
            }
            return new NeteaseAlbumSearchResult(results, 0);
        } else if (search instanceof NameSearch) {
            return new NeteaseNameAlbumSearchResult((NameSearch) search);
        } else {
            throw new InvaildSearchException();
        }
    }

    @Override
    public SearchResult<Playlist> getPlaylists(Search search) throws InvaildSearchException, IOException {
        if (search instanceof IDSearch) {
            IDSearch idSearch = (IDSearch) search;
            List<String> ids = ((IDSearch) search).getIDs();
            List<JSONObject> results = new ArrayList<JSONObject>();
            for (int i = 0; i < ids.size(); i++) {
                results.add(NeteaseHTTPUtil.getJSONLinuxForward("{\"method\":\"POST\",\"params\":{\"id\":" + ids.get(i)
                        + ",\"n\":65536},\"url\":\"https://music.163.com/api/v3/playlist/detail\"}"));
            }
            return new NeteasePlaylistSearchResult(results, 0);
        } else if (search instanceof NameSearch) {
            return new NeteaseNamePlaylistSearchResult((NameSearch) search);
        } else {
            throw new InvaildSearchException();
        }
    }

    public static NeteaseMusicFactory getInstance() {
        return instance;
    }

}
