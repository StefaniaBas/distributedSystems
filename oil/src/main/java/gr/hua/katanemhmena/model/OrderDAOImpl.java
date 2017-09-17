package gr.hua.katanemhmena.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class OrderDAOImpl implements OrderDAO{
	
	private DataSource dataSource;
	  
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public Customer checkCustomer(int customerCode,String afm) {
    	String query = "select * from CustomerInfo where customerCode = ? and afm=?";
        Customer cust = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, customerCode);
            ps.setString(2, afm);
            rs = ps.executeQuery();
            if(rs.next()){
            	cust = new Customer();
            	cust.setCustomerCode(customerCode);
            	cust.setCompanyName(rs.getString("companyName"));
            	cust.setAfm(afm);
            	cust.setContactPerson(rs.getString("contactPerson"));
            	cust.setTelephoneNumber(rs.getInt("telephoneNumber"));
            	cust.setDeliveryAddress(rs.getString("deliveryAddress"));
            	cust.setUsername(rs.getString("username"));
            	cust.setPassword(rs.getInt("password"));
            	cust.setOnlineOrders(rs.getString("onlineOrders"));
                System.out.println("Customer Found::"+cust);
            }else{
                System.out.println("No Customer found with code="+customerCode);
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
        return cust;
    }
    
    public void saveCustomer(Customer customer) {
        String query = "insert into CustomerInfo (customerCode, companyName, afm, contactPerson, telephoneNumber, deliveryAddress, username, password, onlineOrders) values (?,?,?,?,?,?,?,?,?)";
        Connection con = null;
        PreparedStatement ps = null;
        int out = 0;
        try{
        	con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, customer.getCustomerCode());
            ps.setString(2, customer.getCompanyName());
            ps.setString(3, customer.getAfm());
            ps.setString(4, customer.getContactPerson());
	        ps.setInt(5, customer.getTelephoneNumber());
	        ps.setString(6, customer.getDeliveryAddress());
	        ps.setString(7, customer.getUsername());
	        ps.setInt(8, customer.getPassword());
	        ps.setString(9, "disabled");
	        out = ps.executeUpdate();
	        if(out !=0){
	        	System.out.println("Customer saved with code="+customer.getCustomerCode());
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
    
    public void saveOrder(Order order) {
        String query = "insert into OrderInfo (orderCode, customerCode, quantityOfProducts, deliveryTime, status, productCode, type) values (?,?,?,?,?,?,?)";
        Connection con = null;
        PreparedStatement ps = null;
        int out = 0;
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, order.getOrderCode());
            ps.setInt(2, order.getCustomerCode());
            ps.setInt(3, order.getQuantityOfProducts());
            ps.setInt(4, order.getDeliveryTime());
            ps.setString(5, "starter");
            ps.setInt(6, order.getProductCode());
            ps.setString(7, "byCall");
            out = ps.executeUpdate();
            if(out !=0){
                System.out.println("Order saved with code="+order.getOrderCode());
            }
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
    
    public void saveOnlineOrder(Order order) {
        String query = "insert into OrderInfo (orderCode, customerCode, quantityOfProducts, deliveryTime, status, productCode, type) values (?,?,?,?,?,?,?)";
        Connection con = null;
        PreparedStatement ps = null;
        int out = 0;
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, order.getOrderCode());
            ps.setInt(2, order.getCustomerCode());
            ps.setInt(3, order.getQuantityOfProducts());
            ps.setInt(4, order.getDeliveryTime());
            ps.setString(5, "starter");
            ps.setInt(6, order.getProductCode());
            ps.setString(7, "online");
            out = ps.executeUpdate();
            if(out !=0){
                System.out.println("Online order saved with code="+order.getOrderCode());
            }
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
    
    public Order getByOrderCode(int orderCode) {
    	String query = "select * from OrderInfo where orderCode = ?";
        Order ord = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, orderCode);
            rs = ps.executeQuery();
            if(rs.next()){
            	ord = new Order();
            	ord.setOrderCode(orderCode);
            	ord.setCustomerCode(rs.getInt("customerCode"));
            	ord.setQuantityOfProducts(rs.getInt("quantityOfProducts"));
            	ord.setDeliveryTime(rs.getInt("deliveryTime"));
            	ord.setStatus(rs.getString("status"));
            	ord.setProductCode(rs.getInt("productCode"));
            	ord.setType(rs.getString("type"));
                System.out.println("Order Found with code="+orderCode);
            }else{
                System.out.println("No Order found with code="+orderCode);
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
        return ord;
    }
 
    
    public Product getProductAvailability(int productCode) {
        String query = "select * from ProductsInfo where productCode = ?";
        Product product = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, productCode);
            rs = ps.executeQuery();
            if(rs.next()){
            	product = new Product();
            	product.setProductCode(productCode);
            	product.setQuantity(rs.getInt("quantity"));
            	product.setOilVariety(rs.getString("oilVariety"));
                System.out.println("Product Found with code="+productCode);
            }else{
                System.out.println("No Product found with code="+productCode);
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
        return product;
    }
    
    public void updateStatus(String status, int orderCode) {
        String query = "update OrderInfo set status=? where orderCode=?";
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, status);
            ps.setInt(2, orderCode);
            int out = ps.executeUpdate();
            if(out !=0){
                System.out.println("Order updated with orderCode="+orderCode);
            }else System.out.println("No Order found with orderCode="+orderCode);
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
    
    public void updateDeliveryTime(int deliveryTime, int orderCode) {
        String query = "update OrderInfo set deliveryTime=? where orderCode=?";
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, deliveryTime);
            ps.setInt(2, orderCode);
            int out = ps.executeUpdate();
            if(out !=0){
                System.out.println("Order updated with orderCode="+orderCode);
            }else System.out.println("No Order found with orderCode="+orderCode);
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
    
    public List<Order> getAll() {
        String query = "select * from OrderInfo";
        List<Order> orders = new ArrayList<Order>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
            	Order order = new Order();
            	order.setOrderCode(rs.getInt("orderCode"));
            	order.setCustomerCode(rs.getInt("customerCode"));
            	order.setQuantityOfProducts(rs.getInt("quantityOfProducts"));
            	order.setDeliveryTime(rs.getInt("deliveryTime"));
            	order.setStatus(rs.getString("status"));
            	order.setProductCode(rs.getInt("productCode"));
            	order.setType(rs.getString("type"));
            	orders.add(order);
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
        return orders;
    }
    
    public List<Auction> getAllAuctions() {
        String query = "select * from Auctions";
        List<Auction> auctions = new ArrayList<Auction>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
            	Auction auction = new Auction();
            	auction.setId(rs.getInt("id"));
            	auction.setTitle(rs.getString("title"));
            	auction.setLocation(rs.getString("location"));
            	auction.setDescription(rs.getString("description"));
            	auctions.add(auction);
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
        return auctions;
    }
    
    public List<Customer> getAllCustomers() {
        String query = "select * from CustomerInfo";
        List<Customer> customers = new ArrayList<Customer>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
            	Customer customer = new Customer();
            	customer.setCustomerCode(rs.getInt("customerCode"));
            	customer.setCompanyName(rs.getString("companyName"));
            	customer.setAfm(rs.getString("afm"));
            	customer.setContactPerson(rs.getString("contactPerson"));
            	customer.setTelephoneNumber(rs.getInt("telephoneNumber"));
            	customer.setDeliveryAddress(rs.getString("deliveryAddress"));
            	customer.setUsername(rs.getString("username"));
            	customer.setPassword(rs.getInt("password"));
            	customer.setOnlineOrders(rs.getString("onlineOrders"));
            	customers.add(customer);
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
        return customers;
    }

    public List<Order> getPendingOrders() {
        String query = "select * from OrderInfo where status=?";
        List<Order> orders = new ArrayList<Order>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, "pending");
            rs = ps.executeQuery();
            while(rs.next()){
            	Order order = new Order();
            	order.setOrderCode(rs.getInt("orderCode"));
            	order.setCustomerCode(rs.getInt("customerCode"));
            	order.setQuantityOfProducts(rs.getInt("quantityOfProducts"));
            	order.setDeliveryTime(rs.getInt("deliveryTime"));
            	order.setStatus(rs.getString("status"));
            	order.setProductCode(rs.getInt("productCode"));
            	order.setType(rs.getString("type"));
            	orders.add(order);
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
        return orders;
    }
    
    public List<Product> getAllProducts() {
        String query = "select * from ProductsInfo";
        List<Product> products = new ArrayList<Product>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
            	Product product = new Product();
            	product.setProductCode(rs.getInt("productCode"));
            	product.setQuantity(rs.getInt("quantity"));
            	product.setOilVariety(rs.getString("oilVariety"));
            	products.add(product);
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
        return products;
    }
    
    public Product getByProductCode(int productCode) {
    	String query = "select * from ProductsInfo where productCode = ?";
    	Product product = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, productCode);
            rs = ps.executeQuery();
            if(rs.next()){
            	product = new Product();
            	product.setProductCode(productCode);
            	product.setQuantity(rs.getInt("quantity"));
            	product.setOilVariety(rs.getString("oilVariety"));
                System.out.println("Product Found with code="+productCode);
            }else{
                System.out.println("No Product found with code="+productCode);
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
        return product;
    	
    }
    
    public Customer getByCustomerCode(int customerCode) {
    	String query = "select * from CustomerInfo where customerCode = ?";
    	Customer customer = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, customerCode);
            rs = ps.executeQuery();
            if(rs.next()){
            	customer = new Customer();
            	customer.setCustomerCode(customerCode);
            	customer.setCompanyName(rs.getString("companyName"));
            	customer.setAfm(rs.getString("afm"));
            	customer.setContactPerson(rs.getString("contactPerson"));
            	customer.setTelephoneNumber(rs.getInt("telephoneNumber"));
            	customer.setDeliveryAddress(rs.getString("deliveryAddress"));
            	customer.setUsername(rs.getString("username"));
            	customer.setPassword(rs.getInt("password"));
            	customer.setOnlineOrders(rs.getString("onlineOrders"));
                System.out.println("Customer Found with code="+customerCode);
            }else{
                System.out.println("No Customer found with code="+customerCode);
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
        return customer;
    }
    
    public void update(Customer customer) {
        String query = "update CustomerInfo set companyName=?, afm=?, contactPerson=?, telephoneNumber=?, deliveryAddress=? where customerCode=?";
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, customer.getCompanyName());
            ps.setString(2, customer.getAfm());
            ps.setString(3, customer.getContactPerson());
            ps.setInt(4, customer.getTelephoneNumber());
            ps.setString(5, customer.getDeliveryAddress());
            ps.setInt(6, customer.getCustomerCode());
            int out = ps.executeUpdate();
            if(out !=0){
                System.out.println("Customer updated with CustomerCode="+customer.getCustomerCode());
            }else System.out.println("No Customer found with CustomerCode="+customer.getCustomerCode());
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
    	return;
    }
    
    public void updateOrder(Order order) {
        String query = "update OrderInfo set customerCode=?, quantityOfProducts=?, deliveryTime=?, productCode=? where orderCode=?";
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, order.getCustomerCode());
            ps.setInt(2, order.getQuantityOfProducts());
            ps.setInt(3, order.getDeliveryTime());
            ps.setInt(4, order.getProductCode());
            ps.setInt(5, order.getOrderCode());
            int out = ps.executeUpdate();
            if(out !=0){
                System.out.println("Order updated with OrderCode="+order.getOrderCode());
            }else System.out.println("No Order found with OrderCode="+order.getOrderCode());
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
    	return;
    }
    
    public void saveproduct(Product product) {
        String query = "insert into ProductsInfo (productCode, quantity, oilVariety) values (?,?,?)";
        Connection con = null;
        PreparedStatement ps = null;
        int out = 0;
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, product.getProductCode());
            ps.setInt(2, product.getQuantity());
            ps.setString(3, product.getOilVariety());
            out = ps.executeUpdate();
            if(out !=0){
                System.out.println("Product saved with code="+product.getProductCode());
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
    
    public void updateProducts (Product product) { 
        String query = "update ProductsInfo set quantity=?, oilVariety=? where productCode=?";
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, product.getQuantity());
            ps.setString(2, product.getOilVariety());
            ps.setInt(3, product.getProductCode());
            int out = ps.executeUpdate();
            if(out !=0){
                System.out.println("Product updated with code="+product.getProductCode());
            }else System.out.println("No Product found with code="+product.getProductCode());
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
    
    public void deleteByCustomerCode(int customerCode) {
        String query = "delete from CustomerInfo where customerCode=?";
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, customerCode);
            int out = ps.executeUpdate();
            if(out !=0){
                System.out.println("Customer deleted with id="+customerCode);
            }else System.out.println("No Customer found with id="+customerCode);
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
    
    public void deleteProduct(int productCode) {
        String query = "delete from ProductsInfo where productCode=?";
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, productCode);
            int out = ps.executeUpdate();
            if(out !=0){
                System.out.println("Product deleted with code="+productCode);
            }else System.out.println("No Product found with code="+productCode);
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
    
    public Order getByProductCodeInOrders(int productCode) {
       String query = "select * from OrderInfo where productCode = ?";
       Order ord = null;
       Connection con = null;
       PreparedStatement ps = null;
       ResultSet rs = null;
       try{
           con = dataSource.getConnection();
           ps = con.prepareStatement(query);
           ps.setInt(1, productCode);
           rs = ps.executeQuery();
           if(rs.next()){
               ord = new Order();
               ord.setOrderCode(rs.getInt("orderCode"));
               ord.setCustomerCode(rs.getInt("customerCode"));
               ord.setQuantityOfProducts(rs.getInt("quantityOfProducts"));
               ord.setDeliveryTime(rs.getInt("deliveryTime"));
               ord.setStatus(rs.getString("status"));
               ord.setProductCode(productCode);
               ord.setType(rs.getString("type"));
              System.out.println("Order Found with code="+productCode);
           }else{
              System.out.println("No Order found with code="+productCode);
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
       return ord;
    }
    
    public List<Order> getByCustomerCodeInOrders(int customerCode) {
	    String query = "select * from OrderInfo where customerCode = ?";
	    List<Order> orders = new ArrayList<Order>();
        Order ord = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        	con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, customerCode);
            rs = ps.executeQuery();
            while(rs.next()){
                ord = new Order();
                ord.setOrderCode(rs.getInt("orderCode"));
                ord.setCustomerCode(customerCode);
                ord.setQuantityOfProducts(rs.getInt("quantityOfProducts"));
                ord.setDeliveryTime(rs.getInt("deliveryTime"));
                ord.setStatus(rs.getString("status"));
                ord.setProductCode(rs.getInt("productCode"));
                ord.setType(rs.getString("type"));
                orders.add(ord);
                System.out.println("Order Found with code"+customerCode);
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
        return orders; 
    }
    
    public void decreaseProducts(int productCode, int quantity, int quantityOfProducts) {
        String query = "update ProductsInfo set quantity=? where productCode=?";
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, quantity-quantityOfProducts);
            ps.setInt(2, productCode);
            int out = ps.executeUpdate();
            if(out !=0){
                System.out.println("Product updated with code="+productCode);
            }else System.out.println("No Product found with code="+productCode);
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
    
    public void UpdateUsrPassActivation(String username, int password, int customerCode,String activation) {
        String query = "update CustomerInfo set username=? , password=? , onlineOrders=? where customerCode = ?";
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setInt(2, password);
            ps.setString(3, activation);
            ps.setInt (4, customerCode);
            int out = ps.executeUpdate();
            if(out !=0){
                System.out.println("Customer updated with username="+username+" and password="+password);
            }else System.out.println("Customer didn't update with code="+customerCode);
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

    public void saveAuction(Auction auction) {
        String query = "insert into Auctions (id, title, location, description) values (?,?,?,?)";
        Connection con = null;
        PreparedStatement ps = null;
        int out = 0;
        try{
        con = dataSource.getConnection();
        	ps = con.prepareStatement(query);
            ps.setInt(1, auction.getId());
            ps.setString(2, auction.getTitle());
            ps.setString(3, auction.getLocation());
            ps.setString(4, auction.getDescription());
            out = ps.executeUpdate();
            if(out !=0){
            	System.out.println("Auction saved with id="+auction.getId());
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
    
    public void deleteAuction(int id) {
        String query = "delete from Auctions where id=?";
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            int out = ps.executeUpdate();
            if(out !=0){
                System.out.println("Auction deleted with id="+id);
            }else System.out.println("No Auction found with id="+id);
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
    
    public Auction getById(int id) {
        String query = "select title, location, description from Auctions where id = ?";
        Auction auction = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
            	auction = new Auction();
            	auction.setId(id);
            	auction.setTitle(rs.getString("title"));
            	auction.setLocation(rs.getString("location"));
            	auction.setDescription(rs.getString("description"));
                System.out.println("Auction Found with id="+id);
            }else{
                System.out.println("No Auction found with id="+id);
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
        return auction;
    }
    
    public void updateAuction(Auction auction) {
        String query = "update Auctions set title=?, location=?, description=? where id=?";
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, auction.getTitle());
            ps.setString(2, auction.getLocation());
            ps.setString(3, auction.getDescription());
            ps.setInt(4, auction.getId());
            int out = ps.executeUpdate();
            if(out !=0){
                System.out.println("Auction updated with id="+auction.getId());
            }else System.out.println("No Auction found with id="+auction.getId());
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
    	return;
    }

    public List<Subscriber> getAllSubscribers() {
        String query = "select * from Subscribers";
        List<Subscriber> subscribers = new ArrayList<Subscriber>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
            	Subscriber subscriber = new Subscriber();
            	subscriber.setEmail(rs.getString("email"));
            	subscribers.add(subscriber);
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
        return subscribers;
    }
    
    public void saveSubscriber(String email) {
        String query = "insert into Subscribers (email) values (?)";
        Connection con = null;
        PreparedStatement ps = null;
        int out = 0;
        try{
        	con = dataSource.getConnection();
            ps = con.prepareStatement(query);
	        ps.setString(1, email);
	        out = ps.executeUpdate();
	        if(out !=0){
	        	System.out.println("Subscriber saved with e-mail="+email);
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
    
    public int getByEmail(String email) {
        String query = "select email from Subscribers where email = ?";
        Subscriber subscriber = null;
        int counter=0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if(rs.next()){
            	subscriber = new Subscriber();
            	subscriber.setEmail(email);
                System.out.println("Subscriber Found with e-mail="+email);
                counter++;
            }else{
                System.out.println("No Subscriber found with email="+email);
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
        return counter;
    } 
}
