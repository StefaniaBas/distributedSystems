package gr.hua.katanemhmena;

import java.util.List; 
import org.springframework.context.support.ClassPathXmlApplicationContext; 
import org.springframework.http.HttpStatus; 
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.bind.annotation.ResponseBody; 
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PathVariable;
import gr.hua.katanemhmena.model.Auction;
import gr.hua.katanemhmena.model.Customer;
import gr.hua.katanemhmena.model.EmployeeDAO;
import gr.hua.katanemhmena.model.Order;
import gr.hua.katanemhmena.model.OrderDAO;
import gr.hua.katanemhmena.model.Product;

	@Controller
	public class ApiController {
	 ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
	 
	 //Get the EmployeeDAO Bean
	 EmployeeDAO employeeDAO = ctx.getBean("employeeDAO", EmployeeDAO.class);
	 //Get the orderDAO Bean
	 OrderDAO orderDAO = ctx.getBean("orderDAO", OrderDAO.class);
	 
	 
	 @RequestMapping(value = "/api/products", method = RequestMethod.GET, produces = "application/json")
	 public @ResponseBody List<Product> getEmployeesInJSON() {
		 List<Product> products =orderDAO.getAllProducts();
	     return products;
	 }
	 
	 @RequestMapping(value = "/api/customers", method = RequestMethod.GET, produces = "application/json")
	 public @ResponseBody List<Customer> getEssssInJSON() {
		 List<Customer> customers =orderDAO.getAllCustomers();
	     return customers;
	 }
	 
	 @RequestMapping(value = "/api/customers/{customerCode}", method = RequestMethod.GET, produces = "application/json")
	 public @ResponseBody Customer getCustomerInJSON(@PathVariable Integer customerCode) {
		 Customer customer =orderDAO.getByCustomerCode(customerCode);
	     return customer;
	 }
	 
	 @RequestMapping(value = "/api/auctions", method = RequestMethod.GET, produces = "application/json")
     public @ResponseBody List<Auction> getAuctionsInJSON() {
		 List<Auction> auctions= orderDAO.getAllAuctions();
		 return auctions;
     }
	
	 @RequestMapping(value = "/api/customer/{customerCode}", method = RequestMethod.GET, produces = "application/json")
	 public @ResponseBody List<Order> getMyOrdersInJSON(@PathVariable Integer customerCode){
		 List<Order> orders=orderDAO.getByCustomerCodeInOrders(customerCode);
	     return orders;
	 }
	 
	 @RequestMapping(value = "/api/order/addparam", method = RequestMethod.POST, produces = "application/json")
	 @ResponseStatus(HttpStatus.CREATED) 
	 public @ResponseBody void paramUpdateOrderInJSON(@RequestParam("orderCode") String s)  {
		 Order order=new Order();
		 int orderCode=Integer.parseInt(s);
		 order=orderDAO.getByOrderCode(orderCode);
		 System.out.println(order);
		 if(!order.equals(null)){
			 orderDAO.updateStatus("customerAccepted", orderCode);
		 }      
	 }
	 
	 @RequestMapping(value = "/api/orderReject/addparam", method = RequestMethod.POST, produces = "application/json")
	 @ResponseStatus(HttpStatus.CREATED) 
	 public @ResponseBody void paramUpdateRejectOrderInJSON(@RequestParam("orderCode") String s)  {
		 Order order=new Order();
		 int orderCode=Integer.parseInt(s);
		 order=orderDAO.getByOrderCode(orderCode);
		 System.out.println(order);
		 if(!order.equals(null)){
			 orderDAO.updateStatus("failed", orderCode);
		 }      
	 }
	 
	 @RequestMapping(value = "/api/customer/addparam", method = RequestMethod.POST, produces = "application/json")
	 @ResponseStatus(HttpStatus.CREATED) 
	 public @ResponseBody Customer paraminsertOrderInJSON(@RequestParam("customerCode") String s,@RequestParam("afm") String afm )  {
		 Customer customer=new Customer();
		 int customerCode=Integer.parseInt(s);
		 customer=orderDAO.checkCustomer(customerCode,afm);
		 System.out.println(customer);
		 String username = "user"+customerCode;
		 int password = customerCode ;
		 if(!customer.equals(null) && customer.getOnlineOrders().equals("disabled")){
			 orderDAO.UpdateUsrPassActivation(username, password,customer.getCustomerCode(),"enabled");
		 }		 
		 return customer;
	 }
	 
	 @RequestMapping(value = "/api/makeOrder/addparam", method = RequestMethod.POST, produces = "application/json")
	 @ResponseStatus(HttpStatus.CREATED) 
	 public @ResponseBody String paraminsertOrderInJSON(@RequestParam("customerCode") int customerCode,@RequestParam("quantityOfProducts") int quantityOfProducts,@RequestParam("deliveryTime") int deliveryTime,@RequestParam("productCode") int productCode )  {
		 String response = null;
		 Order order=new Order();
		 order.setCustomerCode(customerCode);
		 order.setQuantityOfProducts(quantityOfProducts);
		 order.setDeliveryTime(deliveryTime);
		 order.setProductCode(productCode);
		 System.out.println(order);
		 Customer customer = orderDAO.getByCustomerCode(customerCode);
		 Product product = orderDAO.getByProductCode(productCode);
		 String response2=null;
		 if(!(customer.equals(null) && product.equals(null))){
			 response="success";
			 if(customer.equals(null) && product.equals(null)){
				 response="both wrong";
			 }else{
				 if(customer.equals(null)){
					response="customer code wrong";
				 }else if(product.equals(null)){
					response="product code wrong";
				 }
				 if(quantityOfProducts>0.9*product.getQuantity()){
					 if(product.getQuantity()-quantityOfProducts<0){
						 response2="not available";  
					 }else{
						 orderDAO.saveOnlineOrder(order);
						 response2="ceo required";
					 }		  
				 }else{
					 orderDAO.saveOnlineOrder(order);
				 	response2="not ceo";
				 }
			 }   
		 }
		 return response+"-"+response2;
	 }
	 
	 @RequestMapping(value = "/api/email/addparam", method = RequestMethod.POST, produces = "application/json")
	 @ResponseStatus(HttpStatus.CREATED) 
	 public @ResponseBody String updateEmailInJSON(@RequestParam("email") String email)  {
		 String myResponse=null;
		 int subs = orderDAO.getByEmail(email);
		 System.out.println(subs);
		 if(subs==0){
			 orderDAO.saveSubscriber(email);
			 myResponse="not exist";
			 System.out.println(email);
			 return myResponse;
		 }else{
			 myResponse="exist";
			 return myResponse;
		 }
	 }
}
