package net.sectorbob.fantasy.myfantasyleague;

import net.sectorbob.fantasy.model.Position;
import net.sectorbob.fantasy.support.LinkTableCellData;
import net.sectorbob.fantasy.support.TableCellData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ltm688 on 8/18/15.
 */
public class Util {

    public static int extractPlayerIdFromPlayerLinkCell(LinkTableCellData cell) {
        Matcher m = Pattern.compile("[\\p{Alnum}:/.']*\\?L=[0-9]+&P=([0-9]+)").matcher(cell.getLink());
        if( ! m.matches()) {
            throw new RuntimeException("Unable to parse the player link for the player id");
        }
        return Integer.parseInt(m.group(1));
    }

    public static String[] extractPlayerFirstLastPositionTeamFromCell(TableCellData cell) {
        Matcher m = Pattern.compile("([a-zA-Z0-9\\-'. ]+), ([a-zA-Z'. ]+) ([A-Z\\*]{2,3}) ([a-zA-Z]{2,3})").matcher(cell.getData());
        if( ! m.matches()) {
            throw new RuntimeException("Unable to parse the player name position and team from the player text:\n"+cell.getData());
        }
        return new String[] { m.group(2), m.group(1), m.group(4), m.group(3)};
    }

}
