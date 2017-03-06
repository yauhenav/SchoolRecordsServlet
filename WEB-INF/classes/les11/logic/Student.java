package les11.logic;

import java.util.*;

public class Student {
	public int id;
	public String name;
	public String surname;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName () {
		return name;
	}
	
	public void setName (String name) {
		this.name = name;
	}
	
	public String getSurname () {
		return surname;
	}
	
	public void setSurname (String surname) {
		this.surname = surname;
	}
	
	public String toString() {
		return "[Student's name: " + this.name + " " + this.surname + "]";
	}
}
