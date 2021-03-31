package sample.mybatis.helper.encrypt;


import java.lang.annotation.*;

/**
 * 加密字段注解
 */
@Documented
@Inherited
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EncryptDecryptField {

}