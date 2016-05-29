package com.eva.advice;

import java.awt.TrayIcon.MessageType;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerValidationHandler {

	@Autowired
	private MessageSource msgSource;

	// @ExceptionHandler(value =
	// MethodArgumentNotValidException.class)//不是这个exception
	@ExceptionHandler(value = BindException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public MessageDTO processValidationError(BindException ex) {
		// StringBuilder sb = new StringBuilder("[");
		// ex.getBindingResult().getAllErrors().forEach((t)->{
		// System.out.println(t.getCode());
		// sb.append(t.getObjectName());
		// sb.append(":");
		// sb.append(t.getDefaultMessage());
		// sb.append(",");
		// });
		// sb.delete(sb.lastIndexOf(","), sb.length());
		// sb.append("]");
		BindingResult result = ex.getBindingResult();
		List<FieldError> error = result.getFieldErrors();
		Set<String> set = new HashSet<String>();
		error.forEach((t) -> {
			set.add(t.getDefaultMessage());
		});
		// return new MessageDTO(MessageType.ERROR,sb.toString());
		return new MessageDTO(MessageType.ERROR, set);
	}

	@SuppressWarnings("unused")
	private MessageDTO processFieldError(FieldError error) {
		MessageDTO message = null;
		if (error != null) {
			Locale currentLocale = LocaleContextHolder.getLocale();
			String msg = msgSource.getMessage(error.getDefaultMessage(), null, currentLocale);
			// message = new MessageDTO(MessageType.ERROR, msg);
		}
		return message;
	}

}
