package org.wildhamsters.loginservice;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Handling connection to statistics microservice for reading data.
 *
 * @author Piotr Chowaniec
 */
@Service
public class StatisticsService {

    // @Value("${url.statistics}")
    // private String statisticsUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Gets a list of all statistics from the statistics microservice.
     *
     * @return list of match statistics.
     */
    @SuppressFBWarnings(
            value = "NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE",
            justification = "SpotBugs indicates NPE which is handled with Optional"
    )
    List<SingleMatchStatistics> findAllStatistics() {
        Optional<List<SingleMatchStatistics>> statistics =
                Optional.of(restTemplate.getForObject("http://stats:5500/", StatisticsDTO.class).singleMatchStatisticsList());
        return statistics.orElseGet(ArrayList::new);
    }
}
