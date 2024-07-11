package com.iapes.iapes.controller.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id","username","message", "status", "jwt"})
public record AuthResponse(
		Integer id,
        String username,
        String message,
        String jwt,
        Boolean status) {
}
