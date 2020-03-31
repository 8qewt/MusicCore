/*
 * Copyright (C) 2020 liuyujie
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

/**
 * 可以用ID标志自身的对象接口
 * 其ID可以在同一个类的对象内标志自身。
 * @author liuyujie
 */
public abstract class IdentifierAble {
    /**
     * 获取对象的ID。
     *
     * @return 对象的ID。
     */
    public abstract String getID();
    
    @Override
    public int hashCode(){
        return getID().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return getID().equals(((IdentifierAble) obj).getID());
    }
}
