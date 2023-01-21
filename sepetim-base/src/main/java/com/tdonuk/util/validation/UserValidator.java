package com.tdonuk.util.validation;

import com.tdonuk.dto.domain.user.Name;
import com.tdonuk.dto.domain.user.UserDTO;
import com.tdonuk.exception.ValidationException;

public class UserValidator implements Validator {
    private UserDTO user;

    private boolean isValid = false;
    public UserValidator(UserDTO user) {
        this.user = user;
    }

    public UserValidator validate() {
        try {
            validateEmail(user.getEmail());
            validatePassword(user.getPassword());
            validatePhone(user.getPhone());
            validateName(user.getName());
        } catch(ValidationException e) {
            this.isValid = false;
        }

        return this;
    }

    private void validateEmail(final String email) throws ValidationException {

    }

    private void validatePassword(final String password) throws ValidationException {

    }

    private void validateName(final Name name) throws ValidationException {

    }

    private void validatePhone(final String phone) throws ValidationException {

    }

}
