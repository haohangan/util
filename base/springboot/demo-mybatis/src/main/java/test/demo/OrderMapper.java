package test.demo;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface OrderMapper {

	List<OrderInfo> list();
}
