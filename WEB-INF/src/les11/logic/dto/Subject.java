package les11.logic.dto;

import java.util.*;

import les11.logic.controller.*;
import les11.logic.dao.*;
import les11.logic.exception.*;
import les11.logic.mysql.*;

public class Subject {
	private int id;
	private String description;
		
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
