package learn.mvc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import learn.mvc.models.Employee;
import learn.mvc.models.ProjectLead;

@Service
public class EmployeeService {
	
	public ProjectLead getPL(){
		ProjectLead pl=new ProjectLead(1,"Praveen Kumar",2050000,null);
		pl.setEmployeeList(getListEmployee());
		return pl;
	}
	
	public Employee getOneEmployee(){
		Employee emp=new Employee(1,"Akash Bindal",1250000);
		return emp;
	}
	
	public List<Employee> getListEmployee(){
		List<Employee> empList=new ArrayList<>();
		Employee e1=new Employee(1,"Akash Bindal",1050000);
		Employee e2=new Employee(2,"Shashank",1150000);
		Employee e3=new Employee(3,"Nikhil",1250000);
		Employee e4=new Employee(4,"Boddu",1350000);
		Employee e5=new Employee(5,"Pikachu",1450000);
		empList.add(e1);empList.add(e2);
		empList.add(e3);empList.add(e4);empList.add(e5);		
		return empList;
	}
}
