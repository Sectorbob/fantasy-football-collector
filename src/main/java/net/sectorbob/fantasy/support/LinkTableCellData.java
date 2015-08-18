package net.sectorbob.fantasy.support;

/**
 * Created by ltm688 on 8/16/15.
 */
public class LinkTableCellData extends TableCellData {

    private String link;

    public LinkTableCellData() { }

    public LinkTableCellData(String link) {
        this.link = link;
    }

    public LinkTableCellData(String data, String link) {
        super(data);
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String toString() {
        return "data: " + getData() + " | link: " + getLink();
    }
}
