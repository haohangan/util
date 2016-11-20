package curator_y.common_configuration.buss.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * base访问
 * 
 * @author YKSE
 *
 */
@RestController
public class BaseController {

	@RequestMapping("/")
	public String get() {
		return "this is register fister page,welcome!";
	}
}
