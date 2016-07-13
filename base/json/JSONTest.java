package com.grgbanking.api.jsontest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.time.StopWatch;
import org.junit.Before;
import org.junit.Test;

import com.grgbanking.api.action.utils.JSONUtils;
import com.grgbanking.api.action.vo.OrderInputDetailVO;
import com.grgbanking.api.action.vo.OrderInputVO;

import flexjson.JSONSerializer;

public class JSONTest {

	StopWatch sw;
	OrderInputVO vo;
	List<OrderInputDetailVO> details;

	@Before
	public void init() {
		sw = new StopWatch();

		details = new ArrayList<>();
		OrderInputDetailVO dvo1 = new OrderInputDetailVO();
		dvo1.setGoodsCode("LBG");
		dvo1.setGoodsName("老白干");
		dvo1.setGoodsType("9");
		dvo1.setPrice(new Double(100.00));
		dvo1.setCount(new Integer(100));
		dvo1.setUnit("瓶");
		details.add(dvo1);

		vo = new OrderInputVO();
		vo.setCode(UUID.randomUUID().toString());
		vo.setDetails(details);
		vo.setPno("1111111");
		vo.setPono("11111111111");
		vo.setPuserNo("X007");
		vo.setBusType("T");
		vo.setOrderTime(new Date());
		vo.setAmount(new Double(10000.00));
		vo.setBuyAmount(new Double(10000.00));
		vo.setBuyerCode("adadsa");
		vo.setBuyerName("刘老头");
		vo.setSupplierCode("bbbbbb");
		vo.setSupplierName("鲁提辖");
		vo.setPaymentTime(new Date());
		vo.setPaymentType("TT");
		vo.setStatus("0");
		vo.setRmk("这个是好酒");
		vo.setNotifyUrl("http://dsadsada/asdsad");
	}

	@Test
	public void test1() {
		sw.start();
		for (int i = 0; i < 10000; i++) {
			JSONUtils.toJsonString(vo);
		}
		sw.stop();
		System.out.println("耗時1：" + sw.getTime());
		
		sw.reset();
		sw.start();
		JSONSerializer serializer = new JSONSerializer();
		for (int i = 0; i < 10000; i++) {
			serializer.exclude("*.class").deepSerialize(vo);
		}
		sw.stop();
		System.out.println("耗時2：" + sw.getTime());
	}
}
