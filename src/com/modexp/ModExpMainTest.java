package com.modexp;

import java.math.BigInteger;
import java.util.Random;

import com.vcfe.util.PairingSingleton;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;

/**
 * 测试模幂运算算法的正确性
 * @author Administrator
 *
 */
public class ModExpMainTest {

	public final static int NUMS = 1;
	/**
	 * 测试方案的正确性，测试其是否通过验证
	 */
	public static void SoluctionTest()
	{
		//测试方案的正确性
		Pairing pairing = PairingSingleton.getInstance();
		Field G1 = pairing.getG1();
		Field Zr = pairing.getZr();
		Random rnd = new Random(4561389);
		int n = 2;
		
		for(int i = 0 ;i <NUMS;i++)
		{
			n = rnd.nextInt(100);
			Element[] ui = new Element[n];
			BigInteger[] ai = new BigInteger[n];
			for(int j = 0;j< n ;j++)
			{
				ui[j] = G1.newRandomElement();
				ai[j] = new BigInteger(15,rnd);
			}
			ModExpKeyGen mod = new ModExpKeyGen(ui,ai,1,1,2);
			ModExpKey key = mod.genKey();
			ModExpKey.ModExpSKey sk = key.getSk();
			ModExpKey.ModExpCalcKey ck = key.getCk();
			ModExpDoCalc calc = new ModExpDoCalc(ck);
			ModExpDoCalc.CalcResult result = calc.doCalc();
			System.out.println(ModExpDoVerify.doVerify(sk, result));
		}
	}
	/**
	 * 测试方案的计算结果的正确性，测试通过modexp方案获得的结果与实际结果是否相同
	 */
	public static void CorrectnessTest()
	{
		Pairing pairing = PairingSingleton.getInstance();
		Field G1 = pairing.getG1();
		Field Zr = pairing.getZr();
		Random rnd = new Random(4561389);
		int n = 2;
		
		for(int i = 0 ;i <NUMS;i++)
		{
			Element localResult = G1.newOneElement();
			n = rnd.nextInt(100);
			Element[] ui = new Element[n];
			BigInteger[] ai = new BigInteger[n];
			for(int j = 0;j< n ;j++)
			{
				ui[j] = G1.newRandomElement();
				ai[j] = new BigInteger(15,rnd);
				
				localResult = localResult.mul(ui[j].getImmutable().pow(ai[j]));
			}
			ModExpKeyGen mod = new ModExpKeyGen(ui,ai,2);
			ModExpKey key = mod.genKey();
			ModExpKey.ModExpSKey sk = key.getSk();
			ModExpKey.ModExpCalcKey ck = key.getCk();
			ModExpDoCalc calc = new ModExpDoCalc(ck);
			ModExpDoCalc.CalcResult result = calc.doCalc();
			Element retResult = ModExpDoVerify.doVerify(sk, result);
			System.out.println(retResult);
			System.out.println(retResult.isEqual(localResult));
		}
	}
	public static void main(String[] args) {
		CorrectnessTest();
//		SoluctionTest();
	}
}
