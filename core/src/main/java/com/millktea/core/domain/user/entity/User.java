package com.millktea.core.domain.user.entity;

import com.millktea.core.config.jpa.audit.Auditing;
import com.millktea.core.config.jpa.converter.ListPrivilegeConverter;
import com.millktea.core.domain.business.entity.Business;
import com.millktea.core.domain.business.entity.Status;
import jakarta.persistence.*;
import lombok.*;

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

    @EqualsAndHashCode.Exclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 20)
    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    @Convert(converter = ListPrivilegeConverter.class)
    @Builder.Default
    private List<Privilege> privileges = new ArrayList<>();

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

    public void setDefaultRoleDependingOnRole() {
        privileges = role == Role.REPRESENTATIVE ? List.of(Privilege.ALL) : List.of(Privilege.READ);
    }
}
