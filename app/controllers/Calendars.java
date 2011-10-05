package controllers;

import java.text.SimpleDateFormat;
import java.util.List;

import org.joda.time.DateTime;

import models.Calendar;
import models.Event;

import play.data.validation.Required;
import play.mvc.Controller;

public class Calendars extends Application {
	public static void index() {
		List<Calendar> calendars = Calendar.all().fetch(10);
		render(calendars);
	}
	
	public static void show(@Required long id, Integer year, Integer month) {
		Calendar calendar = Calendar.findById(id);
		DateTime shownMonth = new DateTime()
									.withMonthOfYear(month)
									.withYear(year);
		List<Event> events = calendar.getByMonth(shownMonth);
		render(calendar, events, shownMonth);
	}
}
