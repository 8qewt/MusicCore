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
 * 时间轴上的一行歌词。
 *
 * @author liuyujie
 */
public abstract class TimeLine {

    /**
     * 获取该行歌词的时间。
     *
     * @return 该行歌词的时间。
     */
    public abstract double getTime();

    /**
     * 获取该行歌词的文本内容。
     *
     * @return 该行歌词的文本内容
     */
    public abstract String getText();
}
