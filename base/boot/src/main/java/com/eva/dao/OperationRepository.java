package com.eva.dao;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eva.entity.Operation;

@Repository("operationRepository")
//@Transactional
public interface OperationRepository extends CrudRepository<Operation, Long> {
	
//	@Transactional(readOnly=true)
	List<Operation> findByOpName(String opName);

}
