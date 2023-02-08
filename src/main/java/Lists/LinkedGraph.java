package Lists;

import java.util.Iterator;

import Interfaces.GraphADT;

public class LinkedGraph<T> implements GraphADT<T>{
    protected int numVertices;
    protected int numEdges;
    protected LinearNode<T> list;
    
    public LinkedGraph()
    {
        numVertices = 0;
        numEdges = 0;
        list = null;
    }
    @Override
    public void addVertex(T vertex)
    {
        LinearNode<T> temp = new LinearNode<T>();
        temp.setElement(vertex);
        temp.setNext(list);
        list = temp;
        numVertices++;
    }
    
    @Override
    public void removeVertex(T vertex)
    {
        LinearNode<T> temp = list;
        LinearNode<T> prev = null;
        while(temp != null)
        {
            if(temp.getElement().equals(vertex))
            {
                if(prev == null)
                {
                    list = temp.getNext();
                    temp.setNext(null);
                }
                else
                {
                    prev.setNext(temp.getNext());
                    temp.setNext(null);
                }
                numVertices--;
                return;
            }
            else
            {
                prev = temp;
                temp = temp.getNext();
            }
        }
    }

    @Override
    public void addEdge(T vertex1, T vertex2)
    {
        LinearNode<T> temp = list;
        LinearNode<T> prev = null;
        while(temp != null)
        {
            if(temp.getElement().equals(vertex1))
            {
                LinearNode<T> temp2 = temp;
                LinearNode<T> prev2 = null;
                while(temp2 != null)
                {
                    if(temp2.getElement().equals(vertex2))
                    {
                        if(prev2 == null)
                        {
                            temp.setNext(temp2.getNext());
                            temp2.setNext(null);
                        }
                        else
                        {
                            prev2.setNext(temp2.getNext());
                            temp2.setNext(null);
                        }
                        numEdges--;
                        return;
                    }
                    else
                    {
                        prev2 = temp2;
                        temp2 = temp2.getNext();
                    }
                }
            }
            else
            {
                prev = temp;
                temp = temp.getNext();
            }
        }
    }
    
    @Override
    public void removeEdge(T vertex1, T vertex2)
    {
        LinearNode<T> temp = list;
        LinearNode<T> prev = null;
        while(temp != null)
        {
            if(temp.getElement().equals(vertex1))
            {
                LinearNode<T> temp2 = new LinearNode<T>();
                temp2.setElement(vertex2);
                temp2.setNext(temp.getNext());
                temp.setNext(temp2);
                numEdges++;
                return;
            }
            else
            {
                prev = temp;
                temp = temp.getNext();
            }
        }
    }

	@Override
	public Iterator<T> iteratorBFS(T startVertex) {
        
	}

	@Override
	public Iterator<T> iteratorDFS(T startVertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<T> iteratorShortestPath(T startVertex, T targetVertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
