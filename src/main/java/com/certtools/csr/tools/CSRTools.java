package com.certtools.csr.tools;

import com.certtools.model.PemObjectType;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.jcajce.JcaPKCS10CertificationRequestBuilder;
import sun.security.x509.X500Name;

import java.io.IOException;
import java.security.*;


/**
 * 生成 证书请求文件,对应 私钥信息等
 */
@Slf4j
public class CSRTools {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static void genRSACsr(String commonName, String organization, String country, String state, String city, String algorithm, String keyParams, String password) throws Exception {

        try {
            // 生成密钥对
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            // 指定密钥长度  常见值:2048,3072,4096
            keyPairGenerator.initialize(Integer.parseInt(keyParams));
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();

            // 构建CSR信息
            X500Name subjectName = new X500Name("CN=" + commonName + ", O=" + organization + ", C=" + country + ", ST=" + state + ", L=" + city);
            ContentSigner contentSigner = new JcaContentSignerBuilder(algorithm).build(privateKey);
            PKCS10CertificationRequest csr = new JcaPKCS10CertificationRequestBuilder(subjectName.asX500Principal(), publicKey)
                    .build(contentSigner);


            log.info("证书请求文件信息:\n{}", PemFormatTools.transformByteObject(PemObjectType.TYPE_CERTIFICATE_REQUEST, csr.getEncoded()));
            log.info("pkcs8格式私钥信息:\n{}", PemFormatTools.transformByteObject(PemObjectType.TYPE_PRIVATE_KEY, privateKey.getEncoded()));
            log.info("pkcs1格式私钥信息:\n{}", KeyTools.transformPrivateKeyFromPkcs8ToPkcs1(PemObjectType.TYPE_RSA_PRIVATE_KEY, privateKey.getEncoded()));


        } catch (NoSuchAlgorithmException | IOException | OperatorCreationException e) {
            throw new RuntimeException(e);
        }
    }

    public static void genECDSACsr(String commonName, String organization, String country, String state, String city, String algorithm, String keyParams, String password) throws Exception {

        try {
            // 生成密钥对
//            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ECDSA");
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", "BC");
            // 指定秘钥曲线  常见值:prime256v1
            ECParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec(keyParams);
            keyPairGenerator.initialize(ecSpec, new SecureRandom());
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();

            // 构建CSR信息
            X500Name subjectName = new X500Name("CN=" + commonName + ", O=" + organization + ", C=" + country + ", ST=" + state + ", L=" + city);
            ContentSigner contentSigner = new JcaContentSignerBuilder(algorithm).build(privateKey);
            PKCS10CertificationRequest csr = new JcaPKCS10CertificationRequestBuilder(subjectName.asX500Principal(), publicKey)
                    .build(contentSigner);

            log.info("证书请求文件信息:\n{}", PemFormatTools.transformByteObject(PemObjectType.TYPE_CERTIFICATE_REQUEST, csr.getEncoded()));
            log.info("pkcs8格式私钥信息:\n{}", PemFormatTools.transformByteObject(PemObjectType.TYPE_PRIVATE_KEY, privateKey.getEncoded()));
            log.info("pkcs1格式私钥信息:\n{}", KeyTools.transformPrivateKeyFromPkcs8ToPkcs1(PemObjectType.TYPE_EC_PRIVATE_KEY, privateKey.getEncoded()));


        } catch (NoSuchAlgorithmException | IOException | OperatorCreationException e) {
            throw new RuntimeException(e);
        }
    }


    public static void genGMCsr(String commonName, String organization, String country, String state, String city, String algorithm, String keyParams, String password) throws Exception {

        try {
            // 生成密钥对
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ECDSA");
            // 指定秘钥曲线  常见值:sm2p256v1
            ECParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec(keyParams);
            keyPairGenerator.initialize(ecSpec, new SecureRandom());
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();

            // 构建CSR信息
            X500Name subjectName = new X500Name("CN=" + commonName + ", O=" + organization + ", C=" + country + ", ST=" + state + ", L=" + city);
            ContentSigner contentSigner = new JcaContentSignerBuilder(algorithm).build(privateKey);
            PKCS10CertificationRequest csr = new JcaPKCS10CertificationRequestBuilder(subjectName.asX500Principal(), publicKey)
                    .build(contentSigner);

            log.info("证书请求文件信息:\n{}", PemFormatTools.transformByteObject(PemObjectType.TYPE_CERTIFICATE_REQUEST, csr.getEncoded()));
            log.info("pkcs8格式私钥信息:\n{}", PemFormatTools.transformByteObject(PemObjectType.TYPE_PRIVATE_KEY, privateKey.getEncoded()));
            log.info("pkcs1格式私钥信息:\n{}", KeyTools.transformPrivateKeyFromPkcs8ToPkcs1(PemObjectType.TYPE_EC_PRIVATE_KEY, privateKey.getEncoded()));


        } catch (NoSuchAlgorithmException | IOException | OperatorCreationException e) {
            throw new RuntimeException(e);
        }
    }












}
