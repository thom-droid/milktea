package com.milktea.stub.buisness;

import com.milktea.stub.helper.random.CompanyNameGenerator;
import com.milktea.stub.helper.random.NameGenerator;
import com.millktea.core.domain.business.entity.Business;
import com.millktea.core.domain.business.entity.Status;
import com.millktea.core.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BusinessStub {

    public static Business createBusinessStub() {
        return Business.builder()
                .businessNo(createRandomBusinessNo())
                .name(CompanyNameGenerator.getInstance().generate())
                .representative(NameGenerator.getInstance().generate())
                .addr("123 Test Street")
                .telephoneNumber("010-1234-5678")
                .email("test@example.com")
                .logoSrc("path/to/logo.png")
                .logoName("logo.png")
                .status(Status.ACTIVE)
                .build();
    }

    public static Business createEmptyBusinessStub() {
        return new Business();
    }

    public static Business createBusinessWithUsersStub(List<User> users) {
        Business business = createBusinessStub();
        users.forEach(business::addUser);
        return business;
    }

    private static String createRandomBusinessNo() {
        return String.valueOf((int) (Math.random() * 1000000000));
    }
}
