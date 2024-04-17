package finstats.herokuapp.dto;

import java.util.List;
import java.util.Set;

import finstats.herokuapp.model.Quote;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StockDto {

	String index;
	String yohoofinance;

	Set<Quote> quotes;
	
	public StockDto (IndexDto indexDto) {
		
		this.index=indexDto.getSource();
	
		this.yohoofinance=indexDto.getYohoofinance();
	}

}
