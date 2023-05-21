package com.usc.datos;

import java.io.Serializable;

import javax.persistence.*;

@Embeddable
public class Track_pointsId implements Serializable{
	
	@Column(name = "track", nullable = false)
    private String track;

    @Column(name = "point_id", nullable = false)
    private Integer point_id;

	public Track_pointsId() {
		super();
	}

	public Track_pointsId(String track, Integer point_id) {
		super();
		this.track = track;
		this.point_id = point_id;
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
    
}
