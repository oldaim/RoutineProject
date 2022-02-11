package com.oldaim.routineproject.entity;

import com.oldaim.routineproject.entity.work.ToDoList;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mem_id")
    private Long id;

    @Column(unique = true,nullable = false)
    private String memberId;

    @Column(nullable = false)
    private String memberPassword;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @Column
    @OneToMany(mappedBy = "member",fetch = FetchType.LAZY)
    private List<ToDoList> toDoList;

    @Builder
    public Member(Long id, String memberId, String memberPassword, List<String> roles, List<ToDoList> toDoList) {
        this.id = id;
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.roles = roles;
        this.toDoList = toDoList;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return this.roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

    }

    @Override
    public String getPassword() {
        return memberPassword;
    }

    @Override
    public String getUsername() {
        return memberId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; //계정 만료여부 확인
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; //계정 잠김여부 확인
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; //만료여부 확인
    }

    @Override
    public boolean isEnabled() {
        return true; //계정 활성화 여부 확인
    }
}
