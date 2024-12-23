package tech.powerjob.server.core.scheduler.auxiliary.impl;

import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;
import tech.powerjob.common.PowerJobDKey;
import tech.powerjob.common.enums.TimeExpressionType;
import tech.powerjob.common.exception.PowerJobException;
import tech.powerjob.common.utils.PropertyUtils;
import tech.powerjob.server.core.scheduler.auxiliary.AbstractTimingStrategyHandler;

import javax.annotation.Resource;
import java.time.Duration;

/**
 * @author Echo009
 * @since 2022/3/22
 */
@Component
public class FixedDelayTimingStrategyHandler extends AbstractTimingStrategyHandler {

    @Override
    public void validate(String timeExpression) {
        long delay;

        try {
            Duration duration = ApplicationConversionService.getSharedInstance().convert(timeExpression, Duration.class);
            delay = duration.toMillis();
        } catch (Exception e) {
            throw new PowerJobException("invalid timeExpression!");
        }
        // 默认 120s ，超过这个限制应该考虑使用其他类型以减少资源占用
//        int maxInterval = Integer.parseInt(PropertyUtils.readProperty(PowerJobDKey.FREQUENCY_JOB_MAX_INTERVAL, "120000"));
//        if (delay > maxInterval) {
//            throw new PowerJobException("the delay must be less than " + maxInterval + "ms");
//        }
        if (delay <= 0) {
            throw new PowerJobException("the delay must be greater than 0 ms");
        }
    }

    @Override
    public Long calculateNextTriggerTime(Long preTriggerTime, String timeExpression, Long startTime, Long endTime) {
        Long interval = ApplicationConversionService.getSharedInstance().convert(timeExpression, Duration.class).toMillis();
        long r = startTime != null && startTime > preTriggerTime
                ? startTime : preTriggerTime + interval;
        return endTime != null && endTime < r ? null : r;
    }

    @Override
    public TimeExpressionType supportType() {
        return TimeExpressionType.FIXED_DELAY;
    }
}
