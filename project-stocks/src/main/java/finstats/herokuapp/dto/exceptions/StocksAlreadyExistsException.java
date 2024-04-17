package finstats.herokuapp.dto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StocksAlreadyExistsException extends RuntimeException{
	private static final long serialVersionUID = -4866981786687953526L;

	

}
