package project92;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project92.common.config.JasyptConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = JasyptConfig.class)
public class JasyptDecryptionTest {

    @Autowired
    private StringEncryptor encryptor;

    @Test
    public void testDecryption() {
        String decryptedUrl = encryptor.decrypt("rnHTQmkhlM5cyNnXffCAWJl8L8qFQ1JlIeYZNAaPjGtiDJLRj/PT/k2Qvout4/CR/UJP+/VCTE94QqwkUaEyWljFBJo6T3nUa8OehJdJpSdurwCj7DNSPqwhWT5eFUt7CSybAZU7obE=");
        String decryptedUsername = encryptor.decrypt("u6FsgBxWZ7Q9aTYOnztuu21gnBRuczTp");
        String decryptedPassword = encryptor.decrypt("OE5kHwIsuezBtshBIOdkpRix9O2HC8Ya");

        System.out.println("decryptedPassword = " + decryptedPassword);
        System.out.println("decryptedUsername = " + decryptedUsername);
        System.out.println("decryptedUrl = " + decryptedUrl);

        // You can add assertions to check if the decrypted values match expected values
        assertEquals("jdbc:log4jdbc:mysql://152.67.220.142:3306/project92db?serverTimezone=UTC&characterEncoding=UTF8", decryptedUrl);
        assertEquals("project92id", decryptedUsername);
        assertEquals("project92pw", decryptedPassword);
    }
}