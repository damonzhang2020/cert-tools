package com.certtools.csr.tools;

import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMEncryptedKeyPair;
import org.bouncycastle.openssl.PEMEncryptor;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.openssl.jcajce.JcaPEMWriter;
import org.bouncycastle.openssl.jcajce.JcePEMDecryptorProviderBuilder;
import org.bouncycastle.openssl.jcajce.JcePEMEncryptorBuilder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.jcajce.JcaPKCS10CertificationRequestBuilder;
import org.bouncycastle.util.io.pem.PemObject;
import sun.security.x509.X500Name;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.security.*;

public class CSRTools {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static void genRsaCsr(String commonName, String organization, String country, String state, String city, String algorithm, String keyParams, String password) {


        // 生成密钥对
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
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

            System.out.println(pemObjectStr("CERTIFICATE REQUEST", csr.getEncoded()));
            System.out.println(pemObjectStr("PRIVATE KEY", privateKey.getEncoded()));
            System.out.println(pemObjectStr("RSA PRIVATE KEY", privateKey.getEncoded()));
            System.out.println(pemKeyStr(privateKey));

            if (StringUtils.isNotBlank(password)) {
                System.out.println(encryptorKey(privateKey, password));
                System.out.println(decryptionPemKey(encryptorKey(privateKey, password), password));
            }


        } catch (NoSuchAlgorithmException | IOException | OperatorCreationException e) {
            throw new RuntimeException(e);
        }
    }


    //    私钥信息加密
    public static String encryptorKey(PrivateKey privateKey, String password) throws IOException {
//        Provider provider = Security.getProvider("BC");
//        System.out.println("Bouncy Castle provider: " + (provider != null));
        // 加密 PEM 文件内容
        JcePEMEncryptorBuilder encryptorBuilder = new JcePEMEncryptorBuilder("DES-EDE3-CBC");
        encryptorBuilder.setProvider("BC"); // Bouncy Castle 提供者
//        encryptorBuilder.setSecureRandom(new SecureRandom()); // 设置加密时使用的随机数生成器
        PEMEncryptor encryptor = encryptorBuilder.build(password.toCharArray());

        StringWriter stringWriter = new StringWriter();
        JcaPEMWriter jcaPEMWriter = new JcaPEMWriter(stringWriter);

        jcaPEMWriter.writeObject(privateKey, encryptor);
        jcaPEMWriter.flush();
        stringWriter.flush();
        return stringWriter.toString();

    }

    public static String decryptionPemKey(String pemKey, String password) throws IOException {
        PrivateKey decryptedPrivateKey = null;
        //读取加密私钥信息
        PEMParser pemParser = new PEMParser(new StringReader(pemKey));

        Object object = pemParser.readObject();

        if (object instanceof PEMEncryptedKeyPair) {
            PEMEncryptedKeyPair encryptedKeyPair = (PEMEncryptedKeyPair) object;
            JcePEMDecryptorProviderBuilder decryptorBuilder = new JcePEMDecryptorProviderBuilder();
            // 根据密码构建解密器提供者
            decryptorBuilder.setProvider("BC");

            JcaPEMKeyConverter converter = new JcaPEMKeyConverter()
                    .setProvider("BC");
            KeyPair keyPair = converter.getKeyPair(encryptedKeyPair.decryptKeyPair(decryptorBuilder.build(password.toCharArray())));
            decryptedPrivateKey = keyPair.getPrivate();
        }

        if (decryptedPrivateKey != null) {
            return pemKeyStr(decryptedPrivateKey);
        }

        return "";

    }


    public static String pemObjectStr(String type, byte[] bytes) throws IOException {
        StringWriter stringWriter = new StringWriter();
        JcaPEMWriter jcaPEMWriter = new JcaPEMWriter(stringWriter);
        jcaPEMWriter.writeObject(new PemObject(type, bytes));
        jcaPEMWriter.flush();
        stringWriter.flush();
        return stringWriter.toString();

    }


    private static String pemKeyStr(PrivateKey privateKey) throws IOException {
        StringWriter stringWriter = new StringWriter();
        JcaPEMWriter jcaPEMWriter = new JcaPEMWriter(stringWriter);
        jcaPEMWriter.writeObject(privateKey);
        jcaPEMWriter.flush();
        stringWriter.flush();
        return stringWriter.toString();

    }

}
