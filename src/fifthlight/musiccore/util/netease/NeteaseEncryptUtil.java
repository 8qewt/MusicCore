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
package fifthlight.musiccore.util.netease;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * 网易云加密工具
 * @author fifth_light
 */
public class NeteaseEncryptUtil {
    public static final String encryptKey = "rFgB&h#%2?^eDg:Q";
    
    /**
     * 网易云linux forward api
     * @param text 要加密的文本
     * @return 加密后的文本
     */
    @SuppressWarnings("UseSpecificCatch")
    public static String linuxAPI(String text) {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(encryptKey.getBytes("US-ASCII"), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(text.getBytes());
            String r = "";
            for (byte b : encrypted) {
                String s = Integer.toHexString(b >= 0 ? b : b + 256).toUpperCase();
                r += s.length() == 1 ? "0" + s : s;
            }
            return r;
        } catch (Exception e) {
            throw new RuntimeException("Unexcepted Error!", e);
        }
    }
}
