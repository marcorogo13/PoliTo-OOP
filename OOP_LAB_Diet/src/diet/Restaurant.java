package diet;
import diet.Order.OrderStatus;

import java.sql.Time;
import java.util.*;
/**
 * Represents a restaurant in the take-away system
 *
 */
public class Restaurant implements Comparable<Restaurant>{
	
	
	List <Order> orders = new ArrayList<Order>();
	List <WorkingHours> workingHours = new ArrayList<WorkingHours>(); 
	String name;
	Food food;
	private Map<String, Menu> menuR = new HashMap();
	
	/**
	 * Constructor for a new restaurant.
	 * 
	 * Materials and recipes are taken from
	 * the food object provided as argument.
	 * 
	 * @param name	unique name for the restaurant
	 * @param food	reference food object
	 */
	
	public Restaurant(String name, Food food) {
		// TODO: implement constructor
		this.food = food;
		this.name = name;
	}
	
	/**
	 * gets the name of the restaurant
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Define opening hours.
	 * 
	 * The opening hours are considered in pairs.
	 * Each pair has the initial time and the final time
	 * of opening intervals.
	 * 
	 * for a restaurant opened from 8:15 until 14:00 and from 19:00 until 00:00, 
	 * is thoud be called as {@code setHours("08:15", "14:00", "19:00", "00:00")}.
	 * 
	 * @param hm a list of opening hours
	 */
	public void setHours(String ... hm) {
		for (int i = 0; i <= hm.length/2; i+=2) {
			workingHours.add(new WorkingHours(hm[i], hm[i+1]));
		}
	}
	
	public Menu getMenu(String name) {
		return menuR.get(name);
	}
	
	/**
	 * Creates a new menu
	 * 
	 * @param name name of the menu
	 * 
	 * @return the newly created menu
	 */
	public Menu createMenu(String name) {
		Menu tmp = new Menu(name, food);
		menuR.put(name,tmp);
		return menuR.get(name);
	}

	/**
	 * Find all orders for this restaurant with 
	 * the given status.
	 * 
	 * The output is a string formatted as:
	 * <pre>
	 * Napoli, Judi Dench : (19:00):
	 * 	M6->1
	 * Napoli, Ralph Fiennes : (19:00):
	 * 	M1->2
	 * 	M6->1
	 * </pre>
	 * 
	 * The orders are sorted by name of restaurant, name of the user, and delivery time.
	 * 
	 * @param status the status of the searched orders
	 * 
	 * @return the description of orders satisfying the criterion
	 */
	public String ordersWithStatus(OrderStatus status) {
		StringBuilder sb = new StringBuilder();
		orders.sort(Comparator.comparing(Order::getUser).thenComparing(Order::getDelivery_time));
		
		for (Order o: orders) {
			if (o.getStatus() == status){
				sb.append(o);
			}
		}
		return sb.toString();
	}

	@Override
	public int compareTo(Restaurant o) {
		// TODO Auto-generated method stub
		return this.name.compareTo(o.name);
	}
}
