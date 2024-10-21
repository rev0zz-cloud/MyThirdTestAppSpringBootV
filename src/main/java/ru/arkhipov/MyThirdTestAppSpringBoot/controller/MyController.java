package ru.arkhipov.MyThirdTestAppSpringBoot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.arkhipov.MyThirdTestAppSpringBoot.exception.ValidationFailedException;
import ru.arkhipov.MyThirdTestAppSpringBoot.model.*;
import ru.arkhipov.MyThirdTestAppSpringBoot.service.ModifyResponseService;
import ru.arkhipov.MyThirdTestAppSpringBoot.service.ValidationService;
import ru.arkhipov.MyThirdTestAppSpringBoot.util.DateTimeUtil;

import javax.validation.Valid;
import java.util.Date;

@Slf4j
@RestController
public class MyController {

    private final ValidationService validationService;
    private final ModifyResponseService modifySystemTimeResponseService;
    private final ModifyResponseService modifyOperationUidResponseService;

    @Autowired
    public MyController(ValidationService validationService,
                        @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifySystemTimeResponseService,
                        @Qualifier("ModifyOperationUidResponseService") ModifyResponseService modifyOperationUidResponseService) {
        this.validationService = validationService;
        this.modifySystemTimeResponseService = modifySystemTimeResponseService;
        this.modifyOperationUidResponseService = modifyOperationUidResponseService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request, BindingResult bindingResult) {
        log.info("Received request: {}", request);

        long currentTime = System.currentTimeMillis();
        // Вычисляем разницу во времени
        long timeDifference = currentTime - request.getRequestTime();
        log.info("Разница во времени обработки Service 1 и Service 2: {} ms", timeDifference);

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();

        try {
            validationService.isValid(bindingResult);
            log.info("Request is valid");
        } catch (ValidationFailedException e) {
            log.error("Validation failed: {}", e.getMessage());
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNSUPPORTED_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNKNOWN);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Unexpected error: {}", e.getMessage());
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNSUPPORTED_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNKNOWN);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        modifySystemTimeResponseService.modify(response);  // Обновляем systemTime
        log.info("Updated systemTime in response: {}", response.getSystemTime());

        modifyOperationUidResponseService.modify(response);  // Обновляем operationUid
        log.info("Updated operationUid in response: {}", response.getOperationUid());

        log.info("Created response: {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
