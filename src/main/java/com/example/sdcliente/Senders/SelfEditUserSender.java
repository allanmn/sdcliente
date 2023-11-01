package com.example.sdcliente.Senders;

import com.example.sdcliente.Actions.Actions;
import com.example.sdcliente.Helpers.HelperService;
import com.example.sdcliente.Main;
import com.example.sdcliente.Models.Validation.ValidationException;
import com.example.sdcliente.Senders.Data.EditUserData;
import com.example.sdcliente.Senders.Data.SelfEditUserData;
import com.example.sdcliente.Services.SocketService;
import com.fasterxml.jackson.core.JsonProcessingException;

public class SelfEditUserSender extends BaseSender implements IBaseSender {
    public SelfEditUserSender(SelfEditUserData data) {
        super(Actions.SELF_EDIT_USER, data);
    }

    public SelfEditUserSender() {
        super();
    }

    @Override
    public boolean validate() throws ValidationException {
        this.getData().validate();

        return true;
    }

    @Override
    public String send() {
        String response = null;
        try {
            if (this.validate()) {
                SocketService socket = Main.getSocketService();

                response = socket.send(this.toJson());
            }
        } catch (ValidationException | JsonProcessingException e) {
            HelperService.showErrorMessage(e.getMessage());
        }

        return response;
    }
}
