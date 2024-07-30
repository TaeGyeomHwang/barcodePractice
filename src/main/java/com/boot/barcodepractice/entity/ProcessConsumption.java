package com.boot.barcodepractice.entity;

import com.boot.barcodepractice.constant.ProcessName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "process_consumption")
@Getter
@Setter
public class ProcessConsumption {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long processNo;

    @Column(nullable = false)
    private ProcessName processName;

    @Column(nullable = false)
    private Long processConsumptions;

    @Column(nullable = false)
    private LocalDateTime dateTime;
}
