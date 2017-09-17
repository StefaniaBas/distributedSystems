package gr.hua.katanemhmena.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import gr.hua.katanemhmena.model.Employee;

public class EmployeeDAOImpl implements EmployeeDAO{
	
	private DataSource dataSource;
	  
    public void setDataSource(DataSource dataSource) {
    	this.dataSource = dataSource;
    }
	 
    public void save(Employee employee,int sellerCounter,int CEOCounter,int adminCounter) {
    	String query = "insert into Employee (id, name, role, password) values (?,?,?,?)";
        Connection con = null;
        PreparedStatement ps = null;
        int out = 0;
        try{
        	con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            if(adminCounter<1){
            	ps.setInt(1, employee.getId());
                ps.setString(2, employee.getName());
                ps.setString(3, employee.getRole().toLowerCase());
                ps.setString(4, employee.getPassword());
                out = ps.executeUpdate();
                adminCounter++;
                if(out !=0){
                	System.out.println("Admin saved with id="+employee.getId() +" "+adminCounter);
                }else System.out.println("Admin already exists with id="+employee.getId());
             }
             if(sellerCounter<4){
            	 ps.setInt(1, employee.getId());
            	 ps.setString(2, employee.getName());
            	 ps.setString(3, employee.getRole().toLowerCase());
            	 ps.setString(4, employee.getPassword());
            	 out = ps.executeUpdate();
            	 if(out !=0){
            		 System.out.println("Seller saved with id="+employee.getId() +" "+sellerCounter);
            	 }else System.out.println("Sellers are 4");
             }
             if(CEOCounter<1){
            	 ps.setInt(1, employee.getId());
            	 ps.setString(2, employee.getName());
            	 ps.setString(3, employee.getRole().toLowerCase());
            	 ps.setString(4, employee.getPassword());
            	 out = ps.executeUpdate();
            	 if(out !=0){
            		 System.out.println("Ceo saved with id="+employee.getId() +" "+CEOCounter);
            	 }else System.out.println("CEO already exists with id="+employee.getId());
             }	            
        }catch(SQLException e){
        	e.printStackTrace();
        }finally{
        	try {
        		ps.close();
        		con.close();
        	}catch (SQLException e) {
        		e.printStackTrace();
        	}
        }
    }
	    
    public Employee getById(int id) {
    	String query = "select name, role, password from Employee where id = ?";
        Employee emp = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
            	emp = new Employee();
                emp.setId(id);
                emp.setName(rs.getString("name"));
                emp.setRole(rs.getString("role"));
                emp.setPassword(rs.getString("password"));
                System.out.println("Employee Found::"+emp);
            }else{
                System.out.println("No Employee found with id="+id);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return emp;
    }
	 
    public void update(Employee employee) {
    	if(employee.getRole().equals("admin") || employee.getRole().equals("seller") || employee.getRole().equals("ceo")){
    		String query = "update Employee set name=?, role=?, password=? where id=?";
	        Connection con = null;
	        PreparedStatement ps = null;
	        try{
	        	con = dataSource.getConnection();
	            ps = con.prepareStatement(query);
	            ps.setString(1, employee.getName());
	            ps.setString(2, employee.getRole().toLowerCase());
	            ps.setString(3, employee.getPassword());
	            ps.setInt(4, employee.getId());
	            int out = ps.executeUpdate();
	            if(out !=0){
	            	System.out.println("Employee updated with id="+employee.getId());
	            }else System.out.println("No Employee found with id="+employee.getId());
	        }catch(SQLException e){
	            e.printStackTrace();
	        }finally{
	            try {
	                ps.close();
	                con.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
    	}else{
    		System.out.println("Wrong role! "+employee.getRole());
    		return;
    	}
    }
	 
    public void deleteById(int id) {
    	String query = "delete from Employee where id=?";
        Connection con = null;
        PreparedStatement ps = null;
        try{
        	con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            int out = ps.executeUpdate();
            if(out !=0){
                System.out.println("Employee deleted with id="+id);
            }else System.out.println("No Employee found with id="+id);
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try {
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
	 
    public List<Employee> getAll() {
        String query = "select id, name, role, password from Employee";
        List<Employee> empList = new ArrayList<Employee>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                Employee emp = new Employee();
                emp.setId(rs.getInt("id"));
                emp.setName(rs.getString("name"));
                emp.setRole(rs.getString("role"));
                emp.setPassword(rs.getString("password"));
                empList.add(emp);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return empList;
    }
	    
	    
    public Employee checkEmployee(String name, String password) {
        String query = "select name, role, password from Employee where name = ? and password=?";
        Employee emp = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if(rs.next()){
                emp = new Employee();
                emp.setName(name);
                emp.setPassword(password);
                emp.setRole(rs.getString("role"));
                System.out.println("Employee Found::"+emp);
            }else{
                System.out.println("No Employee found with name="+name+" and password="+password);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return emp;
    }    
}
