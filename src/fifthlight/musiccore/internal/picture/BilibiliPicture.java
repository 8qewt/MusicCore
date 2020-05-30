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
package fifthlight.musiccore.internal.picture;

import fifthlight.musiccore.Picture;
import java.io.IOException;
import java.net.URL;

/**
 *
 * @author liuyujie
 */
public class BilibiliPicture extends Picture {
    
    private final String link;
    
    public BilibiliPicture(String link) {
        this.link = link;
    }

    @Override
    public URL getURL(int xRes, int yRes) throws IOException {
        if(xRes != 0) {
            if(yRes != 0){
                return new URL(link + "@" + xRes + "w_" + yRes + "h.jpg");
            } else {
                return new URL(link + "@" + xRes + "w.jpg");
            }
        } else {
            if(yRes != 0){
                return new URL(link + "@" + yRes + "h.jpg");
            } else {
                return new URL(link);
            }
        }
    }

    @Override
    public String getID() {
        // 返回URL作为ID
        return link;
    }
    
}
