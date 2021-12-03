package com.crmeb.springbootdemo.wxErrorCode;

import com.sun.javafx.binding.StringFormatter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.Security;
import java.util.Base64;


/**
 * 经常遇到 pad block corrupted 和 last block incomplete in decryption
 * @program: springboot-demo
 * @author: 大粽子
 * @create: 2021-11-25 18:35
 **/
public class padBlockCorrupted {

    public static boolean initialized = false;

    static String ALGORITHM_MODE_PADDING = "AES/CBC/PKCS7Padding";



    public static void main(String[] args) throws Exception {
        String testContent = "qNYrS5jz3Xlr3fVYwPHIEKOr0o2ZXahRCAFN3jllnBUAN1aDfXnDg7WdXmTRUI1JNuaH/Q56UI8+jekoj6cX+AU1q9eOo50WsnriRKtEU6YvivaHOn7YBDhsGaQwbPfn/It5ceAWKV33vTYYlzNWqUIxUtljPBA3WUWeXUTqLTNoW7JrpOXEGn+WU4PY1Yuwe9QHtwpJoavwmcxHy5C+fdB44b8+upEXgvCe7VJZ6N5pZtgqdGXscklG/cjDm1GNhvo6n4hnpJNn2y+NxDDAOjPKTKGKvTeZ/1Co9XtSgmVFq8SeOsfFuLZWItQ6ihV8+ndfc+++5CgkN0Q+0pwqu3krXYfmY30fMhVW0Vest3DaI8sy8xvWpxDxx1sP0COrjWBRFrPOdilycoKJmZJfIEuJGbZhWhTPyneJ5pUGZc+paWknahrAuNq760AI5+eHCsaUwQuz9msGqKQUlIBBNXYZXTuB/bTq7JlvxBz5fjCfnzlqKCTZ8PtevzI1Je/rwy9FMIYGzug9IxCxVH6oOqYAG773fCjRg+2S756Q4IQO7uQ+zBjrr0WIaWlqgdvrhJVD7++QAZBeUtcBfmLWlonhzyM7K3s38le4JMLT7fDhB2WlmMl76TjY22u/5xUa8GuHBsLUHQewAjjX806kaXDFb1HpdVtsN4e9RscrxF6b+B8GaCjFvvBxnYKy44VK2v/ElVkpyBsaDJv1woU3XND9EcQCvAjHOi28mVG/zgmJXv2vB0qfWC2kci19vqYCbkTH50yrr4PMohZoIjMDI5O2YXozP1NAkML+m2dFCso7agM0XLyoFb2scWka0iZ7w97/C/tV5SKBGjrwLuH2zyhl00DpYG4BwtUci3R9bvTcrdAQpAJ78cm2spN8UqHN5t7PkmiSp+qpG5hVM7KZrWt0YfSOEzO/dJeFSXpIFn4CO17pSVr2Sob+LcTYYLV6G0upzFfQuTCFwBCpZBU7XdgBMwPbi1eSVy6Z81rnnN+W0c+684DK+252I6abeTkY0EkHbuuqCzgLpoXmOggS/O/scDPr8NioI08yX6qtPLfcMWCt3fww2+zTOC6x5M1A";
        String enCodeData = encryptData(testContent);
        System.out.println(StringFormatter.format("enCodeData:${}",enCodeData));
        String deCodeData = decryptData(enCodeData);
        System.out.println(StringFormatter.format("deCodeData:${}",deCodeData));
    }



    public static String decryptData(final String base64Data) throws Exception {
        initialize();
//        final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        Key keySpec = new SecretKeySpec(base64Data.getBytes(StandardCharsets.UTF_8) , "AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        return new String(cipher.doFinal(base64_decode_8859(base64Data).getBytes("ISO-8859-1")), "utf-8");

    }


    public static String base64_decode_8859(final String source) {
        String result = "";
        final Base64.Decoder decoder = Base64.getDecoder();
        try {
            result = new String(decoder.decode(source), "ISO-8859-1");
        } catch (final UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }



    public static String encryptData(final String data) throws Exception {
        initialize();
        final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        Key keySpec = new SecretKeySpec(data.getBytes(StandardCharsets.UTF_8) , "AES");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        return base64_encode_8859(new String(cipher.doFinal(data.getBytes()), "ISO-8859-1"));
    }



    public static String base64_encode_8859(final String source) {
        String result = "";
        final Base64.Encoder encoder = Base64.getEncoder();
        byte[] textByte = null;
        try {
            textByte = source.getBytes("ISO-8859-1");

        } catch (final UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        result = encoder.encodeToString(textByte);
        return result;
    }


    public static void initialize() {
        if (initialized)
            return;
        Security.addProvider(new BouncyCastleProvider());
        initialized = true;
    }
}
