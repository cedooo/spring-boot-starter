package sample.mybatis.helper.encrypt;


import java.lang.annotation.*;

/**
 * 需要加解密的类注解
 */
@Documented
@Inherited
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface EncryptDecryptClass {
}