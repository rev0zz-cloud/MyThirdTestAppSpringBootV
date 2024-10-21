package ru.arkhipov.MyThirdTestAppSpringBoot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.arkhipov.MyThirdTestAppSpringBoot.model.Response;
import ru.arkhipov.MyThirdTestAppSpringBoot.util.DateTimeUtil;

import java.util.Date;

@Slf4j
@Service
@Qualifier("ModifySystemTimeResponseService")

public class ModifySystemTimeResponseService
    implements ModifyResponseService {

        @Override
        public Response modify(Response response) {

            response.setSystemTime(DateTimeUtil.getCustomFormat().format(new Date()));
            log.info("Updated systemTime to: {}", response.getSystemTime());
            return response;
    }
}
