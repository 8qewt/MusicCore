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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * 带时间轴的歌词。
 * @author liuyujie
 */
public abstract class TimeLineLyric extends Lyric {
    /**
     * 获取歌词时间轴的集合
     * @return 歌曲歌词时间轴的集合
     */
    public abstract Set<TimeLine> getTimeLinesSet();
    
    public List<TimeLine> getTimeLinesList(){
        List<TimeLine> l = new ArrayList(this.getTimeLinesSet());
        l.sort(new Comparator<TimeLine>(){
            @Override
            public int compare(TimeLine o1, TimeLine o2) {
                return (int) (o1.getTime() - o2.getTime());
            }
        });
        return l;
    }
    
    @Override
    public String getLyric(){
        String s = "";
        for(TimeLine tl: this.getTimeLinesList()){
            s += "\n" + tl.getText();
        }
        return s.substring(1);
    }
}
