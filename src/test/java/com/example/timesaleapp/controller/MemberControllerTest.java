package com.example.timesaleapp.controller;

import com.example.timesaleapp.domain.member.Member;
import com.example.timesaleapp.domain.member.dto.MemberSignUpDto;
import com.example.timesaleapp.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MemberController.class)
class MemberControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MemberService memberService;

    private List<Member> members;

    @BeforeEach
    public void setUp(){
        MemberSignUpDto signUpDto = new MemberSignUpDto("test@test.com", "hihihi123!", "hihi");

        Member member1 = Member.builder()
                .id(1L)
                .email("test@test.com")
                .password("hihihi123!")
                .nickName("hihi")
                .build();

        Member member2 = Member.builder()
                .id(2L)
                .email("min@min.com")
                .password("minmin123!")
                .nickName("minmin")
                .build();

        members = Arrays.asList(member1, member2);

    }

    @Test
    @DisplayName("멤버 전체 조회에 성공한다.")
    void getAllMembers() throws Exception {
        //given
        when(memberService.getAllMembers()).thenReturn(members);

        //when                      /api/v1/members
         mvc.perform(get("/api/v1/members")
                 .contentType(APPLICATION_JSON))
                 .andExpect(status().isOk())
                 .andExpect(jsonPath("$.data").isArray())
                 .andExpect(jsonPath("$.data[0].id").value(1))
                 .andExpect(jsonPath("$.data[0].email").value("test@test.com"))
                 .andExpect(jsonPath("$.data[1].email").value("min@min.com"));

//String requestJson = objectMapper.writeValueAsString(request);
//String responseJson = objectMapper.writeValueAsString(response);
//
//// when & then
//this.mockMvc.perform(post("/api/v3/vouchers")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestJson)) // 줄 구분 보임? 얘네까지는 요청에 대한 거
//        .andExpect(status().isOk()) // 여기부터 응답값 검증
//        .andExpect(content().string(responseJson));

    }

    @Test
    void signUp() {
        //given

        //when

        //then
    }

    @Test
    void correctMember() {
        //given

        //when

        //then
    }

    @Test
    void deleteMember() {
        //given

        //when

        //then
    }
}