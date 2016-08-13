/*
 * First create the keystore (to allow SSL protection) by importing the LDAP
 * certificate (cert.pem) with:
 *   keytool -import -keystore keystore -storepass changeit -noprompt -file cert.pem
 *
 * You can get the certificate with OpenSSL:
 *   openssl s_client -connect ldap.server.com:636 </dev/null 2>/dev/null | sed -n '/^-----BEGIN/,/^-----END/ { p }' > cert.pem
 *  
 * Then compile this class with:
 *   javac LdapAuth.java
 *
 * Finally execute it with:
 *   java -Djavax.net.ssl.trustStore=keystore -Djavax.net.ssl.keyStorePassword=changeit LdapAuth <username> <password>
 */

import ldap.LdapController;

public class Main {

    public static void main(String args[]) throws Exception {

        String user = "Vasyan";
        String password = "22222";
        LdapController.getInstance().authenticate("New", "222");
        //test.createUser("New1", "333", "SectionAdmin1");
        //test.deleteUser("New");
        //test.changePass("New", "222");
        //test.authenticate("New", "333");
    }
}