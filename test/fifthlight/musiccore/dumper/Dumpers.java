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

import fifthlight.musiccore.dumper.PictureDumper;
import fifthlight.musiccore.Picture;
import fifthlight.musiccore.dumper.Dumper;
import fifthlight.musiccore.util.DumpUtil;
import fifthlight.musiccore.util.DumpWriter;

/**
 *
 * @author liuyujie
 */
public class Dumpers {
    public static Dumper[] dumpers = new Dumper[] {
        new PictureDumper(),
        new LyricDumper()
    };
    
    public static void dump(String hint, Object obj, DumpWriter writer) throws Exception {
        for(Dumper dumper : dumpers) {
            if(dumper.canDump(obj)) {
                writer.println(hint + " <" + obj.getClass().getTypeName() +
                        "> (Dumper " + dumper.getClass().getTypeName() + "):");
                writer.increaseTab();
                dumper.dump(obj, writer);
                writer.reduceTab();
            }
        }
        
        writer.println(hint == null ? "toString() -> " : hint + ".toString() -> ", obj.toString());
    }
}
