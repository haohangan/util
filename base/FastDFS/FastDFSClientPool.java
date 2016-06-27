package com.grgbanking.aptoto.fastdfs;

import org.springframework.aop.target.CommonsPool2TargetSource;

public class FastDFSClientPool extends CommonsPool2TargetSource {

	public FastDFSClient getFastDfsCleint() {
		FastDFSClient client = null;
		try {
			client = (FastDFSClient) this.getTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return client;
	}

	public void releaseClient(FastDFSClient target) {
		try {
			this.releaseTarget(target);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
