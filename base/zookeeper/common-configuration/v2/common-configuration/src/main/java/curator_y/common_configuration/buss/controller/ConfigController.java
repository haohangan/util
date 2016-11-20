package curator_y.common_configuration.buss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import curator_y.common_configuration.buss.controller.requestvo.PageVO;
import curator_y.common_configuration.buss.controller.responsevo.Json;
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
	public Page<Config> getAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size) {
		Sort sort = new Sort(Direction.ASC, "id");
		Pageable pageable = new PageRequest(page, size, sort);
		return service.get(pageable);
	}

	@RequestMapping(value = "/{appName}", method = { RequestMethod.GET })
	public List<Config> getByAppName(@PathVariable("appName") String appName, PageVO pagevo) {
		Pageable pageable = new PageRequest(pagevo.getPage(), pagevo.getSize());
		return service.get(appName, pageable);
	}

	@RequestMapping(method = { RequestMethod.POST })
	public ResponseEntity<Json> create(@Validated Config config) {
		service.insert(config);
		Json json = new Json.Jsonbuilder().id("insert").msg("新增成功，实际上没有失败情况").SUCCESS().build();
		return new ResponseEntity<Json>(json, HttpStatus.OK);
	}
}
