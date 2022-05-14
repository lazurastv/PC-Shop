package pw.pcshop.services;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pw.pcshop.dataModels.User;
import pw.pcshop.repositories.UserRepository;
import pw.pcshop.verifiers.UserVerifier;
import pw.pcshop.verifiers.VerifierUtils;
import pw.pcshop.viewModels.UserVM;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserVM> getAll() {
        return UserVM.toUserVMs(userRepository.findAll());
    }

    public void add(UserVM userVM) {
        VerifierUtils.throwIfNotCorrect(UserVerifier.verifyUser(userVM));
        User user = new User();
        user.setAddress(userVM.address);
        user.setBirthDate(userVM.birthDate);
        user.setCreditCardNumber(userVM.creditCardNumber);
        user.setEmail(userVM.email);
        user.setLastName(userVM.lastName);
        user.setName(userVM.name);
        user.setPESEL(userVM.PESEL);
        user.setPhoneNumber(userVM.phoneNumber);
        userRepository.save(user);
    }
}
