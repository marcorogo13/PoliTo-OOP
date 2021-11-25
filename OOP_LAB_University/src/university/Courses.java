package university;

public class Courses {
	
	private static final int MAX_ATTENDEES = 100;
	
	private String name;
	private int code;
	private String teacher;
	private Students[] attendees= new Students[MAX_ATTENDEES];
	private int[] marks = new int[MAX_ATTENDEES];
	private int counter=0;
	private int markCounter=0;
	
	public void addMark(int mark) {
		this.marks[markCounter++]=mark;
	}
	public int getMarkCounter() {
		return markCounter;
	}

	public void setMarkCounter(int markCounter) {
		this.markCounter = markCounter;
	}

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
	
	public String getAvg() {
		float sum = 0;
		
		
		if (counter == 0 || markCounter == 0) {
			return null;
		}else {
			for (int i=0; i < markCounter ; i++) {
				sum = sum + marks[i];
			}
		}
		
		return String.valueOf(sum/markCounter);
	}
	
}
