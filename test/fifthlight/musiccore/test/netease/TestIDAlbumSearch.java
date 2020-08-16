/*
 * Copyright (C) 2019 fifth_light
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
package fifthlight.musiccore.test.netease;

import fifthlight.musiccore.album.Album;
import fifthlight.musiccore.factory.NeteaseMusicFactory;
import fifthlight.musiccore.search.IDSearch;
import fifthlight.musiccore.search.searchresult.SearchResult;
import fifthlight.musiccore.util.DumpUtil;
import java.io.IOException;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author fifth_light
 */
public class TestIDAlbumSearch {
    
    @Test
    public void testSearchIDOnce() throws Exception {
        IDSearch s = new IDSearch("3271289");
        SearchResult<Album> ar = NeteaseMusicFactory.getInstance().getAlbums(s);
        assertEquals(ar.length(), 1);
        assertEquals(ar.pageLength(), 1);
        vaildAlbum(ar.getItems(0).get(0));
    }

    @Test
    public void testSearchIDMulti() throws Exception {
        IDSearch s = new IDSearch(new String[]{"3271289", "2875038"});
        SearchResult<Album> ar = NeteaseMusicFactory.getInstance().getAlbums(s);
        assertEquals(ar.length(), 2);
        assertEquals(ar.pageLength(), 1);
        for(Album album : ar.getItems(0)){
            vaildAlbum(album);
        }
    }

    private void vaildAlbum(Album a) throws Exception {
        assertTrue("ゆりしゅらしゅしゅしゅ / おひるねゆにばーす".equals(a.getName()) || "YURUYURI♪♪ 2nd.Series BESTALBUM ゆるゆりずむ♪2".equals(a.getName()));
        if("3271289".equals(a.getID())){
            assertEquals(a.getTitle(), "ゆりしゅらしゅしゅしゅ / おひるねゆにばーす（TV动画《摇曳百合 夏日时光》主题曲专辑）");
        } else if("2875038".equals(a.getID())){
            assertEquals(a.getTitle(), a.getName());
        } 
        System.out.println("---Dump Album---");
        DumpUtil.dump(a, true, DumpUtil.getStandardOut());
    }
}
