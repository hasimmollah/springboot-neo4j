package com.hasim.springboot.neo4j.example.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCodes {
    APPLICATION_EXCEPTION(
            "ERR_001",
            "Exception has occurred, please contact system admin"
    ),
    ENTITY_NOT_FOUND_EXCEPTION(
            "ERR_002",
            "not found"
    );

    private final String code;
    private final String description;
}
