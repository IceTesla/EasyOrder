package cn.IceTesla.easyorder.Data.Model;

import java.io.Serializable;

/**
 * Created by IceTesla on 2017/9/3.
 */

public class TableModel implements Serializable {
    private String name;
    private boolean empty;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }
}
