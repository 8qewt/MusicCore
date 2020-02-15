/*
 * Copyright (C) 2019 liuyujie
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
package fifthlight.musiccore.util.qq;

import com.alibaba.fastjson.JSONObject;
import fifthlight.musiccore.exception.ParseException;
import static fifthlight.musiccore.util.UserAgentUtil.randomUserAgent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.dom4j.io.SAXReader;
import org.dom4j.Document;
import org.dom4j.DocumentException;

/**
 * QQ HTTP工具类
 *
 * @author liuyujie
 */
public class QQHTTPUtil {

    private static final String userAgent = randomUserAgent();

    public static JSONObject JSONHTTPRequest(String url) throws MalformedURLException, IOException {
        URLConnection conn = new URL(url).openConnection();
        conn.addRequestProperty("User-Agent", userAgent);
        conn.addRequestProperty("Cookie", "qqmusic_uin=12345678; qqmusic_key=12345678; qqmusic_fromtag=30; ts_last=y.qq.com/portal/player.html;");
        conn.addRequestProperty("Referer", "https://y.qq.com/portal/player.html");
        conn.connect();
        String result = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
        String line;
        while ((line = br.readLine()) != null) {
            result += line;
        }
        br.close();
        return JSONObject.parseObject(result);
    }
    
    public static Document XMLHTTPRequest(String url) throws MalformedURLException, IOException {
        URLConnection conn = new URL(url).openConnection();
        conn.addRequestProperty("User-Agent", userAgent);
        conn.addRequestProperty("Cookie", "qqmusic_uin=12345678; qqmusic_key=12345678; qqmusic_fromtag=30; ts_last=y.qq.com/portal/player.html;");
        conn.addRequestProperty("Referer", "https://y.qq.com/portal/player.html");
        conn.connect();
        SAXReader reader = new SAXReader();
        try {
            return reader.read(conn.getInputStream());
        } catch (DocumentException ex) {
            throw new ParseException();
        }
    }
}