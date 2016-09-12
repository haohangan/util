package com.eva.rpc.nios.rpc.analysis;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.eva.rpc.nios.rpc.common.ClassMap;
import com.eva.rpc.nios.rpc.vo.RequestVO;
import com.eva.rpc.nios.rpc.vo.ResponseVO;

public class ReqAnalysiser {

	public static ResponseVO process(RequestVO req) {
		ResponseVO resp = new ResponseVO();
		Class<?> targetClass = req.getType();
		Object target = ClassMap.INSTANCE.getBean(targetClass);
		Method method = null;
		try {
			if (req.getKvs() != null) {
				method = targetClass.getDeclaredMethod(req.getMethodName(), req.parameterTypes());
			} else {
				method = targetClass.getDeclaredMethod(req.getMethodName());
			}
		} catch (NoSuchMethodException | SecurityException e1) {
			resp.FALSE();
			resp.setResult(e1.getStackTrace());
			return resp;
		}
		Object rtn = null;
		try {
			if (req.getKvs() != null) {
				rtn = method.invoke(target, req.parameterValues());
			} else {
				rtn = method.invoke(target);
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			resp.FALSE();
			resp.setResult(e.getStackTrace());
			return resp;
		}
		resp.TRUE();
		if (rtn != null) {
			resp.setResult(rtn);
		}
		return resp;
	}
}
