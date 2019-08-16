package org.benni.expensesbackend.controller;

import java.util.Map;

import org.benni.expensesbackend.model.db.ExUser;
import org.benni.expensesbackend.model.dto.UserDTO;
import org.benni.expensesbackend.repositories.ExUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private ExUserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping(path="api/user")
	@ResponseBody
	public UserDTO registerUser(@RequestBody Map<String, Object> obj) {
		String login = (String) obj.get("login");
		String password = (String) obj.get("password");
		String name = (String)obj.get("name");
		
		ExUser user = new ExUser();
		user.setLogin(login);
		user.setName(name);
		user.setPassword(this.passwordEncoder.encode(password));
		
		this.userRepository.save(user);
		
		return new UserDTO(user);		
	}
	
}
