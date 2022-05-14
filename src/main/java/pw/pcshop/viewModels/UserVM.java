package pw.pcshop.viewModels;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.NoArgsConstructor;
import pw.pcshop.dataModels.User;

@NoArgsConstructor
public class UserVM {
    public String name;
    public String lastName;
    public Date birthDate;
    public String PESEL;
    public String email;
    public String phoneNumber;
    public String address;
    public String creditCardNumber;

    public UserVM(User user) {
        name = user.getName();
        lastName = user.getLastName();
        birthDate = user.getBirthDate();
        PESEL = user.getPESEL();
        email = user.getEmail();
        phoneNumber = user.getPhoneNumber();
        address = user.getAddress();
        creditCardNumber = user.getCreditCardNumber();
    }

    public static List<UserVM> toUserVMs(List<User> users) {
        List<UserVM> userVMs = new ArrayList<>();
        users.forEach(user -> userVMs.add(new UserVM(user)));
        return userVMs;
    }
}
