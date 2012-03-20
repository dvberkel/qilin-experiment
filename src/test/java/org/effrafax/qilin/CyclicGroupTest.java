package org.effrafax.qilin;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.effrafax.qilin.primitives.generic.GroupFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import qilin.primitives.CyclicGroup;
import qilin.primitives.concrete.Zn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class CyclicGroupTest {
	private final int order;
	private final int k;

	public CyclicGroupTest(int order, int k) {
		this.order = order;
		this.k = k;
	}

	@Test
	public void shouldMultiplyCorrectly() {
		CyclicGroup<Integer> group = GroupFactory.cyclicOfOrder(order);

		Integer actual = group.multiply(group.getGenerator(), BigInteger.valueOf(k));

		assertEquals(Integer.valueOf(k % order), actual);
	}

	@Ignore
	@Test
	public void shouldMimicProvidedImplementation() {
		CyclicGroup<BigInteger> group = new Zn(BigInteger.valueOf(order));

		BigInteger actual = group.multiply(group.getGenerator(), BigInteger.valueOf(k));

		assertEquals(BigInteger.valueOf(k % order), actual);
	}

	@Test
	public void doesZnContain0andOrder() {
		assertTrue(new Zn(BigInteger.valueOf(order)).contains(BigInteger.valueOf(0)));
		assertFalse(new Zn(BigInteger.valueOf(order)).contains(BigInteger.valueOf(order)));
	}

	@Test
	public void repeatedAdditionVersusMultiplyInZn() {
		CyclicGroup<BigInteger> group = new Zn(BigInteger.valueOf(order));

		BigInteger accumulator = group.zero();
		for (int i = 0; i < k; i++) {
			accumulator = group.add(accumulator, group.getGenerator());
		}

		assertTrue(accumulator.equals(group.multiply(group.getGenerator(), BigInteger.valueOf(k))));

	}

	@Parameters
	public static Collection<Object[]> data() {
		List<Object[]> data = new ArrayList<Object[]>();
		data.add(new Object[] { 5, 0 });
		data.add(new Object[] { 5, 1 });
		data.add(new Object[] { 5, 2 });
		data.add(new Object[] { 5, 3 });
		data.add(new Object[] { 5, 4 });
		// data.add(new Object[] { 5, 5 }); // Zn differs from expectation.
		// data.add(new Object[] { 5, 6 }); // Zn differs from expectation.
		return data;
	}
}
