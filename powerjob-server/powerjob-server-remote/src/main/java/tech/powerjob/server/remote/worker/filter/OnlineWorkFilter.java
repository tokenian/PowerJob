package tech.powerjob.server.remote.worker.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tech.powerjob.server.common.module.WorkerInfo;
import tech.powerjob.server.persistence.remote.model.JobInfoDO;

/**
 * 过滤人工操作的下线服务器
 * @author tokenian
 * @date 2023/10/20 11:14
 */
@Slf4j
@Component
public class OnlineWorkFilter implements WorkerFilter{
    @Override
    public boolean filter(WorkerInfo workerInfo, JobInfoDO jobInfoDO) {
        return !workerInfo.isOnline();
    }
}
