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
package fifthlight.musiccore.interfaces;

import fifthlight.musiccore.search.searchresult.SearchResult;
import java.io.IOException;

/**
 * 热门列表接口<br>
 * 例如网易云的歌手页面中的top50
 * @author fifth_light
 * @param <T> 列表的类型
 */
public interface HotlistAble<T> {
    /**
     * 获取热门列表<br>
     * <em>可能会产生网络请求和线程堵塞。</em>
     * @throws IOException 出现网络错误时抛出此异常。
     * @return 热门列表
     */
    public abstract SearchResult<T> getHotList() throws IOException;
}
