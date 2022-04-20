public class MyLinkedList<Type extends Comparable <Type>> {

    private class Node {
        public Type item;
        public Node next;

        public Node(Type item) {
            this.item = item;
            this.next = next;
        }

        public Node(Type item, Node next) {
            this.item = item;
            this.next = next;
        }

        public String toString() {

            return item.toString();
        }
    }

    public Node first = null;
    public Node cur = first;
    public Node prev;
    public int size = 0;
    public int comparisons = 0;
    public Type first() {
        Type item = null;
        cur = first;
        if (cur != null) {
            item = cur.item;
        }
        return item;
    }

    public void addBefore(Type item) {
        if (first == null) { //when list is empty
            Node temp = new Node(item);
            first = temp;
        } else if (cur == null) { //when current is null add to last position
            Node tempNode = first;
            Node insertNode = new Node(item, null);
            while (tempNode.next != null) {
                tempNode = tempNode.next;
            }
            tempNode.next = insertNode;
        } else {
            Node tempNode = first;
            Node insertNode = tempNode;
            while (tempNode.item != cur.item) {
                insertNode = tempNode;
                tempNode = tempNode.next;
            }
            if (tempNode.item == cur.item) {
                if (cur.item == first.item) {
                    Node newNode = new Node(item, tempNode);
                    first = newNode;
                    cur = first.next;
                } else {
                    insertNode.next = new Node(item, cur);
                }
            }
        }
        size++;
    }

    public void addAfter(Type item) {
        if (cur != null) {
            Node newNode = new Node(item, cur.next);
            cur.next = newNode;
            ++size;
        }
    }

    public Type current() {
        if (cur != null) {
            return cur.item;
        }
        return null;
    }

    public Type next() {
        if (cur != null) {
            prev = cur;
            cur = cur.next;
            return cur == null ? null : current();
        }
        return null;
    }

    public Type remove() {
        if (cur != null) {
            Node tempNode = first;
            prev = tempNode;
            if (tempNode.item == cur.item) {
                first = first.next;
                cur = first;
                --size;
                return tempNode.item;
            }
            while (tempNode.item != cur.item) {
                prev = tempNode;
                tempNode = tempNode.next;
            }
            if (tempNode.item == cur.item) {
                prev.next = tempNode.next;
                cur = prev;
                --size;
                return tempNode.item;
            }
        }
        return null;
    }

    public boolean contains(Type item) {
        comparisons++;
        if (size == 0) {
            return false;
        }
        Node tempNode = first;
        while (tempNode != null) {
            comparisons++;
            if (tempNode.item.compareTo(item) == 0) {
                return true;
            }
            tempNode = tempNode.next;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void sort() {
        if (size <= 0) {
            return;
        }
        for (int i = 0; i < size; i++) {
            Node curr = this.first;
            Node next = this.first.next;
            for (int j = 0; j < size - 1; j++) {
                if (curr.item.compareTo(next.item) > 0) {
                    Type temp = curr.item;
                    curr.item = next.item;
                    next.item = temp;
                }
                curr = next;
                next = next.next;
            }
        }
    }

    public String toString() {
        StringBuilder list = new StringBuilder();
        list.append("[");
        Node tempNode = first;

        if (tempNode == null) {
            list.append("]");
        }
        while (tempNode != null) {
            if (tempNode.next == null) {
                list.append(tempNode.item + "]");
            } else {
                list.append(tempNode.item + ", ");
            }
            tempNode = tempNode.next;
        }
        return list.toString();
    }
}