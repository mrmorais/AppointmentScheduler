package br.ufrn.imd.appointmentscheduler.ms.user.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufrn.imd.appointmentscheduler.ms.user.entity.user.User;
import br.ufrn.imd.appointmentscheduler.ms.user.service.UserService;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, Object> {

    @Autowired
    private UserService userService;

    @Override
    public void initialize(final UniqueUsername constraintAnnotation) {
    }

    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        String userName = (String) obj;
        User user = userService.getUserByUsername(userName);
        return user == null;
    }

}