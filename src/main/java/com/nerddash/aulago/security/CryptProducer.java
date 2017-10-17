package com.nerddash.aulago.security;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.util.SimpleByteSource;

/**
 *
 * @author victornjg
 */
public class CryptProducer {

    private static final int HASH_ITERATIONS = 750000; //750 mil
    private static final String PRIVATE_SALT = "private_salt";
    private final DefaultPasswordService passwordService;
    private final DefaultHashService hashService;

    public CryptProducer() {

        hashService = new DefaultHashService();
        hashService.setHashIterations(HASH_ITERATIONS);
        hashService.setHashAlgorithmName(Sha512Hash.ALGORITHM_NAME);
        hashService.setPrivateSalt(new SimpleByteSource(PRIVATE_SALT));
        hashService.setGeneratePublicSalt(true);
        passwordService = new DefaultPasswordService();
        passwordService.setHashService(hashService);

    }

    public String encryptPassword(String submittedPlaintext) {
        return passwordService.encryptPassword(submittedPlaintext);
    }

    public boolean passwordsMatch(Object submittedPlaintext, String encrypted) {
        return passwordService.passwordsMatch(submittedPlaintext, encrypted);
    }

    @Produces
    @RequestScoped
    public PasswordService getPasswordService() {
        return passwordService;
    }
}
