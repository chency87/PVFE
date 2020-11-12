package com.vcfe.module;

import java.io.Serializable;
import java.util.List;

import com.vcfe.common.Polynomial;

import it.unisa.dia.gas.jpbc.Element;

/**
 * ��ֵ��Կ����Ҫ���͸�������
 * <p>poly ����Ķ���ʽ</p>
 * <p>ui u^(alpha^i)   1 <= i <= 2n</p>
 * <p>gamme ���Ҳ��� </p>
 * @author Administrator
 *
 */
public class VCFEEvaluateKey implements Serializable{
	private static final long serialVersionUID = 1L;
	public Polynomial getPoly() {
		return poly;
	}
	public void setPoly(Polynomial poly) {
		this.poly = poly;
	}
	public List<Element> getUi() {
		return Ui;
	}
	public void setUi(List<Element> ui) {
		Ui = ui;
	}
	public Element getGemma() {
		return gemma;
	}
	public void setGemma(Element gemma) {
		this.gemma = gemma;
	}
	private Polynomial poly;
	private List<Element> Ui;
	private Element gemma;
}
