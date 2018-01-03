package br.com.whs.springrestdatah2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import br.com.whs.springrestdatah2.Model.User;

@Component
public interface UserRepository extends JpaRepository<User, Long>{

	public User findByLoginName(String loginName);
	
}
