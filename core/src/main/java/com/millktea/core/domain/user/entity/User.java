package com.millktea.core.domain.user.entity;

import com.millktea.core.config.jpa.audit.Auditing;
import com.millktea.core.config.jpa.converter.ListPrivilegeConverter;
import com.millktea.core.domain.business.entity.Business;
import com.millktea.core.domain.business.entity.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

// 사용자 정보, 아이디, 비밀번호, 이름(담당자), 이메일, 회사정보, 권한
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "USERS")
@Entity
public class User extends Auditing {

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

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @NotNull
    @Column(nullable = false)
    @Convert(converter = ListPrivilegeConverter.class)
    @Builder.Default
    private List<Privilege> privileges = new ArrayList<>();

    @NotNull
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.ACTIVE;

    @ManyToOne
    @JoinColumn(name = "business_id")
    private Business business;

    public enum Role {
        REPRESENTATIVE, USER
    }

    public enum Privilege {
        WRITE, READ, MODIFY, DELETE, ALL
    }

    public void addBusiness(Business business) {
        this.business = business;
        business.addUser(this);
    }

    public boolean isRepresentative() {
        return role == Role.REPRESENTATIVE;
    }

    public void deactivate() {
        this.status = Status.INACTIVE;
    }

    public boolean isActive() {
        return this.status == Status.ACTIVE;
    }

}
