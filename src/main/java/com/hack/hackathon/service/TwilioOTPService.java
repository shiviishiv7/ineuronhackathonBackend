package com.hack.hackathon.service;

import com.hack.hackathon.config.CustomUserDetails;
import com.hack.hackathon.dao.UserRepository;
import com.hack.hackathon.dto.RegisterOtp;
import com.hack.hackathon.dto.UserDto;
import com.hack.hackathon.dto.UserDtoWithOutPassword;
import com.hack.hackathon.exceptionsHandler.RecordFound;
import com.hack.hackathon.exceptionsHandler.RecordNotFound;
import com.hack.hackathon.model.User;
import com.hack.hackathon.util.JwtUtil;
import com.hack.hackathon.web.response.UserTokenResponse;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class TwilioOTPService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;
    Map<String,String> registerUserMap = new HashMap<>();
    Map<String,String> loginUserMap = new HashMap<>();

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;

    TwilioOTPService(){
//        this.registerUserMap.put("+919350039160","788084");
    }
    public Map<String, String> registerSendOTP(String phoneNumber) throws RecordFound {
        User user = userRepository.findByNumber(phoneNumber);
        if(user!=null){
            throw new RecordFound("Mobile number exists "+phoneNumber);
        }
        String otp = generateOtp();
        String from ="+13153063562";
        Message message = Message.creator(new PhoneNumber(phoneNumber),
                new PhoneNumber(from), "Your OTP code is "+otp).create();
        registerUserMap.put(phoneNumber,otp);
        Map<String,String> map = new HashMap<>();
        map.put("message","OTP is send to this number "+phoneNumber);
        map.put("state","DELIVERED");
        return map;
    }

    public String generateOtp(){
        String format = new DecimalFormat("000000").format(new Random().nextInt(999999));
        return format;
    }

    public UserTokenResponse registerVerifyOTP(RegisterOtp registerOtp) throws RecordNotFound {
        Map<String,String> map = new HashMap<>();
        String number = registerOtp.getNumber();
        String otp = registerOtp.getOtp();
        if(registerUserMap.containsKey(number)){
            if(registerUserMap.get(number).equals(otp)){
                map.put("message","VERIFIED");
                registerUserMap.remove(number);
            }else throw new RecordNotFound("Invalid OTP sent by "+number);
        }else throw new RecordNotFound("Mobile number incorrect please check your Mobile number "+number);




            User userDto = new User();
            userDto.setPassword(registerOtp.getPassword());
            userDto.setEmail(registerOtp.getEmail());
            userDto.setNumber(registerOtp.getNumber());
            userDto.setFirstName(registerOtp.getFirstName());
        userDto.setRole(registerOtp.getRole());

        UserDto registerUser = this.userService.addUser(userDto);

        return generateTokenOfUser(registerUser.getNumber());


    }


    public Map<String, String> loginSendOTP(String phoneNumber) throws RecordNotFound {
        User user = userRepository.findByNumber(phoneNumber);
        if(user==null){
            throw new RecordNotFound("Mobile number not-exists with "+phoneNumber);
        }
        String otp = generateOtp();
        String from ="+13153063562";
        Message message = Message.creator(new PhoneNumber(phoneNumber),
                new PhoneNumber(from), "Your OTP code is "+otp).create();
        loginUserMap.put(phoneNumber,otp);
        Map<String,String> map = new HashMap<>();
        map.put("message","OTP is send to this number "+phoneNumber);
        map.put("state","DELIVERED");
        return map;
    }

    public UserTokenResponse loginVerifyOTP(String number, String otp) throws RecordNotFound {
        Map<String,String> map = new HashMap<>();

        if(loginUserMap.containsKey(number)){
            if(loginUserMap.get(number).equals(otp)){
                map.put("message","VERIFIED");
                loginUserMap.remove(number);
            }else throw new RecordNotFound("Invalid OTP sent by "+number);
        }else throw new RecordNotFound("Mobile number incorrect please check your Mobile number "+number);
       return generateTokenOfUser(number);

    }
    public UserTokenResponse generateTokenOfUser(String phoneNumber){
        User user = userRepository.findByNumber(phoneNumber);
        // fine area..
        UserDetails userDetails = new CustomUserDetails(user);
        String token = this.jwtUtil.generateToken(userDetails);

        UserDtoWithOutPassword data = this.modelMapper.map(user, UserDtoWithOutPassword.class);
        UserTokenResponse userTokenResponse = new UserTokenResponse();
        data.setToken(token);
        userTokenResponse.setMessage("User token response");
        userTokenResponse.setTimeStamp(System.currentTimeMillis());
        userTokenResponse.setStatusCode(HttpStatus.OK.value());
        userTokenResponse.setData(data);
        return userTokenResponse;
    }
}

