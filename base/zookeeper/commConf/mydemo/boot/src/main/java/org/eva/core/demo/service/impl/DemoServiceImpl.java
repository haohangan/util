package org.eva.core.demo.service.impl;

import java.util.List;

import org.eva.core.demo.dao.PriceHistoryRepository;
import org.eva.core.demo.entity.PriceHistory;
import org.eva.core.demo.service.DemoService;
import org.eva.core.demo.vo.DemoSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("service_DemoServiceImpl")
public class DemoServiceImpl implements DemoService{
	
	@Autowired
	private PriceHistoryRepository dao;

	@Override
	public Iterable<PriceHistory> listAll() {
		return dao.findAll();
	}

	@Override
	public List<PriceHistory> search(DemoSearchVO svo) {
		return dao.findByitemName(svo.getItemName());
	}

}
