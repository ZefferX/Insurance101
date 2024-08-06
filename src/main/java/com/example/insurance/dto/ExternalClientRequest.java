package com.example.insurance.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public record ExternalClientRequest(
        @JsonProperty("id")Integer id,
        @JsonProperty("money")Integer money,
        @JsonProperty("age")Integer age
)  {

}
