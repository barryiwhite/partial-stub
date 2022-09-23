package me.fistpump.partialstub;

import javax.swing.table.TableModel;
import java.util.List;

public abstract class PartiallyImplementedTableModelAndList implements TableModel, List<String> {
    @Override
    public int getRowCount() {
        return 10;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return "column-" + columnIndex;
    }

    @Override
    public String get(int index) {
        return "index-" + index;
    }
}
