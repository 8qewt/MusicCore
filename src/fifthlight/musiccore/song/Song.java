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
package fifthlight.musiccore.song;

import fifthlight.musiccore.Picture;
import fifthlight.musiccore.album.Album;
import fifthlight.musiccore.artist.Artist;
import fifthlight.musiccore.exception.InvaildQualityException;
import fifthlight.musiccore.song.lyric.Lyric;
import fifthlight.musiccore.song.songquality.SongQuality;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.List;
import java.util.Set;

/**
 * 歌曲
 *
 * @author fifth_light
 */
public abstract class Song implements Serializable {

    /**
     * 获取歌曲的ID。
     *
     * @return 歌曲的ID。
     */
    public abstract String getID();

    /**
     * 获取歌曲的名称。
     *
     * @return 歌曲的名称。
     */
    public abstract String getName();

    /**
     * 获取歌曲的副名称列表。
     *
     * @return 歌曲的副名称列表。
     */
    public abstract List<String> getSubNames();

    /**
     * 获取歌曲的艺术家列表。
     *
     * @return 歌曲的艺术家列表。
     */
    public abstract List<Artist> getArtists();

    /**
     * 获取歌曲的专辑。<br>
     * 一首歌曲可以没有专辑。
     *
     * @return 如果该歌曲属于一个专辑则返回该专辑，否则返回Null
     */
    public abstract Album getAlbum();

    /**
     * 获取歌曲的可用音质集合。<br>
     * <em>可能会产生网络请求和线程堵塞。</em>
     *
     * @throws IOException 网络错误时抛出此异常。
     * @return 歌曲的可用音质集合
     */
    public abstract Set<SongQuality> getAvailableQualities() throws IOException;

    /**
     * 获取歌曲的图片集合。<br>
     * <em>可能会产生网络请求和线程堵塞。</em>
     *
     * @throws IOException 网络错误时抛出此异常。
     * @return 歌曲的图片，没有则返回空集合，当前网站不支持则返回null。
     */
    public abstract List<Picture> getPics() throws IOException;
    
    /**
     * 获取歌曲的歌词。<br>
     * <em>可能会产生网络请求和线程堵塞。</em>
     *
     * @throws IOException 网络错误时抛出此异常。
     * @return 歌曲的歌词，没有则返回null。
     */
    public abstract Lyric getLyric() throws IOException;
    
    /**
     * 获取歌曲的翻译后歌词。<br>
     * <em>可能会产生网络请求和线程堵塞。</em>
     *
     * @throws IOException 网络错误时抛出此异常。
     * @return 歌曲的歌词，没有则返回null。
     */
    public abstract Lyric getTranslatedLyric() throws IOException;

    /**
     * 获取歌曲的封面。<br>
     * 如果歌曲没有带图片，则会返回歌曲专辑的封面，还没有则返回null。<br>
     * <em>可能会产生网络请求和线程堵塞。</em>
     *
     * @throws IOException 网络错误时抛出此异常。
     * @return 歌曲的封面，没有则返回null。
     */
    public Picture getCover() throws IOException {
        List<Picture> pics = this.getPics();
        if (pics != null && !pics.isEmpty()) {
            return pics.get(0);
        } else {
            Album a = this.getAlbum();
            if (a != null) {
                pics = a.getPictures();
                if (pics != null && !pics.isEmpty()) {
                    return pics.get(0);
                }
            }
        }
        return null;
    }

    /**
     * 获取歌曲的下载链接。<br>
     * <em>可能会产生网络请求和线程堵塞。</em>
     *
     * @param quality 歌曲音质
     * @throws IOException 网络错误时抛出此异常。
     * @throws InvaildQualityException 试图获取歌曲不存在的音质的下载链接时抛出此异常。
     * @return 歌曲的下载URL。
     */
    public abstract URL getURL(SongQuality quality) throws InvaildQualityException, IOException;
}
