package password;

import java.util.Arrays;

public class Password {

    private final Symbol[] value;

    public Password(Symbol[] value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password password = (Password) o;
        return Arrays.equals(value, password.value);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(value);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Password{");
        sb.append("value=").append(Arrays.toString(value));
        sb.append('}');
        return sb.toString();
    }
}
