package com.certtools.csr.example;

import com.certtools.csr.tools.CSRTools;

public class CSRPem {

    public static void main(String[] args)  {
        try {

        CSRTools.genRSACsr("www.rsa-demo.cn","O","CN","SH","SH","SHA256withRSA","2048","123123");
        CSRTools.genECDSACsr("www.ecdsa-demo.cn","O","CN","SH","SH","SHA256withECDSA","prime256v1","123123");
        CSRTools.genGMCsr("www.sm2-demo.cn","O","CN","SH","SH","SM3withSM2","sm2p256v1","123123");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
