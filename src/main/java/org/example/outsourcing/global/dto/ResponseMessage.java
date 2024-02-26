package org.example.outsourcing.global.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
public class ResponseMessage {

    private int httpValue;
    private Object message;
}
