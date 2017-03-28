package les11.logic.dto;

import java.util.*;

import les11.logic.controller.*;
import les11.logic.dao.*;
import les11.logic.exception.*;
import les11.logic.mysql.*;

public class Mark {
	public int id;
	public int value;
	public int StudentId;
	public int SubjectId;
		
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public int getStudentId() {
		return StudentId;
	}
	public void setStudentId(int StudentId) {
		this.StudentId = StudentId;
	}
	
	public int getSubjectId() {
		return SubjectId;
	}
	
	public void setSubjectId(int SubjectId) {
		this.SubjectId = SubjectId;
	}
	
	public String toString() {
		return "[" + this.StudentId +"'s mark for " + this.SubjectId + " is " + this.value + "]";
	}
}
