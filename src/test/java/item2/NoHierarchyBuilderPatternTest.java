package item2;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class NoHierarchyBuilderPatternTest {

    @Test
    public void testBuilder(){
        IntRange.Builder rangeBuilder = new IntRange.Builder();
        IntRange range = rangeBuilder.max(10).min(1).build();

        Assert.assertThat(range.max, CoreMatchers.is(10));
        Assert.assertThat(range.min, CoreMatchers.is(1));
    }
}
