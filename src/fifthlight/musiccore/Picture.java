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
package fifthlight.musiccore;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;

/**
 * 歌曲图片与封面图片等图片。
 * @author fifth_light
 */
public abstract class Picture implements Serializable {
    /**
     * 获取封面的URL<br>
     * <em>可能会产生网络请求和线程堵塞。</em>
     * @param xRes x方向的分辨率，如果要获得最大尺寸可以保持0
     * @param yRes y方向的分辨率，如果要获得最大尺寸可以保持0
     * @throws IOException 出现网络错误时抛出此异常。
     * @return 封面的URL
     */
    public abstract URL getURL(int xRes, int yRes) throws IOException;
}
