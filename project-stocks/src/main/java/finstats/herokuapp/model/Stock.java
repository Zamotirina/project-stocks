package finstats.herokuapp.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;


@Getter
@EqualsAndHashCode(of = "index")
@Document(collection = "stocks")

public class Stock {
	

	@Id
	String index;
	String yohoofinance;
	TreeSet<Quote> quotes;

	public Stock() {
		
		this.quotes=new TreeSet();
	}


public void addQuote(Quote quote) {
	
	quotes.add(quote);
}

}


