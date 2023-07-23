package com.example.timesaleapp.domain.member;

import com.example.timesaleapp.domain.BaseTimeEntity;
import com.example.timesaleapp.domain.member.dto.MemberSignUpDto;
import com.example.timesaleapp.domain.member.dto.MemberUpdateDto;
import com.example.timesaleapp.domain.order.Order;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

import static com.example.timesaleapp.domain.member.MemberStatus.ACTIVE;
import static jakarta.persistence.FetchType.*;

@Getter
@Entity
@Builder
@Table(name = "members")
@AllArgsConstructor
@DynamicUpdate
@NoArgsConstructor
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String nickName;

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<Order> orders = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    private MemberStatus memberStatus;

    public void changeStatusDeleted() {
        this.memberStatus = MemberStatus.DELETED;
    }

    public void update(MemberUpdateDto memberUpdateDto) {
        this.email = validateUpdateString(memberUpdateDto.email(), this.email);
        this.password = validateUpdateString(memberUpdateDto.password(), this.password);
        this.nickName = validateUpdateString(memberUpdateDto.nickName(), this.nickName);
    }

    public static Member of(MemberSignUpDto signUpDto) {

        return Member.builder()
                .email(signUpDto.email())
                .password(signUpDto.password())
                .nickName(signUpDto.nickName())
                .memberStatus(ACTIVE)
                .build();
    }

    private String validateUpdateString(String update, String origin) {
        if (update == null) {

            return origin;
        }

        return update;
    }
}
