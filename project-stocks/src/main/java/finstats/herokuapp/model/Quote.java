package finstats.herokuapp.model;


import java.time.LocalDate;
import java.util.Date;

import javax.print.attribute.standard.MediaSize.Other;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode(of="date")
@NoArgsConstructor
@AllArgsConstructor
public class Quote implements Comparable <Quote>{
	
	Date date;
	Double open;
	Double high;
	Double low;
	Double close;
	Double adjClose;
	Long volume;
	@Override
	public int compareTo(Quote other) {
		
		return this.getDate().compareTo(other.getDate());
	}
	
	




}
