package finstats.herokuapp.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.PublicKey;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import javax.swing.text.DateFormatter;

import org.modelmapper.ModelMapper;import org.modelmapper.internal.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.aop.target.AbstractPoolingTargetSource;
import org.springframework.stereotype.Service;

import com.mongodb.client.model.Indexes;

import finstats.herokuapp.dao.StocksRepository;
import finstats.herokuapp.dto.IndexDto;
import finstats.herokuapp.dto.PeriodBetweenDto;
import finstats.herokuapp.dto.PeriodDto;
import finstats.herokuapp.dto.StatisticsDto;
import finstats.herokuapp.dto.StockDto;
import finstats.herokuapp.dto.exceptions.StockNotFoundException;
import finstats.herokuapp.dto.exceptions.StocksAlreadyExistsException;
import finstats.herokuapp.model.Quote;
import finstats.herokuapp.model.Stock;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class StocksServiceimpl implements StocksService {
	
	final StocksRepository stocksRepository;
	final ModelMapper modelMapper;


	@Override
	public List<IndexDto> addIndex(Map<String, String> indexes) {
		
		
		List<IndexDto> indexesAdded = indexes.entrySet().stream().filter(m->checkIndexInBase(m.getValue())).
				map(x-> new IndexDto(x.getKey(), x.getValue())).toList();
		
		if(indexesAdded.isEmpty()) {
			
			throw new StocksAlreadyExistsException();
		}
	
		List <StockDto> stocksDtos = indexesAdded.stream().map(x->new StockDto(x)).toList();
		
		stocksRepository.saveAll(stocksDtos.stream().map(x->modelMapper.map(x, Stock.class)).toList());
		
		return indexesAdded;
	}
	
	public boolean checkIndexInBase(String index) {
		
		
		if(stocksRepository.existsById(index)) {
			
			return false;
		}
		
		return true;
	}
	
	@Override
	public boolean addHistory(String index, String scv) {
		
	Stock stock=stocksRepository.findById(index).orElseThrow(StockNotFoundException::new);
		
		
		try(BufferedReader br = new BufferedReader(new FileReader(scv))) {
			
			String str = br.readLine();
			
			String [] cells = str.split(",");
		
//			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			
			
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
			
		java.util.Date date;
			double open;
			double high;
			double low;
			double close;
			double AdjClose;
			long volume;
		

			str = br.readLine();
			
			while(str!=null) {
				
				cells = str.split(",");
	
		
					date = dateFormatter.parse(cells[0]);
		
				open=Double.parseDouble(cells[1]);
				high=Double.parseDouble(cells[2]);
				low=Double.parseDouble(cells[3]);
				close=Double.parseDouble(cells[4]);
				AdjClose=Double.parseDouble(cells[5]);
				volume=Long.parseLong(cells[6]);
				
				Quote quote= new Quote(date,open,high,low,close,AdjClose,volume);

			
				stock.addQuote(quote);
				stocksRepository.save(stock);
				str = br.readLine();
			
			}
		br.close();
		
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
		return true;
	}

	@Override
	public PeriodDto getTimeHistory(String index) {
		
		Stock stock=stocksRepository.findById(index).orElseThrow(StockNotFoundException::new);
		
		Quote firstQuote=stock.getQuotes().first();
		Quote lastQuote=stock.getQuotes().last();
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		
		PeriodDto periodDto=new PeriodDto(stock.getIndex(), dateFormatter.format(firstQuote.getDate()),dateFormatter.format(lastQuote.getDate()));
		
		return  periodDto;
		
	}

	@Override
	public Iterable<String> getIndexes() {
		
		List <String> indexes= new ArrayList();
		
		stocksRepository.findAll().forEach(x->indexes.add(x.getIndex()));
		
		return indexes;
	
	}

	@Override
	public StatisticsDto getStatisticsForPeriod(PeriodBetweenDto periodBetweenDto) {
		
		Stock stock=stocksRepository.findById(periodBetweenDto.getIndex()).orElseThrow(StockNotFoundException::new);
		
		return null;
	}



}
