package finstats.herokuapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class PeriodDto {
	
	 String source;
	 String fromData;
	 String toData;
	 
	 public PeriodDto (String source, String fromData, String toData) {
		 
		 this.source=source;
		 this.fromData=fromData;
		 this.toData=toData;
	 }

}
