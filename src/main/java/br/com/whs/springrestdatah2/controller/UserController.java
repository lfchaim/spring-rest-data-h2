package br.com.whs.springrestdatah2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.whs.springrestdatah2.Model.User;
import br.com.whs.springrestdatah2.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepo;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<User> findAll(){
		return userRepo.findAll();
	}

	@RequestMapping(value = "/find-by-id/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable final Long id){
		User user = userRepo.findOne(id);
		if( user == null ){
			String msg = "{\"message\":\"User with id " + id + " not found.\"}";
			return new ResponseEntity<String>(msg,HttpStatus.NOT_FOUND);
		}else{
			return new ResponseEntity<User>(user,HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/find-by-login/{loginName}", method = RequestMethod.GET)
	public ResponseEntity<?> findByLoginName(@PathVariable final String loginName){
		User user = userRepo.findByLoginName(loginName);
		if( user == null ){
			String msg = "{\"message\":\"User with login " + loginName + " not found.\"}";
			return new ResponseEntity<String>(msg,HttpStatus.NOT_FOUND);
		}else{
			return new ResponseEntity<User>(user,HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<User> create(@RequestBody final User user){
		userRepo.save(user);
		return new ResponseEntity<User>(userRepo.findByLoginName(user.getLoginName()),HttpStatus.OK);
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody User user) {
		User userData = userRepo.findOne(id);
        if (userData == null) {
        	String msg = "{\"message\":\"User with id " + id + " not found.\"}";
            return new ResponseEntity<String>(msg,HttpStatus.NOT_FOUND);
        }
        userData.setLoginName(user.getLoginName());
        userData.setFullName(user.getFullName());
        userData.setPassword(user.getPassword());
        userRepo.save(userData);
        return new ResponseEntity<User>(userData, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        User user = userRepo.findOne(id);
        if (user == null) {
        	String msg = "{\"message\":\"User with id " + id + " not found.\"}";
            return new ResponseEntity<String>(msg,HttpStatus.NOT_FOUND);
        }
        userRepo.delete(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
}
