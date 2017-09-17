package gr.hua.net;

public class Product {
	
	private int productCode;
	private int quantity;
	private String oilVariety;
	
	
	public int getProductCode() {
		return productCode;
	}
	
	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getOilVariety() {
		return oilVariety;
	}

	public void setOilVariety(String oilVariety) {
		this.oilVariety = oilVariety;
	}

	@Override
    public String toString(){
        return "{productCode="+productCode+",quantity="+quantity+",oilVariety="+oilVariety+"}";
    }
}
