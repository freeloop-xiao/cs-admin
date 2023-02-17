package com.cs.admin;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSON;
import com.cs.admin.common.util.RequestUtil;
import com.cs.admin.common.util.TokenUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 说明描叙
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2021/1/30 10:56
 */
public class UtilTest {


    @Test
    public void setTest(){
        Set<Integer> set = new HashSet<>();
        set.add(Integer.valueOf("10"));
        set.add(Integer.valueOf("10"));
        System.out.println(set.size());
    }


    @Test
    public void comp(){
        PathMatcher PATH_MATCHER = new AntPathMatcher();
        System.out.println(PATH_MATCHER.match("/amc/operation-management/**","/amc/operation-management/list"));

    }

    @Test
    public void compareTime(){
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.of(2020,11,12,12,12);
        long seconds = Duration.between(end,start).getSeconds();
        System.out.println(seconds);



    }

    @Test
    public void streamTest(){
        List<String> list = Arrays.asList("add,test,hello","free","loop");
        Set<String> sets = list.stream()
                .flatMap(x -> Arrays.stream(x.split(",")))
                .collect(Collectors.toSet());
        System.out.println(JSON.toJSONString(sets));
    }

    @Test
    public void base64(){
        String clientId = "csadmin";
        String clientSecret = "sIIddwIcADANBgkqhki";
        String code = clientId +":"+clientSecret;
        System.out.println(code);
        System.out.println(Base64.encode(code));
    }

    @Test
    public void test(){
        String header = "Basic aGVsbG86d29ybGQ=";
        System.out.println(header.substring(TokenUtil.BASIC.length()));
        System.out.println(header.substring(TokenUtil.BASIC.length()+1));
        String body = header.substring(TokenUtil.BASIC.length());
        body = Base64.decodeStr(body);
        System.out.println(body);
        System.out.println("client-id:"+body.split(":")[0]);
        System.out.println("client-secret:"+body.split(":")[1]);
    }


    @Test
    public void decode(){
        String encoded = "Y3MtYWRtaW46c0lJZGR3SWNBREFOQmdrcWhraQ==";
        System.out.println(Base64.decodeStr(encoded));
    }

    @Test
    public void password(){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String pwd = passwordEncoder.encode(SecureUtil.md5(SecureUtil.md5("111111")));
        String pwd = "$2a$10$jXK1moLKbm9lChX3H7X5Qe41TScOTKPOar8k2Z.0c6q3B0I8Kfu9e";
//        System.out.println(pwd);
//        System.out.println(SecureUtil.md5("123456"));
        System.out.println(passwordEncoder.matches(SecureUtil.md5(SecureUtil.md5("123456")),pwd));
    }

    @Test
    public void ip(){
        String ip = "120.77.215.143";
        String city = RequestUtil.getLocalCityInfo(ip);
        System.out.println(city);
        System.out.println(RequestUtil.getHttpCityInfo(ip));
    }

}
