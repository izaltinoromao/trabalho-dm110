package br.inatel.dm110.api;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuditMessageTO {

    private LocalDateTime timeStamp;
    private String message;
}
