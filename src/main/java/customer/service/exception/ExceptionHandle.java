package customer.service.exception;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import customer.service.common.Constants;


@RestControllerAdvice
public class ExceptionHandle extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandle.class);

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse(Constants.FAILED, errors.get(0), request.getDescription(false)));
	}

	@ExceptionHandler(value = { ConstraintViolationException.class })
	public ResponseEntity<Object> handleResourceNotFoundException(ConstraintViolationException e, WebRequest request) {
		Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
		List<ConstraintViolation<?>> errors = constraintViolations.stream().collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
				new ErrorResponse(Constants.FAILED, errors.get(0).getMessage(), request.getDescription(false)));
	}

	private static ResponseEntity<ErrorResponse> sendResponse(RuntimeException exception, WebRequest request,
			HttpStatus status) {
		LOGGER.error("Exception cause: " + exception.getMessage());
		ErrorResponse errorResponse = new ErrorResponse(Constants.FAILED, exception.getLocalizedMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorResponse, status);

	}

}