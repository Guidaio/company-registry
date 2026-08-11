package com.itau.company_registry.exception;

import java.time.OffsetDateTime;

public record ErrorResponse(String message, int status, OffsetDateTime timestamp) { }