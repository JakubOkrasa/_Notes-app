import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class BCryptEncoder {

    public String getBCryptHash(String input) {
        return (new BCryptPasswordEncoder()).encode(input);
    }

    @Test
    public void getHash() {
        System.out.println(getBCryptHash("1234"));
    }
}
