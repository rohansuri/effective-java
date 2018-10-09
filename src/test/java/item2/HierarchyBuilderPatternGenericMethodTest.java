package item2;

import org.junit.Test;

import static item2.HierarchyBuilderPatternGenericMethod.*;

public class HierarchyBuilderPatternGenericMethodTest {

    @Test
    public void testBuilder(){
        IntegerValidator.Builder ib = new IntegerValidator.Builder();

        // this again gives an incompatible type error
        // if specified no generic type, then it assumes Validator.Builder
        // IntegerValidator iv = ib.max(10).build();

        IntegerValidator iv = ib.<IntegerValidator.Builder>max(10).build();
    }
}
