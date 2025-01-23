package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonUtils {
    public static Map<Integer, List<Integer>> getIndexsMap(int n) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int position = 1;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                map.put(position++, List.of(row, col));
            }
        }
        return map;
    }
}