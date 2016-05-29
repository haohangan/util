package com.eva.dao;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eva.entity.AppUser;

@Repository("userRepository")
//@Transactional
public interface UserRepository extends CrudRepository<AppUser, Long> {
	
//	@Transactional(readOnly=true)
	List<AppUser> findByName(String name);

}
