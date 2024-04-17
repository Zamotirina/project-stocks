package finstats.herokuapp.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsDto {

	
	Date from;
    Date to;
    String source;
    String type;
    double max;
    double mean;
    double median;
    double min;
    double std;
 
}
