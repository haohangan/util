package org.eva.core.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.eva.core.demo.entity.PriceHistory;
import org.eva.core.demo.service.DemoService;
import org.eva.core.demo.vo.DemoSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	@Autowired
	private DemoService service;

	@RequestMapping("/")
	public Object home() {
		return service.listAll();
	}

	@RequestMapping("/search")
	public List<PriceHistory> search(@Valid DemoSearchVO svo) {
		return service.search(svo);
	}
	
	@RequestMapping("/search2")
	public List<PriceHistory> search2(@Validated DemoSearchVO svo) {
		return service.search(svo);
	}
}
