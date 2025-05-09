package com.url.shortner_sb.Controller;
import com.url.shortner_sb.Dtos.LoginRequests;
import com.url.shortner_sb.Model.User;
import com.url.shortner_sb.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.url.shortner_sb.Dtos.RegisterRequests;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private UserService userService;

    @PostMapping("/public/login")
    public ResponseEntity<?> LoginUser(@RequestBody LoginRequests loginRequests) {
        return ResponseEntity.ok(userService.authenticateUser(loginRequests));
    }

    @PostMapping("/public/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequests registerRequest){
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword());
        user.setEmail(registerRequest.getEmail());

        user.setRole("ROLE_USER");
        userService.registerUser(user);
        return ResponseEntity.ok("User Registered Successfully");
    }
}
