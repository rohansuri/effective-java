package item2;

public class NoHierarchyBuilderPattern {
}

class IntRange {
    // should be private, but it's a simple test
    int max, min;

    static class Builder {
        private int max = Integer.MAX_VALUE, min = Integer.MIN_VALUE; // defaults

        Builder max(int max){
            this.max = max;
            return this;
        }

        Builder min(int min){
            this.min = min;
            return this;
        }

        IntRange build(){
            return new IntRange(this);
        }
    }


    /*
    aa-aa, the ctor should get initialised from the builder, not from all params!
    The below would be wrong:
    the point was to avoid having a ctor with a lot of params right?

    This also clearly dictates, the only way to build the API -- is through the builder
    (of course keeping ctor private anyways tells us that)

    private IntRange(int max, int min){
        this.max = max;
        this.min = min;
    }*/

    private IntRange(Builder builder){
        this.max = builder.max;
        this.min = builder.min;
    }
}