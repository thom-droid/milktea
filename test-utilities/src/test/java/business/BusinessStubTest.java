package business;

import com.milktea.stub.buisness.BusinessStub;
import com.millktea.core.domain.business.entity.Business;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class BusinessStubTest {

    @Test
    @DisplayName("has two different random business no")
    void hasDifferentRandomBusinessNo() {
        Business business1 = BusinessStub.createBusinessStub();
        Business business2 = BusinessStub.createBusinessStub();
        String businessNo1 = business1.getBusinessNo();
        String businessNo2 = business2.getBusinessNo();
        assertNotEquals(businessNo1, businessNo2);
    }

}