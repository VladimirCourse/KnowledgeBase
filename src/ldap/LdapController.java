package ldap;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.*;
import java.util.Hashtable;

public class LdapController {

    private static volatile LdapController instance;

    private final String ldapURI = "ldap://localhost";
    private final String contextFactory = "com.sun.jndi.ldap.LdapCtxFactory";

    public static LdapController getInstance() {
        LdapController localInstance = instance;
        if (localInstance == null) {
            synchronized (LdapController.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new LdapController();
                }
            }
        }
        return localInstance;
    }

    private DirContext ldapContext () throws Exception {
        Hashtable<String,String> env = new Hashtable <String,String>();
        return ldapContext(env);
    }

    private DirContext ldapContext (Hashtable <String, String>env) throws Exception {
        env.put(Context.INITIAL_CONTEXT_FACTORY, contextFactory);
        env.put(Context.PROVIDER_URL, ldapURI);
        DirContext ctx = new InitialDirContext(env);
        return ctx;
    }

    private String getUid (String user) throws Exception {
        DirContext ctx = ldapContext();

        //can be changed to cn
        String filter = "(uid=" + user + ")";
        SearchControls ctrl = new SearchControls();
        ctrl.setSearchScope(SearchControls.SUBTREE_SCOPE);
        NamingEnumeration answer = ctx.search("dc=db,dc=test", filter, ctrl);

        String dn;
        if (answer.hasMore()) {
            SearchResult result = (SearchResult) answer.next();
            dn = result.getNameInNamespace();
        }
        else {
            dn = null;
        }

        answer.close();
        return dn;
    }

    public void authenticate (String username, String password) throws Exception {
        String uid = getUid(username);
        if (uid == null){
            throw new Exception("Error: username not exists!");
        }
        Hashtable<String,String> env = new Hashtable <String, String>();
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, uid);
        env.put(Context.SECURITY_CREDENTIALS, password);

        try {
            ldapContext(env);
        }
        catch (javax.naming.AuthenticationException e) {
            throw new Exception("Error: wrong password!");
        }
    }

    private Hashtable getAdminEnv(){
        Hashtable env = new Hashtable();

        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "cn=admin,dc=db,dc=test");
        env.put(Context.SECURITY_CREDENTIALS, "12345");

        return env;
    }

    public void createUser(String username, String password, String type) throws Exception{
        Hashtable env = getAdminEnv();

        String dn = "uid=" + username + ",ou=" + type + ",dc=db,dc=test";

        Attribute newUid = new BasicAttribute("uid", username);
        Attribute pswd = new BasicAttribute("userPassword", password);
        Attribute oc = new BasicAttribute("objectClass");
        oc.add("account");
        oc.add("simpleSecurityObject");
        DirContext ctx = null;

        try {
            ctx = ldapContext(env);

            BasicAttributes entry = new BasicAttributes();
            entry.put(newUid);
            entry.put(pswd);
            entry.put(oc);

            ctx.createSubcontext(dn, entry);
            ctx.close();

        }catch (javax.naming.NameAlreadyBoundException e){
            throw new Exception("Error: user already exists!");
        }
    }

    public void deleteUser(String username) throws Exception{
        Hashtable env = getAdminEnv();
        String uid = getUid(username);
        if (uid == null){
            throw new Exception("Error: username not exists!");
        }

        DirContext ctx = null;
        try {
            ctx = ldapContext(env);
            ctx.destroySubcontext(uid);
            ctx.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void changePass(String username, String password) throws Exception{
        Hashtable env = getAdminEnv();
        String uid = getUid(username);
        if (uid == null){
            throw new Exception("Error: username not exists!");
        }

        DirContext ctx = null;
        try {
            ctx = ldapContext(env);
            Attribute pswd = new BasicAttribute("userPassword", password);
            ModificationItem[] mods = new ModificationItem[1];
            mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, pswd);
            ctx.modifyAttributes(uid, mods);
            ctx.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}



