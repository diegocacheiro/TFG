package com.usc.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usc.repository.TrackPointRepository;

@Service
public class TrackPointService {
	
	@Autowired
	private TrackPointRepository trackPointRepository;
	
	public List<String> buscarTracks(){
		return trackPointRepository.buscarTracks();
	}
}
