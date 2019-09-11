import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;

import org.junit.jupiter.api.Test;

class SetTester {

	@Test
	void testGetSize() {
		ResizableArraySet a = new ResizableArraySet();
		ResizableArraySet b = new ResizableArraySet(20);
		a.add(new Point(4, 10));
		a.add(new Point(6, 2));
		
		// Set a: added (4, 10) (6, 2) Expected size: 2 Actual Size: a.getSize()
		// Set b: added Expected size: 0 Actual Size: b.getSize()
		assertTrue(a.getSize() == 2);
		assertTrue(b.getSize() == 0);
	}

	@Test
	void testIsEmpty() {
		ResizableArraySet a = new ResizableArraySet();
		ResizableArraySet b = new ResizableArraySet(20);
		a.add(new Point(4, 10));
		a.add(new Point(6, 2));
		
		// Set a: added (4, 10) (6, 2) Expected isEmpty() result: false Actual isEmpty() result: a.isEmpty()
		// Set b: added Expected isEmpty() result: true Actual isEmpty() result: b.isEmpty()
		assertFalse(a.isEmpty());
		assertTrue(b.isEmpty());
	}

	@Test
	void testAdd() {
		ResizableArraySet a = new ResizableArraySet();
		a.add(new Point(4, 10));
		a.add(new Point(6, 2));
		
		// Set a: added (4, 10) (6, 2) Expected contains(4, 10): true Actual contains(4, 10): a.contains(new Point(4, 10))
		assertTrue(a.contains(new Point(4, 10)));
	}

	@Test
	void testRemovePoint() {
		ResizableArraySet a = new ResizableArraySet();
		a.add(new Point(4, 10));
		a.add(new Point(6, 2));
		
		// Set a: added (4, 10) (6, 2) Expected remove(6, 2): true Actual remove(6, 2): a.remove(new Point(6, 2))
		// Set a: added (4, 10) (6, 2) Removed (6,2) Expected remove(6, 2): false Actual remove(6, 2): a.remove(new Point(6, 2))
		assertTrue(a.remove(new Point(6, 2)));
		assertFalse(a.remove(new Point(6, 2)));
	}

	@Test
	void testRemove() {
		ResizableArraySet a = new ResizableArraySet();
		a.add(new Point(4, 10));
		a.add(new Point(6, 2));
		
		// Set a: added (4, 10) (6, 2) Expected remove(): (6, 2) Actual remove(): a.remove()
		// Set a: added (4, 10) (6, 2) Removed (6, 2) Expected remove(): (4, 10) Actual remove(): a.remove()
		// Set a: added (4, 10) (6, 2) Removed (6, 2) (4, 10) Expected remove(): null Actual remove() a.remove()
		assertTrue(a.remove().equals(new Point(6, 2)));
		assertTrue(a.remove().equals(new Point(4, 10)));
		assertNull(a.remove());
	}

	@Test
	void testClear() {
		ResizableArraySet a = new ResizableArraySet();
		a.add(new Point(4, 10));
		a.add(new Point(6, 2));
		
		// Set a: added (4, 10) (6, 2) Expected getSize(): 2 Actual getSize(): a.getSize()
		assertTrue(a.getSize() == 2);
		a.clear();
		// Set a: added (4, 10) (6, 2) cleared Expected getSize(): 0 Actual getSize(): a.getSize()
		assertTrue(a.getSize() == 0);
	}

	@Test
	void testContains() {
		ResizableArraySet a = new ResizableArraySet();
		a.add(new Point(4, 10));
		
		// Set a: added (4, 10) Expected contains(4, 10): true Actual contains(4, 10): a.contains(new Point(4, 10))
		// Set a: added (4, 10) Expected contains(2, 6): false Actual contains(2, 6): a.contains(new Point(2, 6))
		assertTrue(a.contains(new Point(4, 10)));
		assertFalse(a.contains(new Point(2, 6)));
	}

	@Test
	void testUnion() {
		ResizableArraySet a = new ResizableArraySet();
		ResizableArraySet b = new ResizableArraySet();
		a.add(new Point(4, 10));
		b.add(new Point(6, 2));
		
		ResizableArraySet c = (ResizableArraySet) a.union(b);
		// Set a: added (4, 10)
		// Set b: added (6, 2)
		// Set c: union of a and b, expected contains(4, 10): true actual contains(4, 10): c.contains(4, 10)
		// Set c: union of a and b, expected contains(6, 2): true actual contains(6, 2): c.contains(6, 2)
		// Set c: union of a and b, expected getSize(): 2 actual getSize(): c.getSize()
		assertTrue(c.contains(new Point(4, 10)));
		assertTrue(c.contains(new Point(6, 2)));
		assertTrue(c.getSize() == 2);
	}

	@Test
	void testIntersection() {
		ResizableArraySet a = new ResizableArraySet();
		ResizableArraySet b = new ResizableArraySet();
		a.add(new Point(4, 10));
		b.add(new Point(6, 2));
		b.add(new Point(4, 10));
		
		ResizableArraySet c = (ResizableArraySet) a.intersection(b);
		// Set a: added (4, 10)
		// Set b: added (6, 2) (4, 10)
		// Set c: intersection of a and b, expected contains(4, 10): true actual contains(4, 10): c.contains(new Point(4, 10))
		// Set c: intersection of a and b, expected contains(6, 2): false actual contains(6, 2): c.contains(new Point(6, 2))
		// Set c: intersection of a and b, expected getSize(): 1 actual getSize(): c.getSize()
		assertTrue(c.contains(new Point(4, 10)));
		assertFalse(c.contains(new Point(6, 2)));
		assertTrue(c.getSize() == 1);
	}

	@Test
	void testDifference() {
		ResizableArraySet a = new ResizableArraySet();
		ResizableArraySet b = new ResizableArraySet();
		a.add(new Point(4, 10));
		a.add(new Point(6, 2));
		b.add(new Point(4, 10));
		
		ResizableArraySet c = (ResizableArraySet) a.difference(b);
		// Set a: added (4, 10) (6, 2)
		// Set b: added (4, 10)
		// Set c: difference of a and b, expected contains(4, 10): false actual contains(4, 10): c.contains(new Point(4, 10))
		// Set c: difference of a and b, expected contains(6, 2): true actual contains(6, 2): c.contains(new Point(6, 2))
		// Set c: difference of a and b, expected getSize(): 1 actual getSize(): c.getSize()
		assertFalse(c.contains(new Point(4, 10)));
		assertTrue(c.contains(new Point(6, 2)));
		assertTrue(c.getSize() == 1);
	}

	@Test
	void testToArray() {
		ResizableArraySet a = new ResizableArraySet();
		Point c = new Point(4, 10);
		Point d = new Point(6, 2);
		a.add(c);
		a.add(d);
		
		Point[] b = new Point[] {c, d};
		// Set a: added (4, 10) (6, 2)
		// a.toArray() expected: { (4, 10), (6, 2) }
		assertTrue(a.toArray()[0].equals(c));
		assertTrue(a.toArray()[1].equals(d));
	}

}
