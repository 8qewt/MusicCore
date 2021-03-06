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
package fifthlight.musiccore.artist;

import fifthlight.musiccore.interfaces.DescriptionAble;
import fifthlight.musiccore.Picture;
import fifthlight.musiccore.interfaces.IdentifierAble;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * 艺术家
 * @author fifth_light
 */
public abstract class Artist extends IdentifierAble implements Serializable, DescriptionAble {
    /**
     * 获取艺术家的名称。
     * @return 艺术家的名称。
     */
    public abstract String getName();
    
    /**
     * 获取艺术家的标题。<br>
     * <em>可能会产生网络请求和线程堵塞。</em>
     * @return 艺术家的标题。
     * @throws IOException 网络错误时抛出此异常。
     */
    public abstract String getTitle() throws IOException;
    
    /**
     * 获取艺术家的图片集合。<br>
     * <em>可能会产生网络请求和线程堵塞。</em>
     * @throws IOException 网络错误时抛出此异常。
     * @return 专辑的图片，没有则返回空集合
     */
    public abstract List<Picture> getPictures() throws IOException;
    
    @Override
    public String toString(){
        return getClass().getName() + " {ID: " + this.getID() + ", Name: " +
                getName() + "}";
    }
}
