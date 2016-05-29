package com.eva.dao;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eva.entity.Role;

@Repository("roleRepository")
//@Transactional
public interface RoleRepository extends CrudRepository<Role, Long> {
	
//	@Transactional(readOnly=true)
	List<Role> findByRoleName(String roleName);

}
