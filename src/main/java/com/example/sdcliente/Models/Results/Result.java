package com.example.sdcliente.Models.Results;

import com.example.sdcliente.Models.Validation.ValidationException;

public interface Result {
    public boolean validate() throws ValidationException;
}
