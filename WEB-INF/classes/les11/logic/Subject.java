package les11.logic;

import java.util.*;

public class Subject {
	public int id;
	public String description;
		
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescription () {
		return description;
	}
	
	public void setDescription (String description) {
		this.description = description;
	}
	
	public String toString() {
		return "[Subject description: " + this.description + "]";
	}
}
