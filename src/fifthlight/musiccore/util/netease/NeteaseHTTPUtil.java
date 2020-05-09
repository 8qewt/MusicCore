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

import com.alibaba.fastjson.JSONObject;
import fifthlight.musiccore.exception.ParseException;
import static fifthlight.musiccore.util.UserAgentUtil.randomUserAgent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

/**
 *
 * @author fifth_light
 */
public class NeteaseHTTPUtil {

    private static final Random r = new Random();
    private static final String music_u = generateMusicU();
    private static final String cookie = generateCookie();
    private static final String userAgent = randomUserAgent();
    
    private static String generateMusicU(){
        String result = "";
        for(int i = 0; i < 128; i++){
            result += Integer.toHexString(r.nextInt(15));
        }
        return result;
    }
    
    private static String generateCookie(){
        String cookieList[] = new String[]{"os=pc; osver=Microsoft-Windows-10-Professional-build-10586-64bit; appver=2.0.3.131777; channel=netease; __remember_me=true",
        "MUSIC_U=" + music_u +"; buildver=1506310743; resolution=1920x1080; mobilename=MI5; osver=7.0.1; channel=coolapk; os=android; appver=4.2.0",
        "osver=%E7%89%88%E6%9C%AC%2010.13.3%EF%BC%88%E7%89%88%E5%8F%B7%2017D47%EF%BC%89; os=osx; appver=1.5.9; MUSIC_U=" + music_u + "; channel=netease;"};
        return cookieList[r.nextInt(cookieList.length - 1)];
    }

    /**
     * 调用网易云linuxForward接口
     *
     * @param arg JSON格式的参数
     * @return 返回的JSON
     * @throws IOException IO错误时抛出
     */
    public static JSONObject getJSONLinuxForward(String arg) throws IOException {
        try {
            URL realUrl = new URL("https://music.163.com/api/linux/forward");
            URLConnection conn = realUrl.openConnection();
            conn.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.addRequestProperty("User-Agent", userAgent);
            conn.addRequestProperty("Cookie", cookie);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            OutputStream os = conn.getOutputStream();
            os.write(("eparams=" + NeteaseEncryptUtil.linuxAPI(arg)).getBytes("US-ASCII"));
            os.flush();
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String result = "";
            String line;
            while ((line = br.readLine()) != null) {
                result += line;
            }
            os.close();
            br.close();
            JSONObject jo = JSONObject.parseObject(result);
            if(jo.containsKey("code")){
                if(jo.getInteger("code") == 200){
                    return jo;
                } else {
                    throw new ParseException("Returl code is " + jo.getInteger("code"));
                }
            }
            throw new ParseException("Returl code not found");
        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
