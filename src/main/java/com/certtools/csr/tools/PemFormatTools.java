package com.certtools.csr.tools;

import com.certtools.model.PemObjectType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.openssl.jcajce.JcaPEMWriter;
import org.bouncycastle.util.io.pem.PemObject;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Base64;

/**
 * 将不同PEM文件进行格式化处理
 */
@Slf4j
public class PemFormatTools {

    static Base64.Decoder decoder = Base64.getDecoder();
    static Base64.Encoder encoder = Base64.getEncoder();


    public static String transformPemCsr(String base64Pem) {
        return transformPemObject(PemObjectType.TYPE_CERTIFICATE_REQUEST, base64Pem);
    }


    public static String transformPemPrivateKey(String base64Pem) {
        return transformPemObject(PemObjectType.TYPE_PRIVATE_KEY, base64Pem);
    }


    public static String transformByteObject(String type, byte[] bytes) {
        StringWriter stringWriter = new StringWriter();
        JcaPEMWriter jcaPEMWriter = new JcaPEMWriter(stringWriter);
        try {
            jcaPEMWriter.writeObject(new PemObject(type, bytes));
            jcaPEMWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringWriter.toString();

    }

    public static String transformPemObject(String type, String base64Pem) {
        if (StringUtils.isNotBlank(base64Pem)) {
            base64Pem = base64Pem.replaceAll("\n|\r\n", "");
            StringWriter stringWriter = new StringWriter();
            JcaPEMWriter jcaPEMWriter = new JcaPEMWriter(stringWriter);
            try {
                jcaPEMWriter.writeObject(new PemObject(type, decoder.decode(base64Pem)));
                jcaPEMWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            log.info("transform type:{}:\n{}", type, stringWriter);
            return stringWriter.toString();
        } else {
            log.error("base64Pem is null");
            return null;
        }

    }
}
