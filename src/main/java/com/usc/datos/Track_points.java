package com.usc.datos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import org.springframework.data.geo.Point;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

@TypeDef(name = "json", typeClass = JsonBinaryType.class)
@Entity
@Table(name = "track_points")
public class Track_points implements Serializable{
	
	@EmbeddedId
    private Track_pointsId id;

	@Column(name = "point_geo", nullable = false, columnDefinition = "GEOGRAPHY(POINT)")
	private Point point_geo;
	
	@Column(name = "speed", nullable = false)
	private Double speed;
	
	@Column(name = "elevation", nullable = false)
	private Double elevation;
	
	@Column(name = "start_time", nullable = false)
	private LocalDateTime  start_time;
	
	@Column(name = "end_time", nullable = false)
	private LocalDateTime end_time;
	
	@Type(type = "json")
    @Column(name = "measures", columnDefinition = "json")
	private List<Measures> measures;
	
	public Track_points() {
		super();
	}

	public Track_points(Track_pointsId id, Point point_geo, Double speed, Double elevation, LocalDateTime start_time,
			LocalDateTime end_time, List<Measures> measures) {
		super();
		this.id = id;
		this.point_geo = point_geo;
		this.speed = speed;
		this.elevation = elevation;
		this.start_time = start_time;
		this.end_time = end_time;
		this.measures = measures;
	}

	public Track_pointsId getId() {
		return id;
	}

	public void setId(Track_pointsId id) {
		this.id = id;
	}

	public Point getPoint_geo() {
		return point_geo;
	}

	public void setPoint_geo(Point point_geo) {
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

	public LocalDateTime getStart_time() {
		return start_time;
	}

	public void setStart_time(LocalDateTime start_time) {
		this.start_time = start_time;
	}

	public LocalDateTime getEnd_time() {
		return end_time;
	}

	public void setEnd_time(LocalDateTime end_time) {
		this.end_time = end_time;
	}

	public List<Measures> getMeasures() {
		return measures;
	}

	public void setMeasures(List<Measures> measures) {
		this.measures = measures;
	}
}
