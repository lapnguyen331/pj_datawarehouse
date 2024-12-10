package dbmart;

import java.sql.Date;

public class DateDim {
	private int date_sk;
	private Date full_date;
	private int day_since_2010;
	private int month_since_2010;
	private String day_of_week;
	private String calendar_month;
	private int calendar_year;
	private int day_of_month;
	private int day_of_year;
	private int week_of_year_sunday;
	private Date week_sunday_start;
	private int week_of_year_monday;
	private Date week_monday_start;
	private int holiday;
	private int day_type;
	public DateDim(int date_sk, Date full_date, int day_since_2010, int month_since_2010, String day_of_week,
			String calendar_month, int calendar_year, int day_of_month, int day_of_year, int week_of_year_sunday,
			Date week_sunday_start, int week_of_year_monday, Date week_monday_start, int holiday, int day_type) {
		super();
		this.date_sk = date_sk;
		this.full_date = full_date;
		this.day_since_2010 = day_since_2010;
		this.month_since_2010 = month_since_2010;
		this.day_of_week = day_of_week;
		this.calendar_month = calendar_month;
		this.calendar_year = calendar_year;
		this.day_of_month = day_of_month;
		this.day_of_year = day_of_year;
		this.week_of_year_sunday = week_of_year_sunday;
		this.week_sunday_start = week_sunday_start;
		this.week_of_year_monday = week_of_year_monday;
		this.week_monday_start = week_monday_start;
		this.holiday = holiday;
		this.day_type = day_type;
	}
	public DateDim() {
		super();
	}
	public int getDate_sk() {
		return date_sk;
	}
	public void setDate_sk(int date_sk) {
		this.date_sk = date_sk;
	}
	public Date getFull_date() {
		return full_date;
	}
	public void setFull_date(Date full_date) {
		this.full_date = full_date;
	}
	public int getDay_since_2010() {
		return day_since_2010;
	}
	public void setDay_since_2010(int day_since_2010) {
		this.day_since_2010 = day_since_2010;
	}
	public int getMonth_since_2010() {
		return month_since_2010;
	}
	public void setMonth_since_2010(int month_since_2010) {
		this.month_since_2010 = month_since_2010;
	}
	public String getDay_of_week() {
		return day_of_week;
	}
	public void setDay_of_week(String day_of_week) {
		this.day_of_week = day_of_week;
	}
	public String getCalendar_month() {
		return calendar_month;
	}
	public void setCalendar_month(String calendar_month) {
		this.calendar_month = calendar_month;
	}
	public int getCalendar_year() {
		return calendar_year;
	}
	public void setCalendar_year(int calendar_year) {
		this.calendar_year = calendar_year;
	}
	public int getDay_of_month() {
		return day_of_month;
	}
	public void setDay_of_month(int day_of_month) {
		this.day_of_month = day_of_month;
	}
	public int getDay_of_year() {
		return day_of_year;
	}
	public void setDay_of_year(int day_of_year) {
		this.day_of_year = day_of_year;
	}
	public int getWeek_of_year_sunday() {
		return week_of_year_sunday;
	}
	public void setWeek_of_year_sunday(int week_of_year_sunday) {
		this.week_of_year_sunday = week_of_year_sunday;
	}
	public Date getWeek_sunday_start() {
		return week_sunday_start;
	}
	public void setWeek_sunday_start(Date week_sunday_start) {
		this.week_sunday_start = week_sunday_start;
	}
	public int getWeek_of_year_monday() {
		return week_of_year_monday;
	}
	public void setWeek_of_year_monday(int week_of_year_monday) {
		this.week_of_year_monday = week_of_year_monday;
	}
	public Date getWeek_monday_start() {
		return week_monday_start;
	}
	public void setWeek_monday_start(Date week_monday_start) {
		this.week_monday_start = week_monday_start;
	}
	public int getHoliday() {
		return holiday;
	}
	public void setHoliday(int holiday) {
		this.holiday = holiday;
	}
	public int getDay_type() {
		return day_type;
	}
	public void setDay_type(int day_type) {
		this.day_type = day_type;
	}
	@Override
	public String toString() {
		return "DateDim [date_sk=" + date_sk + ", full_date=" + full_date + ", day_since_2010=" + day_since_2010
				+ ", month_since_2010=" + month_since_2010 + ", day_of_week=" + day_of_week + ", calendar_month="
				+ calendar_month + ", calendar_year=" + calendar_year + ", day_of_month=" + day_of_month
				+ ", day_of_year=" + day_of_year + ", week_of_year_sunday=" + week_of_year_sunday
				+ ", week_sunday_start=" + week_sunday_start + ", week_of_year_monday=" + week_of_year_monday
				+ ", week_monday_start=" + week_monday_start + ", holiday=" + holiday + ", day_type=" + day_type + "]";
	}
	


}
