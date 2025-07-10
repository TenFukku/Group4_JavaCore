package people;

public final class CEO extends Person {
    public CEO(String name) {
        super(name);
    }

    @Override
    public String getDescription() {
        return "Giám đốc điều hành của công ty";
    }
}
