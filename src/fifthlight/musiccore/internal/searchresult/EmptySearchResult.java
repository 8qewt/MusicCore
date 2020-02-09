/*
 * Copyright (C) 2020 liuyujie
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
package fifthlight.musiccore.internal.searchresult;

import fifthlight.musiccore.search.searchresult.SearchResult;
import fifthlight.musiccore.song.Song;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author liuyujie
 */
public class EmptySearchResult extends SearchResult<Song> {

    public EmptySearchResult() {
    }

    @Override
    public List<Song> getItems(int page) throws IOException {
        return null;
    }

    @Override
    public int length() {
        return 0;
    }

    @Override
    public int pageLength() {
        return 0;
    }
    
}
