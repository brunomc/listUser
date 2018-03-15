package br.com.listUser.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.listUser.entity.User;
import br.com.listUser.repository.UserRepository;

@Stateless
public class UserService implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	UserRepository userRepository;
	
	
	public void salvar(User usr) {
		userRepository.salvar(usr);
		
	}
	public void update(User usr) {
		userRepository.update(usr);		
	}

	public void deletar(User usr) {
		userRepository.deletar(usr);
		
	}

	public List<User> findAll() {
		
		return userRepository.findAll();
	}
	public User findUserById(int id) {
		
		return userRepository.findUserById(id);
	}

	
}
