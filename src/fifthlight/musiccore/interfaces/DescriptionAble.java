/*
 * Copyright (C) 2020 fifth_light
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

import java.io.IOException;

/**
 * 有说明的对象接口。
 * @author liuyujie
 */
public interface DescriptionAble {
    
    /**
     * 获取说明。
     * @return 专辑的说明，如果当前网站不支持则返回null。
     * @throws IOException 网络错误时抛出此异常。
     */
    public abstract String getDescription() throws IOException;
}
