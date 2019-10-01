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
package fifthlight.musiccore.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 通过ID来搜索歌曲
 * @author fifth_light
 */
public final class IDSearch extends Search {
    private final ArrayList<String> ids;
    
    /**
     * 通过ID创建一个IDSearch对象
     * @param id 搜索歌曲的ID
     */
    public IDSearch(String id){
        this.ids = new ArrayList<String>();
        this.ids.add(id);
    }
    
    /**
     * 通过ID列表创建一个IDSearch对象
     * @param ids 搜索歌曲的ID列表
     */
    public IDSearch(List<String> ids){
        this.ids = new ArrayList<String>();
        this.ids.addAll(ids);
    }
    
    /**
     * 通过ID列表创建一个IDSearch对象
     * @param ids 搜索歌曲的ID列表
     */
    public IDSearch(String[] ids){
        this.ids = new ArrayList<String>();
        this.ids.addAll(Arrays.asList(ids));
    }
    
    /**
     * 获取搜索的ID列表
     * @return 搜索的ID列表
     */
    public List<String> getIDs(){
        return ids;
    }
}
