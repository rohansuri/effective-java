package item2;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import static item2.HierarchyBuilderPatternNeedingCasts.*;

public class HierarchyBuilderPatternNeedingCastsTest {

    @Test
    public void testBuilder(){
        IntegerValidator.Builder builder = new IntegerValidator.Builder();

        Validator.Builder b = builder.max(10);

        // incompatible types!
        // IntegerValidator.Builder ib = builder.max(10);
        // hence we need cast, eee

        IntegerValidator.Builder ib = (IntegerValidator.Builder)builder.max(10);
        ib.build();
        Assert.assertThat(ib.max, CoreMatchers.is(10));
    }
}
