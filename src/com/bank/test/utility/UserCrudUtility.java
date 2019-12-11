package com.bank.test.utility;

import com.bank.test.model.ApplicationMessage;
import com.bank.test.model.User;

public interface UserCrudUtility {
    // Functions / No Function body in interface/ Only Skeleton.
    // The class which implements this interface must provide body to this function.
    ApplicationMessage saveUserDetails(User user);
    User getUserDetails(Long accountNumber);
    void deleteUser(User user);
}
