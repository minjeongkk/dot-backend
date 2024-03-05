package com.example.dot_backend.member.dto;

import com.example.dot_backend.member.entity.Member;
import com.example.dot_backend.member.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Data
public class SignupRequestDto {
    @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$", message = "Enter your email")
    private String email;
    @Pattern(regexp = "^[A-Za-z0-9]{6,12}$", message = "Enter your password")
    private String password;
    @NotBlank (message = "Enter your name")
    private String name;
    @Pattern (regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", message = "Enter your phone")
    private String phone;

    public void encodePassword(String encodingPassword){
        this.password = encodingPassword;
    }

    public Member toSaveMember() {
        return Member.builder()
                .email(this.email)
                .password(this.password)
                .name(this.name)
                .phone(this.phone)
                .createdDate(LocalDate.now())
                .updatedDate(LocalDate.now())
                .role(Role.ROLE_USER)
                .build();
    }

    @Builder
    public SignupRequestDto(String email, String password, String name, String phone) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }
}