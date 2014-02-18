package heap;

public class Entry<T extends Key> {
	T key = null;
	Double value = null;
	public Entry(T key, Double value) {
		super();
		this.key = key;
		this.value = value;
	}
	public T getKey() {
		return key;
	}
	public void setKey(T key) {
		this.key = key;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	
	
		
}