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

/**
 * MP3音质
 * @author fifth_light
 */
public class FLACSongQuality extends SongQuality {
    
    private final int bps;
    
    /**
     * 构建一个FLAC音质对象。
     * @param bps 比特率，例如320000，192000
     * @throws IllegalArgumentException 比特率为0或负数时抛出
     */
    public FLACSongQuality(int bps) throws IllegalArgumentException {
        if(bps <= 0){
            throw new IllegalArgumentException();
        }
        this.bps = bps;
    }
    
    /**
     * 返回这个音质是否为无损（返回true）。
     * @return true。
     */
    @Override
    public boolean isLossLess() {
        return true;
    }

    /**
     * 返回这个音质的比特率。
     * @return 如果已知则返回音质的比特率，否则返回null。
     */
    @Override
    public int getBps() {
        return bps;
    }

    /**
     * 返回歌曲的文件类型"flac"。
     * @return "flac"
     */
    @Override
    public String getType() {
        return "flac";
    }
}
