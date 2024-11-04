package com.millktea.core.domain.business.entity;

import com.millktea.core.config.jpa.audit.Auditing;
import com.millktea.core.domain.users.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

//사업자번호, 대표자명, 주소, 전화번호, 이메일, 회사 로고
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "BUSINESS")
@Entity
public class Business extends Auditing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, updatable = false, unique = true, length = 10)
    @NotNull
    @Length(min = 10, max = 10)
    private String businessNo;

    @Column(nullable = false, length = 20)
    @NotNull
    @Length(max = 20)
    private String name;

    @Column(nullable = false, length = 100)
    @NotNull
    @Length(max = 100)
    private String businessOwner;

    @Column(length = 100)
    @Length(max = 100)
    private String addr;

    @Column(length = 20)
    @Length(max = 20)
    private String telephoneNumber;

    @Column(length = 100)
    @Length(max = 100)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")
    private String email;

    private String logoSrc;

    private String logoName;

    @Builder.Default
    private Status status = Status.ACTIVE;

    @OneToMany(mappedBy = "business", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<User> userList = new ArrayList<>();

}
