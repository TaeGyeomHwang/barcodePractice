package com.boot.barcodepractice.repository;

import com.boot.barcodepractice.entity.Barcode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarcodeRepository extends JpaRepository<Barcode, Long> {
}
