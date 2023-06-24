package com.employeeregistry.handlers;

import com.employeeregistry.controllers.EmployeeRegistryController;
import com.employeeregistry.exceptions.EmployeeExistsException;
import com.employeeregistry.exceptions.EmployeeNotFoundException;
import com.employeeregistry.exceptions.UnAuthorizedException;
import com.employeeregistry.exceptions.UserExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice(basePackageClasses = EmployeeRegistryController.class)
@RestController
public class RegistryExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(RegistryExceptionHandler.class);

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnAuthorizedException(UnAuthorizedException ex, WebRequest request) {
        logger.info("Exception handled at 401");
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED, ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler({EmployeeExistsException.class, UserExistsException.class, Exception.class})
    public ResponseEntity<ErrorResponse> handleInternalServerError(Exception ex, WebRequest request) {
        logger.error("Exception handled at 500", ex);
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoContentError(EmployeeNotFoundException ex, WebRequest request) {
        logger.info("Exception handled at 204");
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    private static class ErrorResponse {
        private final int statusCode;
        private final String error;
        private final String message;
        private final LocalDateTime timestamp;

        public ErrorResponse(HttpStatus status, String message) {
            this.statusCode = status.value();
            this.error = status.getReasonPhrase();
            this.message = message;
            this.timestamp = LocalDateTime.now();
        }

        public int getStatusCode() {
            return statusCode;
        }

        public String getError() {
            return error;
        }

        public String getMessage() {
            return message;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }
    }
}
