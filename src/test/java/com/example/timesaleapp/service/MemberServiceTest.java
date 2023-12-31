package com.example.timesaleapp.service;

import com.example.timesaleapp.domain.member.Member;
import com.example.timesaleapp.domain.member.dto.MemberDto;
import com.example.timesaleapp.domain.member.dto.MemberSignUpDto;
import com.example.timesaleapp.domain.member.dto.MemberUpdateDto;
import com.example.timesaleapp.domain.order.dto.OrderDto;
import com.example.timesaleapp.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.timesaleapp.domain.member.MemberStatus.ACTIVE;
import static com.example.timesaleapp.domain.member.MemberStatus.DELETED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    private Member member1;
    private Member member2;
    private List<Member> members;

    @BeforeEach
    public void setUp(){
        member1 = Member.builder()
                .memberId(1L)
                .email("test@test.com")
                .password("hihihi123!")
                .nickName("hihi")
                .build();

        member2 = Member.builder()
                .memberId(2L)
                .email("min@min.com")
                .password("minmin123!")
                .nickName("minmin")
                .memberStatus(DELETED)
                .build();

        members = Arrays.asList(member1, member2);
    }

    @Test
    @DisplayName("회원가입에 성공한다.")
    void join() {
        //given
        MemberSignUpDto signUpDto = new MemberSignUpDto("test@test.com", "hihihi123!", "hihi");
        given(memberRepository.save(any(Member.class))).willReturn(member1);
        //when
        Long memberId = memberService.createMember(signUpDto);
        //then
        assertThat(memberId).isEqualTo(1L);
    }

    @Test
    @DisplayName("사용자 조회에 성공한다.")
    void getMembers() {
        //given
        given(memberRepository.findAll()).willReturn(members);
        //when
        List<MemberDto> allMembers = memberService.getMembers();
        //then
        assertThat(allMembers.get(0).getEmail()).isEqualTo("test@test.com");
        assertThat(allMembers.get(1).getNickName()).isEqualTo("minmin");
    }

    @Test
    @DisplayName("사용자 정보 수정에 성공한다.")
    void updateMember() {
        //given
        MemberUpdateDto updateDto = new MemberUpdateDto("min@min.com", "minmin123!", "minmin");
        given(memberRepository.findById(anyLong())).willReturn(Optional.ofNullable(member2));
        //when
        MemberDto updatedMemberDto = memberService.updateMember(1L,updateDto);
        //then
        assertThat(updatedMemberDto.getNickName()).isEqualTo("minmin");
        assertThat(updatedMemberDto.getEmail()).isEqualTo("min@min.com");
    }

    @Test
    @DisplayName("사용자 정보를 삭제로 변경한다.")
    void deleteMember() {
        //given
        given(memberRepository.findById(anyLong())).willReturn(Optional.ofNullable(member2));
        //when
        MemberDto deletedMemberDto = memberService.deleteMember(1L);
        //then
        assertThat(deletedMemberDto.getMemberStatus()).isEqualTo(DELETED);
    }
}