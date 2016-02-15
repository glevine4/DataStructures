import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertFalse;

public class CListTest {
    static CList<Integer> l1,l2;

    @Before
    public void setup() {
        l1 = new CList<Integer>();
        l2 = new CList<Integer>();
    }

    @Test
    public void testLength() {
        assertEquals(0,l1.length());
        l1.insert(1);
        l1.insert(3);
        assertEquals(2,l1.length());
    }

    @Test
    public void testInsert() {
        l1.insert(2);
        assertEquals(1,l1.length());
        assertEquals((Integer)2,l1.getValue());
        for (int i = 2; i <= 6; i++) {
            l2.insert(i);
        }
        assertEquals(5,l2.length());
        assertEquals((Integer)6,l2.getValue());
    }

    @Test
    public void testClear() {
        l1.insert(2);
        l1.insert(3);
        l1.clear();
        assertEquals(0,l1.length());
        assertEquals(null, l1.getValue());
        assertEquals(0,l1.currPos());
    }

    @Test
    public void testGetValue() {
        l1.insert(2);
        l1.insert(3);
        assertEquals((Integer)3,l1.getValue());
    }

    @Test
    public void testAppend() {
        l1.insert(1);
        l1.insert(2);
        l1.insert(3);
        l1.append(4);
        assertEquals((Integer)3,l1.getValue());
        l1.next();
        assertEquals((Integer)2,l1.getValue());
        l1.next();
        assertEquals((Integer)1,l1.getValue());
        l1.next();
        assertEquals((Integer)4,l1.getValue());
    }

    @Test
    public void testRemove() {
        assertNull(l1.remove());
        l1.insert(1);
        l1.insert(2);
        l1.insert(3);
        assertEquals((Integer)3,l1.remove());
        assertEquals((Integer)2,l1.remove());
        assertEquals((Integer)1,l1.remove());
        assertEquals(0,l1.length());
        assertNull(l1.getValue());
    }

    @Test
    public void testMoveToStart() {
        l1.insert(1);
        l1.insert(2);
        l1.insert(3);
        l1.insert(4);
        l1.remove();
        l1.next();
        l1.moveToStart();
        assertEquals((Integer)3,l1.getValue());
        l2.insert(1);
        l2.insert(2);
        l2.insert(3);
        l2.insert(4);
        l2.moveToPos(2);
        l2.moveToStart();
        assertEquals((Integer)4,l2.getValue());
    }

    @Test
    public void testMoveToEnd(){
        l1.insert(1);
        l1.insert(2);
        l1.insert(3);
        l1.insert(4);
        l1.moveToEnd();
        assertEquals((Integer)1,l1.getValue());
    }

    @Test
    public void testPrev() {
        l1.insert(1);
        l1.insert(2);
        l1.insert(3);
        l1.insert(4);
        l1.moveToEnd();
        assertEquals((Integer)1,l1.getValue());
        l1.prev();
        assertEquals((Integer)2,l1.getValue());
        l1.prev();
        assertEquals((Integer)3,l1.getValue());
        l1.prev();
        assertEquals((Integer)4,l1.getValue());
        l1.prev();
        assertEquals((Integer)4, l1.getValue());
    }

    @Test
    public void testNext() {
        l1.insert(1);
        l1.insert(2);
        l1.insert(3);
        l1.insert(4);
        assertEquals((Integer)4,l1.getValue());
        l1.next();
        assertEquals((Integer)3,l1.getValue());
        l1.next();
        assertEquals((Integer)2,l1.getValue());
        l1.next();
        assertEquals((Integer)1,l1.getValue());
        l1.next();
        assertEquals((Integer)1, l1.getValue());
    }

    @Test
    public void testCurrPos() {
        l1.insert(1);
        l1.insert(2);
        l1.insert(3);
        l1.insert(4);
        assertEquals(0,l1.currPos());

        l1.moveToPos(3);
        assertEquals((Integer)1,l1.getValue());
        assertEquals(3,l1.currPos());
        l1.moveToPos(0);
        assertEquals(0,l1.currPos());
        l1.next();
        assertEquals(1,l1.currPos());
        l1.next();
        assertEquals(2,l1.currPos());
        l1.next();
        assertEquals(3,l1.currPos());
        l1.next();

        assertEquals(0,l2.currPos());

        l1.moveToEnd();
        assertEquals(3,l1.currPos());
        l1.moveToStart();
        assertEquals(0,l1.currPos());

        l1.remove();
        assertEquals(0,l1.currPos());
        l1.moveToEnd();
        l1.remove();
        assertEquals(0,l1.currPos());
        l1.next();
        l1.remove();
        l1.remove();
        assertEquals(0,l1.currPos());
    }

    @Test
    public void testMoveToPos() {
        l1.insert(1);
        l1.insert(2);
        l1.insert(3);
        l1.insert(4);
        assertEquals(0,l1.currPos());
        l1.moveToPos(3);
        assertEquals((Integer)1,l1.getValue());
        assertEquals(3,l1.currPos());
        l1.moveToPos(1);
        assertEquals(1,l1.currPos());
        assertEquals((Integer)3,l1.getValue());
    }

    @Test
    public void testIsAtEnd() {
      l1.insert(1);
      l1.insert(2);
      l1.insert(3);
      l1.insert(4);
      assertFalse(l1.isAtEnd());
      l1.moveToEnd();
      assertFalse(!l1.isAtEnd());
    }
}
