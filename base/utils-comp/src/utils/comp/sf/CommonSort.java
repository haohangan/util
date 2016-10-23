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
public class CommonSort {

    public static void sort(Comparable[] objs) {
//        if (Objects.isNull(objs) || objs.length == 0) {
//            Logger.getGlobal().info("排序数组为空");
//        }else{
//            
//        }
        assert (!Objects.isNull(objs)) && objs.length != 0 : "排序数组为空";
        sort(objs, 0, objs.length - 1);
    }

    public static void sort(Comparable[] objs, int left, int right) {
        if (left < right) {
            int i = left;
            int j = right + 1;
            Comparable povit = objs[i];
            while (i < j) {
                while (--j > i) {
                    if (objs[j].compareTo(povit) == -1) {
                        break;
                    }
                }
                while (++i < j) {
                    if (objs[i].compareTo(povit) >= 0) {
                        break;
                    }
                }
                if (i < j) {
                    SortUtils.swap(objs, i, j);
                }
            }
            if (objs[i].compareTo(povit) == -1) {//i位置上的数，很可能不是中间数
                SortUtils.swap(objs, left, i);
            }
            sort(objs, left, i - 1);
            sort(objs, i, right);
        }
    }

    public static void main(String[] args) {
        Double[] ints = new Double[]{4.12D, 4.18D, 4.15D,4.89D,4.99D,4.05D,4.78D,4.66D};
        System.out.print("原始顺序：");
        SortUtils.print(ints);
        sort(ints);
        System.out.print("排序顺序：");
        SortUtils.print(ints);
    }
}
