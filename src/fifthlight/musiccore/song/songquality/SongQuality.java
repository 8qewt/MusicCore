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
package fifthlight.musiccore.song.songquality;

import java.io.Serializable;

/**
 * 音质的抽象表达。
 * @author fifth_light
 */
public abstract class SongQuality implements Serializable {
    /**
     * 返回这个音质是否为无损。
     * @return 如果为true则是无损，否则有损。
     */
    public abstract boolean isLossLess();
    
    /**
     * 返回这个音质的比特率。
     * @return 如果已知则返回音质的比特率，否则返回-1。
     */
    public abstract int getBps();
    
    /**
     * 返回歌曲的文件类型，例如mp3、flac、m4a等。
     * @return 返回歌曲的文件类型。
     */
    public abstract String getType();
    
    @Override
    public boolean equals(Object o){
        if(o != null && o instanceof SongQuality){
            SongQuality songQuality = (SongQuality) o;
            return this.isLossLess() == songQuality.isLossLess() && this.getBps() == songQuality.getBps() && this.getType().equals(songQuality.getType());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash *= this.isLossLess() ? 1 : 2;
        hash *= this.getBps();
        hash *= this.getType().hashCode();
        return hash;
    }
}
