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
package fifthlight.musiccore.internal.artist;

import com.alibaba.fastjson.JSONObject;
import fifthlight.musiccore.Picture;
import fifthlight.musiccore.artist.Artist;
import fifthlight.musiccore.internal.picture.BilibiliPicture;
import fifthlight.musiccore.util.bilibili.BilibiliHTTPUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author liuyujie
 */
public class BilibiliUser extends Artist {

    private int uid;
    private String name;
    private JSONObject spaceObject;

    public BilibiliUser(int uid, String name) {
        this.uid = uid;
        this.name = name;
    }

    public BilibiliUser(JSONObject object, int type) {
        switch (type) {
            case 0:
                // SPACE
                spaceObject = object;
                uid = spaceObject.getIntValue("mid");
                name = spaceObject.getString("name");
                break;
            default:
                throw new AssertionError();
        }
    }

    private void getSpaceObject() throws IOException {
        spaceObject = BilibiliHTTPUtil.JSONHTTPRequest("https://api.bilibili.com/x/space/acc/info?mid=" + uid);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getTitle() throws IOException {
        return getName();
    }

    @Override
    public List<Picture> getPictures() throws IOException {
        if (spaceObject == null) {
            getSpaceObject();
        }
        if (spaceObject.containsKey("face") && !spaceObject.getString("face").isEmpty()) {
            ArrayList<Picture> list = new ArrayList<Picture>(1);
            list.add(new BilibiliPicture(spaceObject.getString("face")));
            return list;
        } else {
            return null;
        }
    }

    @Override
    public String getID() {
        return String.valueOf(uid);
    }

    @Override
    public String getDescription() throws IOException {
        if (spaceObject == null) {
            getSpaceObject();
        }
        return spaceObject.getString("sign");
    }

}
