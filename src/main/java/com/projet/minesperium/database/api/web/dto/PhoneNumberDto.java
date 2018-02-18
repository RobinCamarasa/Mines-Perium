package com.projet.minesperium.database.api.web.dto;

import com.projet.minesperium.database.api.model.User;

public class PhoneNumberDto {
    private String phonenumber;

    public PhoneNumberDto(User user) {
        this.phonenumber = user.getPhonenumber();
    }

    public String getPhonenumber() {
        return phonenumber;
    }
}
