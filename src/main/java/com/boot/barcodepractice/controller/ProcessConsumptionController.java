package com.boot.barcodepractice.controller;

import com.boot.barcodepractice.entity.CalendarEvent;
import com.boot.barcodepractice.entity.ProcessConsumption;
import com.boot.barcodepractice.service.ProcessConsumptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/charts")
public class ProcessConsumptionController {
    private final ProcessConsumptionService processConsumptionService;

    @PostMapping
    public ProcessConsumption createProcessConsumption(@RequestBody ProcessConsumption processConsumption) {
        return processConsumptionService.saveProcessConsumption(processConsumption);
    }

    @GetMapping("/consumptions")
    public Map<String, List<Object[]>> getConsumptions() {
        Map<String, List<Object[]>> response = new HashMap<>();
        response.put("currentMonth", processConsumptionService.getCurrentMonthConsumption());
        response.put("lastMonth", processConsumptionService.getLastMonthConsumption());
        return response;
    }
}
