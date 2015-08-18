package net.sectorbob.fantasy.model;

/**
 * Created by ltm688 on 8/16/15.
 */
public enum Position {
    Quarterback,
    Tight_End,
    Running_Back,
    Wide_Reciever,
    Kicker,
    Unsupported;

    public static Position fromString(String s) {
        switch(s.toUpperCase().trim()) {
            case "QB":
                return Position.Quarterback;
            case "TE":
                return Position.Tight_End;
            //case "CB": // For Jones, Taiwan in Stats sheet
            case "RB":
                return Position.Running_Back;
            case "WR":
                return Position.Wide_Reciever;
            case "PK":
                return Position.Kicker;
            default:
                return Position.Unsupported;

        }
    }
}
