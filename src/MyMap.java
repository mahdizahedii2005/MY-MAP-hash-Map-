import java.util.Iterator;

public class MyMap<K,V>{
    // TODO: Add all the methods based on the document and make sure the names are the same


    interface MyIterator<K> extends Iterator<K> {
        K nextByAmount(int amount);

        K prevByAmount(int amount);

        boolean hasPrevious();

        K previous();

        void remove();
        // same as Iterator remove

        int previousIndex();

        int nextIndex();
    }
}
