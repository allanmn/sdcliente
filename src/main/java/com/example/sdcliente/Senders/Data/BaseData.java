package com.example.sdcliente.Senders.Data;

import com.example.sdcliente.Models.Validation.ValidationException;

public interface BaseData {
    public boolean validate() throws ValidationException;
}
