package com.creatifcubed.clavier;

import scala.math.BigInt;
import java.math.BigInteger;
import java.util.Random;

object Cryptography {
	val RND = new Random();

	def rsaGenKeys(): (BigInt, BigInt, BigInt) = {
		val p = NumberTheory.genPrime(2048);
		val q = NumberTheory.genPrime(2048);
		val n = p * q;
		val phi = (p - NumberTheory.BigIntOne) * (q - NumberTheory.BigIntOne);
		var e = NumberTheory.BigIntZero;
		while (!NumberTheory.areCoprime(e, phi)) {
			e = new BigInt(new BigInteger(NumberTheory.log2BigInt(phi).intValue, RND));
		}
		/* val d = e.modInverse(phi); */
		val d = NumberTheory.modInverse(e, phi);
		return (n, e, d);
	}

	def rsaEncrypt(n: BigInt, e: BigInt, m: BigInt): BigInt = {
		return m.modPow(e, n);
	}

	def rsaDecrypt(n: BigInt, d: BigInt, c: BigInt): BigInt = {
		return c.modPow(d, n);
	}

	def main(args: Array[String]) {
		val (n: BigInt, e: BigInt, d: BigInt) = rsaGenKeys();
		println("N is " + n.toString + ", E is " + e.toString + ", D is " + d.toString);
		val m = new BigInt(new BigInteger("1234"));
		val c = rsaEncrypt(n, e, m);
		println("C is " + c.toString);
		println("M is " + rsaDecrypt(n, d, c).toString);
	}

}
