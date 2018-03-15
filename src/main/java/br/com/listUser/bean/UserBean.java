package br.com.listUser.bean;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import org.primefaces.context.RequestContext;

import br.com.listUser.entity.User;
import br.com.listUser.service.UserService;

@ManagedBean
@RequestScoped
public class UserBean {
	@Inject
	UserService userService;
	private List<User> users;
	private User user;
	private User userEdit;
	private String check;
	
	
	@PostConstruct
    public void init() {
		try {
			setUsers(userService.findAll());
		} catch (NoResultException e) {
			System.out.println("Erro ao listar todos Users:"+e);
		}
		
		user = new User();
		userEdit = new User();
		
	}
	public String changeLabel(User usr) {
			if(usr.getIs_enabled()) {
				return "Ativo";
			}else {
				return "Inativo";
			}
		}
	public void populaEdit(User usr) {
		if(usr != null) {
			
			userEdit = usr;
			RequestContext.getCurrentInstance().execute("PF('dlg3').show();");
		    RequestContext.getCurrentInstance().update("form2");
		}
		
	}
	
	    public Date mostraData(){
	       Date data = new Date();
	       return data;
	    }
	public void save() throws ExecutionException {
		if(user != null) {
			user.setRegister_date(mostraData());
			userService.salvar(user);
			setUsers(userService.findAll());
			
			user = new User();
			RequestContext.getCurrentInstance().update("form");
		}
	}
	public void update(User usr) throws ExecutionException {
		if(usr != null) {
			userService.update(usr);
		}
	}
	public void delete(User usr) throws ExecutionException {
		userService.deletar(usr);
		setUsers(userService.findAll());
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}
	public User getUserEdit() {
		return userEdit;
	}
	public void setUserEdit(User userEdit) {
		this.userEdit = userEdit;
	}
	
	
	
	
	

}
