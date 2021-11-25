package diet;

import java.util.Comparator;

/**
 * Represent a take-away system user
 *  
 */
public class User implements Comparable<User> {
	
	String name;
	String sur;
	String mail;
	String phone;
	
	
	
	
	
	public User(String name, String sur, String mail, String phone) {
		this.name = name;
		this.sur = sur;
		this.mail = mail;
		this.phone = phone;
	}
	
	

	@Override
	public String toString() {
		return name + " " + sur;
	}



	@Override
	public int compareTo(User o) {
		if(this.getFirstName().compareTo(o.name) < 0)
			return -1;
		if (this.getFirstName().compareTo(o.name) > 0)
			return +1;
		
		return this.getLastName().compareTo(o.sur);
	}
		


	/**
	 * get user's last name
	 * @return last name
	 */
	public String getLastName() {
		return sur;
	}
	
	/**
	 * get user's first name
	 * @return first name
	 */
	public String getFirstName() {
		return name;
	}
	
	/**
	 * get user's email
	 * @return email
	 */
	public String getEmail() {
		return mail;
	}
	
	/**
	 * get user's phone number
	 * @return  phone number
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * change user's email
	 * @param email new email
	 */
	public void SetEmail(String email) {
		this.mail=email;
	}
	
	/**
	 * change user's phone number
	 * @param phone new phone number
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}



}
