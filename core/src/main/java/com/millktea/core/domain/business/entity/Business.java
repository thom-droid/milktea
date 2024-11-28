package com.millktea.core.domain.business.entity;

import com.millktea.core.config.jpa.audit.Auditing;
import com.millktea.core.domain.user.entity.User;
import com.millktea.core.exception.BusinessRuntimeException;
import com.millktea.core.exception.RuntimeErrorCode;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    public boolean isActive() {
        return this.status == Status.ACTIVE;
    }

    public void deactivate() {
        this.status = Status.INACTIVE;
        this.userList.forEach(User::deactivate);
    }

    public boolean hasUsers() {
        return !userList.isEmpty();
    }

    public boolean containsUser(User user) {return userList.contains(user);}

    public boolean hasRepresentativeUser() {
        return userList.stream().anyMatch(User::isRepresentative);
    }

    public Optional<User> getRepresentativeUser() {
        return userList.stream()
                .filter(User::isRepresentative)
                .findFirst();
    }

    public boolean isRepresentativeUser(User user) {
        return getRepresentativeUser().map(u -> u.equals(user)).orElse(false);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Business business = (Business) o;
        return Objects.equals(businessNo, business.businessNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(businessNo);
    }
}
