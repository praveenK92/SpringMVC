package praveen.aicf.event.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="aicf_events")
public class AICFEvent {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column(nullable=false)
	private String title;
	@Column(nullable=false)
	private Date start;
	@Column(nullable=false)
	private Date end;
	@Column(name="prize_money",nullable=false)
	private String prizeMoney;
	@Column(nullable=false)
	private String download;
	
	private String eventCode;
	private List<EventCategory> eventCategoryList;
	private Organizer organizer;
	
	@Column(name="latitude",nullable=false)
	private float latitude;
	@Column(name="longitude",nullable=false)
	private float longitude;
	private String address;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public String getPrizeMoney() {
		return prizeMoney;
	}
	public void setPrizeMoney(String prizeMoney) {
		this.prizeMoney = prizeMoney;
	}
	public String getDownload() {
		return download;
	}
	public void setDownload(String download) {
		this.download = download;
	}
	public List<EventCategory> getEventCategoryList() {
		return eventCategoryList;
	}
	public void setEventCategoryList(List<EventCategory> eventCategoryList) {
		this.eventCategoryList = eventCategoryList;
	}
	public Organizer getOrganizer() {
		return organizer;
	}
	public void setOrganizer(Organizer organizer) {
		this.organizer = organizer;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEventCode() {
		return eventCode;
	}
	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}
}