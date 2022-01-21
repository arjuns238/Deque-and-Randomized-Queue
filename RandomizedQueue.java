/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int n;
    private Item[] arr;

    // construct an empty randomized queue
    public RandomizedQueue() {
        arr = (Item[]) new Object[1];
        n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (n == arr.length) resize(2 * arr.length);
        arr[n++] = item;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        int counter = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[counter] != null) {
                copy[counter] = arr[counter];
                counter++;
            }
            else break;
        }
        arr = copy;
    }


    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        int randomIndex = StdRandom.uniform(n);
        Item item = arr[randomIndex];
        arr[randomIndex] = arr[n - 1];
        arr[n - 1] = null;
        n--;
        if (n > 0 && n == (arr.length / 4)) resize(arr.length / 2);
        return item;

    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        int random = StdRandom.uniform(n);
        while (true) {
            if (arr[random] == null) {
                random = StdRandom.uniform(n);
            }
            else break;
        }
        return arr[random];
    }


    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int i, count1;
        Item[] lolArray = (Item[]) new Object[n];

        public ArrayIterator() {
            count1 = 0;
            i = 0;
            for (i = 0; i < arr.length; i++) {
                if (arr[i] != null) {
                    lolArray[i] = arr[i];
                }
                else break;
            }
            StdRandom.shuffle(lolArray);
        }

        public boolean hasNext() {
            return count1 < lolArray.length;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            if (isEmpty()) throw new NoSuchElementException();
            return lolArray[count1++];
        }

    }

    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        queue.enqueue("HI");
        queue.enqueue("YES!");
        queue.enqueue("lol!");
        System.out.println(queue.dequeue());
        System.out.println(queue.sample());
        System.out.println(queue.size());
        for (String s : queue)
            StdOut.println(s);
    }
}
