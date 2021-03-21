package university;

/**
 * This class represents a university education system.
 * 
 * It manages students and courses.
 *
 */
public class University {

	/**
	 * Constructor
	 * @param name name of the university
	 */
	String name=new String();
	String firstDir=new String();
	String lastDir=new String();
	
	public University(String name){
		//TODO: to be implemented
		this.name=name;
	}
	
	/**
	 * Getter for the name of the university
	 * 
	 * @return name of university
	 */
	public String getName(){
		//TODO: to be implemented
		
		return name;
	}
	
	/**
	 * Defines the rector for the university
	 * 
	 * @param first
	 * @param last
	 */
	public void setRector(String first, String last){
		//TODO: to be implemented
		this.firstDir=first;
		this.lastDir=last;
		
	}
	
	/**
	 * Retrieves the rector of the university
	 * 
	 * @return name of the rector
	 */
	public String getRector(){
		//TODO: to be implemented
		return firstDir+" "+lastDir;
	}
	
	/**
	 * Enrol a student in the university
	 * 
	 * @param first first name of the student
	 * @param last last name of the student
	 * 
	 * @return unique ID of the newly enrolled student
	 */
	
	Students[] studentList=new Students[1000];
	int studentCounter=0;
	
	public int enroll(String first, String last){
		//TODO: to be implemented
		studentList[studentCounter]=new Students(first,last,studentCounter+10000);
		studentCounter++;
		return (studentList[studentCounter-1].getId());
	}
	
	/**
	 * Retrieves the information for a given student
	 * 
	 * @param id the ID of the student
	 * 
	 * @return information about the student
	 */
	public String student(int id){
		//TODO: to be implemented
		
		return (studentList[id-10000].getName()+" "+studentList[id-10000].getSurname()+" "+studentList[id-10000].getId());
	}
	
	/**
	 * Activates a new course with the given teacher
	 * 
	 * @param title title of the course
	 * @param teacher name of the teacher
	 * 
	 * @return the unique code assigned to the course
	 */
	
	Courses[] coursesList=new Courses[50];
	int courseCounter=0;
	
	public int activate(String title, String teacher){
		//TODO: to be implemented
		coursesList[courseCounter]=new Courses(title, courseCounter+10, teacher);
		courseCounter++;
		return coursesList[courseCounter-1].getCode();
	}
	
	/**
	 * Retrieve the information for a given course.
	 * 
	 * The course information is formatted as a string containing 
	 * code, title, and teacher separated by commas, 
	 * e.g., {@code "10,Object Oriented Programming,James Gosling"}.
	 * 
	 * @param code unique code of the course
	 * 
	 * @return information about the course
	 */
	public String course(int code){
		//TODO: to be implemented
		
		return (coursesList[code-10].getCode()+","+coursesList[code-10].getName()+" "+coursesList[code-10].getTeacher());
	}
	
	/**
	 * Register a student to attend a course
	 * @param studentID id of the student
	 * @param courseCode id of the course
	 */
	public void register(int studentID, int courseCode){
		//TODO: to be implemented
		
		studentList[studentID-10000].addCourse(coursesList[courseCode-10]);
		coursesList[courseCode-10].addStudent(studentList[studentID-10000]);
	}
	
	/**
	 * Retrieve a list of attendees
	 * 
	 * @param courseCode unique id of the course
	 * @return list of attendees separated by "\n"
	 */
	public String listAttendees(int courseCode){
		//TODO: to be implemented
		String toReturn=new String();
		int counter=coursesList[courseCode-10].getCounter();
		for(int i=0;i<counter;++i) {
			toReturn+=student(coursesList[courseCode-10].getAttendees(i));
			toReturn+="\n";
		}
		
		return toReturn;
	}

	/**
	 * Retrieves the study plan for a student.
	 * 
	 * The study plan is reported as a string having
	 * one course per line (i.e. separated by '\n').
	 * The courses are formatted as describe in method {@link #course}
	 * 
	 * @param studentID id of the student
	 * 
	 * @return the list of courses the student is registered for
	 */
	public String studyPlan(int studentID){
		//TODO: to be implemented
		String toReturn=new String();
		for(int i=0;i<studentList[studentID-10000].getCounter();++i) {
			toReturn+=course(studentList[studentID-10000].returnCode(i));
			toReturn+="\n";
		}
		return toReturn;
	}
}
