package com.certtools.csr.tools;


import com.certtools.model.PemObjectType;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.pkcs.RSAPrivateKey;
import org.bouncycastle.asn1.sec.ECPrivateKey;

import java.io.IOException;
import java.util.Base64;

//公钥  私钥处理工具
@Slf4j
public class KeyTools {
    static Base64.Decoder decoder = Base64.getDecoder();
    static Base64.Encoder encoder = Base64.getEncoder();




    public static String transformPrivateKeyFromPkcs8ToPkcs1(String type, byte[] bytes) throws IOException {
        PrivateKeyInfo privateKeyInfo = PrivateKeyInfo.getInstance(bytes);
        log.info("Algorithm:{}", privateKeyInfo.getPrivateKeyAlgorithm().getAlgorithm().getId());
        log.info("Parameters:{}", privateKeyInfo.getPrivateKeyAlgorithm().getParameters().toString());
        ASN1Object asn1Object = null;
        if (PemObjectType.TYPE_EC_PRIVATE_KEY.equalsIgnoreCase(type)) {
            asn1Object = ECPrivateKey.getInstance(privateKeyInfo.parsePrivateKey());
        } else if (PemObjectType.TYPE_RSA_PRIVATE_KEY.equalsIgnoreCase(type)) {
            asn1Object = RSAPrivateKey.getInstance(privateKeyInfo.parsePrivateKey());
        }
        return PemFormatTools.transformByteObject(type, asn1Object.getEncoded());
    }

}
