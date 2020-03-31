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
package fifthlight.musiccore.test;

import fifthlight.musiccore.interfaces.IdentifierAble;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;

/**
 *
 * @author liuyujie
 */
public class IdentifierAbleTest {
    public class StringIdentifier extends IdentifierAble{
        private final String ID;
        public StringIdentifier(String ID){
            this.ID = ID;
        }

        @Override
        public String getID() {
            return ID;
        }
    }
    
    @Test
    public void equalsTest(){
        assertEquals(new StringIdentifier("abc123"), new StringIdentifier("abc" + String.valueOf(123)));
    }
    
    @Test
    public void notEqualsTest(){
        assertNotEquals(new StringIdentifier("abc"), new StringIdentifier("123"));
    }
}
