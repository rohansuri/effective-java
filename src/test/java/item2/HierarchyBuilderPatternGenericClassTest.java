package item2;

import org.junit.Test;
import static item2.HierarchyBuilderPatternGenericClass.*;

public class HierarchyBuilderPatternGenericClassTest {
    @Test
    public void testBuilder(){
        IntegerValidator.Builder ib = new IntegerValidator.Builder();

        IntegerValidator iv = ib.max(10).build();
    }
}
