import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public interface IteratorADT<T> {
    T next() throws ConcurrentModificationException, NoSuchElementException;

    boolean hasNext();

    void remove() throws ConcurrentModificationException, NoSuchElementException, UnsupportedOperationException, EmptyCollectionException;
}