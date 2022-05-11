package org.example.jwt.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HistoryRequest {

    private String userName;

    private Long countMessages;

}
