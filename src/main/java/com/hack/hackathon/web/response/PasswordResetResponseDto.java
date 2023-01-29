package com.hack.hackathon.web.response;

import com.hack.hackathon.dao.OtpStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PasswordResetResponseDto {
	private OtpStatus status;
    private String message;

}
