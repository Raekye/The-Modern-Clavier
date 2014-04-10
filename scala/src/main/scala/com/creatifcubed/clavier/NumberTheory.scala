package com.creatifcubed.clavier;

import java.math.BigInteger;
import java.util.Random;
import scala.math.BigInt;

object NumberTheory {
	val LN2 = math.log(2);
	val BigIntZero = new BigInt(BigInteger.ZERO);
	val BigIntOne = new BigInt(BigInteger.ONE);

	val genPrime: (Int) => BigInt = {
		val r: Random = new Random();
		(bitLength: Int) => new BigInt(new BigInteger(bitLength, 256, r)); // TODO: prime check?
	}

	def isPrimeAKS(n: Int): Boolean = {
		return false;
	}

	def logBigInt(x: BigInt): Double = {
		if (x <= BigIntZero) {
			throw new IllegalArgumentException("Log parameter must be greater than 0");
		}
		val maxShift = math.max(x.bitLength - 256, 0);
		val y = x >> maxShift;
		return math.log(y.doubleValue()) + maxShift * LN2;
	}

	def log2BigInt(x: BigInt): Double = {
		return logBigInt(x) / LN2;
	}

	def gcd(a: BigInt, b: BigInt): BigInt = {
		if (b == BigIntZero) {
			return a;
		}
		return gcd(b, a % b);
	}

	def areCoprime(a: BigInt, b: BigInt): Boolean = {
		return gcd(a, b) == 1;
	}

	def extendedEuclidean(a: BigInt, b: BigInt): (BigInt, BigInt, BigInt) = {
		if (a == BigIntZero) {
			return (b, BigIntZero, BigIntOne);
		}
		val (g: BigInt, y: BigInt, x: BigInt) = extendedEuclidean(b % a, a);
		return (g, x - b / a * y, y);
	}

	def modInverse(a: BigInt, m: BigInt): BigInt = {
		if (!areCoprime(a, m)) {
			return BigIntZero;
		}
		return extendedEuclidean(a, m)._2 % m;
	}

	def main(args: Array[String]): Unit = {
		for (i <- 1 until 1 << 16 by 1 << 12) {
			println(logBigInt(new BigInt(BigInteger.valueOf(i))) - math.log(i));
		}
		println(genPrime(512));
		println();
		println(genPrime(1024));
		println();
		println(genPrime(2048));
		println();
		println(genPrime(4096));
		println();
	}
}
