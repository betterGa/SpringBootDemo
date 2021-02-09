package com.jia.SpringBootDemo.Component;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.InitializingBean;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.ValidatorFactory;
import java.util.Set;


public class BeanValidator<T> implements Validator<T>, InitializingBean {
    javax.validation.Validator validator;


    @Override
    public void validate(T t) {
        Set<ConstraintViolation<T>> constraintViolationSet = validator.validate(t);
        if (constraintViolationSet.size() > 0) {
            StringBuilder message = new StringBuilder();
            for (ConstraintViolation<T> constraintViolationSets : constraintViolationSet) {
                message.append(constraintViolationSets.getMessage() + "\n");
            }
            throw new ValidationException(message.toString());
        }
    }

    // 这个方法将在所有的属性被初始化后调用
    @Override
    public void afterPropertiesSet() throws Exception {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.usingContext().getValidator();
    }
}
