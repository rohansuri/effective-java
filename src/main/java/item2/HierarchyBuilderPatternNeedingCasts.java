package item2;

public class HierarchyBuilderPatternNeedingCasts {

    static abstract class Validator {
        // should be private, but it's a simple test
        int max, min;

        abstract static class Builder {
            protected int max = Integer.MAX_VALUE, min = Integer.MIN_VALUE; // defaults

        /*

        we shouldn't return Builder here.
        Because then we'd need casts.
        IntegerValidator.Builder builder = new IntegerValidator.Builder().max(10)
        would give a compile error

        since max returns Validator.Builder, we can't hold it in IntegerValidator.Builder
        even though yes, at runtime that'd be the real type

        Hence we need to return here a type which extends Validator.Builder

        Next try:
        is <T extends Builder> T max(int max); allowed?

        Although then we'd have to invoke the method as new IntegerValidator.Builder().<IntegerValidator.Builder>max(10)
        */

        Builder max(int max){
            this.max = max;
            return this;
        }

        Builder min(int min){
            this.min = min;
            return this;
        }

            // we can't build it, because to-be-built class is abstract
            // hence a subclass builder will have to build it
            abstract Validator build();


        }

        abstract boolean valid(String value);

        // the Builder here could be either Validator.Builder
        // or any subclass of Validator's .Builder
        protected Validator(Builder builder){
            this.max = builder.max;
            this.min = builder.min;
        }
    }

    static class IntegerValidator extends Validator {

        static class Builder extends Validator.Builder {

            // important: covariant return typing
            // where this builder doesn't return a Validator, but an IntegerValidator
            // hence users don't need an explict cast
            // remember, overridden methods don't take return values as an criteria
            @Override
            IntegerValidator build() {
                IntegerValidator validator = new IntegerValidator(this);
                return validator;
            }
        }

        @Override
        boolean valid(String value) {
            int val = Integer.parseInt(value);
            return val >= min && val <= max;
        }

        private IntegerValidator(Builder builder){
            super(builder);
        }
    }
}
