package finstats.herokuapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import finstats.herokuapp.dto.IndexDto;
import finstats.herokuapp.dto.IndexesDto;
import finstats.herokuapp.dto.PeriodBetweenDto;
import finstats.herokuapp.dto.PeriodDto;
import finstats.herokuapp.dto.StatisticsDto;
import finstats.herokuapp.service.StocksService;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/communication")
public class StocksController {

	
	final StocksService stocksService;
	
	@PostMapping("/parser/addIndex")
	public List <IndexDto> addIndex(@RequestBody IndexesDto indexToHTML) {
		return stocksService.addIndex(indexToHTML.getIndexToHTML());
	}
	
	@PostMapping("/parser/{indexForHistory}/{csv}")
	public boolean addHistory(@PathVariable String indexForHistory, @PathVariable String csv) {
		return stocksService.addHistory(indexForHistory, csv);
	}
	
	@GetMapping("/index/{indexForHistory}")
	public PeriodDto getTimeHistory(@PathVariable String indexForHistory) {
		return stocksService.getTimeHistory(indexForHistory);
	}
	
	@GetMapping("/indexes")
	public Iterable <String> getIndexes() {
		return stocksService.getIndexes();
	}
	
	@PostMapping("/index")
	public StatisticsDto getStatisticsForPeriod(@RequestBody PeriodBetweenDto periodBetweenDto) {
		return stocksService.getStatisticsForPeriod(periodBetweenDto);
	}
}
