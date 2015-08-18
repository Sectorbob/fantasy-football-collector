package net.sectorbob.fantasy.support;

/**
 * Created by ltm688 on 8/16/15.
 */
public class TableCellData {
    private String data;

    public TableCellData() {}

    public TableCellData(String data) {
        setData(data);
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String toString() {
        return "data: " + getData();
    }
}
