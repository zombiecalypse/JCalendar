package models;

import java.util.LinkedList;
import java.util.List;

import java.util.*;
import javax.persistence.*;

import org.joda.time.DateTime;
 
import play.data.validation.Required;
import play.db.jpa.*;

@Entity
public class Calendar extends Model {
	@OneToMany(mappedBy = "calendar")
	public List<Event> events;
	
	@ManyToOne
	public User owner;
	
	public String name;

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public List<Event> getEvents() {
		return new LinkedList<Event>(events);
	}
	
	public List<Event> getByMonth(DateTime cal) {
		List<Event> lst = new LinkedList<Event>();
		for (Event e : events) {
			if (e.startDate.getYear()== cal.getYear()
					&& e.startDate.getMonthOfYear() == cal.getMonthOfYear()) {
				lst.add(e);
			}
		}
		return events;
	}
}
