package XML.xmlmanager.classes;

import XML.xmlmanager.interfaces.Pair;

public class ConcretePair <K, V> implements Pair<K, V>{
	
	private K key;
	private V value;

	public ConcretePair(K key, V value){
		this.key = key;
		this.value = value;
	}
	
	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}

	@Override
	public void setKey(K k) {
		this.key = k;
	}

	@Override
	public void setValue(V v) {
		this.value = v;
	}

}
