package com.certtools.csr.example;

import com.certtools.csr.tools.CSRTools;

import java.io.IOException;

public class dePemKey {

    public static void main(String[] args) throws IOException {
        String encKeyPem = "-----BEGIN RSA PRIVATE KEY-----\n" +
                "Proc-Type: 4,ENCRYPTED\n" +
                "DEK-Info: DES-EDE3-CBC,8d93d4f23870fed2\n" +
                "\n" +
                "n8q7N5y4WJFBtNXWUrq17l0UcW5g0DXee41OQdaRD5cZw+2qFyQslSO9wB60rkXC\n" +
                "nmJhALGRG714oJ5clLHKCLxfnLKa135zpvGkcXdhjw3nQq/KY84vsRYzdImqcE2i\n" +
                "k96nJfxG1Vm8O8hN5reIj1nj7nNS+3n5vozq3H2RAUa3+0kklRvb3BN4s1XqrUY9\n" +
                "v/ckP0f0nu0L1xzwUXp6pDjk8xikypjGamE/DQJYJhWZ+OsiWlWDNDgZ1v1/b8s9\n" +
                "uIaYULNo7X6dC43Hx34W0KNHSor5nW9il4rm80VHJ54xE7bW3cpHlPi0IBkJhZyA\n" +
                "0EKuyc4hAMbAVKX6qphYzFLBU47wbslLPfkWO6YhAQAu7BPfilUUvB3N8YLBUt8m\n" +
                "u5CWZpeQBkLQXh9QUb3KA52TqVdg/risrBi6pPK5aRFjx4+28XuXWOnhdSZeXvFt\n" +
                "VlB/HSNY7JDufbs0cOtT8JbIAroWUFmpjIm0Hn9NFiwXryH3RpEn8Xn+lhQV62bP\n" +
                "t3o0xl9sB312vvUy9+pUbAY1qTpIB3PknXiggHYhLFn6in715qa2n1gnpHfXsozu\n" +
                "NPq9GyhIZq12jgwAi1JpfMBqWeGxlOUGxsWq4KUSX1ON2vxiGZxQ90jkbXb95xjn\n" +
                "ECcDe05duVrvyZ12S5rFL19kkGlReKYIV/ejd0DbQa619NuJNFVV1cQigN8U9KLP\n" +
                "vnm9zsXFrR3BTp2Xrop/o5MYMBqlzpk2GwPYW9EmVSzmEqrpFVFh+P7DqnSA2t/I\n" +
                "PgVfOK1ZyzVO2FDaQb3jKPaxHTHr/sbL1k0O/xfLkxt5sX+nqBRXw+DBr6Mu9v2t\n" +
                "ogqZ0UjQue60NiAdFAgNhK7vp2HK+/e2apSh4BF0f5VaAqqYI4fyjsljJVeXayyh\n" +
                "/ysSuz+PiB2+I73rk2DsxP6Xt8LhM/dHo9fPGWWEA9CvMMehxPg1YLt+a6RBMXZH\n" +
                "F8Kxk8i4PTGjxjZSuVDSuPbXUXhs97HjMsnytXbyqA9LHmTGD/T1De6tO7jd6z9K\n" +
                "dBBUqeVvrY7AJb8aRwh2ERZ93IURkTVe1JRx4eUOnNoD8hwHIN8J0PVY2FIxmEbO\n" +
                "59etadgctfKawpz4oFChIYbbJtQ67HsvG3BjYLHcPerv0wUu7H3ULRTDOxYoUPhq\n" +
                "tESO29cs752NSh/uRZeKiKwwA2oL48ZFDorHf4IihDCPku3KnZ+21lkL0/lDsTmF\n" +
                "mY9wYOtvdH9IU9rFcWbhbGSM1c5+d4ce1d6Zji85fz++9BNLR243f/lYBo8OKezK\n" +
                "UTlShV/Y+yoUN6NeexaFfx/p3TOTp4yLnJig8Goe8pvqc/672pvTGdqoGLVI2LD4\n" +
                "EVMKFxsyUKi/NYJpUKWIy7qoWgnM/QdEoXaXSwc3gPm5z/cv8fHzIckzRk5dDfDA\n" +
                "Iej9RwvoKhw8ROXRjoqr6FP0DzDgpn/1UGELMxg2ekq9RUsFjuJCgVYkNTAp8XAM\n" +
                "6oeTfSv9Dq5s+B8Iae7n6g5gl1kjwIkklDu+9dCOPUC790T079UYkPNvpTHck6UX\n" +
                "b1OuQns4SJiYiWARnj+zmbi8ShsvE8U3roCIchlW3Lf6K3EBtTMSPUFe2lMMytUr\n" +
                "-----END RSA PRIVATE KEY-----\n";
        String pemKey = CSRTools.decryptionPemKey(encKeyPem, "123");
        System.out.println(pemKey);
    }
}
