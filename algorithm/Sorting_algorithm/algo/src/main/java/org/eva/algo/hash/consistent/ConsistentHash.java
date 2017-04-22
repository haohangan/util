package org.eva.algo.hash.consistent;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;

/**
 * 一致hash，参考维基百科
 * 
 * @author 976175665
 * @date 2017年4月22日 下午10:15:35
 * @param <T>
 */
public class ConsistentHash<T> {
	private static final Charset UTF_8 = StandardCharsets.UTF_8;
	private final HashFunction hashFunction;// hash方法
	private final int numberOfReplicas;// 复制品数目
	private final SortedMap<Integer, T> circle = new TreeMap<Integer, T>();// 环

	/**
	 * 
	 * @param hashFunction
	 *            初始化的时候选择的hash方法
	 * @param numberOfReplicas
	 * @param nodes
	 */
	public ConsistentHash(HashFunction hashFunction, int numberOfReplicas, Collection<T> nodes) {
		this.hashFunction = hashFunction;
		this.numberOfReplicas = numberOfReplicas;

		for (T node : nodes) {
			add(node);
		}
	}

	public void add(T node) {
		for (int i = 0; i < numberOfReplicas; i++) {
			// circle.put(hashFunction.hash(node.toString() + i), node);
			String key = node.toString() + i;
			HashCode hc = hashFunction.hashString(key, UTF_8);
			circle.put(hc.asInt(), node);
		}
	}

	public void remove(T node) {
		for (int i = 0; i < numberOfReplicas; i++) {
			String key = node.toString() + i;
			HashCode hc = hashFunction.hashString(key, UTF_8);
			circle.remove(hc.asInt());
		}
	}

	public T get(Object key) {
		if (circle.isEmpty()) {
			return null;
		}
		HashCode hc = hashFunction.hashString(key.toString(), UTF_8);
		int hash = hc.asInt();
		if (!circle.containsKey(hash)) {
			SortedMap<Integer, T> tailMap = circle.tailMap(hash);
			hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
		}
		return circle.get(hash);
	}
}
