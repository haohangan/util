package drop.level.module.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
	public String test() {
		return "demo : test";
	}

	@RequestMapping(path = "test1", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
	public String test1() {
		return "demo : test1";
	}

	@RequestMapping(path = "test2", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
	public String test2() {
		return "demo : test2";
	}
}
