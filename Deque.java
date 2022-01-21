/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int n;            // size of stack
    private Node first;      // Top of stack
    private Node last;

    private class Node {
        private Item item;
        private Node next;
        private Node previous;
    }

    // construct an empty deque
    public Deque() {
        first = new Node();
        last = new Node();
        first = null;
        last = null;
        n = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("Dumbo");
        Node newNode = new Node();
        newNode.item = item;
        if (n == 0) {
            first = newNode;
            last = first;
        }

        else if (n == 1) {

            newNode.next = last;
            last.previous = newNode;
            first = newNode;
        }
        else {
            newNode.next = first;
            first.previous = newNode;
            first = newNode;

        }
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("Dumbo");
        Node newNode = new Node();
        newNode.item = item;
        if (isEmpty()) {
            last = newNode;
            first = newNode;
        }
        else if (n == 1) {
            first.next = newNode;
            newNode.previous = first;
            last = newNode;
        }
        else {

            last.next = newNode;
            newNode.previous = last;
            last = newNode;
        }
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Empty Deque");
        Item item = first.item;
        if (n == 1) {
            first = null;
            last = null;
        }
        else {
            first.next.previous = null;
            first = first.next;
        }
        n--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Empty Deque");
        Item item = last.item;
        if (n == 1) {
            first = null;
            last = null;
        }
        else if (n == 2) {
            first.next = null;
            last = first;
        }
        else {
            last.previous.next = null;
            last = last.previous;
        }
        n--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new LinkedIterator();
    }

    // iterator
    private class LinkedIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException("Cant call remove");
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;

        }
    }

    public static void main(String[] args) {
        Deque<Integer> lol = new Deque<Integer>();
        for (int i = 0; i < 6; i++) {
            lol.addLast(i);
        }
        System.out.println(lol.removeLast());

        for (int i = 0; i < 5; i++) {
            System.out.println(lol.removeFirst());
        }


    }
}
