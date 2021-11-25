package university;

import java.util.logging.Logger;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;


/**
 * This class is an extended version of the {@Link University} class.
 * 
 *
 */
public class UniversityExt extends University {
	
	private final static Logger logger = Logger.getLogger("University");

	public UniversityExt(String name) {
		super(name);
		// Example of logging
		logger.info("Creating extended university object");
	} 
	
	public int enroll(String first, String last){
		int id = super.enroll(first, last);
		logger.info("New student enrolled: " + id + ", " + first + " " + last );
		return id;
	}
	
	public int activate(String title, String teacher){
		int code = super.activate(title, teacher);
		logger.info("New course activated: " + (courseCounter+10) + ", " + title + " " + teacher);
		return code;
	}
	
	public void register(int studentID, int courseCode){		
		super.register(studentID, courseCode);
		logger.info("Student " + (studentID) + " signed up for course " + courseCode);
	}
	
	/**
	 * records the grade (integer 0-30) for an exam can 
	 * 
	 * @param studentId the ID of the student
	 * @param courseID	course code 
	 * @param grade		grade ( 0-30)
	 */
	public void exam(int studentId, int courseID, int grade) {
		studentList[studentId-10000].addMark(grade, coursesList[courseID-10]);
		coursesList[courseID-10].addMark(grade);
		logger.info("Student " + studentId + " took an exam in course " + courseID + " with grade " + grade);
	}

	/**
	 * Computes the average grade for a student and formats it as a string
	 * using the following format 
	 * 
	 * {@code "Student STUDENT_ID : AVG_GRADE"}. 
	 * 
	 * If the student has no exam recorded the method
	 * returns {@code "Student STUDENT_ID hasn't taken any exams"}.
	 * 
	 * @param studentId the ID of the student
	 * @return the average grade formatted as a string.
	 */
	public String studentAvg(int studentId) {
		String toReturn;
		if (studentList[studentId-10000].getMarkCounter()==0) {
			toReturn = "student" + studentId + "hasn't taken any exams";
		}else {
			toReturn = "Student " + studentList[studentId-10000].getId() + " : " + studentList[studentId-10000].getAvg();
		}
		return toReturn;
	}
	
	/**
	 * Computes the average grades of all students that took the exam for a given course.
	 * 
	 * The format is the following: 
	 * {@code "The average for the course COURSE_TITLE is: COURSE_AVG"}.
	 * 
	 * If no student took the exam for that course it returns {@code "No student has taken the exam in COURSE_TITLE"}.
	 * 
	 * @param courseId	course code 
	 * @return the course average formatted as a string
	 */
	public String courseAvg(int courseId) {
		String toReturn;
		
		if (coursesList[courseId-10].getMarkCounter()==0) {
			toReturn = "No student has taken the exam in " + coursesList[courseId-10].getName();
		}else {
			toReturn = "The average for the course " + coursesList[courseId-10].getName() + " is: " + coursesList[courseId-10].getAvg();
		}
		
		return toReturn;
		
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
	public String topThreeStudents() {
		String toReturn = "";
		
		
		Tuple[] tupleList = new Tuple[studentCounter];
		
		
		for (int i = 0; i < studentCounter ; i++) {
			tupleList[i] = new Tuple(studentList[i], 0);
			tupleList[i].setScore(studentList[i].studentScore());
		}
		
		
		Arrays.sort(tupleList , Tuple::compare);
		
		for(int i = 0; i < studentCounter && i < 3; i++) {
			toReturn = toReturn + tupleList[i].toString()+"\n";
		}
		
		return toReturn;
		
	}
	

	
}
