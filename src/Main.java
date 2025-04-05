
public class Main {
    public static void main(String[] args) {
        LRUCache<Integer, String> lruCache = new LRUCache<>(3);

        System.out.println("Get result: " + lruCache.get(3));
        lruCache.put(3, "Pranav");
        lruCache.put(6, "Sagar");
        System.out.println(lruCache);
        System.out.println("Get result: " + lruCache.get(3));
        System.out.println(lruCache);
        System.out.println("Get result: " + lruCache.get(6));
        System.out.println(lruCache);
        lruCache.put(2, "Pratik");
        System.out.println(lruCache);
        lruCache.put(1, "Mohit");
        System.out.println(lruCache);
    }
}