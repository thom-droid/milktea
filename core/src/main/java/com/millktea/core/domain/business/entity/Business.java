package com.millktea.core.domain.business.entity;

import com.millktea.core.config.jpa.audit.Auditing;
import com.millktea.core.domain.user.entity.User;
import com.millktea.core.exception.BusinessRuntimeException;
import com.millktea.core.exception.RuntimeErrorCode;
import jakarta.persistence.*;
import lombok.*;

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
    private Long id;

    @Column(nullable = false, updatable = false, unique = true, length = 10)
    private String businessNo;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 100)
    private String representative;

    private String addr;

    private String telephoneNumber;

    private String email;

    @Builder.Default
    private String logoSrc = "/icons/img/logo-default.png";

    @Builder.Default
    private String logoName = "default-logo.png";

    @Builder.Default
    private Status status = Status.ACTIVE;

    @OneToMany(mappedBy = "business", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<User> userList = new ArrayList<>();

    public void addUser(User user) {
        if (!userList.contains(user) ) {
            userList.add(user);
            user.setBusiness(this);
        }
    }

    public boolean hasUsers() {
        return !userList.isEmpty();
    }

    public boolean hasRepresentative() {
        return userList.stream().anyMatch(User::isRepresentative);
    }

    public User getRepresentativeUser() {
        return userList.stream()
                .filter(User::isRepresentative)
                .findFirst()
                .orElseThrow(() -> new BusinessRuntimeException(RuntimeErrorCode.BUSINESS_HAS_NO_REPRESENTATIVE_USER));
    }

    public boolean isRepresentativeUser(User user) {
        return getRepresentativeUser().equals(user);
    }

    public void checkRepresentativeUser(User user) {
        if (!isRepresentativeUser(user)) throw new BusinessRuntimeException(RuntimeErrorCode.USER_NOT_REPRESENTATIVE);
    }

}
