package me.fistpump.partialstub;

import javax.swing.table.TableModel;

public abstract class PartiallyImplementedTableModel implements TableModel {
    @Override
    public int getRowCount() {
        return 10;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return "column-" + columnIndex;
    }
}
