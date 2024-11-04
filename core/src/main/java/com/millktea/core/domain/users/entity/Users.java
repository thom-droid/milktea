package com.millktea.core.domain.users.entity;

import com.millktea.core.domain.business.entity.Business;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

// 사용자 정보, 아이디, 비밀번호, 이름(담당자), 이메일, 회사정보, 권한
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, length = 50)
    @NotNull
    @Length(max = 50)
    private String userId;

    @Column(nullable = false, length = 100)
    @NotNull
    @Length(max = 100)
    private String password;

    @Column(nullable = false, length = 20)
    @NotNull
    @Length(max = 20)
    private String name;

    @Column(nullable = false, length = 100)
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")
    private String email;

    @ManyToOne
    @JoinColumn(name = "business_id")
    private Business business;

}
