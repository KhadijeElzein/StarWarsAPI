package br.com.khadije.zein.starwarsapi.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(InvalidFormatException.class)
	public ResponseEntity<Object>  invalidFormatExceptionHandler(InvalidFormatException ex) {
		 ApiError apiException = new ApiError(HttpStatus.PRECONDITION_FAILED,"Tipo de dado inválido");
		 return new ResponseEntity<>(apiException, apiException.getStatus());
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object>  illegalArgumentExpcetionHandler(IllegalArgumentException ex) {
		 ApiError apiException = new ApiError(HttpStatus.PRECONDITION_FAILED,"Campos são obrigatórios");
		 return new ResponseEntity<>(apiException, apiException.getStatus());
	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Object>  BusinessExpcetionHandler(BusinessException ex) {
		 ApiError apiException = new ApiError(HttpStatus.BAD_REQUEST,ex.getMensagem());
		 return new ResponseEntity<>(apiException, apiException.getStatus());
	}
	
	@ExceptionHandler(NoContentException.class)
	public ResponseEntity<Object>  NoContentExpcetionHandler(NoContentException ex) {
		 ApiError apiException = new ApiError(HttpStatus.NO_CONTENT,ex.getMensagem());
		 return new ResponseEntity<>(apiException, apiException.getStatus());
	}

}