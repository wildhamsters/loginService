package org.wildhamsters.loginservice;

import java.util.List;

/**
 * A data transfer object for a list of the SingleMatchStatistics using for receiving data
 * from the statistics microservice.
 *
 * @author Piotr Chowaniec
 */
record StatisticsDTO(List<SingleMatchStatistics> singleMatchStatisticsList) {}
