//Jules Blaustein
public class RecursiveLinkedCollection<T> implements CollectionInterface<T>
{
	LLNode<T> front;
	int size;
	
	public RecursiveLinkedCollection()
	{
		front = null;
		size=0;
	}
	
	private LLNode<T> recAdd(LLNode<T> node, T element)	
	{
		if(node != null)
			node.setLink(recAdd(node.getLink(), element));
		else
			node = new LLNode<T>(element);
		
		return node;
	}
	
	public boolean add(T element)
	{
		front = recAdd(front, element);
		size++;
		return true;
	}

	private T recGet(LLNode<T> node, T target)
	{
		if(node==null)
			return null;
		
		if(node.getInfo().equals(target))
			return target;
		
		return recGet(node.getLink(), target);
	}
	
	public T get(T target) 
	{
		return recGet(front, target);
	}
	
	private LLNode<T> recRemove(LLNode<T> node, T element)
	{
		if(front.getInfo().equals(element))	//case for calling remove on front element
		{
			front = front.getLink();
			return front;
		}
		
		if(node.getLink().getInfo() != element && node.getLink() != null)
		{
			recRemove(node.getLink(), element);
		}
		
		if(node.getLink().getInfo().equals(element))
		{
			node.setLink(node.getLink().getLink());
		}
		
		return node;
	}
	public boolean remove(T element) 
	{
		front = recRemove(front, element);
		size--;
		return true;
	}
	
	private boolean recContains(LLNode<T> node, T target)
	{
		if(node==null)
			return false;
		
		if(node.getInfo().equals(target))
			return true;
		
		return recContains(node.getLink(), target);
	}

	public boolean contains(T target) 
	{	
		return recContains(front,target);
	}

	public boolean isFull()
	{
		return false;
	}

	public boolean isEmpty()
	{
		return (size==0);
	}

	private int recSize(LLNode<T> node)
	{
		if(node==null)
			return 0;
		
		return 1 + recSize(node.getLink());
	}
	
	public int size() 
	{
		return recSize(front);
	}
	
	public String toString()
	{
		String s = "";
		LLNode<T> temp = front;
		while(temp!=null)
		{
			s+=temp.getInfo();
			
			if(temp.getLink()!=null)
				s+=",";
			
			temp = temp.getLink();
		}
		return s;
	}
}
