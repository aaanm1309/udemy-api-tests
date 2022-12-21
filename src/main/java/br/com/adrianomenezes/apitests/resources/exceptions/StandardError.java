package br.com.adrianomenezes.apitests.resources.exceptions;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class StandardError {
    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String path;
}
