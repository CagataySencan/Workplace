package com.tdonuk.util.validation;

import com.tdonuk.dto.domain.user.Name;
import com.tdonuk.dto.domain.user.UserDTO;
import com.tdonuk.exception.ValidationException;
import com.tdonuk.util.text.StringUtils;
import com.tdonuk.util.text.ValidationUtils;

public class UserValidator implements Validator {
    private UserDTO user;

    private boolean isValid = false;
    public UserValidator(UserDTO user) {
        this.user = user;
    }

    public UserValidator validate() throws ValidationException {
        try {
            validateEmail(user.getEmail());
            validatePassword(user.getPassword());
            validatePhone(user.getPhone());
            validateName(user.getName());
        } catch(ValidationException e) {
            this.isValid = false;
            throw e;
        }

        return this;
    }

    private void validateEmail(final String email) throws ValidationException {
        if(!ValidationUtils.isEmailValid(email)) throw new ValidationException("Geçersiz e-posta", "E-posta adresi .-_ karakterleri haricinde özel karakter içermemelidir");
    }

    private void validatePassword(final String password) throws ValidationException {
        if(!ValidationUtils.isPasswordValid(password)) throw new ValidationException("Geçersiz parola", "Parolanız 6-20 karakter uzunluğunda ve bir büyük harf, bir sayı ve _ - . sembollerinden en az birini içermelidir");
    }

    private void validateName(final Name name) throws ValidationException {
        if(!ValidationUtils.isNameValid(name)) if(!ValidationUtils.isNameValid(name)) throw new ValidationException("Geçersiz isim", "İsim ve soyisim 2-30 karakter aralığında olmalı ve yalnızca harf ve boşluk içermelidir");
    }

    private void validatePhone(final String phone) throws ValidationException {
        if(!ValidationUtils.isPhoneValid(phone.replaceAll(" ", "")))throw new ValidationException("Geçersiz telefon", "Telefon numarası '0xxx xxx xx xx' veya 'xxx xxx xx xx' formatında olmalıdır");
    }

}
