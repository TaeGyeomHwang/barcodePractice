package com.boot.barcodepractice.service;

import com.boot.barcodepractice.entity.ProcessConsumption;
import com.boot.barcodepractice.repository.ProcessConsumptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcessConsumptionService {
    private final ProcessConsumptionRepository processConsumptionRepository;
    public ProcessConsumption saveProcessConsumption(ProcessConsumption processConsumption) {
        return processConsumptionRepository.save(processConsumption);
    }

    public List<Object[]> getCurrentMonthConsumption() {
        return processConsumptionRepository.findCurrentMonthConsumption();
    }

    public List<Object[]> getLastMonthConsumption() {
        return processConsumptionRepository.findLastMonthConsumption();
    }
}
