package vorlesung;

import java.util.Comparator;

public class Vorlesung implements Comparable<Vorlesung>, Comparator<Vorlesung> {
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
		if(subject.compareTo(o.subject)>0 || title.compareTo(o.title)>0 || lecturer.compareTo(o.lecturer)>0)
			return 1;
		
		if(subject.compareTo(o.subject)<0 || title.compareTo(o.title)<0 || lecturer.compareTo(o.lecturer)<0)
			return -1;
		return 0;
	}

	@Override
	public int compare(Vorlesung v1, Vorlesung v2) {
		if(v1.getAttendance()>v2.getAttendance())
			return 1;
		if(v1.getAttendance()<v2.getAttendance())
			return -1;
		return 0;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		if(o == null)
			return false;
		if(!(o instanceof Vorlesung))
			return false;
		Vorlesung v = (Vorlesung) o;
		if(subject.equals(v.subject) && title.equals(v.title) && lecturer.equals(v.lecturer) && attendance==v.attendance)
			return true;
		else
			return false;
	}
	
}
