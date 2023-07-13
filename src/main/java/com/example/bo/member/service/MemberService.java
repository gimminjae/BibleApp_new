package com.example.bo.member.service;

import java.util.List;
import java.util.Map;

import com.example.bo.member.dto.MemberDto;

public interface MemberService {

    void signUp(MemberDto memberDto);

    void modifyEmail(MemberDto memberDto, String email);

    void changePassword(String memId, String oldPassword, String newPassword1, String newPassword2);

    List<MemberDto> getAllMember();

    void deleteMember(MemberDto memberDto);

    void modifyNickname(MemberDto savedMemberDto, String email);

    void empowerDEPTSUBADMIN(MemberDto savedMemberDto);

    Map<String, String> login(MemberDto memberDto);

    MemberDto getByMemId(String memId);

    void logout(String memId);

    String regenAccessToken(String refreshToken);

    void confirmEmail(String email);

    void confirmNicknameDuplication(String nickname);

    void confirmEmailCode(String email, String authCode);

    MemberDto getByEmail(String email);
}
