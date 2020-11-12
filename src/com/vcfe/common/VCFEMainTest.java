package com.vcfe.common;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.modexp.ModExpDoCalc;
import com.modexp.ModExpKey;
import com.modexp.test.DoModExpLocal;
import com.vcfe.module.PublicParam;
import com.vcfe.module.VCFESecretKey;
import com.vcfe.util.PairingSingleton;
import com.vcfe.util.Timer;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;

/**
 * @author Administrator
 *
 */
public class VCFEMainTest {
	public static void CorrectnessTest()
	{
		Pairing pairing = PairingSingleton.getInstance();
		Field Zr = pairing.getZr();
		List<Element> coeff = new ArrayList<Element>(2);
		coeff.add(Zr.newElement(2));
		coeff.add(Zr.newElement(5));
		Polynomial poly = new Polynomial(Zr,2,coeff);
		poly.print();
		System.out.println("EvalAt 2 : " + poly.EvalAt(Zr.newElement(2)));
		
		PublicParam pp =new PublicParam();
		pp.setPairing(PairingSingleton.getInstance());
		pp.setLemada(new BigInteger("1234567890123"));
		Element alpha = Zr.newRandomElement();
		Element gemma = Zr.newRandomElement();
		Element g = pairing.getG1().newRandomElement();
		Element u = pairing.getG2().newRandomElement();
//		pp.print();
		
		VCFEKeyGen keygen = new VCFEKeyGen(poly, pp,g,u,alpha,gemma,Zr.newOneElement());
		ModExpKey key = keygen.modExpKeyGen();
		key.print();
		ModExpDoCalc calc = new ModExpDoCalc(key.getCk());
		ModExpDoCalc.CalcResult res = calc.doCalc();
		VCFESecretKey sk = keygen.restoreSecurityKey(key.getSk(), res);
		System.out.println(sk.toString());
		
		
		if(sk !=null) {
			VCFEDoCompute comp = new VCFEDoCompute(pp,keygen.getEk());
			VCFEDoCompute.ResultAndProof result = comp.doCompute(Zr.newElement(2));
			VCFEDoVerify.doVerify(pp, keygen.getEk(), sk, Zr.newElement(2), result);
		}
	}
	/**
	 * 模幂运算效率验证
	 */
	public static void modExpEfficiency(int degree) {
		Pairing pairing = PairingSingleton.getInstance();
		Field Zr = pairing.getZr();
		List<Element> coeff = new ArrayList<Element>();
		for(int i = 0;i<degree;i++) {
			coeff.add(Zr.newRandomElement());
		}
		Polynomial poly = new Polynomial(Zr,coeff.size(),coeff);
		PublicParam pp =new PublicParam();
		pp.setPairing(PairingSingleton.getInstance());
		pp.setLemada(new BigInteger("1234567890123"));
		Element alpha = Zr.newRandomElement();
		Element gemma = Zr.newRandomElement();
		Element g = pairing.getG1().newRandomElement();
		Element u = pairing.getG2().newRandomElement();
		Timer timer = new Timer();
		timer.start(5);
		new DoModExpLocal(poly, u, alpha);
		System.out.println(timer.stop(5));
		VCFEKeyGen keygen = new VCFEKeyGen(poly, pp,g,u,alpha,gemma,Zr.newOneElement());
		timer.start(3);
		ModExpKey key = keygen.modExpKeyGen();
		double step1 = timer.stop(3);
		System.out.println(step1);
		timer.start(1);
		ModExpDoCalc calc = new ModExpDoCalc(key.getCk());
		ModExpDoCalc.CalcResult res = calc.doCalc();
		System.out.println(timer.stop(1));
		timer.start(2);
		VCFESecretKey sk = keygen.restoreSecurityKey(key.getSk(), res);
		System.out.println(timer.stop(2));
		
	}
	/**
	 * 多项式密钥求值计算效率和验证效率对比
	 * @param args
	 */
	public static void polyEvalEfficiency(int degree) {
		
		Timer timer = new Timer();
		Pairing pairing = PairingSingleton.getInstance();
		Field Zr = pairing.getZr();
		
		List<Element> coeff = new ArrayList<Element>();
		for(int i = 0;i<degree;i++) {
			coeff.add(Zr.newRandomElement());
		}
		Polynomial poly = new Polynomial(Zr,coeff.size(),coeff);
//		poly.print();
//		System.out.println("EvalAt 2 : " + poly.EvalAt(Zr.newElement(2)));
	
		PublicParam pp =new PublicParam();
		pp.setPairing(PairingSingleton.getInstance());
		pp.setLemada(new BigInteger("1234567890123"));
		Element alpha = Zr.newRandomElement();
		Element gemma = Zr.newRandomElement();
		Element g = pairing.getG1().newRandomElement();
		Element u = pairing.getG2().newRandomElement();
		
		VCFEKeyGen keygen = new VCFEKeyGen(poly, pp,g,u,alpha,gemma,Zr.newOneElement());
		ModExpKey key = keygen.modExpKeyGen();
//		key.print();
		ModExpDoCalc calc = new ModExpDoCalc(key.getCk());
		ModExpDoCalc.CalcResult res = calc.doCalc();
		VCFESecretKey sk = keygen.restoreSecurityKey(key.getSk(), res);
//		System.out.println(sk.toString());
		 
		timer.start(1);
		System.out.println("EvalAt 2 : " + poly.EvalAt(Zr.newElement(2)));
		System.out.println(timer.stop(1));
		if(sk !=null) {
			VCFEDoCompute comp = new VCFEDoCompute(pp,keygen.getEk());
			VCFEDoCompute.ResultAndProof result = comp.doCompute(Zr.newElement(2));
//			timer.start(9);
			VCFEDoVerify.doVerify(pp, keygen.getEk(), sk, Zr.newElement(2), result);
//			System.out.println(timer.stop(9));
		}
		System.out.println("============================================");
	}
	public static void main(String[] args) {
		Pairing pairing = PairingSingleton.getInstance();
//		System.out.println(pairing.getG1().getOrder());
//		System.out.println(pairing.getG2().getOrder());
//		 CorrectnessTest();
//		for(int i =10;i<160;i=i+10) {
//			modExpEfficiency(i);	
//			System.out.println("====================================");
//		}
		for(int i =100;i<200;i=i+10) {
			polyEvalEfficiency(i);
		}
//		polyEvalEfficiency(100);
			
	}

}
