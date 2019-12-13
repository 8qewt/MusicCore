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
package fifthlight.musiccore.song;

import java.io.IOException;

/**
 *
 * @author liuyujie
 */
public abstract class LyricSong extends Song{
    /**
     * 获取歌曲的歌词<br>
     * <em>可能会产生网络请求和线程堵塞。</em>
     * @return 歌曲的歌词文本
     * @throws IOException 
     */
    public abstract String getLyric() throws IOException;
}
