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
import static fifthlight.musiccore.util.UserAgentUtil.randomUserAgent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

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
    
    public static Document XMLHTTPRequest(String url) throws MalformedURLException, IOException, SAXException {
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
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            return db.parse(result);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(QQHTTPUtil.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}