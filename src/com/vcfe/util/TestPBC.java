package com.vcfe.util;


import java.math.BigInteger;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.ElementPowPreProcessing;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

public class TestPBC {
	public static void main(String[] args) {
		Pairing pairing = PairingFactory.getPairing("src/com/vcfe/properties/a_181_603.properties");
		Field<Element> Zr = pairing.getZr();
		Field G1 = pairing.getG1();
		
		Element g = G1.newRandomElement();
		System.out.println("g " +g);
		System.out.println(g.getImmutable().pow(BigInteger.valueOf(5)));
		ElementPowPreProcessing ppp = g.getElementPowPreProcessing();
		
		Element out1 = ppp.pow(BigInteger.valueOf(5));
		Element out2 = ppp.pow(BigInteger.valueOf(25));
		System.out.println(out1);
		System.out.println(out2);
		System.out.println("g " +g);
		
	}

}
