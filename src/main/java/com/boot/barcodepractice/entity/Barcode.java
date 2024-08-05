package com.boot.barcodepractice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "barcode")
@Getter
@Setter
public class Barcode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long barcodeNo;

    @Column(nullable = false)
    private String name;
}
