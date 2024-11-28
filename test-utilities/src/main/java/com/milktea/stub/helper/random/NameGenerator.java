package com.milktea.stub.helper.random;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class NameGenerator implements RandomValueGenerator<String> {

    private static final String[] FIRST_NAMES = {
            "James", "John", "Robert", "Michael", "William", "David", "Richard", "Joseph", "Charles", "Thomas",
            "Mary", "Patricia", "Jennifer", "Linda", "Elizabeth", "Susan", "Jessica", "Sarah", "Karen", "Nancy"
    };

    private static final String[] LAST_NAMES = {
            "Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor",
            "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin", "Thompson", "Garcia", "Martinez", "Roberts"
    };

    public static NameGenerator getInstance() {
        return new NameGenerator();
    }

    @Override
    public String generate() {
        return FIRST_NAMES[(int) (Math.random() * FIRST_NAMES.length)]
                + " "
                + LAST_NAMES[(int) (Math.random() * LAST_NAMES.length)];
    }

}
