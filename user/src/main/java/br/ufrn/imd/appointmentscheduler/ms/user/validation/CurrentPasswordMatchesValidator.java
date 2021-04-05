package br.ufrn.imd.appointmentscheduler.ms.user.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;

import br.ufrn.imd.appointmentscheduler.ms.user.entity.user.User;
import br.ufrn.imd.appointmentscheduler.ms.user.model.ChangePasswordForm;
import br.ufrn.imd.appointmentscheduler.ms.user.service.UserService;

public class CurrentPasswordMatchesValidator implements ConstraintValidator<CurrentPasswordMatches, Object> {

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Override
    public void initialize(final CurrentPasswordMatches constraintAnnotation) {
    }

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return false;
	}

//    @Override
//    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
//        ChangePasswordForm form = (ChangePasswordForm) obj;
//        boolean isValid = false;
//        User user = userService.getUserById(form.getId());
//        if (passwordEncoder.matches(form.getCurrentPassword(), user.getPassword())) {
//            isValid = true;
//        }
//        if (!isValid) {
//            context.disableDefaultConstraintViolation();
//            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
//                    .addPropertyNode("currentPassword").addConstraintViolation();
//        }
//        return isValid;
//    }
}
