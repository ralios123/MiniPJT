package com.model2.mvc.web.user;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;


//==> ȸ������ RestController
@RestController
@RequestMapping("/user/*")
public class UserRestController {
	
	///Field
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	//setter Method ���� ����
		
	public UserRestController(){
		System.out.println(this.getClass());
	}
	
	@RequestMapping( value="json/getUser/{userId}", method=RequestMethod.GET )
	public User getUser( @PathVariable String userId ) throws Exception{
		
		System.out.println("/user/json/getUser : GET");
		
		//Business Logic
		return userService.getUser(userId);
	}

	@RequestMapping( value="json/login", method=RequestMethod.POST )
	public User login(	@RequestBody User user,
									HttpSession session ) throws Exception{
	
		System.out.println("/user/json/login : POST");
		//Business Logic
		System.out.println("::"+user);
		User dbUser=userService.getUser(user.getUserId());
		
		if( user.getPassword().equals(dbUser.getPassword())){
			session.setAttribute("user", dbUser);
		}
		
		return dbUser;
	}
	
	@RequestMapping(value="json/addUser", method =RequestMethod.POST)
		public User addUser(@RequestBody User user)throws Exception{
		
		System.out.println("/user/json/addUser : POST");
		
		
		userService.addUser(user);
		
			return user;
		
	}

	@RequestMapping(value="json/updateUser/{userId}", method = RequestMethod.GET)
	public User updateUser(@PathVariable String userId)throws Exception{
		
		
		System.out.println("/user/json/updateUser : GET");
		
	
		
		return 	userService.getUser(userId);
		
		
	}
	
	
	@RequestMapping(value = "json/updateUser", method = RequestMethod.POST)
	public User updateUser(@RequestBody(required = false) User user)throws Exception{
		
		System.out.println("/user/json/updateUser : POST");
		
		System.out.println("userȮ��:"+user);
	
		userService.updateUser(user);
		
		System.out.println("serverȮ��:"+user);
		
		return user;
	}
		

	
	@RequestMapping(value = "json/listUser", method = RequestMethod.GET)
		public User listUser(@RequestBody Search search, Model model,
																	HttpServletRequest request, int pageSize, int pageUnit)throws Exception{
			
		System.out.println("/user/json/listUser : GET ");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		// Business logic ����
		Map<String , Object> map=userService.getUserList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model �� View ����
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		
		
		
		return (User) userService.getUserList(search);
		
	
	}
	
	
	
	
	
}