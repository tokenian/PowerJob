package tech.powerjob.server.remote.worker.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import tech.powerjob.server.common.module.WorkerInfo;
import tech.powerjob.server.persistence.remote.model.JobInfoDO;

/**
 * 按照微服务来分配执行地址。
 * @author tokenian
 * @date 2023/10/19 16:34
 */
@Slf4j
@Component
public class MicroServiceFilter implements WorkerFilter {
    @Override
    public boolean filter(WorkerInfo workerInfo, JobInfoDO jobInfoDO) {
        if(StringUtils.hasText(jobInfoDO.getServiceName())) {
            return !workerInfo.getServiceName().equals(jobInfoDO.getServiceName());
        }

        return false;
    }
}
