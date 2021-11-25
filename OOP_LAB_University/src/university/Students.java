package university;

public class Students {
	private static final int MAX_COURSES = 25;
	
	
	String name=new String();
	String surname=new String();
	int id;
	private Courses[] followed=new Courses[MAX_COURSES];
	private int[] marks = new int[MAX_COURSES];
	
	int counter;
	int markCounter;
	
	
	
	
	public int getMarkCounter() {
		return markCounter;
	}

	public Students(){	

	}
	
	public Students(Students toCopy){	
		this.name = toCopy.name;
		this.surname = toCopy.surname;
		this.id = toCopy.id;
	}
	
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
	
	public int getMark(int courseId) {
		return marks[courseId-10];
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
	
	public void addMark(int mark, Courses course) {
		marks[markCounter++] = mark;
	}
	
	public String getAvg() {
		float sum = 0;
		for (int i = 0; i < markCounter; i++) {
			sum = sum + (float)marks[i];
		}
		
		return String.valueOf(sum/markCounter);
	}
	
	/**
	 * Retrieve information for the best students to award a price.
	 * 
	 * The students' score is evaluated as the average grade of the exams they've taken. 
	 * To take into account the number of exams taken and not only the grades, 
	 * a special bonus is assigned on top of the average grade: 
	 * the number of taken exams divided by the number of courses the student is enrolled to, multiplied by 10.
	 * The bonus is added to the exam average to compute the student score.
	 * 
	 * The method returns a string with the information about the three students with the highest score. 
	 * The students appear one per row (rows are terminated by a new-line character {@code '\n'}) 
	 * and each one of them is formatted as: {@code "STUDENT_FIRSTNAME STUDENT_LASTNAME : SCORE"}.
	 * 
	 * @return info of the best three students.
	 */
	
	public float studentScore() {
		float sum = 0;
		for (int i = 0; i < counter; i++) {
			sum = sum + (float)marks[i];
		}
		
		return ((sum/counter)+(10*(float)markCounter/(float)counter));
	}
	
	
}
