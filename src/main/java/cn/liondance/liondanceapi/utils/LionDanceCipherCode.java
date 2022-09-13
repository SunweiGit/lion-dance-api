package cn.liondance.liondanceapi.utils;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Base64;

/**
 * @author sunwei
 */
@Slf4j
@Builder
@NoArgsConstructor
public class LionDanceCipherCode {


    public static final String                 algorithm    = "AES";
    public static final Base64.Decoder         base64decode = Base64.getDecoder();
    public static final Base64.Encoder         base64encode = Base64.getEncoder();
    public static       Cipher                 cipher;
    public static       SecretKeySpec          keySpec;
    public static       AlgorithmParameterSpec ivSpec;


    static {
        try {
            Security.addProvider(new BouncyCastleProvider());
            String transformation = "AES/CBC/PKCS7Padding";
            cipher = Cipher.getInstance(transformation);
            keySpec = new SecretKeySpec("axap1tanexmj7kiveunnawse" .getBytes(StandardCharsets.UTF_8), algorithm);
            ivSpec = new IvParameterSpec("1954682228745975" .getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }


    @SneakyThrows
    public static String encryptAES(String input) {
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        byte[] bytes = cipher.doFinal(input.getBytes());
        return base64encode.encodeToString(bytes);
    }

    @SneakyThrows
    public static String decryptAES(String encrypted) {
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        byte[] bytes = cipher.doFinal(base64decode.decode(encrypted));
        return new String(bytes);
    }
}
