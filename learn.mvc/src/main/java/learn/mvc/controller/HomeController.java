package learn.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;

import learn.mvc.dao.RedisDao;
import learn.mvc.models.Employee;
import learn.mvc.service.EmployeeService;

@Controller
public class HomeController {
	@Autowired
	private EmployeeService empService;
	@Autowired
	private RedisDao redisDao;
	
	private Gson gson=new Gson();
	
	private List<Employee> eList=new ArrayList<>();
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	@RequestMapping(value = "/pikachu/{x:[a-zA-Z0-9]+\\.[a-zA-Z0-9]+}", method = RequestMethod.GET)
	public String registerEmployee1(@PathVariable String x){
		return x;
	}
	
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerEmployee(Model model){
		model.addAttribute("employee",new Employee());
		return "registerEmployee2";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerEmployeePost(@Valid Employee e,Errors errors) {
		System.out.println("Post Method");
		if(errors.hasErrors()){
			return "registerEmployee2";
		}
		eList.add(e);
		
		redisDao.setToCache(e.getName(),gson.toJson(e));
		
		return "redirect:/emp";
	}
	
	@RequestMapping(value = "/emp", method = RequestMethod.GET)
	public String employee(Model model){
		//Employee e1=empService.getOneEmployee();
		if(eList.size()<1)eList=empService.getListEmployee();
		
		//model.addAttribute("emp",e1);
		model.addAttribute("empList",eList);
		
		return "employee";
	}
}