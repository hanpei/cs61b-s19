import com.sun.corba.se.impl.corba.AsynchInvoke;
import org.junit.Assert;
import org.junit.Test;

import java.util.Deque;

/**
 * Performs some basic linked list tests.
 */
public class ArrayDequeTest {
    @Test
    public void testAddItems() {
        ArrayDeque<String> deque = new ArrayDeque<String>();
        deque.addFirst("1");
        deque.addFirst("2");
        deque.addFirst("3");
        deque.addLast("a");
        deque.addLast("b");
        deque.addLast("c");
        deque.printDeque();

        Assert.assertEquals("{3,2,1,a,b,c}", deque.toString());

        deque.addLast("d");
        deque.addLast("e");
        deque.addLast("f");
        deque.addLast("g");
        deque.addLast("h");
        deque.printDeque();
        Assert.assertEquals("{3,2,1,a,b,c,d,e,f,g,h}", deque.toString());
        Assert.assertEquals(11, deque.size());
    }

    @Test
    public void testRemoveItems() {
        ArrayDeque<String> deque = new ArrayDeque<String>();
        deque.addLast("a");
        deque.addLast("b");
        deque.addLast("c");
        deque.addFirst("1");
        deque.addFirst("2");
        deque.addFirst("3");
        deque.printDeque();
        Assert.assertEquals("{3,2,1,a,b,c}", deque.toString());

        deque.removeFirst();
        deque.removeLast();
        deque.removeLast();
        deque.printDeque();
        Assert.assertEquals("{2,1,a}", deque.toString());
        Assert.assertEquals(3, deque.size());
    }

}