package curator_y.common_configuration.buss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import curator_y.common_configuration.buss.entity.Config;
import curator_y.common_configuration.buss.service.ConfigService;

/**
 * base访问
 * 
 * @author YKSE
 *
 */
@RequestMapping("config")
@RestController
public class ConfigController {

	@Autowired
	private ConfigService service;

	@RequestMapping(method = { RequestMethod.GET })
	public Iterable<Config> get() {
		return service.get();
	}
}
