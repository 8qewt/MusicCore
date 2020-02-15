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
package fifthlight.musiccore.playlist;

import fifthlight.musiccore.Picture;
import fifthlight.musiccore.interfaces.IdentifierAble;
import fifthlight.musiccore.search.searchresult.SearchResult;
import fifthlight.musiccore.song.Song;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 播放列表
 * @author fifth_light
 */
public abstract class Playlist implements Serializable, IdentifierAble {
    
    /**
     * 获取播放列表的名称。
     * @return 播放列表的名称。
     */
    public abstract String getName();
    
    
    /**
     * 获取播放列表的副名称列表。
     * @return 播放列表的副名称列表，不适用于该网站返回null。
     */
    public abstract List<String> getSubNames();
    
    
    /**
     * 获取播放列表的图片集合。<br>
     * <em>可能会产生网络请求和线程堵塞。</em>
     * @throws IOException 网络错误时抛出此异常。
     * @return 播放列表的图片，没有则返回空集合
     */
    public abstract Set<Picture> getPictures() throws IOException;
    
    /**
     * 获取播放列表中的歌曲。<br>
     * <em>可能会产生网络请求和线程堵塞。</em>
     * @throws IOException 网络错误时抛出此异常。
     * @return 播放列表中的歌曲
     */
    public abstract SearchResult<Song> getSongs() throws IOException;
}
