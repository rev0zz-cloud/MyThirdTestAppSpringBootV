package ru.arkhipov.MyThirdTestAppSpringBoot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.arkhipov.MyThirdTestAppSpringBoot.exception.ValidationFailedException;

@Slf4j
@Service
public class RequestValidationService implements ValidationService {

    @Override
    public void isValid(BindingResult bindingResult) throws ValidationFailedException {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            log.error("Validation errors: {}", errorMessage);  // Логируем ошибку
            throw new ValidationFailedException(errorMessage);
        }
    }
}
