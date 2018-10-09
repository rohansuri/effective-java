package item2;

public class HierarchyBuilderPatternGenericClass {
    static abstract class Validator {
        // should be private, but it's a simple test
        int max, min;

        // we simply took the defn of T from methods to the class itself
        // so that we could return a subclass type (IntegerValidator.Builder)
        // in a super class (Validator.Builder)
        abstract static class Builder<T extends Builder<T>> {
                                                    // ^^ TODO: question here, not specifying T here works fine
                                                    // specifying ? also works
                                                    // is there a difference?
                                                    // of course we should specify something, else it'd be a raw type

            protected int max = Integer.MAX_VALUE, min = Integer.MIN_VALUE; // defaults

            // can't use "return this"
            // this would be Validator.Builder, but we need to return the this of the subclass
            // that'd be extending Builder, hence the need for simulated self-type idiom
            T max(int max){
                this.max = max;
                return self();
            }

            T min(int min){
                this.min = min;
                return self();
            }

            abstract T self();

            // we can't build it, because to-be-built class is abstract
            // hence a subclass builder will have to build it
            abstract Validator build();


        }

        abstract boolean valid(String value);

        // the Builder here could be either Validator.Builder
        // or any subclass of Validator's .Builder
        // TODO: why <?> here?
        protected Validator(Builder<?> builder){
            this.max = builder.max;
            this.min = builder.min;
        }
    }

    static class IntegerValidator extends Validator {

        static class Builder extends Validator.Builder<Builder> {

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
