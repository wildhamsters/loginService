package org.wildhamsters.loginservice;

import java.time.LocalDateTime;

/**
 * A data transfer object using for sending statistics to the front end.
 *
 * @author Piotr Chowaniec
 */
record SingleMatchStatistics(Integer id,
                             String matchId,
                             int accurateShots,
                             int missedShots,
                             int rounds,
                             LocalDateTime startTime,
                             LocalDateTime finishTime) {
}
