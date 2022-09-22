package me.fistpump.partialstub;

import org.junit.jupiter.api.Test;

import javax.swing.table.TableModel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PartialStubTest {
    @Test
    public void implementedMethods() {
        TableModel partiallyImplementedTableModel = PartialStub.create(PartiallyImplementedTableModel.class);
        assertEquals(10, partiallyImplementedTableModel.getRowCount());
        assertEquals("column-3", partiallyImplementedTableModel.getColumnName(3));
    }

    @Test
    public void unimplementedMethods() {
        PartialStubException exception = assertThrows(PartialStubException.class, () -> {
            TableModel partiallyImplementedTableModel = PartialStub.create(PartiallyImplementedTableModel.class);
            partiallyImplementedTableModel.getColumnCount();
        });
        assertEquals("Method is not implemented on abstract class", exception.getMessage());
    }
}