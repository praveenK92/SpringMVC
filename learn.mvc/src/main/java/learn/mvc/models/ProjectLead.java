package learn.mvc.models;

import java.util.List;

public class ProjectLead {
	private int id;
	private String name;
	private int salary;
	private List<Employee> employeeList;
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employeeList == null) ? 0 : employeeList.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + salary;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjectLead other = (ProjectLead) obj;
		if (employeeList == null) {
			if (other.employeeList != null)
				return false;
		} else if (!employeeList.equals(other.employeeList))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (salary != other.salary)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ProjectLead [id=" + id + ", name=" + name + ", salary=" + salary + ", employeeList=" + employeeList
				+ "]";
	}
	public ProjectLead(){}
	public ProjectLead(int id, String name, int salary, List<Employee> employeeList) {
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.employeeList = employeeList;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public List<Employee> getEmployeeList() {
		return employeeList;
	}
	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}
}
