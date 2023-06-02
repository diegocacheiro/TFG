package com.usc.datos;


import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.locationtech.jts.geom.Geometry;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import lombok.Data;

@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Entity
@Table(name="track_points")
@Data
public class Track_points {

	@EmbeddedId
	private TrackPointPK trackPointPK;
	@Column(name = "point_geo")
	private Geometry point;
	@Column(name = "speed")
	private Double speed;
	@Column(name = "elevation")
	private Double elevation;
	@Column(name = "start_time")
	private Timestamp  startTime;
	@Column(name = "end_time")
	private Timestamp  endTime;
	@Type(type = "jsonb")
	@Column(name = "measures")
	private List<Measures> measures;
	
	public Track_points() {
		super();
	}
	
	public Track_points(TrackPointPK trackPointPK, Geometry point, Double speed, Double elevation, Timestamp startTime,
			Timestamp endTime, List<Measures> measures) {
		super();
		this.trackPointPK = trackPointPK;
		this.point = point;
		this.speed = speed;
		this.elevation = elevation;
		this.startTime = startTime;
		this.endTime = endTime;
		this.measures = measures;
	}
	
}
