package ru.mezgin.tracker.util;

import ru.mezgin.tracker.model.Status;

import java.util.List;

import static java.time.ZoneOffset.UTC;

/**
 * The class CalculationTimeWorked.
 *
 * @author Alexander Mezgin
 * @version 1.0
 * @since 17.04.2018
 */
public class CalculationTimeWorked {

    /**
     * Calculate the the elapsed time.
     *
     * @param statusList the list of users statuses.
     * @return the elapsed time
     */
    public static Long calculate(List<Status> statusList) {
        long fullTime = 0;
        if (statusList != null) {
            long startWork = 0;
            long endWork = 0;
            int index = 0;
            while (index < statusList.size()) {
                if (statusList.get(index).getName().equals("Отработал") && !statusList.get(index + 1).getName().equals("Ушёл")) {
                    endWork = statusList.get(index).getDateTime().atZone(UTC).toInstant().toEpochMilli();
                    startWork = statusList.get(index + 1).getDateTime().atZone(UTC).toInstant().toEpochMilli();
                    index += 2;
                } else if (statusList.get(index).getName().equals("Отработал") && statusList.get(index + 1).getName().equals("Ушёл")) {
                    endWork = statusList.get(index + 1).getDateTime().atZone(UTC).toInstant().toEpochMilli();
                    startWork = statusList.get(index + 2).getDateTime().atZone(UTC).toInstant().toEpochMilli();
                    index += 3;
                } else {
                    endWork = statusList.get(index).getDateTime().atZone(UTC).toInstant().toEpochMilli();
                    startWork = statusList.get(index + 1).getDateTime().atZone(UTC).toInstant().toEpochMilli();
                    index += 2;
                }
                fullTime += endWork - startWork;
            }
        }
        return fullTime;
    }
}
