package com.cs.admin.common.config;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.AsymmetricAlgorithm;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * <p>
 * 说明描叙
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2021/1/27 13:56
 */
@Configuration
public class AlgorithmConfig {

    @Autowired
    private AuthYamlConfig authYamlConfig;

    @Bean
    public Algorithm algorithm() {
//        return Algorithm.none();
        byte[] privateKeyBytes = Base64.decode(authYamlConfig.getPriKey());
        byte[] publicKeyBytes = Base64.decode(authYamlConfig.getPubKey());
        RSAPrivateKey privateKey = (RSAPrivateKey) SecureUtil.generatePrivateKey(AsymmetricAlgorithm.RSA.getValue(), privateKeyBytes);
        RSAPublicKey publicKey = (RSAPublicKey) SecureUtil.generatePublicKey(AsymmetricAlgorithm.RSA.getValue(), publicKeyBytes);
        return Algorithm.RSA256(publicKey, privateKey);
    }
}
