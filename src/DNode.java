
public class DNode<K, V> {
	
	private K key;
	private V value;
	private DNode<K, V> next;
	
	public DNode()
	{
		next = null;
	}
	
	public void setKey(K newKey)
	{
		key = newKey;
	}
	public void setValue(V newValue)
	{
		value = newValue;
	}
	
	public void setNext(DNode<K, V> node)
	{
		next = node;
	}
	public K getKey()
	{
		return key;
	}
	public V getValue()
	{
		return value;
	}
	public DNode<K, V> getNext()
	{
		return next;
	}
}