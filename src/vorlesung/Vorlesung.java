package vorlesung;

public class Vorlesung implements Comparable<Vorlesung> {
	private String subject;
	private String title;
	private String lecturer;
	private int attendance;
	
	public Vorlesung() {
		super();
	}
	
	public Vorlesung(String s, String t, String l, int a) {
		this.subject = s;
		this.title = t;
		this.lecturer = l;
		this.attendance = a;
	}
	
	public String getSubject() {
		return this.subject;
	}
	
	public void setSubject(String s) {
		this.subject = s;
	}

	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String t) {
		this.title = t;
	}
	
	public String getLecturer() {
		return this.lecturer;
	}
	
	public void setLecturer(String l) {
		this.lecturer = l;
	}
	
	public int getAttendance() {
		return this.attendance;
	}
	
	public void setAttendance(int a) {
		this.attendance = a;
	}

	@Override
	public String toString() {
		return "["+subject+", "+title+ ", "+lecturer+", "+attendance+"]";
	}

	@Override
	public int compareTo(Vorlesung o) {
		if(subject.compareTo(o.subject)>0)
			return 1;
		
		if(subject.compareTo(o.subject)<0)
			return -1;
		return 1;
	}
	
}
