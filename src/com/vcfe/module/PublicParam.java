package com.vcfe.module;

import java.io.Serializable;
import java.math.BigInteger;

import it.unisa.dia.gas.jpbc.Pairing;
/**
 * ��������
 * @param pairing ˫������� 
 * @param lemada ��ȫ����
 * @author Administrator
 *
 */
public class PublicParam implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Pairing getPairing() {
		return pairing;
	}
	public void setPairing(Pairing pairing) {
		this.pairing = pairing;
	}
	public BigInteger getLemada() {
		return lemada;
	}
	public void setLemada(BigInteger lemada) {
		this.lemada = lemada;
	}
	private Pairing pairing;
	private BigInteger lemada;
	
	public void print() {
		System.out.println("Order: " + pairing.getG1().getOrder());
		System.out.println("Order: " + pairing.getG1().getOrder());
		
		System.out.println("g: "+pairing.getG1().newRandomElement());
		System.out.println("u: "+pairing.getG2().newRandomElement());
		System.out.println("pairing degree : "+pairing.getDegree());
		System.out.println("lemada : " + lemada.toString());
	}
	
}
