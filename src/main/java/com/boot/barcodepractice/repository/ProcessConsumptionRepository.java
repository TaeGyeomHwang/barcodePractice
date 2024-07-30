package com.boot.barcodepractice.repository;

import com.boot.barcodepractice.entity.ProcessConsumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessConsumptionRepository extends JpaRepository<ProcessConsumption, Long> {
    @Query("SELECT p.processName, SUM(p.processConsumptions) " +
            "FROM ProcessConsumption p " +
            "WHERE MONTH(p.dateTime) = MONTH(CURRENT_DATE) " +
            "AND YEAR(p.dateTime) = YEAR(CURRENT_DATE) " +
            "GROUP BY p.processName")
    List<Object[]> findCurrentMonthConsumption();

    @Query("SELECT p.processName, SUM(p.processConsumptions) " +
            "FROM ProcessConsumption p " +
            "WHERE MONTH(p.dateTime) = MONTH(CURRENT_DATE) - 1 " +
            "AND YEAR(p.dateTime) = YEAR(CURRENT_DATE) " +
            "GROUP BY p.processName")
    List<Object[]> findLastMonthConsumption();
}
