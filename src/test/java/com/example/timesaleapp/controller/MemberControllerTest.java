package com.example.timesaleapp.controller;

import com.example.timesaleapp.domain.member.Member;
import com.example.timesaleapp.domain.member.dto.MemberDto;
import com.example.timesaleapp.domain.member.dto.MemberSignUpDto;
import com.example.timesaleapp.domain.member.dto.MemberUpdateDto;
import com.example.timesaleapp.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private MemberSignUpDto signUpDto;
    private MemberUpdateDto updateDto;

    @BeforeEach
    public void setUp(){
        signUpDto = new MemberSignUpDto("test@test.com", "hihihi123!", "hihi");
        updateDto = new MemberUpdateDto("hihi@hihi.com", "password1!", "heehee");

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

        //when
        mvc.perform(get("/api/v1/members")
                        .contentType(APPLICATION_JSON)) // 요청에 관한 정보 전달
                .andExpect(status().isOk()) // 응답을 기대하는 부분
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].id").value(1))
                .andExpect(jsonPath("$.data[0].email").value("test@test.com"))
                .andExpect(jsonPath("$.data[1].email").value("min@min.com"));
    }

    @Test
    @DisplayName("회원가입에 성공한다.")
    void signUp() throws Exception{
        //given
        when(memberService.join(signUpDto)).thenReturn(1L);

        //when, then
        mvc.perform(post("/api/v1/members/signup")
                        .contentType(APPLICATION_JSON)
                        .content(asJsonString(signUpDto)))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(APPLICATION_JSON))
                        .andExpect(jsonPath("$.data").value(1L));
    }

    @Test
    @DisplayName("회원정보 수정에 성공한다.")
    void correctMember() throws Exception {
        //given
        Member updatedMember = Member.builder()
                .email("hihi@hihi.com")
                .password("password1!")
                .nickName("heehee")
                .build();

        when(memberService.correct(1L, updateDto)).thenReturn(MemberDto.of(updatedMember));

        //when,then
        mvc.perform(patch("/api/v1/members/{id}/update", 1)
                .contentType(APPLICATION_JSON)
                .content(asJsonString(updateDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.data.email").value("hihi@hihi.com"))
                .andExpect(jsonPath("$.data.password").value("password1!"))
                .andExpect(jsonPath("$.data.nickName").value("heehee"));
    }

    @Test
    @DisplayName("멤버 정보 수정에 성공한다.")
    void deleteMember() throws Exception{
        //given
        Member deletedMember = Member.builder()
                .email("test@test.com")
                .password("hihihi123!")
                .nickName("hihi")
                .build();

        when(memberService.delete(1L)).thenReturn(MemberDto.of(deletedMember));

        //when, then
        mvc.perform(patch("/api/v1/members/{id}/delete", 1)
                        .contentType(APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(APPLICATION_JSON))
                        .andExpect(jsonPath("$.data.email").value("test@test.com"))
                        .andExpect(jsonPath("$.data.password").value("hihihi123!"))
                        .andExpect(jsonPath("$.data.nickName").value("hihi"));
    }


    private String asJsonString(Object obj) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

}