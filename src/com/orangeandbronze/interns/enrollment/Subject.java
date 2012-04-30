
//WAAAAAAAAAAAAAAAAAAAAAAA

package com.orangeandbronze.interns.enrollment;

import java.util.HashSet;
import java.util.Set;

public class Subject {

	private Set<Subject> prerequisites = new HashSet<Subject>();
	private String subjectID;
	private int units = 3;
	
	
	public Subject(){
		
	}
	
	public Subject(Set<Subject> prerequisites, String subjectID) {
		this.prerequisites = prerequisites;
		this.subjectID = subjectID;
	}

	public Set<Subject> getPrerequisites() {
		return prerequisites;
	}

	@Override
	public String toString() {
		return subjectID;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((prerequisites == null) ? 0 : prerequisites.hashCode());
		result = prime * result
				+ ((subjectID == null) ? 0 : subjectID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Subject)) {
			return false;
		}
		Subject other = (Subject) obj;
		if (prerequisites == null) {
			if (other.prerequisites != null) {
				return false;
			}
		} else if (!prerequisites.equals(other.prerequisites)) {
			return false;
		}
		if (subjectID == null) {
			if (other.subjectID != null) {
				return false;
			}
		} else if (!subjectID.equals(other.subjectID)) {
			return false;
		}
		return true;
	}

	public int getUnits() {
		return units;
	}

}
