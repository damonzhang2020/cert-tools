package com.certtools.csr.example;

import com.certtools.csr.tools.CSRTools;

public class CSRPem {

    public static void main(String[] args){
        CSRTools.genRsaCsr("www.demo.cn","O","CN","SH","SH","SHA256withRSA","2048","123123");
    }
}
