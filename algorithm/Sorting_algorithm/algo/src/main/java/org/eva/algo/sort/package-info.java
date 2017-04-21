/**
 * https://zh.wikipedia.org/wiki/%E6%8E%92%E5%BA%8F%E7%AE%97%E6%B3%95
不稳定排序算法可能会在相等的键值中改变纪录的相对次序，但是稳定排序算法从来不会如此。不稳定排序算法可以被特别地实现为稳定。作这件事情的一个方式是人工扩充键值的比较，如此在其他方面相同键值的两个对象间之比较，（比如上面的比较中加入第二个标准：第二个键值的大小）就会被决定使用在原先数据次序中的条目，当作一个同分决赛。然而，要记住这种次序通常牵涉到额外的空间负担。
排序算法列表[编辑]
在这个表格中，n是要被排序的纪录数量以及k是不同键值的数量。
稳定的排序[编辑]
冒泡排序（bubble sort）— O(n2)
插入排序（insertion sort）—O(n2)
鸡尾酒排序（cocktail sort）—O(n2)
桶排序（bucket sort）—O(n)；需要O(k)额外空间
计数排序（counting sort）—O(n+k)；需要O(n+k)额外空间
归并排序（merge sort）—O(n log n)；需要O(n)额外空间
原地归并排序— O(n log2 n)如果使用最佳的现在版本
二叉排序树排序（binary tree sort）— O(n log n)期望时间；O(n2)最坏时间；需要O(n)额外空间
鸽巢排序（pigeonhole sort）—O(n+k)；需要O(k)额外空间
基数排序（radix sort）—O(n·k)；需要O(n)额外空间
侏儒排序（gnome sort）— O(n2)
图书馆排序（library sort）— O(n log n)期望时间；O(n2)最坏时间；需要(1+ε)n额外空间
块排序（block sort）— O(n log n)
不稳定的排序[编辑]
选择排序（selection sort）—O(n2)
希尔排序（shell sort）—O(n log2 n)如果使用最佳的现在版本
Clover排序算法（Clover sort）—O(n)期望时间，O(n2)最坏情况
梳排序— O(n log n)
堆排序（heap sort）—O(n log n)
平滑排序（smooth sort）— O(n log n)
快速排序（quick sort）—O(n log n)期望时间，O(n2)最坏情况；对于大的、随机数列表一般相信是最快的已知排序
内省排序（introsort）—O(n log n)
耐心排序（patience sort）—O(n log n + k)最坏情况时间，需要额外的O(n + k)空间，也需要找到最长的递增子序列（longest increasing subsequence）
不实用的排序[编辑]
Bogo排序— O(n × n!)，最坏的情况下期望时间为无穷。
Stupid排序—O(n3);递归版本需要O(n2)额外存储器
珠排序（bead sort）— O(n) or O(√n),但需要特别的硬件
煎饼排序—O(n),但需要特别的硬件
臭皮匠排序（stooge sort）算法简单，但需要约n^2.7的时间
 */
/**
 * @author 976175665
 * @date 2017年4月9日 下午11:31:38
 */
package org.eva.algo.sort;