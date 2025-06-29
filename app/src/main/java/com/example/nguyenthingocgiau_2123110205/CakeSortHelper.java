package com.example.nguyenthingocgiau_2123110205;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CakeSortHelper {

    // Sắp xếp A-Z theo tên
    public static void sortAZ(List<Cake> list) {
        Collections.sort(list, Comparator.comparing(Cake::getName));
    }

    // Sắp xếp Z-A theo tên
    public static void sortZA(List<Cake> list) {
        Collections.sort(list, (c1, c2) -> c2.getName().compareTo(c1.getName()));
    }
}
