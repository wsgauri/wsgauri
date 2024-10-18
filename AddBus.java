package com.project.Model;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class AddBus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer busNumber;
    private String busName;
    private String fromCity;
    private String toCity;
    private LocalDate date;
    private LocalTime time;
    private Integer duration;
    private Double price;
    private String description;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBusNumber() {
		return busNumber;
	}
	public void setBusNumber(Integer busNumber) {
		this.busNumber = busNumber;
	}
	public String getBusName() {
		return busName;
	}
	public void setBusName(String busName) {
		this.busName = busName;
	}
	public String getFromCity() {
		return fromCity;
	}
	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}
	public String getToCity() {
		return toCity;
	}
	public void setToCity(String toCity) {
		this.toCity = toCity;
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
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public AddBus( Integer busNumber, String busName, String fromCity, String toCity, LocalDate date,
			LocalTime time, Integer duration, Double price, String description) {
		super();
		this.busNumber = busNumber;
		this.busName = busName;
		this.fromCity = fromCity;
		this.toCity = toCity;
		this.date = date;
		this.time = time;
		this.duration = duration;
		this.price = price;
		this.description = description;
	}
	public AddBus() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "AddBus [id=" + id + ", busNumber=" + busNumber + ", busName=" + busName + ", fromCity=" + fromCity
				+ ", toCity=" + toCity + ", date=" + date + ", time=" + time + ", duration=" + duration + ", price="
				+ price + ", description=" + description + "]";
	}
}