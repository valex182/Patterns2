package ru.netology;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationInfo {
    private String login;
    private String password;
    private String status;

    public RegistrationInfo(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
