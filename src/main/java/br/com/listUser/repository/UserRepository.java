package br.com.listUser.repository;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.listUser.entity.User;
@Stateful
public class UserRepository {
	@PersistenceContext
	EntityManager em;
	
	public void salvar(User usr) {
		Object o = em.merge(usr);
		em.persist(o);
		
	}

	public void update(User usr) {
		Object c=em.merge(usr);
		em.persist(c);
		
	}

	public void deletar(User usr) {
		Object c=em.merge(usr);
		em.remove(c);
		
	}

	public List<User> findAll() {
		TypedQuery<User> query  = em.createQuery("select f from User f",User.class);
		return query.getResultList();
	}

	public User findUserById(int id) {
		TypedQuery<User> query  = em.createQuery("select f from User f where id=?",User.class);
		query.setParameter(1, id);
		return query.getSingleResult();
		
	}

}
