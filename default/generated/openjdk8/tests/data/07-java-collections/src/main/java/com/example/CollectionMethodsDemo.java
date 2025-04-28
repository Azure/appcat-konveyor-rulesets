import java.util.*;

public class CollectionMethodsDemo {
    public void demonstrateRemoveAll() {
        // Create a sample collection
        Collection<String> collection = new ArrayList<>();
        collection.add("A");
        collection.add("B");
        collection.add("C");

        // Create another collection to remove
        Collection<String> toRemove = new ArrayList<>();
        toRemove.add("B");
        toRemove.add("D");

        // Before Java 8, passing null to removeAll() when the collection is empty would not throw an exception
        System.out.println("Initial collection: " + collection);
        collection.removeAll(toRemove);
        System.out.println("Collection after removeAll(toRemove): " + collection);
        
        // Demonstrating removeAll with null
        try {
            collection.removeAll(null);
        } catch (NullPointerException e) {
            System.out.println("Caught NullPointerException when calling removeAll(null): " + e.getMessage());
        }
    }

    public void demonstrateRetainAll() {
        // Create a sample collection
        Collection<String> collection = new ArrayList<>();
        collection.add("A");
        collection.add("B");
        collection.add("C");

        // Create another collection to retain
        Collection<String> toRetain = new ArrayList<>();
        toRetain.add("B");
        toRetain.add("D");

        // Before Java 8, passing null to retainAll() when the collection is empty would not throw an exception
        System.out.println("Initial collection: " + collection);
        collection.retainAll(toRetain);
        System.out.println("Collection after retainAll(toRetain): " + collection);
        
        // Demonstrating retainAll with null
        try {
            collection.retainAll(null);
        } catch (NullPointerException e) {
            System.out.println("Caught NullPointerException when calling retainAll(null): " + e.getMessage());
        }
    }
}