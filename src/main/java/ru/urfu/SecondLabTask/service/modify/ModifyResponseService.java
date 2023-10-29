package ru.urfu.SecondLabTask.service.modify;

import org.springframework.stereotype.Service;
import ru.urfu.SecondLabTask.models.Response;

@Service
public interface ModifyResponseService {

    Response modify(Response response);
}
