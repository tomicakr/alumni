package hr.petsonly.web.api;

import hr.petsonly.model.User;
import hr.petsonly.model.details.UserDetailsBasic;
import hr.petsonly.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/users")
public class UserAPI {

    private final UserRepository userRepository;

    @Autowired
    public UserAPI(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ResponseBody
    @GetMapping
    public List<UserDetailsBasic> getUsers(@RequestParam(value="role",required = false) String role) {
        List<User> users = role!=null && role.equals("employee") ? userRepository.findAllEmployees() : userRepository.findAll();
        return users.stream().map(UserDetailsBasic::new).collect(Collectors.toList());
    }
}
