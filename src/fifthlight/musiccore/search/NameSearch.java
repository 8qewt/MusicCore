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

/**
 * 通过名字来搜索歌曲
 * @author fifth_light
 */
public final class NameSearch extends Search {
    private final String name;
    
    /**
     * 通过名字创建一个NameSearch对象
     * @param name 搜索歌曲的名字
     */
    public NameSearch(String name){
        this.name = name;
    }
    
    /**
     * 获取搜索的名字
     * @return 搜索的名字
     */
    public String getName(){
        return name;
    }
}
