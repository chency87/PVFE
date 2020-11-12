package com.vcfe.module;

import java.io.Serializable;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
/**
 * �û����ر������֤˽Կ��ͨ��ģ�����㻹ԭ
 * @author Administrator
 * @param alpha �û�ѡȡ�������ȫ��
 * @param tag	ģ����������
 * @see restoreSecurityKey
 * @param g	ѡȡ��ȺG1 ������Ԫ
 * @param u	ѡȡ��ȺG2 ������Ԫ
 *
 */
public class VCFESecretKey implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Element getAlpha() {
		return alpha;
	}
	public void setAlpha(Element alpha) {
		this.alpha = alpha;
	}
	public Element getTag() {
		return tag;
	}
	public void setTag(Element tag) {
		this.tag = tag;
	}
	public Element getG() {
		return g;
	}
	public void setG(Element g) {
		this.g = g;
	}
	public Element getU() {
		return u;
	}
	public void setU(Element u) {
		this.u = u;
	}
	private Pairing pairing;
	public Pairing getPairing() {
		return pairing;
	}
	public void setPairing(Pairing pairing) {
		this.pairing = pairing;
	}
	private Element alpha;
	private Element tag;
	private Element g;
	private Element u;
	
	public VCFESecretKey(Pairing pairing,Element alpha,Element tag, Element g,Element u)
	{
		this.pairing = pairing;
		this.alpha = alpha;
		this.tag = tag;
		this.g = g;
		this.u = u;
	}
	@Override
	public String toString() {
		return "VCFESecretKey [pairing=" + pairing + ", alpha=" + alpha + ", tag=" + tag + ", g=" + g + ", u=" + u
				+ "]";
	}
	
}
