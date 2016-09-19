package com.grgbanking.aptoto.controller.cometd.interceptor;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.grgbanking.aptoto.controller.cometd.vo.Json;

public class FileInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpServletRequest req = (HttpServletRequest) request;
		MultipartResolver res = new org.springframework.web.multipart.commons.CommonsMultipartResolver();
		if (res.isMultipart(req)) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
			Map<String, MultipartFile> files = multipartRequest.getFileMap();
			Iterator<String> iterator = files.keySet().iterator();
			while (iterator.hasNext()) {
				String formKey = (String) iterator.next();
				MultipartFile multipartFile = multipartRequest.getFile(formKey);
				String fileName = multipartFile.getOriginalFilename();
				if (!checkFile(fileName)) {
					response.setContentType("text/html;charset=utf-8");
					PrintWriter out = response.getWriter();
					Json j = new Json.Builder().failure("").msg("上传失败：文件类型错误:" + fileName).build();
					out.print(j.toJsonString());
					out.flush();
					out.close();
					return false;
				}
			}
		}
		return true;
	}

	private Set<String> suffix;

	public void setSuffix(Set<String> suffix) {
		this.suffix = suffix;
	}

	boolean checkFile(String fileName) {
		if (suffix == null) {
			suffix = new HashSet<>();
		}
		String[] arr = fileName.split("\\.");
		if (arr != null && arr.length > 1) {
			String ext = arr[arr.length - 1];
			if (suffix.contains(ext)) {
				return true;
			}
		}
		return false;
	}
}


String[] arr = fileName.split("\\.");
		if (arr != null && arr.length > 1) {
			String ext = arr[arr.length - 1];
			if (suffix.contains(ext)) {
				return true;
			}
		}
