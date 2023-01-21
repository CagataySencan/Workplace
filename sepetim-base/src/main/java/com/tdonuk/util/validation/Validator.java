package com.tdonuk.util.validation;

import com.tdonuk.exception.ValidationException;

public interface Validator {
    boolean validate() throws ValidationException;
}
