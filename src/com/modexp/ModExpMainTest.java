package com.modexp;

import java.math.BigInteger;
import java.util.Random;

import com.vcfe.util.PairingSingleton;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;

/**
 * ����ģ�������㷨����ȷ��
 * @author Administrator
 *
 */
public class ModExpMainTest {

	public final static int NUMS = 1;
	/**
	 * ���Է�������ȷ�ԣ��������Ƿ�ͨ����֤
	 */
	public static void SoluctionTest()
	{
		//���Է�������ȷ��
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
	 * ���Է����ļ���������ȷ�ԣ�����ͨ��modexp������õĽ����ʵ�ʽ���Ƿ���ͬ
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
