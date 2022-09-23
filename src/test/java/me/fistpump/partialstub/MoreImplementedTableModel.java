package me.fistpump.partialstub;

import java.util.Arrays;

public abstract class MoreImplementedTableModel extends PartiallyImplementedTableModel {
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return Arrays.asList(rowIndex, columnIndex);
    }
}
