package gr.hua.katanemhmena.model;

import java.util.List;

public interface OrderDAO {
	
	//Check if customer is saved
    public Customer checkCustomer(int customerCode, String afm);
    //Save customer
    public void saveCustomer(Customer customer) ;
    //Save order
    public void saveOrder(Order order);
    //Save online order
    public void saveOnlineOrder(Order order);
    //Get order by orderCode
    public Order getByOrderCode(int orderCode);
    //Get product availability
    public Product getProductAvailability(int productCode);
    //Update status
    public void updateStatus(String status, int orderCode);
    //Get all orders
    public List<Order> getAll();
    //Get all pending orders
    public List<Order> getPendingOrders();
    //Get all customers
    public List<Customer> getAllCustomers();
    //Get all products
    public List<Product> getAllProducts();
    //Get product by productCode
    public Product getByProductCode(int productCode);
    //Save product
    public void saveproduct(Product product);
    //Update product
    public void updateProducts (Product product);
    //Delete customer
    public void deleteByCustomerCode(int customerCode);
    //Update delivery time for settlement
    public void updateDeliveryTime(int deliveryTime, int orderCode);
    //Get customer by customerCode
    public Customer getByCustomerCode(int customerCode);
    //Update customer
    public void update(Customer customer);
    //Update order
    public void updateOrder(Order order);
    //Delete product
    public void deleteProduct(int productCode);
    //Get order with specific productCode
    public Order getByProductCodeInOrders(int productCode);
    //Get all orders with specific customerCode
    public List<Order> getByCustomerCodeInOrders(int customerCode);
    //Decrease products
    public void decreaseProducts(int productCode, int quantity, int quantityOfProducts);
    //Get all auctions
    public List<Auction> getAllAuctions();
    //Update Customer username and password
    public void UpdateUsrPassActivation(String username, int password, int customerCode,String activation);
    //Save auction
    public void saveAuction(Auction auction);
    //Delete auction
    public void deleteAuction(int id);
    //Get auction by id
    public Auction getById(int id);
    //Edit auctions
    public void updateAuction(Auction auction);
    //Get all Subscribers
    public List<Subscriber> getAllSubscribers();
    //Save subscriber
    public void saveSubscriber(String email);
    //Get Subscriber by email
    public int getByEmail(String email);
}
