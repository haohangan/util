package com.eva.rpcserver.base.data;

import java.util.UUID;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2016年3月1日 下午6:13:34
 * 类说明
 */
public class Test {
    public static void main(String[] args) {
		System.out.println(UUID.randomUUID().toString().replaceAll("-", "").length());
	}
}
