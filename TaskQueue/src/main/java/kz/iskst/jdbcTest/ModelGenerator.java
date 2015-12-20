package kz.iskst.jdbcTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kz.iskst.model.User;
import kz.iskst.model.UserRequest;
import kz.iskst.model.UserRequest.Problems;

public class ModelGenerator {
    	
	List<Integer> ids = new ArrayList<>();
	public List<Integer> getUserIds(List<User> userList){
	List<Integer> ids = new ArrayList<Integer>();	
		for (User us : userList){
		ids.add(us.getId());
	}
		return ids;
		}
	
	public List<Integer> getUserRequestIds(List<UserRequest> userList){
	List<Integer> ids = new ArrayList<Integer>();	
		for (UserRequest us : userList){
		ids.add(us.getId());
	}
		return ids;
		}
	
	public List<User> getUserList(int count){
		List<User> us = new ArrayList<User>();
	Random random = new Random();
	int rand = 0;
	for (int i=0; i<count; i++){
		rand = random.nextInt(10000);
	    String uname = "User" + rand;
	    String email = "email" + rand + "@mail.ru";
	    System.out.println(email);
	    User user = new User(uname,email);
	    //user.set
	    us.add(user);
	    
	}
	return us;
	}
	
	public List<UserRequest> getUserRequestsList(int count){
	    List<UserRequest> ur = new ArrayList<UserRequest>();
	    Random random = new Random(10000);
		int rand = 0;
		for (int i=0; i<count; i++){
			rand = random.nextInt(10);
		    String uname = "Serega";//"User" + rand;
		    String email = "Kotov@kotov.kk";//"email" + rand + "@mail.ru";
		    //System.out.println(email);
		    ur.add(new UserRequest(new User(uname,email),Problems.AUTOCAD,rand));
		    
		}
		return ur;
	}
	
	
}
