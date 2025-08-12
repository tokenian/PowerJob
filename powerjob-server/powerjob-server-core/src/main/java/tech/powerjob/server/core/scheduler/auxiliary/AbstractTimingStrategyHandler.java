package tech.powerjob.server.core.scheduler.auxiliary;


import org.springframework.boot.convert.ApplicationConversionService;

import java.time.Duration;

/**
 * @author Echo009
 * @since 2022/3/22
 */
public abstract class AbstractTimingStrategyHandler implements TimingStrategyHandler {
    @Override
    public void validate(String timeExpression) {
        // do nothing
    }

    @Override
    public Long calculateNextTriggerTime(Long preTriggerTime, String timeExpression, Long startTime, Long endTime) {
        // do nothing
        return null;
    }

    public static long convertSpringTime(String timeExpression) {
        Duration duration = ApplicationConversionService.getSharedInstance().convert(timeExpression, Duration.class);
        return duration.toMillis();
    }

}
