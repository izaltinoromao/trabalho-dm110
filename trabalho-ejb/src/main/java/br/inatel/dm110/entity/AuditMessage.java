package br.inatel.dm110.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "AUDIT_MESSAGE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuditMessage {

    public AuditMessage(String productCode, String operation, LocalDateTime timeStamp) {
        this.productCode = productCode;
        this.operation = operation;
        this.timeStamp = timeStamp;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String productCode;
    private String operation;
    private LocalDateTime timeStamp;
}
