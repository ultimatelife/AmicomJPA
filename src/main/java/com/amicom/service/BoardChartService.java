package com.amicom.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amicom.dao.BoardChart;
import com.amicom.dao.en.BoardType;
import com.amicom.repository.BoardChartRepository;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

@Service
public class BoardChartService {
	
	@Autowired
	BoardChartRepository boardChartRepository;
	
	public Map<BoardType, Collection<String>> list() {
		List<BoardChart> boardCharts = boardChartRepository.findAll();
		Multimap<BoardType, String> map = ArrayListMultimap.create();
		
		for(BoardChart boardChart : boardCharts){
			map.put(boardChart.getType(), boardChart.getBoardName());
		}
		
		return map.asMap();
	}

}
