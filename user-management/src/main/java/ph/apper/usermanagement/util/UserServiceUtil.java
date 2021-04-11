package ph.apper.usermanagement.util;

import ph.apper.usermanagement.domain.User;
import ph.apper.usermanagement.payload.GetUserResponse;
import ph.apper.usermanagement.payload.UserData;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class UserServiceUtil {

    public static UserData toUserData(User u) {
        UserData userData = new UserData();
        userData.setFirstName(u.getFirstName());
        userData.setLastName(u.getLastName());
        userData.setEmail(u.getEmail());
        userData.setVerified(u.isVerified());
        userData.setAuth(u.isAuth());
        userData.setDateRegistered(u.getDateRegistered().format(DateTimeFormatter.ISO_DATE_TIME));
        userData.setId(u.getId());
        userData.setAccountId(u.getAccountId());
        userData.setBalance(u.getBalance());

        if (Objects.nonNull(u.getLastLogin())) {
            userData.setLastLogin(u.getLastLogin().format(DateTimeFormatter.ISO_DATE_TIME));
        }

        if (Objects.nonNull(u.getDateVerified())) {
            userData.setDateVerified(u.getDateVerified().format(DateTimeFormatter.ISO_DATE_TIME));
        }

        return userData;
    }


    public static GetUserResponse toGetUserDataResponse(User u) {
        GetUserResponse getUserResponse = new GetUserResponse();
        getUserResponse.setFirstName(u.getFirstName());
        getUserResponse.setLastName(u.getLastName());
        getUserResponse.setEmail(u.getEmail());
        getUserResponse.setBalance(u.getBalance());
        return getUserResponse;
    }
}
