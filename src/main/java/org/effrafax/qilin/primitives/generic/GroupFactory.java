package org.effrafax.qilin.primitives.generic;

import java.math.BigInteger;
import java.util.Random;

import qilin.primitives.CyclicGroup;

public class GroupFactory {

	public static CyclicGroup<Integer> cyclicOfOrder(int order) {
		return new Z_n(order);
	}
}

class Z_n implements CyclicGroup<Integer> {

	private final Integer order;

	public Z_n(int order) {
		this.order = order;
	}

	@Override
	public Integer add(Integer u, Integer v) {
		return (u + v) % order;
	}

	@Override
	public boolean contains(Integer u) {
		return 0 <= u && u < order;
	}

	@Override
	public Integer multiply(Integer u, BigInteger k) {
		Integer accumalator = 0;
		BigInteger count = BigInteger.valueOf(0);
		while (count.compareTo(k) < 0) {
			accumalator = add(accumalator, u);
			count = count.add(BigInteger.ONE);
		}
		return accumalator;
	}

	@Override
	public Integer negate(Integer u) {
		return -u;
	}

	@Override
	public BigInteger orderUpperBound() {
		return BigInteger.valueOf(order);
	}

	@Override
	public Integer sample(Random random) {
		return random.nextInt(order);
	}

	@Override
	public Integer zero() {
		return 0;
	}

	@Override
	public Integer getGenerator() {
		return 1;
	}

}
