package service;

import apper.domain.User;
import payload.UserData;

public class UserServiceUtil {
    // convert User object to a UserData object
    public static UserData toUserData(User u) {
        UserData userData = new UserData();

        userData.setFirstName(u.getFirstName());
        userData.setLastName(u.getLastName());
        userData.setEmail(u.getEmail());
        userData.setAccBalance(u.getBalance());

        return userData;
    }
}