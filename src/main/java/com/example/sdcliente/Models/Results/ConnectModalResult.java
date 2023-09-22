package com.example.sdcliente.Models.Results;

import com.example.sdcliente.Models.Validation.ValidationException;

public class ConnectModalResult implements Result {
    private final String ip;
    private final String port;

    public ConnectModalResult(String ip, String port) {
        this.ip = ip;
        this.port = port;
    }

    public boolean validate() throws ValidationException {
        if (ip == null || ip.isEmpty()) {
            throw new ValidationException("IP é obrigatório");
        }

        if (port == null || port.isEmpty()) {
            throw new ValidationException("Porta é obrigatório");
        }

        return true;
    }

    public String getPort() {
        return port;
    }

    public String getIp() {
        return ip;
    }
}
