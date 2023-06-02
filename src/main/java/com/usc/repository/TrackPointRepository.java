package com.usc.repository;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.usc.datos.TrackPointPK;
import com.usc.datos.Track_points;

@Repository
public interface TrackPointRepository extends CrudRepository<Track_points, TrackPointPK>{
	@Query("SELECT distinct(tp.trackPointPK.track) FROM Track_points tp") 
	public List<String> buscarTracks();
}
