package com.baking.authservice.domain.port.out;

import com.baking.authservice.domain.model.User;

public interface SaveUserPort {

    void save (User user);
}
