package org.wildhamsters.loginservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Mariusz Bal
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping
    ResponseEntity<List<SingleMatchStatistics>> readAllStatistics() {
        return ResponseEntity.ok(statisticsService.findAllStatistics());
    }
}
