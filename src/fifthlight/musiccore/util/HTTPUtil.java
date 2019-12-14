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
package fifthlight.musiccore.util;

import static fifthlight.musiccore.util.UserAgentUtil.randomUserAgent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * HTTP工具类
 *
 * @author liuyujie
 */
public class HTTPUtil {

    private static final String userAgent = randomUserAgent();

    public static String HTTPRequest(String url) throws MalformedURLException, IOException {
        URLConnection conn = new URL(url).openConnection();
        conn.addRequestProperty("User-Agent", userAgent);
        conn.connect();
        String result = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
        String line;
        while ((line = br.readLine()) != null) {
            result += line;
        }
        br.close();
        return result;
    }
}
