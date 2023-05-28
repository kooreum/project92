package project92;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

@SpringBootTest
class Project92ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void jasypt() {
        String username = "username";
        System.out.println(jasyptEncoding(username));
    }

    public String jasyptEncoding(String value) {

        String key = "my_jasypt_key";
        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWithMD5AndDES");
        pbeEnc.setPassword(key);
        return pbeEnc.encrypt(value);
    }
}
