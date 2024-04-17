package finstats.herokuapp.service
;

import java.util.List;
import java.util.Map;

import finstats.herokuapp.dto.IndexDto;
import finstats.herokuapp.dto.PeriodBetweenDto;
import finstats.herokuapp.dto.PeriodDto;
import finstats.herokuapp.dto.StatisticsDto;

public interface StocksService {



	List<IndexDto> addIndex(Map<String, String> indexes);

	boolean addHistory(String index, String scv);

	PeriodDto getTimeHistory(String indexForHistory);

	Iterable<String> getIndexes();

	StatisticsDto getStatisticsForPeriod(PeriodBetweenDto periodBetweenDto);


}
