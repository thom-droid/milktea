package com.milktea.stub.helper.comparator;

import com.millktea.core.domain.business.entity.Business;

public class BusinessComparator {

    public static void throwsIfNotTheSameObjects(Business entity, Business source) {
        boolean equals = entity.getBusinessNo().equals(source.getBusinessNo()) &&
                entity.getRepresentative().equals(source.getRepresentative()) &&
                entity.getEmail().equals(source.getEmail()) &&
                entity.getName().equals(source.getName()) &&
                entity.getAddr().equals(source.getAddr()) &&
                entity.getStatus() == source.getStatus() &&
                entity.getTelephoneNumber().equals(source.getTelephoneNumber());

        if (!equals) throw new RuntimeException("Not equal");
    }

}
