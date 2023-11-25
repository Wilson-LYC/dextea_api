package com.dextea;

import com.dextea.mapper.StoreMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootTest
class DexteaApplicationTests {

    @Test
    void contextLoads() {
        String hashedPassword1 = "$2a$10$t1pG/Ptt7KW0wUhuBp3XiOhFoEhBn8m.MdLyCFOIGUuc0gdalipOy";
        String hashedPassword2 = "$2a$10$an2eDpufT8k9vdASdba5cuGRaM7mGiI5oN6sHq8Y86r3W3CwI4KMS";

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        boolean isMatch = encoder.matches(hashedPassword1, hashedPassword2);

        System.out.println("Hashed Password 1: " + hashedPassword1);
        System.out.println("Hashed Password 2: " + hashedPassword2);
        System.out.println("Is Match: " + isMatch);
    }
}
