package org.example.outsourcing.global.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseMessage {

    private int httpValue;
    private String message;
}
