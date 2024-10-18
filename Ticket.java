package com.project.Model;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Ticketid;
	
	private String name;
	private String email;
	private int busnumber;
	private String busname;
	private String fromcity;
	private String tocity;
	private LocalDate date;
	private LocalTime time;
	private int Noofperson;
	private double price;
	public int getTicketid() {
		return Ticketid;
	}
	public void setTicketid(int ticketid) {
		Ticketid = ticketid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getBusnumber() {
		return busnumber;
	}
	public void setBusnumber(int busnumber) {
		this.busnumber = busnumber;
	}
	public String getBusname() {
		return busname;
	}
	public void setBusname(String busname) {
		this.busname = busname;
	}
	public String getFromcity() {
		return fromcity;
	}
	public void setFromcity(String fromcity) {
		this.fromcity = fromcity;
	}
	public String getTocity() {
		return tocity;
	}
	public void setTocity(String tocity) {
		this.tocity = tocity;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public int getNoofperson() {
		return Noofperson;
	}
	public void setNoofperson(int noofperson) {
		Noofperson = noofperson;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Ticket(int ticketid, String name, String email, int busnumber, String busname, String fromcity,
			String tocity, LocalDate date, LocalTime time, int noofperson, double price) {
		super();
		Ticketid = ticketid;
		this.name = name;
		this.email = email;
		this.busnumber = busnumber;
		this.busname = busname;
		this.fromcity = fromcity;
		this.tocity = tocity;
		this.date = date;
		this.time = time;
		Noofperson = noofperson;
		this.price = price;
	}
	public Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Ticket [Ticketid=" + Ticketid + ", name=" + name + ", email=" + email + ", busnumber=" + busnumber
				+ ", busname=" + busname + ", fromcity=" + fromcity + ", tocity=" + tocity + ", date=" + date
				+ ", time=" + time + ", Noofperson=" + Noofperson + ", price=" + price + "]";
	}
}
