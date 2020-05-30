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
package fifthlight.musiccore.song.lyric;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * LRC歌词
 *
 * @author liuyujie
 */
public class LrcLyric extends TimeLineLyric implements Serializable, LyricMetadata {

    private final Set<TimeLine> timeLineSet;
    private static final Pattern metaDataRegex = Pattern.compile("^\\[([a-z]+):(.*)\\]$");
    private static final Pattern textlineRegex = Pattern.compile("^((\\[([0-9]{2}:[0-9]{2}.([0-9]{1,3}){0,1})\\])*)(.*)$");
    private static final Pattern timeRegex = Pattern.compile("\\[([0-9]{2}):([0-9]{2}).([0-9]{1,3})\\]");
    private static final Pattern isLRCRegex = Pattern.compile("^\\[.*\\]", Pattern.MULTILINE);
    private final HashMap<String, String> metaData = new HashMap<String, String>();

    public LrcLyric(String source) {
        double lastTime = 0;
        timeLineSet = new HashSet<TimeLine>();
        for (String line : source.split("\n")) {
            Matcher m = metaDataRegex.matcher(line);
            if (m.find()) {
                metaData.put(m.group(1), m.group(2));
            } else {
                m = textlineRegex.matcher(line);
                if (m.find()) {
                    Matcher timeMatcher = timeRegex.matcher(m.group(1));
                    while (timeMatcher.find()) {
                        lastTime = Integer.valueOf(timeMatcher.group(1)) * 60 + 
                                Integer.valueOf(timeMatcher.group(2)) + 
                                Integer.valueOf(timeMatcher.group(3)) * 0.01d;
                        timeLineSet.add(new LrcTimeline(lastTime, m.group(5)));
                    }
                } else {
                    timeLineSet.add(new LrcTimeline(lastTime, m.group(5)));
                }
            }
        }
    }

    /**
     * LRC歌词的一个时间轴
     *
     * @author liuyujie
     */
    public class LrcTimeline extends TimeLine {

        private final double time;
        private final String text;

        protected LrcTimeline(double time, String text) {
            this.time = time;
            this.text = text;
        }

        @Override
        public double getTime() {
            return time;
        }

        @Override
        public String getText() {
            return text;
        }

    }

    @Override
    public Set<TimeLine> getTimeLinesSet() {
        return timeLineSet;
    }

    @Override
    public String getArtistName() {
        return metaData.get("ar");
    }

    @Override
    public String getAlbumName() {
        return metaData.get("al");
    }

    @Override
    public String getTitle() {
        return metaData.get("tl");
    }

    @Override
    public String getAuthor() {
        return metaData.get("by");
    }
    
    public static boolean isLRC(String source){
        return isLRCRegex.matcher(source).find();
    }

}
