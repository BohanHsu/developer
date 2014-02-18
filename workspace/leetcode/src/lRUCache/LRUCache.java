package lRUCache;

import java.util.LinkedList;
import java.util.Iterator;

public class LRUCache {
    private int clock;
    private int count;
    private boolean isFull;
    private int capacity;
    private LinkedList<Unit> cache = null;
    private int leastUsed;
    
    public LRUCache(int capacity) {
        this.clock = 1;
        this.count = 0;
        this.isFull = false;
        this.capacity = capacity;
        this.cache = new LinkedList<Unit>();
        this.leastUsed = 0;
    }
    
    public int get(int key) {
        int value = -1;
        int index = indexOf(key);
        if (index >= 0){
            // this key exists
            Unit u = cache.get(index);
            value = u.value;
            u.clock = getClock();
            if (index == this.leastUsed || cache.get(leastUsed).clock == -1){
                // this element is the current least used element
                // after update this element is(possibily) no longer the least used element
                // need to serach this cache again
                this.leastUsed = findLeastUsedUnit();
            }
        }else{
            // this key not exists
        }
        return value;
    }
    
    public void set(int key, int value) {
        if (!isFull){
            // this cache is not full
        	if (indexOf(key) == -1){
        		// this key is not present in the cache
        		cache.add(new Unit(key,value,-1));
        		this.count++;
        	}
            if(this.count == this.capacity){
                this.isFull = true;
            }
        }else{
            // this cache is full
            // invaild the LRU unit, and add a new one
            cache.remove(leastUsed);
            if (indexOf(key) == -1){
        		// this key is not present in the cache
        		cache.add(new Unit(key,value,-1));
        	}
        }
    }
    
    /**
     * Void -> int
     * return : the index of least used unit in the cache
     */
    private int findLeastUsedUnit(){
        Iterator<Unit> it = this.cache.iterator();
        Unit u = null;
        int index = 0;
        int count = 0;
        int minClock = Integer.MAX_VALUE;
        while(it.hasNext()){
            u = it.next();
            if (u.clock >= 0 && u.clock < minClock) {
                index = count;
            }
            count++;
        }
        return index;
    }
    
    /**
     * int -> int
     * key : the give of the retrivaling value
     * return : the index of this element in the LinkedList iff the key exists in the list
     *          otherwise return -1
     */
    private int indexOf(int key){
        Iterator<Unit> it = this.cache.iterator();
        Unit u = null;
        int count = 0;
        while (it.hasNext()){
            u = it.next();
            if (u.key == key){
                return count;
            }
            count++;
        }
        return -1;
    }
    
    /**
     * Void -> int 
     * return : the current when call this method
     * effect : add 1 to the cache's clock
     */ 
    private int getClock(){
        int now = this.clock;
        this.clock++;
        return now;
    }
    
    /**
     * for test
     */
    private void showCache(){
    	System.out.println("+++++");
    	for (Unit u : this.cache) {
			System.out.println(u);
		}
    	System.out.println();
    }
    
    /**
     * test
     * @param args
     */
    public static void main(String[] args) {
		LRUCache c = new LRUCache(5);
		c.set(1, 1);
		c.showCache();
		c.set(1, 1);
		c.showCache();
		c.set(2, 2);
		c.set(3, 3);
		c.set(4, 4);
		c.set(5, 5);
		c.showCache();
		c.set(6, 6);
		c.showCache();
		int v = 0;
		v = c.get(5);
		System.out.println("value = " + v);
		c.showCache();
		c.set(7, 7);
		c.showCache();
//		System.out.println(c.count);
	}
}

class Unit{
    int key;
    int value;
    int clock;
    
    public Unit(int key, int value, int clock){
        this.key = key;
        this.value = value;
        this.clock = clock;
    }
    
    @Override
    public String toString() {
    	return "key:"+this.key+"\tvalue:" + this.value + "\tclock:" + this.clock;
    }
}