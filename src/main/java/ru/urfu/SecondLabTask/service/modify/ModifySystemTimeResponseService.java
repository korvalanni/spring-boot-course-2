package ru.urfu.SecondLabTask.service.modify;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.urfu.SecondLabTask.models.Response;
import ru.urfu.SecondLabTask.utils.DateTimeUtil;

import java.util.Date;

@Slf4j
@Service
@Qualifier("ModifyOperationSystemTimeResponseService")
public class ModifySystemTimeResponseService implements ModifyResponseService {
    @Override
    public Response modify(Response response) {
        String systemTimeBeforeUpdate = response.getSystemTime();
        response.setSystemTime(DateTimeUtil.getCustomFormat().format(new Date()));
        log.info("Modified system time response from {} to {}",
                systemTimeBeforeUpdate, response.getSystemTime());

        return response;
    }
}
