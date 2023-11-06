package com.certtools.csr.example;

import com.certtools.csr.tools.CSRTools;

import java.io.IOException;
import java.util.Base64;

public class GenRsaCSR {

    public static void main(String[] args){
        try {
            System.out.println(CSRTools.pemObjectStr("CERTIFICATE REQUEST", Base64.getDecoder().decode("MIIB6DCCAY0CAQAwggEpMVEwTwYDVQQDDEjlpI3ml6blpKflrabpmYTlsZ7lpofkuqfnp5HljLvpmaLplb/kuInop5LkuIDkvZPljJbnpLrojIPljLrpnZLmtabliIbpmaIxUTBPBgNVBAsMSOWkjeaXpuWkp+WtpumZhOWxnuWmh+S6p+enkeWMu+mZoumVv+S4ieinkuS4gOS9k+WMluekuuiMg+WMuumdkua1puWIhumZojFRME8GA1UECgxI5aSN5pem5aSn5a2m6ZmE5bGe5aaH5Lqn56eR5Yy76Zmi6ZW/5LiJ6KeS5LiA5L2T5YyW56S66IyD5Yy66Z2S5rWm5YiG6ZmiMQ0wCwYDVQQHEwQzMTAxMRIwEAYDVQQIDAnkuIrmtbfluIIxCzAJBgNVBAYTAkNOMFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAEvJouW+ywuUuA781jTWi6UHeN8sWETF7Ea9BVJxAAPa2u5xXiP4Y3nDZvOTqBH3VEa2x1U5Hx3GCAkzO5T0N7paAAMAoGCCqBHM9VAYN1A0kAMEYCIQC/U1hBaeLwYj520ls5FqjqpmMxCJ97ru2g15jZUjyIzwIhAOxo2hsLVtIMTYHIxJxpd269ImGwVQj4RRV4prFxVMWc")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
