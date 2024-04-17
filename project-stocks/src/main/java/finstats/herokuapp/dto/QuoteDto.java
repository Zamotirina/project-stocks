package finstats.herokuapp.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuoteDto {

	LocalDate date;
	Double open;
	Double high;
	Double low;
	Double close;
	Double AdjClose;
	Long volume;
	
}
