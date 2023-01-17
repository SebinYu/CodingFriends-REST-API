//
//package net.skhu.codingFriends.config;
//
//
//import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Value;
//import org.bouncycastle.jce.provider.BouncyCastleProvider;
//import org.jasypt.encryption.StringEncryptor;
//import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
//import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.stream.Collectors;
//
//
//@Configuration
//public class JasyptConfig {
//    @Value("${jasypt.encryptor.password}")
//    private static String KEY = System.getenv("JASYPT_PASSWORD");
//    private static final String ALGORITHM = "PBEWithMD5AndDES";
//
//    @Bean("jasyptStringEncryptor")
//    public StringEncryptor stringEncryptor() {
//        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
//        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
//
//        encryptor.setPassword(KEY); // 암호화 키
//        encryptor.setAlgorithm(ALGORITHM); // 사용 알고리즘
//        config.setKeyObtentionIterations("1000");
//        config.setPoolSize("1");
//        config.setProviderName("SunJCE");
//        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
//        config.setStringOutputType("base64");
//        encryptor.setConfig(config);
//
//        return encryptor;
//    }
//}