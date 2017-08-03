package test.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.demo.OrderInfo;
import test.demo.dao.OrderMapper;

@Service
public class OrderService {

	@Autowired
	OrderMapper mapper;
	
	public List<OrderInfo> list(){
		return mapper.list();
	}
}
