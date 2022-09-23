package me.fistpump.partialstub;

import java.util.List;

public abstract class PartiallyImplementedGenericList implements List<String> {
    @Override
    public String get(int index) {
        return "index-" + index;
    }
}
