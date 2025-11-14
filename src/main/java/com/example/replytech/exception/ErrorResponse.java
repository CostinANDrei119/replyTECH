package com.example.replytech.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * ErrorResponse - Standard error response structure
 * 
 * Used by global exception handler to return consistent error
 * messages to API clients with timestamp and HTTP status.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {

    /**
     * HTTP status code
     */
    private int status;

    /**
     * Error message
     */
    private String message;

    /**
     * Detailed error description
     */
    private String details;

    /**
     * Timestamp when error occurred
     */
    private LocalDateTime timestamp;

    /**
     * API path that caused the error
     */
    private String path;
}
