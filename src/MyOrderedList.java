public class MyOrderedList<Type extends Comparable <Type>> {
    private MyArrayList<Type> list = new MyArrayList<>();
    public int comparisons = 0;
    public MyOrderedList(){
    }
    public void add(Type item) {
        comparisons++;
        for (int i = 0; i < list.size(); i++) {
            comparisons++;
            if (list.get(i).compareTo(item) > 0) {
                list.insert(item, i);
                return;
            }
        }
        list.insert(item, list.size());
    }
    public Type remove(Type item) {
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).compareTo(item) == 0) {
                list.remove(i);
                return item;
            }
        }
        return null;
    }
    public Type binarySearch(Type item) {
        return binarySearch(item, 0, list.size() - 1);
    }
    private Type binarySearch(Type item, int start, int end) {
        if (start > end) {
            comparisons++;
            return null;
        }

        comparisons++;
        int mid = start + (end - start) / 2;
        if (list.get(mid).compareTo(item) == 0) {
            comparisons++;
            return list.get(mid);
        }

        comparisons++;
        if (item.compareTo(list.get(mid)) < 0) {
            comparisons++;
            return binarySearch(item, start, mid - 1);
        }
        return binarySearch(item, mid + 1, end);
    }

    public int size() {
        return list.size();
    }
    public boolean isEmpty() {
        return list.isEmpty();
    }
    public int getComparisons() {
        return comparisons;
    }
    public Type get(int index) {
        return list.get(index);
    }
    public String toString() {
        return list.toString();
    }
}
