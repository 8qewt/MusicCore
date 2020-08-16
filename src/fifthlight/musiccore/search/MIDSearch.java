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
package fifthlight.musiccore.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 通过QQ音乐的MID来搜索歌曲
 * @author liuyujie
 */
public class MIDSearch extends Search {
    private final ArrayList<String> mids;
    
    /**
     * 通过MID创建一个MIDSearch对象
     * @param mid 搜索歌曲的MID
     */
    public MIDSearch(String mid){
        this.mids = new ArrayList<String>();
        this.mids.add(mid);
    }
    
    /**
     * 通过MID列表创建一个MIDSearch对象
     * @param mids 搜索歌曲的MID列表
     */
    public MIDSearch(List<String> mids){
        this.mids = new ArrayList<String>();
        this.mids.addAll(mids);
    }
    
    /**
     * 通过MID列表创建一个MIDSearch对象
     * @param mids 搜索歌曲的MID列表
     */
    public MIDSearch(String[] mids){
        this.mids = new ArrayList<String>();
        this.mids.addAll(Arrays.asList(mids));
    }
    
    /**
     * 获取搜索的MID列表
     * @return 搜索的MID列表
     */
    public List<String> getMIDs(){
        return mids;
    }
    
    @Override
    public String toString(){
        StringBuilder midBuilder = new StringBuilder();
        boolean start = true;
        for(String mid: mids) {
            if(start) {
                start = false;
            } else {
                midBuilder.append(", ");
            }
            midBuilder.append(mid);
        }
        return getClass().getName() + " {MIDs: " + midBuilder.toString() + "}";
    }
}
