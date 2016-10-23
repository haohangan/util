/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.comp.search;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.CompareGenerator;
import java.util.Objects;


/**
 *
 * @author 97617
 */
public class BinarySearch {
    public static int search(Comparable[] objs,Comparable t){
        assert !Objects.isNull(objs) && objs.length>0;
        assert !Objects.isNull(t);
        
        return search(objs,t,0,objs.length-1);
    }
    
    public static int search(Comparable[] objs,Comparable t,int left,int right){
        if(left < right){
            int mid = (right-left)/2 + left;
            switch (objs[mid].compareTo(t)) {
                case 0:
                    return mid;
                case -1:
                    return search(objs,t,mid+1,right);
                default:
                    return search(objs,t,left,mid-1);
            }
        }
        return -1;
    }
    
    public static void main(String[] args) {
        Integer[] arr = new Integer[]{1,2,3,4,5,6,7,8,9};
        System.out.println(BinarySearch.search(arr, 7));
    }
}
