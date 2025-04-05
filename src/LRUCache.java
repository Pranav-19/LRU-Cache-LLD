import java.util.HashMap;
import java.util.Map;

public class LRUCache<K,V> {
    private final int capacity;
    private final Map<K,Node<K,V>> cache;
    private final Node<K,V> head;
    private final Node<K,V> tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>(capacity);
        head = new Node<>(null, null);
        tail = new Node<>(null, null);
        head.next = tail;
        tail.prev = head;
    }

    @Override
    public String toString() {
        Node<K,V> current = head.next;
        StringBuilder str = new StringBuilder();
        while(current != tail){
            str.append("Key: ").append(current.key).append(" Val: ").append(current.value).append(", ");
            current = current.next;
        }
        return str.toString();
    }

    public synchronized V get(K key){
        if(!cache.containsKey(key)){
            System.out.println("Key " + key + " is not present");
            return null;
        }
        Node<K,V> node = cache.get(key);
        moveToHead(node);
        return node.value;
    }

    public synchronized void put(K key, V value){
        if(cache.get(key) != null){
            Node<K,V> node = cache.get(key);
            node.value = value;
            moveToHead(node);
        } else {
            Node<K,V> node = new Node<>(key, value);
            addToHead(node);
            cache.put(key, node);
            if(cache.size() > capacity){
                Node<K,V> leastRecentlyUsedNode = removeTail();
                cache.remove(leastRecentlyUsedNode.key);
            }
        }
    }

    private void removeNode(Node<K,V> node){
        Node<K,V> previousNode = node.prev;
        Node<K,V> nextNode = node.next;
        previousNode.next = nextNode;
        nextNode.prev = previousNode;
    }

    private void addToHead(Node<K,V> node){
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void moveToHead(Node<K,V> node){
        removeNode(node);
        addToHead(node);
    }

    private Node<K,V> removeTail(){
        Node<K,V> nodeToDelete = tail.prev;
        removeNode(nodeToDelete);
        return nodeToDelete;
    }

}
