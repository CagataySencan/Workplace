package com.tdonuk.util.validation;

import com.tdonuk.exception.ValidationException;

public interface Validator {
    Validator validate() throws ValidationException;
}
