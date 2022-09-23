package me.fistpump.partialstub;

import org.junit.jupiter.api.Test;

import javax.swing.table.TableModel;
import java.util.Arrays;
import java.util.List;

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
        AbstractMethodError exception = assertThrows(AbstractMethodError.class, () -> {
            TableModel partiallyImplementedTableModel = PartialStub.create(PartiallyImplementedTableModel.class);
            partiallyImplementedTableModel.getColumnCount();
        });
        assertEquals("Method is not implemented on abstract class", exception.getMessage());
    }

    @Test
    public void implementedMethodsOnHierarchy() {
        TableModel partiallyImplementedTableModel = PartialStub.create(MoreImplementedTableModel.class);
        assertEquals(10, partiallyImplementedTableModel.getRowCount());
        assertEquals("column-3", partiallyImplementedTableModel.getColumnName(3));
        assertEquals(Arrays.asList(4, 5), partiallyImplementedTableModel.getValueAt(4, 5));
    }

    @Test
    public void unimplementedMethodsOnHierarchy() {
        AbstractMethodError exception = assertThrows(AbstractMethodError.class, () -> {
            TableModel partiallyImplementedTableModel = PartialStub.create(PartiallyImplementedTableModel.class);
            partiallyImplementedTableModel.getColumnCount();
        });
        assertEquals("Method is not implemented on abstract class", exception.getMessage());
    }

    @Test
    public void nothingImplemented() {
        AbstractMethodError exception = assertThrows(AbstractMethodError.class, () -> {
            TableModel notImplementedTableModel = PartialStub.create(TableModel.class);
            notImplementedTableModel.getColumnCount();
        });
        assertEquals("Method is not implemented on abstract class", exception.getMessage());
    }

    @Test
    public void implementedGenericMethod() {
        List<String> partiallyImplementedGenericList = PartialStub.create(PartiallyImplementedGenericList.class);
        assertEquals("index-3", partiallyImplementedGenericList.get(3));
    }

    @Test
    public void implementedMethodsFromTwoInterfaces() {
        PartiallyImplementedTableModelAndList partiallyImplementedTableModelAndList = PartialStub.create(PartiallyImplementedTableModelAndList.class);
        assertEquals(10, partiallyImplementedTableModelAndList.getRowCount());
        assertEquals("column-3", partiallyImplementedTableModelAndList.getColumnName(3));
        assertEquals("index-3", partiallyImplementedTableModelAndList.get(3));
    }
}