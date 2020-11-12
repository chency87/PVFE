package com.vcfe.common;

import java.util.ArrayList;
import java.util.List;

import com.vcfe.util.PairingSingleton;
import com.vcfe.util.Timer;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;

/**
 * ���Զ���ʽ��ȷ��
 * @author Administrator
 *
 */
public class TestPolynomial {
	
	public static void main(String[] args) {
		Pairing pairing = PairingSingleton.getInstance();
		Field Zr = pairing.getZr();
		
//		List<Element> coeff = new ArrayList<Element>(2);
//		coeff.add(Zr.newElement(2));
//		coeff.add(Zr.newElement(5));
//		Polynomial poly = new Polynomial(Zr,2,coeff);
//		poly.print();
//		System.out.println(poly.EvalAt(Zr.newElement(2)));
//		
		
		List<Element> coeff = new ArrayList<Element>();
		int degree = 5000;
		for(int i = 0;i<degree;i++) {
			coeff.add(Zr.newRandomElement());
		}
		
		Polynomial poly = new Polynomial(Zr,coeff.size(),coeff);
		Timer timer = new Timer();
		for (int j =5000;j<5100;j+=10)
		{
			
			timer.start(1);
			System.out.println(poly.EvalAt(Zr.newRandomElement()));
			System.out.println(timer.stop(1));
			System.out.println("===================");
		}
	
//		for(int i =100;i<200;i+=10) {
//		timer.start(2);
//		Element left = pairing.pairing(pairing.getG1().newRandomElement(), pairing.getG2().newRandomElement());
//		Element right = pairing.pairing(pairing.getG1().newRandomElement(), pairing.getG2().newRandomElement()).powZn(Zr.newRandomElement());
//		Element right2 = pairing.pairing(pairing.getG1().newRandomElement(), pairing.getG2().newRandomElement());
//		System.out.println(timer.stop(2));
//		}
	}

}
