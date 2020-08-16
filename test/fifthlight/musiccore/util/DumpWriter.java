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
package fifthlight.musiccore.util;

import java.io.PrintStream;
import java.util.Collections;

/**
 *
 * @author liuyujie
 */
public class DumpWriter {
    private int tabs = 0;
    private final PrintStream printWriter;

    public DumpWriter(PrintStream printWriter) {
        this.printWriter = printWriter;
    }
    
    public void increaseTab() {
        tabs++;
    }
    
    public void reduceTab() {
        if(tabs >= 1) {
            tabs--;
        }
    }
    
    public void println(Object... objs) {
        String tab = String.join("", Collections.nCopies(tabs, "\t"));
        String tab1 = String.join("", Collections.nCopies(tabs + 1, "\t"));
        printWriter.print(tab);
        for(Object obj : objs) {
            String str = String.valueOf(obj);
            printWriter.print(str.replaceAll("\n", "\n" + tab1));
        }
        printWriter.println();
    }
    
}
