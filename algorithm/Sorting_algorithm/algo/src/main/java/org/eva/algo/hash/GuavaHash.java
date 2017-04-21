package org.eva.algo.hash;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
/**
 * https://github.com/google/guava/wiki/HashingExplained
 * @author 976175665
 * @date 2017年4月21日 上午10:34:07
 */
public class GuavaHash {

	public static void main(String[] args) {
		HashFunction hf = Hashing.md5();
		HashCode hc = hf.hashBytes("".getBytes());
		System.out.println(hc);
	}
}
