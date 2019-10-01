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

import fifthlight.musiccore.album.Album;
import fifthlight.musiccore.artist.Artist;
import fifthlight.musiccore.exception.InvaildSearchException;
import fifthlight.musiccore.search.Search;
import fifthlight.musiccore.search.searchresult.SearchResult;
import fifthlight.musiccore.playlist.Playlist;
import fifthlight.musiccore.song.Song;
import java.io.IOException;

/**
 * 搜索歌曲或者通过ID创建歌曲
 * @author fifth_light
 */
public abstract class MusicFactory {
    /**
     * 通过Search给出的条件搜索歌曲。<br>
     * <em>可能会产生网络请求和线程堵塞。</em>
     * @param search 搜索参数
     * @throws IOException 出现网络错误时抛出此异常。
     * @throws InvaildSearchException 遇到非法的搜索时抛出该异常。
     * @return 搜索歌曲的结果。
     */
    public abstract SearchResult<Song> getSongs(Search search) throws InvaildSearchException, IOException;
    
    /**
     * 通过Search给出的条件搜索艺术家。<br>
     * <em>可能会产生网络请求和线程堵塞。</em>
     * @param search 搜索参数
     * @throws IOException 出现网络错误时抛出此异常。
     * @throws InvaildSearchException 遇到非法的搜索时抛出该异常。
     * @return 搜索艺术家的结果。
     */
    public abstract SearchResult<Artist> getArtists(Search search) throws InvaildSearchException, IOException;
    
    
    /**
     * 通过Search给出的条件搜索专辑。<br>
     * <em>可能会产生网络请求和线程堵塞。</em>
     * @param search 搜索参数
     * @throws IOException 出现网络错误时抛出此异常。
     * @throws InvaildSearchException 遇到非法的搜索时抛出该异常。
     * @return 搜索专辑的结果。
     */
    public abstract SearchResult<Album> getAlbums(Search search) throws InvaildSearchException, IOException;
    
    /**
     * 通过Search给出的条件搜索歌单。<br>
     * <em>可能会产生网络请求和线程堵塞。</em>
     * @param search 搜索参数
     * @throws IOException 出现网络错误时抛出此异常。
     * @throws InvaildSearchException 遇到非法的搜索时抛出该异常。
     * @return 搜索歌单的结果。
     */
    public abstract SearchResult<Playlist> getPlaylists(Search search) throws InvaildSearchException, IOException;
}
