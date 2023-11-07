package ru.urfu.SecondLabTask.service.modify;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.urfu.SecondLabTask.models.Response;


import java.util.UUID;

@Slf4j
@Service
@Qualifier("ModifyOperationUidResponseService")
public class ModifyUuidResponseService implements ModifyResponseService {
    @Override
    public Response modify(Response response) {
        String operationUidBeforeUpdate = response.getOperationUid();
        UUID uuid = UUID.randomUUID();
        response.setOperationUid(uuid.toString());
        log.info("Modified operation uuid response from {} to {}",
                operationUidBeforeUpdate, response.getOperationUid());
        return response;
    }
}
