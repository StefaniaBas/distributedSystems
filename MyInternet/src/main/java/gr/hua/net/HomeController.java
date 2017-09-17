package gr.hua.net;

import java.awt.Component;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.validation.Valid;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import gr.hua.net.Customer;
import gr.hua.net.Product;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	public static final String SERVER_URI = "http://localhost:8080/katanemhmena/";
	//variable to keep customer connected when he/she changes tabs
	boolean login=false;
	//keep code from connected customer
	int myCode=0;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String allProducts(Locale locale, Model model) throws JsonParseException, JsonMappingException, IOException {
		RestTemplate restTemplate = new RestTemplate();
		Product[] products = restTemplate.getForObject(SERVER_URI + "api/products", Product[].class);
		model.addAttribute("products", products);
		System.out.println(Arrays.asList(products).size());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Object> entity = new HttpEntity<Object>(headers);
		ResponseEntity<String> out = restTemplate.exchange(SERVER_URI + "api/products", HttpMethod.GET, entity,String.class);

		ObjectMapper mapper = new ObjectMapper();
		Product[] prods = mapper.readValue(out.getBody(), Product[].class);
		System.out.println("------" + Arrays.asList(prods).size());
		System.out.println(out.getBody());
		System.out.println(out.getStatusCode());

		return "home";
	}
	
	@RequestMapping(value = "/viewAuctions", method = RequestMethod.GET)
	public String allAuctions(Locale locale, Model model) throws JsonParseException, JsonMappingException, IOException {
		RestTemplate restTemplate = new RestTemplate();
		Auction[] auctions = restTemplate.getForObject(SERVER_URI + "api/auctions", Auction[].class);
		model.addAttribute("auctions", auctions);
		System.out.println(Arrays.asList(auctions).size());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Object> entity = new HttpEntity<Object>(headers);
		ResponseEntity<String> out = restTemplate.exchange(SERVER_URI + "api/auctions", HttpMethod.GET, entity,String.class);

		ObjectMapper mapper = new ObjectMapper();
		Auction[] aucts = mapper.readValue(out.getBody(), Auction[].class);
		System.out.println("------" + Arrays.asList(aucts).size());
		System.out.println(out.getBody());
		System.out.println(out.getStatusCode());

		return "auctions";
	}
	
	@RequestMapping(value = "/loginCustomer", method = RequestMethod.GET, params = { "login" })
	public String loginCustomer(@ModelAttribute("/loginCustomer") @Valid Customer customer, Model model,BindingResult result) throws JsonParseException, JsonMappingException, IOException {
		if (result.hasErrors()) {
            return "redirect:/";
        }
		try{
			RestTemplate restTemplate = new RestTemplate();
			String thisUsername = customer.getUsername();
			int thisPassword = customer.getPassword();
			System.out.println(thisUsername);
			Customer[] customers = restTemplate.getForObject(SERVER_URI + "api/customers", Customer[].class);
			model.addAttribute("customers", customers);
			System.out.println(customer);
			String usrnm=null;
			int passwd=0;
			for(Customer cust:customers){
				if(thisUsername.equals(cust.getUsername()) && thisPassword==cust.getPassword()){
					usrnm = cust.getUsername();
					passwd = cust.getPassword();
					myCode=cust.getCustomerCode();
					System.out.println(usrnm+","+passwd+","+myCode);
					System.out.println("exist");
				}
			}
			if( !(usrnm.equals(null)) && passwd != 0){
				System.out.println("yes");
				login=true;
				return "redirect:/allMyOrders";
			}else{
				return "activate";
			}
		}catch(NullPointerException e){
			return "activate";
		}
	}
	
	@RequestMapping(value = "/activationCustomer", method = RequestMethod.GET, params = { "activate" })
	public String activateCustomer(@ModelAttribute("/loginCustomer") @Valid Customer customer, Model model,BindingResult result) throws JsonParseException, JsonMappingException, IOException {
		if (result.hasErrors()) {
            return "redirect:/";
        }
		return "activate";
	}
	
	@RequestMapping(value = "/allMyOrders", method = RequestMethod.GET)
	public String allMyOrders(Locale locale, Model model,@ModelAttribute("newCustomer") Customer customer) throws JsonParseException, JsonMappingException, IOException {
		if(login==true && myCode!=0){
			try {
				RestTemplate restTemplate = new RestTemplate();
				Order[] orders = restTemplate.getForObject(SERVER_URI + "api/customer/"+myCode, Order[].class);
				model.addAttribute("orders", orders);
				System.out.println(Arrays.asList(orders).size());
	
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<Object> entity = new HttpEntity<Object>(headers);
				ResponseEntity<String> out = restTemplate.exchange(SERVER_URI + "api/customer/"+myCode, HttpMethod.GET, entity,String.class);
	
				ObjectMapper mapper = new ObjectMapper();
				Order[] ords = mapper.readValue(out.getBody(), Order[].class);
				System.out.println("------" + Arrays.asList(ords).size());
				System.out.println(out.getBody());
				System.out.println(out.getStatusCode());  
			  }catch(HttpClientErrorException e) {
				  System.out.println("error:  " + e.getResponseBodyAsString());		  
			  }catch (Exception e) {
				  System.out.println("error:  " + e.getMessage());
			}
			return "myOrders";
		}else{
			return "onlineOrder";
		}
	}
	
	@RequestMapping(value = "/activateAccount", method = RequestMethod.POST) public
	String activateAccount(@ModelAttribute("newCustomer") Customer customer,Model model) {
		Component frame = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>(); 
			parameters.add("customerCode",customer.getCustomerCode()+"");
			parameters.add("afm",customer.getAfm());
			Customer response = restTemplate.postForObject(SERVER_URI +"api/customer/addparam", parameters, Customer.class);  
			System.out.println("Response  " + response);
	  
			Customer customer2 = restTemplate.getForObject(SERVER_URI + "api/customers/"+customer.getCustomerCode(), Customer.class);
			
			if(response.equals(null)){
				JOptionPane.showMessageDialog(frame,"Wrong data!!!\n"+"Propably you don't exist in our system!!");
				return "activate";
			}else if(!response.equals(null) && response.getOnlineOrders().equals("enabled")){
				JOptionPane.showMessageDialog(frame,"Your username is: "+response.getUsername()+" and your password is: "+response.getPassword()+".\n"+"Please don't forget it.\n"+" You must log in with it!!");
				return "onlineOrder";
			}else if(!response.equals(null) && response.getOnlineOrders().equals("disabled")){
				JOptionPane.showMessageDialog(frame,"Your account just activated.\n"+"Your username is: "+customer2.getUsername()
				+" and your password is: "+customer2.getPassword()+".\n"+"Please don't forget it.\n"+" You must log in with it!!");
				return "onlineOrder";
			}
		}catch (HttpClientErrorException e) {
			JOptionPane.showMessageDialog(frame,"Wrong data!!!\n"+"Propably you don't exist in our system!!");	  
			System.out.println("error:  " + e.getResponseBodyAsString());  
		}catch (Exception e) {
			JOptionPane.showMessageDialog(frame,"Wrong data!!!\n"+"Propably you don't exist in our system!!");
			System.out.println("error:  " + e.getMessage());
		}
		return "activate";
	}
	  
	@RequestMapping(value = "/ContactInfos", method = RequestMethod.GET)
	public String ContactInfos(Locale locale, Model model) throws JsonParseException, JsonMappingException, IOException {
		return "contactInfo";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Locale locale, Model model) throws JsonParseException, JsonMappingException, IOException {
		login=false;
		
		return "onlineOrder";
	}
	
	@RequestMapping(value = "/makeOrder", method = RequestMethod.GET)
	public String makeOrder(Locale locale, Model model) throws JsonParseException, JsonMappingException, IOException {
		if(!(login==true && myCode!=0)){
			return "redirect:/allMyOrders";
		}
		return "order";
	}
	
	@RequestMapping(value = "/OnlineOrders", method = RequestMethod.GET)
	public String OnlineOrders(Locale locale, Model model) throws JsonParseException, JsonMappingException, IOException {
		if(login==true && myCode!=0){
			return "redirect:/allMyOrders";
		}
		return "onlineOrder";
	}
	
	@RequestMapping(value = "/checkOnlineOrder/{orderCode}", method = RequestMethod.GET)
	public String checkOnlineOrder(@PathVariable("orderCode")int orderCode, Locale locale, Model model) throws JsonParseException, JsonMappingException, IOException {
		if(login==true){
			try {
				RestTemplate restTemplate = new RestTemplate();
				MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>(); 
				parameters.add("orderCode",orderCode+"");
				restTemplate.postForObject(SERVER_URI +"api/order/addparam", parameters, Void.class);		  
			}catch (HttpClientErrorException e) {
				System.out.println("error:  " + e.getResponseBodyAsString());			  
			}catch (Exception e) {
				System.out.println("error:  " + e.getMessage());
			} 
			return "redirect:/allMyOrders";
		}
		return "onlineOrder";
	}
	
	@RequestMapping(value = "/rejectOnlineOrder/{orderCode}", method = RequestMethod.GET)
	public String rejectOnlineOrder(@PathVariable("orderCode")int orderCode, Locale locale, Model model) throws JsonParseException, JsonMappingException, IOException {
		if(login==true){
			  try {
				  RestTemplate restTemplate = new RestTemplate();
				  MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>(); 
				  parameters.add("orderCode",orderCode+"");
				  restTemplate.postForObject(SERVER_URI +"api/orderReject/addparam", parameters, Void.class);		  
			  }catch (HttpClientErrorException e) {
				  System.out.println("error:  " + e.getResponseBodyAsString());		  
			  }catch (Exception e) {
				  System.out.println("error:  " + e.getMessage());
			  } 
			  return "redirect:/allMyOrders";
		}
		return "onlineOrder";
	}
	
	@RequestMapping(value = "/makeOnlineOrder", method = RequestMethod.GET)
	public String makeOnlineOrder(@ModelAttribute("newOrder") Order order, Locale locale, Model model) throws JsonParseException, JsonMappingException, IOException {
		Component frame = null;
		try {
			RestTemplate restTemplate = new RestTemplate();  
			MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>(); 
			parameters.add("customerCode",order.getCustomerCode()+"");
			parameters.add("quantityOfProducts",order.getQuantityOfProducts()+"");
			parameters.add("deliveryTime",order.getDeliveryTime()+"");
			parameters.add("productCode",order.getProductCode()+"");
			System.out.println("ok");
			String response = restTemplate.postForObject(SERVER_URI +"api/makeOrder/addparam", parameters, String.class);
			System.out.println(response);
			String[] parts=response.split("-");
			String part1=parts[0];
			String part2=parts[1];
			System.out.println(order.getCustomerCode()+","+myCode);
			if(order.getCustomerCode()!=myCode){
				JOptionPane.showMessageDialog(frame,"Please give your code.");
				return "order";
			}
			if(part1.equals("success")){
				if(part2.equals("not available")){
					JOptionPane.showMessageDialog(frame,"The required quantity of this product is too big.");
				}else if(part2.equals("ceo required")){
					JOptionPane.showMessageDialog(frame,"This order must be checked by ceo.");
				}else if(part2.equals("not ceo")){
					JOptionPane.showMessageDialog(frame,"This order must be checked only by seller.");
				}
				return "redirect:/allMyOrders";
			}else if(part1.equals("both wrong")){
				JOptionPane.showMessageDialog(frame,"Invalid customer code and product code.");
			}else if(part1.equals("customer code wrong")){
				JOptionPane.showMessageDialog(frame,"Invalid customer code.");
			}else if(part1.equals("product code wrong")){
				JOptionPane.showMessageDialog(frame,"Invalid product code.");
			}
	     }catch(HttpClientErrorException e) {
	    	 System.out.println("error11:  " + e.getResponseBodyAsString());
	     }catch (Exception e) {
	    	 JOptionPane.showMessageDialog(frame,"Invalid customer code or product code.");
	    	 System.out.println("error:  " + e.getMessage());
	     } 
		return "order";
	}

	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public String employee(Model model) {
		Product newProduct = new Product();
		model.addAttribute("newProduct", newProduct);
		return "product";
	}
	
	@RequestMapping(value = "/subscribe", method = RequestMethod.GET)
	public String subscribe(@ModelAttribute("newSubscriber") Subscriber subscriber, Locale locale, Model model) throws JsonParseException, JsonMappingException, IOException {
		Component frame1 = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>(); 
			parameters.add("email",subscriber.getEmail());
			String response = restTemplate.postForObject(SERVER_URI +"api/email/addparam", parameters, String.class);
			if(response.equals("not exist")){
				JOptionPane.showMessageDialog(frame1,"You subscribed succesfully!!.");
			}else if(response.equals("exist")){
				JOptionPane.showMessageDialog(frame1,"Your email account is already exist.");
			}	    
		}catch(HttpClientErrorException e) {
			System.out.println("error11:  " + e.getResponseBodyAsString());	     
		}catch (Exception e) {
			System.out.println("error:  " + e.getMessage());
		} 
		return "redirect:/viewAuctions";
	}
}