package XML.xmlmanager.interfaces;

public interface Pair <K, V> {

	K getKey();
	
	V getValue();
	
	void setKey(K k);
	
	void setValue(V v);
	
}
