package br.com.brasilprev.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class DesafioRsiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String userMessage = messageSource.getMessage("invalid.message", null, LocaleContextHolder.getLocale());
		String exception = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
		return handleExceptionInternal(ex, new Error(userMessage, exception), headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<Error> errorList = createErrorList(ex.getBindingResult());
		return handleExceptionInternal(ex, errorList, headers, status, request);
	}
	
	@ExceptionHandler({EmptyResultDataAccessException.class})
	protected ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
		String userMessage = messageSource.getMessage("resource.not-found", null, LocaleContextHolder.getLocale());
		String exception = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
		return handleExceptionInternal(ex, new Error(userMessage, exception), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler({DataIntegrityViolationException.class})
	protected ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
		String userMessage = messageSource.getMessage("resource.operation-not-allowed", null, LocaleContextHolder.getLocale());
		String exception = ExceptionUtils.getRootCauseMessage(ex); 
		return handleExceptionInternal(ex, new Error(userMessage, exception), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({CostumerUnavailableException.class})
	protected ResponseEntity<Object> handleCostumerUnavailableException(CostumerUnavailableException ex) {
		String userMessage = messageSource.getMessage("costumer.unavailable", null, LocaleContextHolder.getLocale());
		String exception = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
		return ResponseEntity.badRequest().body(Arrays.asList(new Error(userMessage, exception)));
	}
	
	private List<Error> createErrorList(BindingResult bindingResult){
		List<Error> errors = new ArrayList<Error>();
		for (FieldError fieldError: bindingResult.getFieldErrors()) {
			String userMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String exception = fieldError.toString();
			errors.add(new Error(userMessage, exception));
		}
		return errors;
	}
	
	
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Error {
		private String userMessage;
		private String exception;
	}

}
