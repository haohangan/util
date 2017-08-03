package test.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import test.demo.OrderInfo;
@Mapper
public interface OrderMapper {

	List<OrderInfo> list();
}
