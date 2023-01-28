package com.tdonuk.util.text;

import com.tdonuk.dto.domain.user.Name;
import com.tdonuk.util.BaseUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ValidationUtils extends BaseUtils {
    public static Boolean isEmailValid(String s) {
        return s.matches(emailPattern.pattern()) && s.length() < 70;
    }

    public static Boolean isPhoneValid(String s) {
        if(Objects.isNull(s)) return true; // not mandatory
        return s.replaceAll(" ", "").matches(phonePattern.pattern());
    }

    public static Boolean isPasswordValid(String s) {
        return s.matches(passwordPattern.pattern());
    }

    public static Boolean isNameValid(Name name) {
        return name.getFirstname().matches(namePattern.pattern()) && name.getLastname().matches(namePattern.pattern());
    }

}
