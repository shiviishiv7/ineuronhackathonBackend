package com.hack.hackathon.web.Controller;


import com.hack.hackathon.config.UserDetailsServiceImpl;
import com.hack.hackathon.dto.LoginDto;
import com.hack.hackathon.dto.UserDtoWithOutPassword;
import com.hack.hackathon.model.User;
import com.hack.hackathon.service.UserService;
import com.hack.hackathon.util.JwtUtil;
import com.hack.hackathon.web.response.UserTokenResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public/auth")
public class UserController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestBody LoginDto loginDto) throws Exception {

        if (loginDto.getEmail().toString().contains("@")) {
            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        }


        // fine area..
        UserDetails userDetails = null;

        User user = null;
        String requestUsername = loginDto.getEmail();
        user = userService.findByUserName(requestUsername);
        userDetails = this.userDetailsService.loadUserByUsername(user.getEmail());

        String token = this.jwtUtil.generateToken(userDetails);

        UserDtoWithOutPassword data = this.modelMapper.map(user, UserDtoWithOutPassword.class);
        UserTokenResponse userTokenResponse = new UserTokenResponse();
        data.setToken(token);
        userTokenResponse.setMessage("User token response");
        userTokenResponse.setTimeStamp(System.currentTimeMillis());
        userTokenResponse.setStatusCode(HttpStatus.OK.value());
        userTokenResponse.setData(data);
        return new ResponseEntity<UserTokenResponse>(userTokenResponse, HttpStatus.OK);
    }


}
