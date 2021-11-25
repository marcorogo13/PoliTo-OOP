package diet;

import java.util.*;

/**
 * Represents the main class in the
 * take-away system.
 * 
 * It allows adding restaurant, users, and creating orders.
 *
 */
public class Takeaway {
	
	Hashtable<String,Restaurant> restaurants = new Hashtable<>();
	List<User> users = new ArrayList<User>();
	/**
	 * Adds a new restaurant to the take-away system
	 * 
	 * @param r the restaurant to be added
	 */
	public void addRestaurant(Restaurant r) {
		restaurants.put(r.getName(), r);
	}
	
	/**
	 * Returns the collections of restaurants
	 * 
	 * @return collection of added restaurants
	 */
	public Collection<String> restaurants() {
		List <String> toReturn = new ArrayList<>();
		toReturn.addAll(restaurants.keySet());
		return toReturn;
	}
	
	/**
	 * Define a new user
	 * 
	 * @param firstName first name of the user
	 * @param lastName  last name of the user
	 * @param email     email
	 * @param phoneNumber telephone number
	 * @return
	 */
	public User registerUser(String firstName, String lastName, String email, String phoneNumber) {
		User tmp = new User(firstName, lastName, email, phoneNumber);
		users.add(tmp);
		return tmp;
	}
	
	/**
	 * Gets the collection of registered users
	 * 
	 * @return the collection of users
	 */
	public Collection<User> users(){
		
		List <User> toReturn = new ArrayList<User>(users);
		toReturn.sort(Comparator.comparing(User :: getLastName).thenComparing(User :: getFirstName));
		for (User u : toReturn) {
			System.out.println(u.toString());
		}
		return toReturn;
	}
	
	/**
	 * Create a new order by a user to a given restaurant.
	 * 
	 * The order is initially empty and is characterized
	 * by a desired delivery time. 
	 * 
	 * @param user				user object
	 * @param restaurantName	restaurant name
	 * @param h					delivery time hour
	 * @param m					delivery time minutes
	 * @return
	 */
	public Order createOrder(User user, String restaurantName, int h, int m) {
		Restaurant res = restaurants.get(restaurantName);
		Order tmp = new Order(user, res, h, m);
		res.orders.add(tmp);
		return tmp;
	}
	
	/**
	 * Retrieves the collection of restaurant that are open
	 * at the given time.
	 * 
	 * @param time time to check open
	 * 
	 * @return collection of restaurants
	 */
	public Collection<Restaurant> openedRestaurants(String time){
		String hh[] = time.split(":");
		Time t = new Time(Integer.parseInt(hh[0]), Integer.parseInt(hh[1]));
		
		List<Restaurant> toReturn = new LinkedList<Restaurant>();
		for (Restaurant r : restaurants.values()) {
			for (WorkingHours h : r.workingHours) {
				if (h.includes(t)) {
					toReturn.add(r);
				}
			}
		}
		Collections.sort(toReturn);
		return toReturn;
	}

	
	
}
