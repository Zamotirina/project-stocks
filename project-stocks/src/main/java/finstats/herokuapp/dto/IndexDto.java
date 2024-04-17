package finstats.herokuapp.dto;



import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class IndexDto {
	
	String source;
	String yohoofinance;



public IndexDto (String ind, String html) {
	

	this.source=ind;
	this.yohoofinance="https://finance.yahoo.com/quote/"+html+"/history?p="+html+"";
	
}
}