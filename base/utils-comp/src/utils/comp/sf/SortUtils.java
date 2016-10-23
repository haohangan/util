/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.comp.sf;

import java.util.Objects;

/**
 *
 * @author 97617
 */
public class SortUtils {

    public static void print(Object[] arr) {
        assert !Objects.isNull(arr) : "数组问题";
        for (Object obj : arr) {
            System.out.print(obj + "\t");
        }
        System.out.println("");
    }

    public static void swap(Object[] arr, int x, int y) {
        assert !Objects.isNull(arr) && arr.length >= 2 : "数组问题";
        assert x >= 0 && y >= 0 && x < arr.length && y < arr.length : "下标问题";
        if (x != y) {
            Object temp = arr[x];
            arr[x] = arr[y];
            arr[y] = temp;
        }
    }
}
