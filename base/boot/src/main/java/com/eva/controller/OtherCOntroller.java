package com.eva.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.eva.dao.OperationRepository;
import com.eva.dao.RoleRepository;
import com.eva.dao.UserRepository;
import com.eva.model.user.authcache.AuthCache;
import com.eva.service.InitService;

//@RestController
public class OtherCOntroller {

	// @Autowired
	// private IService service;

	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;

	@Autowired
	@Qualifier("operationRepository")
	private OperationRepository operationRepository;

	@Autowired
	@Qualifier("roleRepository")
	private RoleRepository roleRepository;
	
	@Autowired
	private InitService service;

//	@RequestMapping("/obj")
	public Object obj(String name) {
		System.out.println("name:" + name);
		List<Object> list = new ArrayList<Object>();
		// userRepository.findAll().forEach((t)->{System.out.println(t);list.add(t);});
		// operationRepository.findAll().forEach((t)->{System.out.println(t);list.add(t);});
		// roleRepository.findAll().forEach((t)->{System.out.println(t);list.add(t);});

//		Iterable<AppUser> it = userRepository.findAll();
//		it.forEach((t) -> t.getRoles().forEach((b) -> System.out.println(b.getRoleName() + b.getOpts().toString())));
//
//		Iterable<Operation> it2 = operationRepository.findAll();
//		it2.forEach((t) -> System.out.println(t));
//
//		Iterable<Role> it3 = roleRepository.findAll();
//		it3.forEach((t) -> System.out.println(t));
		AuthCache.init(operationRepository.findAll());// 初始化
		return list;
	}

//	@RequestMapping("/init")
	public void init() {
//		AppUser app = new AppUser();
//		app.setName("KK");
//		app.setAge(81);
//		app.setPwd("213213");
//
//		Operation op = new Operation();
//		op.setFop(null);
//		op.setOpName("操作1");
//		op.setOpType("Leaf");
//		op.setOpUrl("/test/name");
//		
//
//		Role role = new Role();
//		role.setRoleName("角色1");
//		role.setRoleType("测试角色");
//		Set<Operation> set = new HashSet<>();
//		set.add(op);
//		role.setOpts(set);
//
//		Set<Role> roles = new HashSet<>();
//		roles.add(role);
//		
//		app.setRoles(roles);
//		
//		op.setRoles(roles);
//
//		operationRepository.save(op);
//		roleRepository.save(role);
//		userRepository.save(app);
		service.init();
	}
}
