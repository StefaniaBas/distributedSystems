package gr.hua.net;

public class Order {
	
	private int orderCode;
    private int customerCode;
    private int quantityOfProducts;
    private int deliveryTime;
    private int productCode;
    private String status;
    private String type;
    
    
    public int getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}

	public int getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(int customerCode) {
		this.customerCode = customerCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getQuantityOfProducts() {
		return quantityOfProducts;
	}

	public void setQuantityOfProducts(int quantityOfProducts) {
		this.quantityOfProducts = quantityOfProducts;
	}

	public int getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(int deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getProductCode() {
		return productCode;
	}

	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}
		
	@Override
    public String toString(){
        return "{orderCode="+orderCode+",customerCode="+customerCode+",type="+type+",quantityOfProducts="+quantityOfProducts+",deliveryTime="+deliveryTime+",status="+status+",productCode="+productCode+"}";
    }
}
