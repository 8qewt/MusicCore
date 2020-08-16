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

import java.io.Serializable;

/**
 * 歌词。<br>
 * 这个类所表示的歌词没有时间轴。
 * @author liuyujie
 */
public abstract class Lyric implements Serializable {
    /**
     * 获取歌词内容
     * @return 歌词文本
     */
    public abstract String getLyric();
    
    @Override
    public String toString() {
        return getLyric();
    }
}
