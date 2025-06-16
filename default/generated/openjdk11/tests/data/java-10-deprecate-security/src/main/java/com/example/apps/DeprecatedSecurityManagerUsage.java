import java.lang.SecurityManager;

import com.sun.security.auth.PolicyFile;
import com.sun.security.auth.SolarisNumericGroupPrincipal;
import com.sun.security.auth.SolarisNumericUserPrincipal;
import com.sun.security.auth.SolarisPrincipal;
import com.sun.security.auth.X500Principal;
import com.sun.security.auth.module.SolarisLoginModule;
import com.sun.security.auth.module.SolarisSystem;

import java.security.Identity;
import java.security.IdentityScope;
import java.security.Signer;
import java.security.Certificate;
import java.security.Principal;
import java.security.PublicKey;

import java.security.acl.Acl;
import java.security.acl.AclEntry;
import java.security.acl.Group;
import java.security.acl.NotOwnerException;
import java.security.acl.Permission;
import java.security.Principal;
import java.util.Enumeration;
import java.util.Vector;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.security.auth.Policy; // 🚫 Deprecated
import java.security.PermissionCollection;
import java.security.Principal;

public class DeprecatedSecurityManagerUsage extends Identity {

    public static void main(String[] args) {
        // Get the system security manager
        SecurityManager sm = System.getSecurityManager();

        if (sm != null) {
            // ❌ 1. Accessing deprecated field
            boolean isInCheck = sm.inCheck;
            System.out.println("inCheck: " + isInCheck);

            // ❌ 2. Using deprecated getInCheck method
            boolean check = sm.getInCheck();
            System.out.println("getInCheck: " + check);

            // ❌ 3. Using classDepth to check class stack
            int depth = sm.classDepth("java.lang.String");
            System.out.println("classDepth: " + depth);

            // ❌ 4. Using classLoaderDepth
            int loaderDepth = sm.classLoaderDepth();
            System.out.println("classLoaderDepth: " + loaderDepth);

            // ❌ 5. Get current class loader
            ClassLoader cl = sm.currentClassLoader();
            System.out.println("currentClassLoader: " + cl);

            // ❌ 6. Get currently loaded class
            Class<?> loadedClass = sm.currentLoadedClass();
            System.out.println("currentLoadedClass: " + loadedClass);

            // ❌ 7. Check if in a specific class
            boolean inClass = sm.inClass("java.lang.String");
            System.out.println("inClass: " + inClass);

            // ❌ 8. Check if in class loader
            boolean inCL = sm.inClassLoader();
            System.out.println("inClassLoader: " + inCL);

            // ⚠️ 9. Using checkMemberAccess (now throws unless AllPermission)
            try {
                sm.checkMemberAccess(String.class, java.lang.reflect.Member.PUBLIC);
                System.out.println("checkMemberAccess passed.");
            } catch (SecurityException e) {
                System.out.println("checkMemberAccess blocked.");
            }
        } else {
            System.out.println("No SecurityManager installed.");
        }

        // ---

        // Example 1: SolarisPrincipal
        SolarisPrincipal principal = new SolarisPrincipal("username");
        System.out.println("SolarisPrincipal: " + principal.getName());

        // Example 2: SolarisNumericUserPrincipal
        SolarisNumericUserPrincipal userPrincipal = new SolarisNumericUserPrincipal(1000);
        System.out.println("SolarisNumericUserPrincipal: " + userPrincipal.getName());

        // Example 3: SolarisNumericGroupPrincipal
        SolarisNumericGroupPrincipal groupPrincipal = new SolarisNumericGroupPrincipal(2000, true);
        System.out.println("SolarisNumericGroupPrincipal: " + groupPrincipal.getName());

        // Example 4: X500Principal (com.sun.security.auth version, NOT javax)
        X500Principal x500 = new X500Principal("CN=Test, OU=Dev, O=Example, L=City, ST=State, C=US");
        System.out.println("X500Principal: " + x500.getName());

        // Example 5: SolarisSystem info
        SolarisSystem solarisSystem = new SolarisSystem();
        System.out.println("User name from SolarisSystem: " + solarisSystem.getUsername());

        // Example 6: SolarisLoginModule
        SolarisLoginModule loginModule = new SolarisLoginModule();
        Subject subject = new Subject();
        Map<String, Object> sharedState = new HashMap<>();
        Map<String, Object> options = new HashMap<>();

        loginModule.initialize(subject, null, sharedState, options);
        System.out.println("Initialized SolarisLoginModule");

        // Example 7: PolicyFile
        PolicyFile policy = new PolicyFile();
        System.out.println("PolicyFile loaded: " + policy);
    
        // ---

        IdentityScope scope = new IdentityScope("TestScope") {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public Identity getIdentity(String name) {
                return null;
            }

            @Override
            public Identity getIdentity(PublicKey key) {
                return null;
            }

            @Override
            public Enumeration<Identity> identities() {
                return null;
            }
        };

        // Create an Identity (deprecated)
        Identity identity = new Identity("TestIdentity") {
            @Override
            public String toString() {
                return "Deprecated Identity: " + getName();
            }
        };
        System.out.println(identity);

        // Create a Signer (also deprecated)
        Signer signer = new Signer("TestSigner") {
            @Override
            public String toString() {
                return "Deprecated Signer: " + getName();
            }
        };
        System.out.println(signer);

        // Use a Certificate (abstract and deprecated)
        Certificate cert = new Certificate("DeprecatedCert") {
            @Override
            public byte[] getEncoded() {
                return new byte[0];
            }

            @Override
            public void verify(PublicKey key) {}

            @Override
            public void verify(PublicKey key, String sigProvider) {}

            @Override
            public String toString() {
                return "Deprecated Certificate";
            }

            @Override
            public PublicKey getPublicKey() {
                return null;
            }
        };
        System.out.println(cert);

        // ---

        Principal owner = () -> "ownerUser";

        Acl acl = new Acl() {
            private String name = "exampleAcl";
            private Principal ownerPrincipal = owner;
            private Vector<AclEntry> entries = new Vector<>();

            @Override
            public void setName(Principal caller, String name) throws NotOwnerException {
                this.name = name;
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public boolean addEntry(Principal caller, AclEntry entry) throws NotOwnerException {
                return entries.add(entry);
            }

            @Override
            public boolean removeEntry(Principal caller, AclEntry entry) throws NotOwnerException {
                return entries.remove(entry);
            }

            @Override
            public Enumeration<AclEntry> entries() {
                return entries.elements();
            }

            @Override
            public boolean checkPermission(Principal principal, Permission permission) {
                return false; // not implemented
            }

            @Override
            public Enumeration<Principal> getPrincipals() {
                Vector<Principal> principals = new Vector<>();
                principals.add(ownerPrincipal);
                return principals.elements();
            }

            @Override
            public String toString() {
                return "Deprecated ACL Implementation";
            }
        };

        System.out.println("ACL Name: " + acl.getName());
        System.out.println("ACL Entries: " + acl.entries().hasMoreElements());

        // ---

        try {
            // Create a Subject with a dummy Principal
            Principal principal = () -> "testUser";
            Subject subject = new Subject();
            subject.getPrincipals().add(principal);

            // Use the deprecated Policy class
            Policy policy = Policy.getPolicy(); // 🚫 Deprecated usage
            PermissionCollection permissions = policy.getPermissions(subject);

            System.out.println("Permissions retrieved from deprecated javax.security.auth.Policy:");
            permissions.elements().asIterator().forEachRemaining(System.out::println);

        } catch (Exception e) {
            System.err.println("An error occurred: " + e);
        }

    }

}
