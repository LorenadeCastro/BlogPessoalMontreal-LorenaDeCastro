package montreal.lorenadecastro.blogpessoal.security;

import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.util.Base64;

public class KeyGenerator {
    public static void main(String[] args) {
        byte[] keyBytes = new byte[32]; 
        new SecureRandom().nextBytes(keyBytes);
        @SuppressWarnings("unused")
		SecretKey key = Keys.hmacShaKeyFor(keyBytes);
        
        String base64Key = Base64.getEncoder().encodeToString(keyBytes);
        System.out.println("Generated Key: " + base64Key);
    }
}
