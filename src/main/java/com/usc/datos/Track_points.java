package com.usc.datos;


import javax.persistence.*;

@Entity
@Table(name = "track_points")
public class Track_points{
	
	@Column(name = "track", nullable = false)
    private String track;

	@Id
    @Column(name = "point_id")
    private Long pointId;
	
	public Track_points() {
		super();
	}
	
	public Track_points(String track, Long pointId) {
		super();
		this.track = track;
		this.pointId = pointId;
	}

	public String getTrack() {
		return track;
	}

	public void setTrack(String track) {
		this.track = track;
	}

	public Long getPointId() {
		return pointId;
	}

	public void setPointId(Long pointId) {
		this.pointId = pointId;
	}
	
	
}
