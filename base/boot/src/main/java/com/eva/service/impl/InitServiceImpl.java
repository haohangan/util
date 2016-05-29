package com.eva.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eva.dao.OperationRepository;
import com.eva.dao.RoleRepository;
import com.eva.dao.UserRepository;
import com.eva.entity.AppUser;
import com.eva.entity.Operation;
import com.eva.entity.Role;
import com.eva.model.user.authcache.AuthCache;
import com.eva.service.InitService;

@Service
@Transactional
public class InitServiceImpl implements InitService{

	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;

	@Autowired
	@Qualifier("operationRepository")
	private OperationRepository operationRepository;

	@Autowired
	@Qualifier("roleRepository")
	private RoleRepository roleRepository;
	
	@Override
	public void init() {
		AppUser app = new AppUser();
		app.setName("KK");
		app.setAge(81);
		app.setPwd("213213");

		userRepository.save(app);
		
		Operation op = new Operation();
		op.setFop(null);
		op.setOpName("操作1");
		op.setOpType("Leaf");
		op.setOpUrl("/opt/name");
		operationRepository.save(op);

		Role role = new Role();
		role.setRoleName("角色1");
		role.setRoleType("测试角色");
		roleRepository.save(role);
		
		Set<Operation> set = new HashSet<>();
		set.add(op);
		role.setOpts(set);

		Set<Role> roles = new HashSet<>();
		roles.add(role);
		
		app.setRoles(roles);
		
		op.setRoles(roles);

		operationRepository.save(op);
		roleRepository.save(role);
		userRepository.save(app);
	}

	@Override
	public void initAuthCache() {
		AuthCache.init(operationRepository.findAll());		
	}

}
