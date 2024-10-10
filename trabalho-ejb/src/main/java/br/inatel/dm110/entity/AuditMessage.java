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

    public AuditMessage(LocalDateTime timeStamp, String message) {
        this.timeStamp = timeStamp;
        this.message = message;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private LocalDateTime timeStamp;
    private String message;
}
