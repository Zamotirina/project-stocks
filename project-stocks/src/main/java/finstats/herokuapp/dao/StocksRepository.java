package finstats.herokuapp.dao;

import org.springframework.data.repository.CrudRepository;

import finstats.herokuapp.model.Stock;

public interface StocksRepository extends CrudRepository<Stock, String> {
	


}
