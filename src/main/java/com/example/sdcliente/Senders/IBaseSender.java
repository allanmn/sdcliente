package com.example.sdcliente.Senders;

import com.example.sdcliente.Models.Validation.ValidationException;

public interface IBaseSender {
    public boolean validate() throws ValidationException;

    public void send();
}
