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
package fifthlight.musiccore.internal.picture.netease;

import fifthlight.musiccore.util.Base64;
import fifthlight.musiccore.Picture;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 网易云的图片
 * @author fifth_light
 */
public class NeteasePicture extends Picture {
    
    private Long id;
    private String neteasePickey;
    private static final byte magic[] = "3go8&$8*3*3h0k(2)2".getBytes();
    
    public NeteasePicture(Long id){
        try {
            this.id = id;
            byte[] idChar = String.valueOf(id).getBytes();
            for(int i = 0; i < idChar.length; i++){
                idChar[i] = (byte) (idChar[i] ^ magic[i % magic.length]);
            }
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(idChar);
            String k = new String(Base64.encode(md.digest(), Base64.DEFAULT)).replace("\n", "");
            k = k.replaceAll("/", "_");
            k = k.replaceAll("\\+", "-");
            neteasePickey = k;
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public URL getURL(int xRes, int yRes) throws IOException {
        if(xRes == 0 && yRes == 0){
            return new URL("https://p3.music.126.net/" + neteasePickey + "/" + id + ".jpg");
        } else if(xRes == 0 || yRes == 0){
            return new URL("https://p3.music.126.net/" + neteasePickey + "/" + id + ".jpg?param=" + (xRes + yRes)  +"y" + (xRes + yRes));
        } else {
            return new URL("https://p3.music.126.net/" + neteasePickey + "/" + id + ".jpg?param=" + xRes  +"y" + yRes);
        }
    }

    @Override
    public String getID() {
        return String.valueOf(id);
    }
    
}
