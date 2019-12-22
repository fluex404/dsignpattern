package com.fluex404.behavioral;

public class IteratorExample {
    public static void main(String[] args) {
        CollectionofNames collectionofNames = new CollectionofNames();

//        for(Iterator iter = collectionofNames.getIterator();iter.hashNext();) {
//            String name = (String)iter.next();
//            System.out.println("Name : "+name);
//        }
        Iterator iterator = collectionofNames.getIterator();
        while(iterator.hashNext()) {
            System.out.println(iterator.next());
        }
    }
}

interface Iterator{
    boolean hashNext();
    Object next();
}
interface Container{
    Iterator getIterator();
}
class CollectionofNames implements Container{
    public String[] names = {"Isa", "Rara", "Indun", "Josh", "Hamed", "Zaa"};

    @Override
    public Iterator getIterator() {
        return new CollectionofNamesIterate();
    }

    public class CollectionofNamesIterate implements Iterator{

        int i;

        @Override
        public boolean hashNext() {
            if(i<names.length) {
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            if(this.hashNext()) {
                return names[i++];
            }
            return null;
        }
    }

}