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
package fifthlight.musiccore.dumper;

import fifthlight.musiccore.Picture;
import fifthlight.musiccore.util.DumpUtil;
import fifthlight.musiccore.util.DumpWriter;

/**
 *
 * @author liuyujie
 */
public class PictureDumper extends Dumper {

    protected PictureDumper() {
    }

    public boolean canDump(Object object) {
        return object instanceof Picture;
    }

    @Override
    public void dump(Object pic, DumpWriter out) throws Exception {
        DumpUtil.dump(pic.getClass().getMethod("getURL", int.class, int.class),
                "getURL(0, 0)", pic, out, 0, 0);
        DumpUtil.dump(pic.getClass().getMethod("getURL", int.class, int.class),
                "getURL(50, 50)", pic, out, 50, 50);
    }
}
