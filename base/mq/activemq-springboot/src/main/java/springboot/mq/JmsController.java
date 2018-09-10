package springboot.mq;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JmsController {
	
	@Autowired
	private Producer producer;
	
	
	@GetMapping("/")
	public String send(String msg) {
		msg = StringUtils.isBlank(msg)?"aaaaa":msg;
		producer.send(msg);
		return null;
	}
}
