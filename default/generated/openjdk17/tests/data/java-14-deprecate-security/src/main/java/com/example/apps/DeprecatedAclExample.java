import java.security.acl.Acl;
import java.security.acl.AclEntry;
import java.security.acl.Group;
import java.security.acl.Permission;
import java.security.acl.Owner;
import java.security.acl.NotOwnerException;
import java.security.Principal;
import java.util.Enumeration;

public class DeprecatedAclExample {
    public static void main(String[] args) {
        // This is just a skeletal example, actual implementation would require
        // custom classes implementing interfaces like Acl, Permission, etc.
        Acl acl = new MyAcl("exampleACL");
        Principal user = new MyPrincipal("user1");
        Permission readPermission = new MyPermission("read");

        try {
            AclEntry entry = new MyAclEntry();
            entry.setPrincipal(user);
            entry.addPermission(readPermission);

            acl.addEntry(user, entry); // ❌ Deprecated API
            System.out.println("Entry added.");
        } catch (NotOwnerException e) {
            e.printStackTrace();
        }
    }
}
