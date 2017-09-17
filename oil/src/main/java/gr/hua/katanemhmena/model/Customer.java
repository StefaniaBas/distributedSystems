package gr.hua.katanemhmena.model;

public class Customer {
	
	private int customerCode;
	private int telephoneNumber;
	private int password;
    private String companyName;
    private String afm;
    private String contactPerson;
    private String deliveryAddress;
    private String username;
    private String onlineOrders;
    
    
    public String getOnlineOrders() {
    	return onlineOrders;
	}

	public void setOnlineOrders(String onlineOrders) {
		this.onlineOrders = onlineOrders;
	}

	public int getCustomerCode() {
		return customerCode;
	}
    
	public void setCustomerCode(int customerCode) {
		this.customerCode = customerCode;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getAfm() {
		return afm;
	}
	
	public void setAfm(String afm) {
		this.afm = afm;
	}
	
	public String getContactPerson() {
		return contactPerson;
	}
	
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	
	public int getTelephoneNumber() {
		return telephoneNumber;
	}
	
	public void setTelephoneNumber(int telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getPassword() {
		return password;
	}

	public void setPassword(int password) {
		this.password = password;
	}
	
	@Override
    public String toString(){
		return "{customerCode="+customerCode+",companyName="+companyName+",afm="+afm+",contactPerson="+contactPerson+",telephoneNumber="+telephoneNumber+",deliveryAddress="+deliveryAddress+",username="+username+",password="+password+",onlineOrders="+onlineOrders+"}";
    }
}