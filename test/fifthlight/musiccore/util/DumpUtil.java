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

import fifthlight.musiccore.dumper.Dumpers;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 通过反射来方便的进行dump
 *
 * @author liuyujie
 */
public class DumpUtil {

    private DumpUtil() {
    }

    private static DumpWriter standardOut;

    public static DumpWriter getStandardOut() {
        if (standardOut == null) {
            standardOut = new DumpWriter(System.out);
        }
        return standardOut;
    }

    public static void dump(Object object, boolean onlyBasicType,
            DumpWriter out) throws Exception {
        dump(getGetterList(object, onlyBasicType), object, out);
    }

    public static void dump(Collection<Method> methodList, Object object,
            DumpWriter out) throws Exception {
        for (Method method : methodList) {
            dump(method, object, out);
        }
    }

    public static void dump(Method method, Object object,
            DumpWriter out, Object... args) throws Exception {
            StringBuilder hint = new StringBuilder(method.getName());
            hint.append("(");
            boolean first = true;
            for (Object arg : args) {
                if (first) {
                    first = false;
                    hint.append(", ");
                }
                hint.append(arg.toString());
            }
            hint.append(")");
            dump(method, hint.toString(), object, out, args);
    }

    public static void dump(Method method, String hint, Object object,
            DumpWriter out, Object... args) throws Exception {
        if (method.getReturnType() != Void.class) {
            try {
                Object result = method.invoke(object, args);
                if(result == null) {
                    out.println(hint, " -> null");
                } else if(isBasicType(result.getClass())) {
                    out.println(hint, " -> ", String.valueOf(result));
                } else {
                    Dumpers.dump(hint, result, out);
                }
            } catch (InvocationTargetException ex) {
                out.println(hint, " -> ERROR");
                ex.getTargetException().printStackTrace();
                throw ex;
            }
        }
    }

    private static List<Method> getGetterList(Object object, boolean onlyBasicType) {
        List<Method> result = new ArrayList<Method>();
        for (Method method : object.getClass().getMethods()) {
            if (method.getParameterCount() == 0) {
                String name = method.getName();
                if (name.startsWith("get") && !name.equals("getClass")) {
                    if (onlyBasicType || isBasicType(method.getReturnType())) {
                        result.add(method);
                    }
                }
            }
        }
        return result;
    }

    private static boolean isBasicType(Class object) {
        return Number.class.equals(object) || Boolean.class.equals(object)
                || String.class.equals(object) || Void.class.equals(object);
    }

}
