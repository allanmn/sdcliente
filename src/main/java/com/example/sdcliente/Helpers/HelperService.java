package com.example.sdcliente.Helpers;

import javax.swing.*;

public class HelperService {
    public static void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
