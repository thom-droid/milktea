package com.milktea.stub.helper.random;

import lombok.NoArgsConstructor;

import java.util.concurrent.ThreadLocalRandom;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class CompanyNameGenerator implements RandomValueGenerator<String> {

    private static final String[] ADJECTIVES = {
        "Global", "Dynamic", "Innovative", "NextGen", "Advanced", "Creative", "Tech", "Prime", "Modern", "Reliable",
        "Pro", "Smart", "Elite", "Leading", "World", "Bright", "Visionary", "Strategic", "Noble", "Green"
    };

    private static final String[] NOUNS = {
        "Solutions", "Technologies", "Enterprises", "Systems", "Consulting", "Corporation", "Group", "Partners",
        "Industries", "Ventures", "Concepts", "Services", "Networks", "Dynamics", "International", "Holdings",
        "Designs", "Developers", "Logistics", "Partners", "Innovations"
    };

    public static CompanyNameGenerator getInstance() {
        return new CompanyNameGenerator();
    }

    @Override
    public String generate() {
        return ADJECTIVES[ThreadLocalRandom.current().nextInt(ADJECTIVES.length)]
                + " "
                + NOUNS[ThreadLocalRandom.current().nextInt(NOUNS.length)];
    }

}
