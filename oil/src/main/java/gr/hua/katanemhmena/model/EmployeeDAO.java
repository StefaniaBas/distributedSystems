package gr.hua.katanemhmena.model;

import java.util.List;
import gr.hua.katanemhmena.model.Employee;

public interface EmployeeDAO {
	
	//Create employee
    public void save(Employee employee,int sellerCounter,int CEOCounter,int adminCounter);
    //Get employee
    public Employee getById(int id);
    //Update employee
    public void update(Employee employee);
    //Delete employee
    public void deleteById(int id);
    //Get All employees
    public List<Employee> getAll();
    //Check employee
    public Employee checkEmployee(String name,String password);
}
