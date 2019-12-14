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
package fifthlight.musiccore.song.lyric;

/**
 * 有元数据的歌词
 *
 * @author liuyujie
 */
public interface LyricMetadata {

    /**
     * 获取歌曲元数据中的艺术家名。
     *
     * @return 歌曲元数据中的艺术家名，没有则返回null。
     */
    public String getArtistName();

    /**
     * 获取歌曲元数据中的专辑名。
     *
     * @return 歌曲元数据中的专辑名，没有则返回null。
     */
    public String getAlbumName();

    /**
     * 获取歌曲元数据中的歌曲标题。
     *
     * @return 歌曲元数据中的歌曲标题，没有则返回null。
     */
    public String getTitle();

    /**
     * 获取歌曲元数据中的歌词编辑者名。
     *
     * @return 歌曲元数据中的歌词编辑者名，没有则返回null。
     */
    public String getAuthor();
}
