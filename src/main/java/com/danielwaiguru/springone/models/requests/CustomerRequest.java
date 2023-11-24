package com.danielwaiguru.springone.models.requests;

public record CustomerRequest(
        String name,
        String email,
        Integer age
) {
}
