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
package fifthlight.musiccore.util.bilibili;

import com.alibaba.fastjson.JSONObject;
import fifthlight.musiccore.exception.ParseException;
import static fifthlight.musiccore.util.HTTPUtil.readInputStream;
import fifthlight.musiccore.util.MusicCoreConfig;
import static fifthlight.musiccore.util.UserAgentUtil.randomUserAgent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

/**
 * Bilibili的HTTP访问工具
 *
 * @author liuyujie
 */
public class BilibiliHTTPUtil {

    private static String userAgent = "MusicCore/" + MusicCoreConfig.VERSION + " (812558777@qq.com)";
    private static final String cookie = randomCookie();

    public static void setDefaultUA() {
        userAgent = "MusicCore/" + MusicCoreConfig.VERSION + " (812558777@qq.com)";
    }

    public static void setUA(String userAgent) {
        BilibiliHTTPUtil.userAgent = userAgent;
    }

    public static void setRandomUA() {
        userAgent = randomUserAgent();
    }

    public static JSONObject JSONHTTPRequest(String url) throws MalformedURLException, IOException {
        URLConnection conn = new URL(url).openConnection();
        conn.addRequestProperty("User-Agent", userAgent);
        conn.addRequestProperty("Cookie", cookie);
        conn.addRequestProperty("Referer", "https://www.bilibili.com/");
        conn.setDoOutput(false);
        conn.setDoInput(true);
        conn.connect();

        JSONObject jo;
        if ("gzip".equals(conn.getContentEncoding())) {
            jo = JSONObject.parseObject(readInputStream(conn.getInputStream(), true));
        } else {
            jo = JSONObject.parseObject(readInputStream(conn.getInputStream(), false));
        }

        if (jo.containsKey("msg")) {
            if (!jo.getString("msg").equals("success")) {
                throw new ParseException("Message is not success");
            }
        } else if (jo.containsKey("message")) {
            if (!jo.getString("message").equals("0")) {
                throw new ParseException("Message is not 0");
            }
        } else {
            throw new ParseException("No message");
        }
        if (jo.getInteger("code") != 0) {
            throw new ParseException("Code is not 0");
        }
        return jo.getJSONObject("data");
    }

    private static String randomCookie() {
        return "_uuid=" + UUID.randomUUID().toString().toUpperCase()
                + "infoc; buvid3=" + UUID.randomUUID().toString().toUpperCase() + "infoc";
    }
}
