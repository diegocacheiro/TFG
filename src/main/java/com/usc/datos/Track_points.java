package com.usc.datos;

import java.util.List;

public class Track_points {
	private String track;
	private Integer point_id;
	private Object point_geo;
	private Double speed;
	private Double elevation;
	private String start_time;
	private String end_time;
	private List<Measures> measures;
	
	
	public Track_points() {
		super();
	}

	public Track_points(String track, Integer point_id, Object point_geo, Double speed, Double elevation,
			String start_time, String end_time, List<Measures> measures) {
		super();
		this.track = track;
		this.point_id = point_id;
		this.point_geo = point_geo;
		this.speed = speed;
		this.elevation = elevation;
		this.start_time = start_time;
		this.end_time = end_time;
		this.measures = measures;
	}
	
	public String getTrack() {
		return track;
	}
	public void setTrack(String track) {
		this.track = track;
	}
	public Integer getPoint_id() {
		return point_id;
	}
	public void setPoint_id(Integer point_id) {
		this.point_id = point_id;
	}
	public Object getPoint_geo() {
		return point_geo;
	}
	public void setPoint_geo(Object point_geo) {
		this.point_geo = point_geo;
	}
	public Double getSpeed() {
		return speed;
	}
	public void setSpeed(Double speed) {
		this.speed = speed;
	}
	public Double getElevation() {
		return elevation;
	}
	public void setElevation(Double elevation) {
		this.elevation = elevation;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public List<Measures> getMeasures() {
		return measures;
	}
	public void setMeasures(List<Measures> measures) {
		this.measures = measures;
	}
	@Override
	public String toString() {
		return "Track_points [track=" + track + ", point_id=" + point_id + ", point_geo=" + point_geo + ", speed="
				+ speed + ", elevation=" + elevation + ", start_time=" + start_time + ", end_time=" + end_time
				+ ", measures=" + measures + "]";
	}
	
	

}
