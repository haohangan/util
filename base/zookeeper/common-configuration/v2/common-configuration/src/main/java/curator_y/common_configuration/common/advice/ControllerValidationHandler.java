package curator_y.common_configuration.common.advice;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerValidationHandler {
   
	@ExceptionHandler(value = BindException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public MessageDTO processValidationError(BindException ex){
		BindingResult result = ex.getBindingResult();
		List<FieldError> error = result.getFieldErrors();
		Set<String> set = new HashSet<String>();
		error.forEach((f)->{
			set.add(f.getDefaultMessage());
		});
		return new MessageDTO(set, MessageType.ERROR);
	}
}
