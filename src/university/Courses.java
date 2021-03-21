package university;

public class Courses {
	private String name;
	private int code;
	private String teacher;
	private Students[] attendees= new Students[100];
	private int counter;
	
	public Courses(String name, int code, String teacher) {
		
		this.name = name;
		this.code = code;
		this.teacher = teacher;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	
	public void addStudent(Students toAdd) {
		attendees[counter++]=toAdd;
		
	}
	
	public int getAttendees(int pos) {
		
		return attendees[pos].getId();
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
	
	
	
}
