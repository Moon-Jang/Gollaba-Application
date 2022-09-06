package kr.mj.gollaba.exception;

import kr.mj.gollaba.common.ErrorAPIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<ErrorAPIResponse> handleException(HttpServletRequest request, Exception e) {
		return GollabaException.getResult(e);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorAPIResponse> handleValidationExceptions(HttpServletRequest request, MethodArgumentNotValidException e) {
		return new ResponseEntity<>(new ErrorAPIResponse(e), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BindException.class)
	public ResponseEntity<ErrorAPIResponse> handleBindingExceptions(HttpServletRequest request, BindException e) {
		return new ResponseEntity<>(new ErrorAPIResponse(e), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<ErrorAPIResponse> handleHttpMediaTypeNotSupportedExceptions(HttpServletRequest request, HttpMediaTypeNotSupportedException e) {
		return new ResponseEntity<>(new ErrorAPIResponse(GollabaErrorCode.NOT_SUPPORTED_HTTP_MEDIA_TYPE), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorAPIResponse> handleAccessDeniedExceptions(HttpServletRequest request, AccessDeniedException e) {
		return new ResponseEntity<>(new ErrorAPIResponse(GollabaErrorCode.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
	}
}
