package gr.hua.katanemhmena;

import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import gr.hua.katanemhmena.model.Auction;
import gr.hua.katanemhmena.model.Customer;
import gr.hua.katanemhmena.model.Employee;
import gr.hua.katanemhmena.model.EmployeeDAO;
import gr.hua.katanemhmena.model.Order;
import gr.hua.katanemhmena.model.OrderDAO;
import gr.hua.katanemhmena.model.Product;
import gr.hua.katanemhmena.model.SendMail;
import gr.hua.katanemhmena.model.Subscriber;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	//limit the number of users in system. max admin:1, max seller:4, max CEO:1
	int sellerCounter=0;
	int CEOCounter=0;
	int adminCounter=0;
	//to pass the orderCode in methods
	int checkTimeCode=0;
	//check if user exists or not
	int counterException=0;
	//variables to compare values with the given ones
	String name1,password1;
	//variable to control the flow of the success and failure messages
	String checksSF="";
	String checkAccess="";
	//variable to control the url cheating.For example,if the connected employee is CEO,he mustn't go to admin pages.
	Employee connectedEmployee = new Employee();
	SendMail mail = new SendMail();
	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        //Get the EmployeeDAO Bean
	EmployeeDAO employeeDAO = ctx.getBean("employeeDAO", EmployeeDAO.class);
	//Get the EmployeeDAO Bean
	OrderDAO orderDAO = ctx.getBean("orderDAO", OrderDAO.class);

	
	//display all employees and modify them
	@RequestMapping("/adminhome")
	public String adminhome(Model model) {
		try{
			if(connectedEmployee.getRole().equals("admin")){
				model.addAttribute("employee", new Employee());
				List<Employee> empList = employeeDAO.getAll();
				model.addAttribute("employees", empList);
				return "adminhome";
			}else{
				return "access";
			}
		}catch(NullPointerException e){
			return "access";
		}
	}
	
	//display all orders and modify them
	@RequestMapping("/sellerhome")
	public String sellerhome(Model model) {
		try{
			if(connectedEmployee.getRole().equals("seller")){
				model.addAttribute("order", new Order());
				List<Order> orderList = orderDAO.getAll();
				model.addAttribute("orders", orderList);
				return "sellerhome";
			}else{
				return "access";
			}
		}catch(NullPointerException e){
			return "access";
		}
	}
	
	//display all customers and modify them
	@RequestMapping("/customerhome")
	public String customerhome(Model model) {
		try{
			if(connectedEmployee.getRole().equals("seller")){
				model.addAttribute("customer", new Customer());
				List<Customer> customerList = orderDAO.getAllCustomers();
				model.addAttribute("customers", customerList);
				return "customerhome";
			}else{
				return "access";
			}
		}catch(NullPointerException e){
			return "access";
		}
	}
	
	//display all pending orders
	@RequestMapping("/ceoOrders")
	public String ceoOrders(Model model) {
		try{
			if(connectedEmployee.getRole().equals("ceo")){
				model.addAttribute("order", new Order());
				List<Order> pendingList = orderDAO.getPendingOrders();
				model.addAttribute("orders", pendingList);
				return "ceoOrders";
			}else{
				return "access";
			}
		}catch(NullPointerException e){
			return "access";
		}
	}
	
	//display all products and modify them
	@RequestMapping("/ceohome")
	public String ceohome(Model model) {
		try{
			if(connectedEmployee.getRole().equals("ceo")){
				model.addAttribute("product", new Product());
				List<Product> productList = orderDAO.getAllProducts();
				model.addAttribute("products", productList);
				return "ceohome";
			}else{
				return "access";
			}
		}catch(NullPointerException e){
			return "access";
		}
	}
	
	
	//display all products and modify them
	@RequestMapping("/auctions")
	public String auctions(Model model) {
		try{
			if(connectedEmployee.getRole().equals("seller")){
				model.addAttribute("auction", new Auction());
				List<Auction> auctionList = orderDAO.getAllAuctions();
				model.addAttribute("auctions", auctionList);
				return "auctions";
			}else{
				return "access";
			}
		}catch(NullPointerException e){
			return "access";
		}
	}
		
	//access message
	@RequestMapping(value = "/access", method = RequestMethod.POST)
	 public String access(BindingResult result) {
		  try{
			  if (result.hasErrors()) {
				  return "success";
			  }
		  }catch(NullPointerException e){
			  return "access";
		  }
		  return "access";
	 }
		
	//success message
	@RequestMapping(value = "/success", method = RequestMethod.POST)
	 public String success(@ModelAttribute("newOrder") @Valid Order order, BindingResult result) {
		  try{
			  if (result.hasErrors()) {
				  return "success";
			  }
		  }catch(NullPointerException e){
		  }
		  if(checksSF=="/sellerhome"){
			  return "redirect:/sellerhome";
		  }else if(checksSF=="/adminhome"){
			  return "redirect:/adminhome";
		  }else if(checksSF=="/customerhome"){
			  return "redirect:/customerhome";
		  }else if(checksSF=="/ceohome"){
			  return "redirect:/ceohome";
		  }else if(checksSF=="removeEmployee"){
			  return "redirect:/adminhome";
		  }else if(checksSF=="removeCustomer"){
			  return "redirect:/customerhome";
		  }else if(checksSF=="acceptceo"){
			  return "redirect:/ceoOrders";
		  }else if(checksSF=="rejectceo"){
			  return "redirect:/ceoOrders";
		  }else if(checksSF=="checkAvailability"){
			  return "redirect:/sellerhome";
		  }else if(checksSF=="removeProduct"){
			  return "redirect:/ceohome";
		  }else if(checksSF=="decrease"){
			    return "redirect:/sellerhome";
		  }else if(checksSF=="/auctions"){
			    return "redirect:/auctions";
		  }else if(checksSF=="removeAuction"){
			    return "redirect:/auctions";
		  }else{
			  return "redirect:/";
		  }
	 }
	
	//failure message
	@RequestMapping(value = "/failure", method = RequestMethod.POST)
	public String failure(@ModelAttribute("newOrder") @Valid Order order, BindingResult result) {
		  try{
			  if (result.hasErrors()) {
				  return "failure";
		      }
		  }catch(NullPointerException e){
		  }
		  if(checksSF=="/sellerhome"){
			  return "redirect:/sellerhome";
		  }else if(checksSF=="/adminhome"){
			  return "redirect:/adminhome";
		  }else if(checksSF=="/customerhome"){
			  return "redirect:/customerhome";
		  }else if(checksSF=="/ceohome"){
			  return "redirect:/ceohome";
		  }else if(checksSF=="removeEmployee"){
			  return "redirect:/adminhome";
		  }else if(checksSF=="removeCustomer"){
			  return "redirect:/customerhome";
		  }else if(checksSF=="acceptceo"){
			  return "redirect:/ceoOrders";
		  }else if(checksSF=="rejectceo"){
			  return "redirect:/ceoOrders";
		  }else if(checksSF=="checkAvailability"){
			  return "redirect:/sellerhome";
		  }else if(checksSF=="/authenticateCustomer"){
			  return "redirect:/authenticateCustomer";
		  }else if(checksSF=="removeProduct"){
			  return "redirect:/ceohome";
		  }else if(checksSF=="decrease"){
			    return "redirect:/sellerhome";
		  }else if(checksSF=="/auctions"){
			    return "redirect:/auctions";
		  }else{
			  return "redirect:/";
		  }
	 }
	
	//seller's options
	@RequestMapping("/sellerchoice")
	public String sellerchoice(Model model) {
		try{
			if(connectedEmployee.getRole().equals("seller")){
				return "sellerchoice";
			}else{
				return "access";
			}
		}catch(NullPointerException e){
			return "access";
		}
	}
	
	//root page
	@RequestMapping("/")
	public String login(@ModelAttribute("/") @Valid Employee employee, BindingResult result, Model model) {
		if (result.hasErrors()) {
            return "login";
        }
		Employee newEmployee = new Employee();
		connectedEmployee = employeeDAO.checkEmployee(employee.getName(), employee.getPassword());
		model.addAttribute("newEmployee", newEmployee);
		String name = employee.getName();
	    String password = employee.getPassword();
	    Employee emp = employeeDAO.checkEmployee(name,password);
	    try{
		    name1 = emp.getName();
		    password1 = emp.getPassword();
		    String role = emp.getRole();
		    System.out.println(name+" "+password);
		    if(name.equals(name1) && password.equals(password1)){
			   if(role.equals("admin") ){
				   return "redirect:/adminhome";
			   }else if(role.equals("seller") ){
				   return "redirect:/sellerchoice";
			   }else if(role.equals("ceo") ){
				   return "redirect:/ceochoice";
			   }
		    }
		}catch(NullPointerException e){
			if(counterException!=0){
				counterException--;
				return "failure";
			}
			counterException++;
		}
		return "login";
	}
	
	//display employee form
	@RequestMapping(value = "/adminhome/employee", method = RequestMethod.GET)
	public String employee(Model model) {
		try{
			if(connectedEmployee.getRole().equals("admin")){
				Employee newEmployee = new Employee();
				model.addAttribute("newEmployee", newEmployee);
				return "employee";
			}else{
				return "access";
			}
		}catch(NullPointerException e){
			return "access";
		}
	}
	
	//ceo's options
	@RequestMapping("/ceochoice")
	public String ceochoice(Model model) {
		try{
			if(connectedEmployee.getRole().equals("ceo")){
				model.addAttribute("order", new Order());
				List<Order> orderList = orderDAO.getPendingOrders();
				model.addAttribute("orders", orderList);
				return "ceochoice";
			}else{
				System.out.println(connectedEmployee+"wrong url");
				return "access";
			}
		}catch(NullPointerException e){
			return "access";
		}
	}
	
	//admin create new employee
	@RequestMapping(value = "/adminhome/addEmployee", method = RequestMethod.POST)
	public String addEmployee(@ModelAttribute("newEmployee") @Valid Employee employee, BindingResult result) {
		try{
			if(connectedEmployee.getRole().equals("admin")){
				checksSF="/adminhome";
				if (result.hasErrors()) {
					return "failure";
				}
				if (employee.getId() == 0) {
					if(employee.getRole().toLowerCase().equals("seller")){
						employeeDAO.save(employee,sellerCounter,2,2);
						sellerCounter++;
					}else if(employee.getRole().toLowerCase().equals("admin")){
						employeeDAO.save(employee,5,2,adminCounter);
						adminCounter++;
					}else if(employee.getRole().toLowerCase().equals("ceo")){
						employeeDAO.save(employee,5,CEOCounter,2);
						CEOCounter++;
					}else{
						System.out.println("Wrong role! "+employee.getRole());
						return "failure";
					}
				} else {
					if(employee.getRole().toLowerCase().equals("seller") || employee.getRole().toLowerCase().equals("admin") || employee.getRole().toLowerCase().equals("ceo")){
				    employeeDAO.update(employee);
					}else{
						return "failure";
					}
				}
			}else{
				return "access";
			}
		}catch(NullPointerException e){  
			return "success";
		}
		return "success";	
	 }
	
	//display order form
	@RequestMapping(value = "/sellerhome/order", method = RequestMethod.GET)
	public String order(Model model) {
		try{
			if(connectedEmployee.getRole().equals("seller")){
				Order newOrder = new Order();
				model.addAttribute("newOrder", newOrder);
				return "order";
			}else{
				return "access";
			}
		}catch(NullPointerException e){
			  return "access";
		}
	}
	
	//seller create new order
	@RequestMapping(value = "/sellerhome/addOrder", method = RequestMethod.POST)
	public String addOrder(@ModelAttribute("newOrder") @Valid Order order, BindingResult result) {
		try{
			if(connectedEmployee.getRole().equals("seller")){
				try{
					checksSF="/sellerhome";
					if (result.hasErrors()) {
						return "failure";
					}
					int productcodetmp = order.getProductCode();
					Product p = orderDAO.getByProductCode(productcodetmp);
					System.out.println(p);
					if (order.getOrderCode() == 0) { 
						if(p==null){
							return "failure";
						}
						orderDAO.saveOrder(order);  
					} else {
						orderDAO.updateOrder(order);
					}
				}catch(NullPointerException e){
					return "failure";
				}
				return "success";
			}else{
				return "access";
			}
		}catch(NullPointerException e2){
			return "access";
		}
	}
	
	//display customer form
	@RequestMapping(value = "/customerhome/customer", method = RequestMethod.GET)
	public String customer(Model model) {
		try{
			if(connectedEmployee.getRole().equals("seller")){
				Customer newCustomer = new Customer();
				model.addAttribute("newCustomer", newCustomer);
				return "customer";
			}else{
				return "access";
			}
		}catch(NullPointerException e2){
			  return "access";
		}
	}
	
	//seller create new customer
	@RequestMapping(value = "/customerhome/addCustomer", method = RequestMethod.POST)
	public String addCustomer(@ModelAttribute("newCustomer") @Valid Customer customer, BindingResult result) {
		try{
			if(connectedEmployee.getRole().equals("seller")){
				try{
					checksSF="/customerhome";
					if (result.hasErrors()) {
						return "failure";
					}
					if (customer.getCustomerCode() != 0) {
						orderDAO.update(customer);
						System.out.println("okss");
						return "success";
					}
					int counterafm=0;
					int countercontactperson=0;
					List<Employee> empList = employeeDAO.getAll();
					List<Customer> custList = orderDAO.getAllCustomers();
					for(Employee e :empList){
						if(e.getName().equals(customer.getContactPerson())){
							System.out.println("contact person exists");
							countercontactperson++;
						}
					}
					for(Customer c :custList){
						if(c.getAfm().equals(customer.getAfm())){
							System.out.println("afm exists");
							counterafm++;	    		
						}
					}
					if(customer.getAfm().equals("")){
						return "failure";
					}
					if(counterafm==0 && countercontactperson!=0){
						orderDAO.saveCustomer(customer);
						return "redirect:/sellerhome";
					}else if(counterafm!=0 && countercontactperson!=0){
						return "failure";
					}			    
				}catch(NullPointerException e){
					return "failure";
				}
				return "success";
			}else{
				return "access";
			}
		}catch(NullPointerException e2){
			return "access";
		}
	}
	
	//display product form
	@RequestMapping(value = "/ceohome/ceo", method = RequestMethod.GET)
	public String product(Model model) {
		try{
			if(connectedEmployee.getRole().equals("ceo")){
				Product newProduct = new Product();
				model.addAttribute("newProduct", newProduct);
				return "ceo";
			}else{
				return "access";
			}
		}catch(NullPointerException e2){
			  return "access";
		}
	}
	
	//display auction form
	@RequestMapping(value = "/sellerhome/auction", method = RequestMethod.GET)
	public String auction(Model model) {
		try{
			if(connectedEmployee.getRole().equals("seller")){
				Auction newAuction = new Auction();
				model.addAttribute("newAuction", newAuction);
				return "auction";
			}else{
				return "access";
			}
		}catch(NullPointerException e2){
			  return "access";
		}
	}
		
	//seller create new auction
	@RequestMapping(value = "/sellerhome/addAuction", method = RequestMethod.POST)
	public String addAuction(@ModelAttribute("newAuction") @Valid Auction auction, BindingResult result) {
		try{
			if(connectedEmployee.getRole().equals("seller")){
				try{
					checksSF="/auctions";
					if (result.hasErrors()) {
						return "failure";
					}	
					if (auction.getId() == 0) {
						orderDAO.saveAuction(auction);
						List<Subscriber> subscribers = orderDAO.getAllSubscribers();
						mail.send2(subscribers);
					}else{
						orderDAO.updateAuction(auction);
					}    
				}catch(NullPointerException e){
					return "failure";
				}
				return "success";
			}else{
				return "access";
			}
		}catch(NullPointerException e2){
			return "access";
		}
	}
	
	//CEO create new product
	@RequestMapping(value = "/ceohome/AddProduct", method = RequestMethod.POST)
	public String AddProduct(@ModelAttribute("newProduct") @Valid Product product, BindingResult result) {
		try{
			if(connectedEmployee.getRole().equals("ceo")){
				try{
					checksSF="/ceohome";
					if (result.hasErrors()) {
						return "failure";
					}	
					if (product.getProductCode() == 0) {
						orderDAO.saveproduct(product);
					}else{
						orderDAO.updateProducts(product);
					}    
				}catch(NullPointerException e){
					return "failure";
				}
				return "success";
			}else{
				return "access";
			}
		}catch(NullPointerException e2){
			return "access";
		}
	}
	
	//check if user is saved
	@RequestMapping(value = "/authenticateCustomer")
	public String authenticateCustomer(@ModelAttribute("authenticateCustomer") @Valid Customer customer, BindingResult result) {
		try{
			if(connectedEmployee.getRole().equals("seller")){
				if (result.hasErrors()) {
			    	return "authenticateCustomer";
				}
			    return "authenticateCustomer";
			}else{
				return "access";
			}
		}catch(NullPointerException e2){
			  return "access";
		}
	}
	
	//if customer is saved continue 
	@RequestMapping(value = "/authenticateCustomer/login", method = RequestMethod.POST, params = { "login" })
	public String checkCustomer(@ModelAttribute("newCustomer") @Valid Customer customer, BindingResult result, Model model) {
		try{
			if(connectedEmployee.getRole().equals("seller")){
				try{
					Customer newCustomer = orderDAO.checkCustomer(customer.getCustomerCode(), customer.getAfm());
					String afm = newCustomer.getAfm();
				    int code = newCustomer.getCustomerCode();
					if (code==customer.getCustomerCode() && afm.equals(customer.getAfm())) {
						   System.out.println("Exists!");
						   return "redirect:/sellerhome";
					}
				}catch(NullPointerException e){
					checksSF="/authenticateCustomer";
					return "failure";
				}
			    return "redirect:/customerhome";
			}else{
				return "access";
			}
		}catch(NullPointerException e2){
			  return "access";
		}
	}

	//create new customer if he doesn't exist
	@RequestMapping(value = "/authenticateCustomer/login", method = RequestMethod.POST, params = { "create" })
	public String create(@ModelAttribute("newCustomer") @Valid Customer customer, BindingResult result) {
		try{
			if(connectedEmployee.getRole().equals("seller")){
				customer.setCustomerCode(0);
				return "customer";
			}else{
				return "access";
			}
		}catch(NullPointerException e2){
			  return "access";
		}
	}
	
	//admin edit employees
	@RequestMapping("/adminhome/edit/{id}")
	public String editPerson(@PathVariable("id") int id, Model model) {
		try{
			if(connectedEmployee.getRole().equals("admin")){
				model.addAttribute("newEmployee", employeeDAO.getById(id));
				return "employee";
			}else{
				return "access";
			}
		}catch(NullPointerException e2){
			  return "access";
		}
	}
	
	//seller edit auctions
	@RequestMapping("/auctions/edit/{id}")
	public String editAuction(@PathVariable("id") int id, Model model) {
		try{
			if(connectedEmployee.getRole().equals("seller")){
				model.addAttribute("newAuction", orderDAO.getById(id));
				return "auction";
			}else{
				return "access";
			}
		}catch(NullPointerException e2){
			  return "access";
		}
	}
	
	//seller edit customers
	@RequestMapping("/customerhome/edit/{customerCode}")
	public String editCustomer(@PathVariable("customerCode") int customerCode, Model model) {
		try{
			if(connectedEmployee.getRole().equals("seller")){
				model.addAttribute("newCustomer", orderDAO.getByCustomerCode(customerCode));
				return "customer";
			}else{
				return "access";
			}
		}catch(NullPointerException e2){
			  return "access";
		}
	}
	
	//seller edit orders
	@RequestMapping("/sellerhome/edit/{orderCode}")
	public String editOrder(@PathVariable("orderCode") int orderCode, Model model) {
		try{
			if(connectedEmployee.getRole().equals("seller")){
				model.addAttribute("newOrder", orderDAO.getByOrderCode(orderCode));
				return "order";
			}else{
				return "access";
			}
		}catch(NullPointerException e2){
			  return "access";
		}
	}
	
	//seller check if availability is under 90% or not(system check it)
	@RequestMapping("/sellerhome/checkavailability/{orderCode}")
	public String checkavailability(@PathVariable("orderCode") int orderCode, Model model) {
		try{
			if(connectedEmployee.getRole().equals("seller")){
				Order order = orderDAO.getByOrderCode(orderCode);
				int code = order.getProductCode();
				int quantity = order.getQuantityOfProducts();
				Product product = orderDAO.getProductAvailability(code);
				int quantityProduct = product.getQuantity();
				if(quantity>0.9*quantityProduct){
					orderDAO.updateStatus("pending", orderCode);
					System.out.println("Inform ceo");
				}else{
					orderDAO.updateStatus("sellerAccepted", orderCode);
					System.out.println("ok");
				}
				return "redirect:/sellerhome";
			}else{
				return "access";
			}
		}catch(NullPointerException e){
			return "access";
		}	
	}
	
	//CEO edit products
	@RequestMapping("/ceohome/edit/{productCode}")
	public String edit(@PathVariable("productCode") int productCode, Model model) {
		try{
			if(connectedEmployee.getRole().equals("ceo")){
				model.addAttribute("newProduct",orderDAO.getByProductCode(productCode) );
				return "ceo";
			}else{
				return "access";
			}
		}catch(NullPointerException e){
			return "access";
		}
	}
	
	//CEO accept pending orders
	@RequestMapping("/ceoOrders/accept/{orderCode}")
	public String ordersAccept(@PathVariable("orderCode") int orderCode, Model model) {
		try{
			if(connectedEmployee.getRole().equals("ceo")){
				model.addAttribute("order", new Order());
				List<Order> orderList = orderDAO.getPendingOrders();
				model.addAttribute("orders", orderList);
				Order order1 = orderDAO.getByOrderCode(orderCode);
				int quantityOfProducts = order1.getQuantityOfProducts();
				int productCode = order1.getProductCode();
				Product product1 = orderDAO.getByProductCode(productCode);
			    int quantity = product1.getQuantity();
			    if(quantity-quantityOfProducts>=0){
			    	orderDAO.decreaseProducts(productCode,quantity,quantityOfProducts);
			    	orderDAO.updateStatus("ceoAccepted", orderCode);
			    	checksSF="acceptceo";
			    	return "success";
			    }else{
			    	orderDAO.updateStatus("failed", checkTimeCode);
			    	checksSF="rejectceo";
			    	return "failure";
			    }
			}else{
				return "access";
			}
		}catch(NullPointerException e){
			return "access";
		}
	 }
	
	//Ceo reject pending orders
	@RequestMapping("/ceoOrders/reject/{orderCode}")
	public String ordersReject(@PathVariable("orderCode") int orderCode, Model model) {
		try{
			if(connectedEmployee.getRole().equals("ceo")){
				orderDAO.updateStatus("failed", orderCode);
				model.addAttribute("order", new Order());
				List<Order> orderList = orderDAO.getPendingOrders();
				model.addAttribute("orders", orderList);
				checksSF="rejectceo";
				return "success";
			}else{
				return "access";
			}
		}catch(NullPointerException e){
			return "access";
		}
	}
	
	//seller check time and settlement new time,if he want to settle then he changes the value of delivery time and
	//press settlement
	@RequestMapping(value = "/sellerhome/checkTime/{orderCode}", method = RequestMethod.GET)
	public String checkTime(@PathVariable("orderCode") int orderCode, Model model) {
		try{
			if(connectedEmployee.getRole().equals("seller")){
				checkTimeCode=orderCode;
				model.addAttribute("Time", orderDAO.getByOrderCode(orderCode));
				return "checkTime";
			}else{
				return "access";
			}
		}catch(NullPointerException e){
			return "access";
		}
	}
	
	//seller approve the starter delivery time 
	@RequestMapping(value = "/sellerhome/checkTime", method = RequestMethod.POST, params = { "approve" })
	public String approve(@ModelAttribute("Time") @Valid Order order, BindingResult result, Model model) {
		try{
		if(connectedEmployee.getRole().equals("seller")){
		    checksSF="decrease";
		    orderDAO.updateStatus("accepted", checkTimeCode);
		    int time = order.getDeliveryTime();
		    orderDAO.updateDeliveryTime(time, checkTimeCode);
		    System.out.println("Inform "+checkTimeCode);
		    return "redirect:/sellerhome";
		}else{
			return "access";
		}
		}catch(NullPointerException e){
			return "access";
		}
	}

	//seller make settlement
	@RequestMapping(value = "/sellerhome/checkTime", method = RequestMethod.POST, params = { "settlement" })
	public String settlement(@ModelAttribute("Time") @Valid Order order, BindingResult result) {
		try{
		if(connectedEmployee.getRole().equals("seller")){
			orderDAO.updateStatus("failed", checkTimeCode);
		    return "redirect:/sellerhome";
		}else{
			return "access";
		}
		}catch(NullPointerException e){
			return "access";
		}
	}
	
	//admin remove employees
	@RequestMapping("/adminhome/remove/{id}")
	public String removePerson(@PathVariable("id") int id,Model model) {
		try{
		if(connectedEmployee.getRole().equals("admin")){
			Employee emp;
			emp = employeeDAO.getById(id);
			String result = emp.getRole();
			if(!result.equals("admin")){
				employeeDAO.deleteById(id);
				checksSF="removeEmployee";
			}else{
				return "failure";
			}
			return "success";
		}else{
			return "access";
		}
		}catch(NullPointerException e){
			return "access";
		}
	}
	
	//seller remove customers only if they don't have orders
	@RequestMapping("/customerhome/remove/{customerCode}")
	public String removeCustomer(@PathVariable("customerCode") int customerCode) {
		try{
		if(connectedEmployee.getRole().equals("seller")){
			List<Order> orderList = orderDAO.getByCustomerCodeInOrders(customerCode);
			int counter=0;
			checksSF="removeCustomer";
			for(Order o :orderList){
	    		counter++;
			}
			if(counter==0){
				orderDAO.deleteByCustomerCode(customerCode);;
				return "success";
			}else{
				return "failure";
			}
		}else{
			return "access";
		}
		}catch(NullPointerException e){
			return "access";
		}
	}
	
	//seller remove auction
	@RequestMapping("/sellerhome/remove/{id}")
	public String removeAuction(@PathVariable("id") int id) {
		try{
			if(connectedEmployee.getRole().equals("seller")){
				orderDAO.deleteAuction(id);
				checksSF="removeAuction";
				return "success";
			}else{
				return "access";
			}
		}catch(NullPointerException e){
				return "access";
		}
	}
	
	//Ceo remove products only if orders don't use this product
	@RequestMapping("/ceohome/remove/{productCode}")
	public String removeProduct(@PathVariable("productCode") int productCode) {
		try{
		if(connectedEmployee.getRole().equals("ceo")){
			checksSF="removeProduct";
			orderDAO.deleteProduct(productCode);
			Product product = orderDAO.getByProductCode(productCode);
			if(product.equals(null)){
				return "success";
			}else{
				return "failure";
			}
		}else{
			return "access";
		}
		}catch(NullPointerException e){
			return "success";
		}
	}
}
