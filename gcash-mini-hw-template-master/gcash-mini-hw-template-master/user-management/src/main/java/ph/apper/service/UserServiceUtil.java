package ph.apper.service;

import org.springframework.stereotype.Service;
import ph.apper.domain.User;
import ph.apper.payload.UserData;

@Service
public class UserServiceUtil {
    // convert User object to a UserData object
    public static UserData toUserData(User u) {
        UserData userData = new UserData();

        userData.setFirstName(u.getFirstName());
        userData.setLastName(u.getLastName());
        userData.setEmail(u.getEmail());
        userData.setBalance(u.getBalance());

        return userData;
    }
}