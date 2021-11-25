package diet;

import java.util.Collections;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;

/**
 * Represents an order in the take-away system
 */
public class Order  {
	

	User user;
	Restaurant restaurant;
	Time delivery_time;
	OrderStatus orderStatus;
	PaymentMethod paymentMethod;
	private SortedMap<Menu, Integer> menus = new TreeMap<>();
	
	/**
	 * Defines the possible order status
	 */
	public enum OrderStatus {
		ORDERED, READY, DELIVERED;
	}
	/**
	 * Defines the possible valid payment methods
	 */
	public enum PaymentMethod {
		PAID, CASH, CARD;
	}
	
	public Order(User user, Restaurant restaurant, int hour, int min) {
		paymentMethod = PaymentMethod.CASH;
		orderStatus = OrderStatus.ORDERED;
		this.user = user;
		this.restaurant = restaurant;
		Time tmp = new Time(hour, min);
		Collections.sort(restaurant.workingHours);
		for (WorkingHours w : restaurant.workingHours) {
			if (w.includes(tmp)) {
				this.delivery_time = tmp;
				return;
			}
		}
		for(WorkingHours w : restaurant.workingHours) {
			if (w.getOpen().compareTo(tmp) > 0) {
				this.delivery_time = w.getOpen();
				return;
			}
		}
		
		this.delivery_time = restaurant.workingHours.get(0).getOpen();
	}
	
	
	
	

	public User getUser() {
		return user;
	}

	



	public Time getDelivery_time() {
		return delivery_time;
	}





	/**
	 * Total order price
	 * @return order price
	 */
	public double Price() {
		return -1.0;
	}
	
	/**
	 * define payment method
	 * 
	 * @param method payment method
	 */
	public void setPaymentMethod(PaymentMethod method) {
		this.paymentMethod = method;
	}
	
	/**
	 * get payment method
	 * 
	 * @return payment method
	 */
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	
	/**
	 * change order status
	 * @param newStatus order status
	 */
	public void setStatus(OrderStatus newStatus) {
		this.orderStatus = newStatus;
	}
	
	/**
	 * get current order status
	 * @return order status
	 */
	public OrderStatus getStatus(){
		return orderStatus;
	}
	
	/**
	 * Add a new menu with the relative order to the order.
	 * The menu must be defined in the {@link Food} object
	 * associated the restaurant that created the order.
	 * 
	 * @param menu     name of the menu
	 * @param quantity quantity of the menu
	 * @return this order to enable method chaining
	 */
	public Order addMenus(String menu, int quantity) {
		Menu tmp = restaurant.getMenu(menu);
		if(menus.containsKey(tmp)) {
			menus.replace(tmp, quantity);
			return this;
		}else {
			menus.put(tmp, quantity);
			return this;
		}
	}
	
	/**
	 * Converts to a string as:
	 * <pre>
	 * RESTAURANT_NAME, USER_FIRST_NAME USER_LAST_NAME : DELIVERY(HH:MM):
	 * 	MENU_NAME_1->MENU_QUANTITY_1
	 * 	...
	 * 	MENU_NAME_k->MENU_QUANTITY_k
	 * </pre>
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(this.restaurant.getName() + ", " + this.user.getFirstName() + " " + this.user.getLastName() + " : " + "(" + this.delivery_time.toString() + "):").append("\n");
		for (Entry<Menu, Integer> m : menus.entrySet()) {
			sb.append("\t").append(m.getKey().getName()).append("->").append(m.getValue().toString()).append("\n");
		}
		
		return sb.toString();
		
	}
	
}
