package com.ggctech.emilia.exceptions;
import com.ggctech.emilia.model.dtos.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /* =====================
       401 - NÃO AUTENTICADO
       ===================== */
    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleUnauthenticated(
            AuthenticationCredentialsNotFoundException ex,
            HttpServletRequest request
    ) {
        return build(HttpStatus.UNAUTHORIZED, "Unauthorized", "Authentication required", request);
    }

    /* =====================
       403 - NÃO AUTORIZADO
       ===================== */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiErrorResponse> handleAccessDenied(
            AccessDeniedException ex,
            HttpServletRequest request
    ) {
        return build(HttpStatus.FORBIDDEN, "Forbidden", ex.getMessage(), request);
    }

    /* =====================
       404 - NÃO ENCONTRADO
       ===================== */
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiErrorResponse> handleResponseStatus(
            ResponseStatusException ex,
            HttpServletRequest request
    ) {
        return build(
                HttpStatus.valueOf(ex.getStatusCode().value()),
                ex.getStatusCode().toString(),
                ex.getReason(),
                request
        );
    }

    /* =====================
       409 - CONFLITO
       ===================== */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiErrorResponse> handleConflict(
            DataIntegrityViolationException ex,
            HttpServletRequest request
    ) {
        return build(
                HttpStatus.CONFLICT,
                "Conflict",
                "Resource already exists",
                request
        );
    }

    /* =====================
       400 - VALIDAÇÃO
       ===================== */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .orElse("Validation error");

        return build(HttpStatus.BAD_REQUEST, "Bad Request", message, request);
    }

    /* =====================
       500 - ERRO GENÉRICO
       ===================== */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiErrorResponse> handleRuntime(
            RuntimeException ex,
            HttpServletRequest request
    ) {
        return build(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal Server Error",
                ex.getMessage(),
                request
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGeneric(
            Exception ex,
            HttpServletRequest request
    ) {
        return build(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal Server Error",
                "Unexpected error occurred",
                request
        );
    }

    /* =====================
       BUILDER
       ===================== */
    private ResponseEntity<ApiErrorResponse> build(
            HttpStatus status,
            String error,
            String message,
            HttpServletRequest request
    ) {
        return ResponseEntity.status(status)
                .body(new ApiErrorResponse(
                        status.value(),
                        error,
                        message,
                        request.getRequestURI(),
                        LocalDateTime.now()
                ));
    }
}
