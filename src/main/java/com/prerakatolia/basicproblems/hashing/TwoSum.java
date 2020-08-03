package com.prerakatolia.basicproblems.hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoSum {
    /*Given an array of integers, find two numbers such that
    they add up to a specific target number.*/
    public ArrayList<Integer> twoSum(final List<Integer> A, int B) {
        Map<Integer, Integer> numberIndexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < A.size(); i++) {
            if (numberIndexMap.containsKey(B - A.get(i))) {
                ArrayList<Integer> pair = new ArrayList<Integer>();
                pair.add(numberIndexMap.get(B - A.get(i)));
                pair.add(i + 1);
                return pair;
            } else {
                if (!numberIndexMap.containsKey(A.get(i))) {
                    numberIndexMap.put(A.get(i), i + 1);
                }

            }
        }
        return new ArrayList<Integer>();
    }
}
