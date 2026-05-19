package com.profession.suggest.database.entities.users;

import com.profession.suggest.database.entities.auth.Account;

public interface User {
    Account getAccount();
    String getName();
    String getSurname();
    String getPatronymic();
    default String getFullName() {
        return String.format("%s %s %s",
                getSurname() != null ? getSurname() : "",
                getName() != null ? getName() : "",
                getPatronymic() != null ? getPatronymic() : ""
        ).trim();
    }
}
