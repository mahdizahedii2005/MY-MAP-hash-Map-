import java.util.Comparator;
import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class MyMap<k, v> {
    nude<k, v>[] nudes = new nude[0];

    private void add (nude<k, v> nude) {
        nude<k, v>[] nud = new nude[nudes.length + 1];
        nud[nud.length - 1] = nude;
        for (int i = 0 ; i < nudes.length ; i++) {
            nud[i] = nudes[i];
        }
        nudes = new nude[nud.length];
        for (int i = 0 ; i < nud.length ; i++) {
            nudes[i] = nud[i];
        }
    }

    private void removeArr (nude<k, v> nude) {
        int remove = -1;
        for (int i = 0 ; i < nudes.length ; i++) {
            if (nudes[i] == nude) {
                remove = i;
            }
        }
        if (remove > -1) {
            nude<k, v>[] nud = new nude[nudes.length - 1];
            for (int i = 0 ; i < remove ; i++) {
                nud[i] = nudes[i];
            }
            for (int i = remove + 1 ; i < nudes.length ; i++) {
                nud[i - 1] = nudes[i];
            }
            nudes = new nude[nud.length];
            for (int i = 0 ; i < nud.length ; i++) {
                nudes[i] = nud[i];
            }
        }
    }

    // TODO: Add all the methods based on the document and make sure the names are the same
    public class nude<k, v> {
        private k key;
        private v value;

        public nude (k key, v value) {
            this.key = key;
            this.value = value;
        }

        public k getKey () {
            return key;
        }

        public void setKey (k key) {
            this.key = key;
        }

        public v getValue () {
            return value;
        }

        public void setValue (v value) {
            this.value = value;
        }

        @Override
        public String toString () {
            return "[" + getKey () + "->" + getValue () + "]";
        }
    }

    private nude<k, v> findNudeByKey (k key) {
        for (nude<k, v> nude : nudes) {
            if (nude.getKey () == key) {
                return nude;
            }
        }
        return null;
    }

    private nude<k, v> findNudeByValue (v value) {
        for (nude<k, v> nude : nudes) {
            if (nude.getValue () == value) {
                return nude;
            }
        }
        return null;
    }

    public v get (k key) {
        if (findNudeByKey (key) != null) {
            return findNudeByKey (key).getValue ();
        }
        return null;
    }

    public v getOrDefault (k key, v value) {
        if (findNudeByKey (key) != null) {
            return findNudeByKey (key).getValue ();
        }
        return value;
    }

    public v put (k key, v value) {
        nude<k, v> nude = new nude<> (key, value);
        if (findNudeByKey (key) != null) {
            v val = findNudeByKey (key).getValue ();
            findNudeByKey (key).setValue (value);
            return val;
        } else {
            add (nude);
        }
        return null;
    }

    public int indexOf (k key) {
        for (int i = 0 ; i < nudes.length ; i++) {
            if (nudes[i].getKey () == key) {
                return i;
            }
        }
        return -1;
    }

    public v putIfAbsent (k key, v value) {
        nude<k, v> nude = new nude<> (key, value);
        v val;
        if (findNudeByKey (key) == null) {
            add (nude);
        } else {
            val = findNudeByKey (key).getValue ();
            return val;
        }
        return null;
    }

    public Object[] keys () {
        Object[] keys = new Object[nudes.length];
        for (int i = 0 ; i < nudes.length ; i++) {
            keys[i] = nudes[i].getKey ();
        }
        return keys;
    }

    public Object[] values () {
        Object[] values = new Object[nudes.length];
        for (int i = 0 ; i < nudes.length ; i++) {
            values[i] = nudes[i].getValue ();
        }
        return values;
    }

    public void putAll (MyMap<? extends k, ? extends v> myMap) {
        for (int i = 0 ; i < myMap.keys ().length ; i++) {
            put ((k) myMap.keys ()[i], (v) myMap.values ()[i]);
        }
    }

    public boolean containsKey (k key) {
        for (Object k : keys ()) {
            if (k == key) {
                return true;
            }
        }
        return false;
    }

    public boolean containsValue (v value) {
        for (Object v : values ()) {
            if (v == value) {
                return true;
            }
        }
        return false;
    }

    public int size () {
        return nudes.length;
    }

    public void clear () {
        nudes = new nude[0];
    }

    public v remove (k key) {
        try {
            v good = findNudeByKey (key).getValue ();
            removeArr (findNudeByKey (key));
            return good;
        } catch (NullPointerException r) {
            return null;
        }
    }

    public v remove (int index) {
        try {
            v good = nudes[index].getValue ();
            removeArr (findNudeByKey (nudes[index].getKey ()));
            return good;
        } catch (NullPointerException r) {
            return null;
        }
    }

    public void removeAll (k[] keys) {
        for (k key : keys) {
            remove (key);
        }
    }

    public void removeIfValue (Predicate<v> predicate) {
        for (Object value : values ()) {
            if (predicate.test ((v) value)) {
                remove (findNudeByValue ((v) value).getKey ());
            }
        }
    }

    public void removeIfKey (Predicate<k> predicate) {
        for (Object key : keys ()) {
            if (predicate.test ((k) key)) {
                remove ((k) key);
            }
        }
    }

    private k giveLowest (Object[] key, Comparator<k> comparator) {
        k min = (k) key[0];
        for (int i = 0 ; i < key.length ; i++) {
            if (comparator.compare (min, (k) key[i]) > 0) {
                min = (k) key[i];
            }
        }
        return min;
    }

    public void sort (Comparator<k> comparator) {
        nude<k, v>[] sortNude = new MyMap.nude[nudes.length];
        int size = nudes.length;
        for (int i = 0 ; i < size ; i++) {
            k lowestKey = giveLowest (keys (), comparator);
            sortNude[i] = findNudeByKey (lowestKey);
            remove (lowestKey);
        }
        nudes = new nude[size];
        for (int i = 0 ; i < nudes.length ; i++) {
            nudes[i] = sortNude[i];
        }
    }

    public void replaceAll (BiFunction<? super k, ? super v, ? extends v> function) {
        for (nude<k, v> nude : nudes) {
            nude.setValue (function.apply (nude.getKey (), nude.getValue ()));
        }
    }

    public v compute (k key, BiFunction<? super k, ? super v, ? extends v> function) {
        try {
            nude<k, v> nude;
            if (findNudeByKey (key) == null) {
                put (key, function.apply (key, null));
                nude = findNudeByKey (key);
            } else {
                nude = findNudeByKey (key);
                nude.setValue (function.apply (nude.getKey (), nude.getValue ()));
                if (nude.getValue () == null) {
                    remove (key);
                    return null;
                }
            }
            return nude.getValue ();
        } catch (
                Exception w) {
            return null;
        }
    }

    public MyIterator<k> iterator () {
        return new MyIteratorr<k> (this);
    }

    class MyIteratorr<k> implements MyIterator<k> {
        private MyMap<k, v> thisMyMap;
        private Object[] keyList;
        private int future;
        private int current;

        public MyIteratorr (MyMap<k, v> myMap) {
            this.thisMyMap = myMap;
            future = 0;
            current = -1;
            keyList = new Object[thisMyMap.size ()];
            for (int i = 0 ; i < thisMyMap.size () ; i++) {
                keyList[i] = thisMyMap.keys ()[i];
            }
        }

        @Override
        public k nextByAmount (int amount) {

            current = current + amount;
            future = current + 1;
            return (k) keyList[current];
        }

        @Override
        public k prevByAmount (int amount) {
            current = current - amount;
            future = current + 1;
            return (k) keyList[current + 1];
        }

        @Override
        public boolean hasPrevious () {
            return keyList.length > current - 1 && -1 < current;
        }

        @Override
        public k previous () {
            return prevByAmount (1);
        }

        @Override
        public boolean hasNext () {
            return future != thisMyMap.size ();
        }

        @Override
        public k next () {
            return nextByAmount (1);
        }

        @Override
        public void remove () {
            thisMyMap.remove (current);
            current -= 1;
            future -= 1;
        }

        @Override
        public int previousIndex () {
            return current;
        }

        @Override
        public int nextIndex () {
            return future;
        }
    }

    interface MyIterator<k> extends Iterator<k> {
        k nextByAmount (int amount);

        k prevByAmount (int amount);

        boolean hasPrevious ();

        k previous ();

        void remove ();
        // same as Iterator remove

        int previousIndex ();

        int nextIndex ();

    }

    @Override
    public String toString () {
        String a = "";
        for (nude<k, v> nude : nudes) {
            a += nude.toString ();
        }
        return a;
    }
}
