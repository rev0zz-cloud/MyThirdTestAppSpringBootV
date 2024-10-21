package ru.arkhipov.MyThirdTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.arkhipov.MyThirdTestAppSpringBoot.exception.ValidationFailedException;

@Service
public interface ValidationService {
    void isValid(BindingResult bindingResult) throws ValidationFailedException;
}
