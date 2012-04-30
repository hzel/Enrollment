package com.orangeandbronze.interns.enrollment;

public class Schedule {

	private Day day = Day.MON_THU;
	private TimeSlot timeslot = TimeSlot.TS_0830_1000;
	
	
	public Schedule(){
		
	}
	
	public Schedule(Day day, TimeSlot timeslot) {
		verifyParameter(day);
        verifyParameter(timeslot);
		this.day = day;
		this.timeslot = timeslot;
	}

	private void verifyParameter(Object obj) throws IllegalArgumentException{
		if (obj == null) {
			throw new IllegalArgumentException("Null parameter");
		}
	}
	
	@Override
	public String toString() {
		return  day +" "+ timeslot ;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result
				+ ((timeslot == null) ? 0 : timeslot.hashCode());
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
		if (!(obj instanceof Schedule)) {
			return false;
		}
		Schedule other = (Schedule) obj;
		if (day != other.day) {
			return false;
		}
		if (timeslot != other.timeslot) {
			return false;
		}
		return true;
	}

}
