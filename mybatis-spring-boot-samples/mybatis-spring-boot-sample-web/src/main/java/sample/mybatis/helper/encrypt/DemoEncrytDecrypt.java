package sample.mybatis.helper.encrypt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;

@Slf4j
@Component
public class DemoEncrytDecrypt implements IEncryptDecrypt{

    @Value("${domain.aesKey}")
    private String aesKey;

    @Override
    public <T> T encrypt(Field[] declaredFields, T parameterObject) throws IllegalAccessException {
        for (Field field : declaredFields) {
            if (field.getType() == String.class) {
                field.setAccessible(true);
                for (Annotation annotation : field.getAnnotations()) {
                    if (annotation.annotationType() == EncryptDecryptField.class) {
                        log.info("加密字段：" + field.getName());
                        Object fieldVal = field.get(parameterObject);
                        if(fieldVal!=null) {
                            field.set(parameterObject, encrypt(field.get(parameterObject).toString()));
                        }
                    }
                }
            }
        }
        return parameterObject;
    }

    @Override
    public <T> T decrypt(T result) throws IllegalAccessException {

        for (Field field : result.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                if (field.get(result) != null) {
                    for (Annotation annotation : field.getAnnotations()) {
                        if (annotation.annotationType() == EncryptDecryptField.class) {
                            log.info("解密密字段：" + field.getName());
                            String fieldVal = field.get(result).toString();
                            if(fieldVal.endsWith("==")||fieldVal.endsWith("=")) {
                                try {
                                    field.set(result, decrypt(fieldVal));
                                }catch (Exception e){
                                    log.info("解密时出现错误，设置为空");
                                    field.set(result, null);
                                }
                            }
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                throw e;
            }
        }
        return result;
    }

    /**
     * 根据密钥对指定的明文plainText进行加密.
     *
     * @param plainText 明文
     * @return 加密后的密文.
     */
    public final String encrypt(String plainText) {
        Key secretKey = getKey(aesKey);
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] p = plainText.getBytes("UTF-8");
            byte[] result = cipher.doFinal(p);
            Base64.Encoder encoder = Base64.getEncoder();
            String encoded = encoder.encodeToString(result);
            return encoded;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据密钥对指定的密文cipherText进行解密.
     *
     * @param cipherText 密文
     * @return 解密后的明文.
     */
    public final String decrypt(String cipherText) {
        Key secretKey = getKey(aesKey);
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] c = decoder.decode(cipherText);
            byte[] result = cipher.doFinal(c);
            String plainText = new String(result, "UTF-8");
            return plainText;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Key getKey(String keySeed) {
        if (keySeed == null) {
            keySeed = System.getenv("AES_SYS_KEY");
        }
        if (keySeed == null) {
            keySeed = System.getProperty("AES_SYS_KEY");
        }
        if (keySeed == null || keySeed.trim().length() == 0) {
            keySeed = "abcd1234!@#$";// 默认种子
        }
        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(keySeed.getBytes());
            KeyGenerator generator = KeyGenerator.getInstance("AES");
            generator.init(secureRandom);
            return generator.generateKey();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
