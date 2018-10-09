package item2;

public class HierarchyBuilderPatternGenericMethod {
    static abstract class Validator {
        // should be private, but it's a simple test
        int max, min;

        // ok, no need for casts now, but still we explictly need to specify the
        // generic type for being able to use all those builder methods present in the abstract super class
        // and all those generic method's type defn is the same too
        // let's refactor into defining the type on class level
        // so that the class itself defines the type once and hence all generic methods get their
        // type definitions too, no longer needing the users to put the type
        abstract static class Builder {
            protected int max = Integer.MAX_VALUE, min = Integer.MIN_VALUE; // defaults

            // can't use "return this"
            // this would be Validator.Builder, but we need to return the this of the subclass
            // that'd be extending Builder, hence the need for simulated self-type idiom
            <T extends Builder> T max(int max){
                this.max = max;
                return self();
            }

            <T extends Builder> T min(int min){
                this.min = min;
                return self();
            }

            abstract <T extends Builder> T self();

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

            @Override
            Builder self(){
                return this;
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
