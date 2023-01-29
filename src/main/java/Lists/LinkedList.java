package Lists;

import java.util.Iterator;

import Interfaces.OrderedListADT;

public class LinkedList<T> implements OrderedListADT<T> {
    protected LinearNode<T> head = null;
    protected LinearNode<T> tail = null;
    protected int count = 0;
    protected int modCount = 0;

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            T result = this.head.getElement();
            this.head = this.head.getNext();
            --this.count;
            if (isEmpty()) {
                this.tail = null;
            } 
            ++this.modCount;
            return result;
        }
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            T result = this.tail.getElement();
            if (this.count == 1) {
                this.head = null;
                this.tail = null;
            } else {
                LinearNode<T> current = this.head;
                while (current.getNext() != this.tail) {
                    current = current.getNext();
                }
                this.tail = current;
                this.tail.setNext(null);
            }
            --this.count;
            ++this.modCount;
            return result;
        }
    }

    @Override
    public T remove(T element) {
        if (isEmpty()) {
            return null;
        } else {
            T result = null;
            if (this.count == 1) {
                if (this.head.getElement().equals(element)) {
                    result = this.head.getElement();
                    this.head = null;
                    this.tail = null;
                    --this.count;
                    ++this.modCount;
                }
            } else {
                if (this.head.getElement().equals(element)) {
                    result = this.head.getElement();
                    this.head = this.head.getNext();
                    --this.count;
                    ++this.modCount;
                } else {
                    LinearNode<T> current = this.head;
                    while (current.getNext() != null && !current.getNext().getElement().equals(element)) {
                        current = current.getNext();
                    }
                    if (current.getNext() != null) {
                        result = current.getNext().getElement();
                        current.setNext(current.getNext().getNext());
                        --this.count;
                        ++this.modCount;
                    }
                }
            }
            return result;
        }
    }

    public LinearNode<T> getHead() {
        return this.head;
    }

    @Override
    public T first() {
        if (isEmpty()) {
            return null;
        } else {
            return this.head.getElement();
        }
    }

    @Override
    public T last() {
        if (isEmpty()) {
            return null;
        } else {
            return this.tail.getElement();
        }
    }

    @Override
    public boolean contains(T target) {
        if (isEmpty()) {
            return false;
        } else {
            LinearNode<T> current = this.head;
            while (current != null && !current.getElement().equals(target)) {
                current = current.getNext();
            }
            return current != null;
        }
    }

    public T find(T target) {
        if (isEmpty()) {
            return null;
        } else {
            LinearNode<T> current = this.head;
            while (current != null && !current.getElement().equals(target)) {
                current = current.getNext();
            }
            if (current != null) {
                return current.getElement();
            } else {
                return null;
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }

    @Override
    public int size() {
        return this.count;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator<T>(this.head, this.count);
    }

    @Override
    public void add(T element) {
        if (isEmpty()) {
            this.head = this.tail = new LinearNode<T>(element);
            this.count = 1;
        } else {
            this.tail.setNext(new LinearNode<T>(element));
            this.tail = this.tail.getNext();
            ++this.count;
        }
    }

    private class LinkedListIterator<T> implements Iterator<T>{
        private LinearNode<T> current;
        private int expectedModCount;
        private int count;

        public LinkedListIterator(LinearNode<T> head, int size) {
            this.current = head;
            this.expectedModCount =modCount;
            this.count = size;
        }

        @Override
        public boolean hasNext() {
            return this.count != 0;
        }

        @Override
        public T next() {
            if (!this.hasNext()) {
                return null;
            } else {
                T result = this.current.getElement();
                this.current = this.current.getNext();
                return result;
            }
        }
        
    }
}
  
