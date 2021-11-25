package university;

public class Tuple {
	Students student;
	float score;
	
	
	
	public Tuple(Students student, float score) {
		super();
		this.student = student;
		this.score = score;
	}



	
	
	public Students getStudent() {
		return student;
	}





	public void setScore(float score) {
		this.score = score;
	}
	
	public void setStudent(Students student) {
		this.student = student;
	}
	
	static int compare(Tuple a, Tuple b)
    {
        return Float.compare(b.score, a.score);
    }
	
	public String toString () {
		return (student.getName()+ " " + student.getSurname() + " : " + score );
	}
	
}
