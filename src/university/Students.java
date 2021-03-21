package university;

public class Students {
	String name=new String();
	String surname=new String();
	int id;
	private Courses[] followed=new Courses[25];
	int counter;
	
	public Students(String name, String surname, int id) {
		this.name = name;
		this.surname = surname;
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public void addCourse(Courses toAdd) {
		followed[counter++]=toAdd;
	}

	public int returnCode(int pos) {
		return followed[pos].getCode();
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
	
	
	
}
