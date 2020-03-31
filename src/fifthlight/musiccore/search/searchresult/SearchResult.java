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
package fifthlight.musiccore.search.searchresult;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 一次搜索的搜索结果
 * @author fifth_light
 * @param <T> 搜索结果中所包含的对象。
 */
public abstract class SearchResult<T> implements Serializable {
    /**
     * 获取指定页数的元素。页数从0开始。<br>
     * 如果指定的页数不存在则返回null。<br>
     * <em>可能会产生网络请求和线程堵塞。</em>
     * 不保证每页的元素数相同。<br>
     * @param page 页数
     * @throws IOException 出现网络错误时抛出此异常。
     * @return 指定的页数不存在为null，否则为元素列表。
     */
    public abstract List<T> getItems(int page) throws IOException;
    
    /**
     * 返回搜索结果中共有多少元素
     * @return 如果长度已知则返回，否则返回-1
     */
    public abstract int length();
    
    
    /**
     * 返回搜索结果中共有多少页面
     * @return 如果页面长度已知则返回，否则返回-1
     */
    public abstract int pageLength();
    
    /**
     * 返回搜索结果中的所有页面
     * @return 搜索结果中的页面列表
     * @throws IOException 出现网络错误时抛出此异常。
     */
    public List<List<T>> getPages() throws IOException{
        List<List<T>> l = new ArrayList<List<T>>();
        int i = 0;
        List<T> tmpl;
        do {
            tmpl = getItems(i++);
            if(tmpl != null){
                l.add(tmpl);
            } else {
                break;
            }
        } while (i < pageLength());
        return l;
    }
    
    /**
     * 返回搜索结果中的所有元素
     * @return 搜索结果中的元素列表
     * @throws IOException 出现网络错误时抛出此异常。
     */
    public List<T> getItems() throws IOException{
        List<T> l = new ArrayList<T>();
        int i = 0;
        List<T> tmpl;
        do {
            tmpl = getItems(i++);
            if(tmpl != null){
                l.addAll(tmpl);
            } else {
                break;
            }
        } while (i < pageLength());
        return l;
    }
}
