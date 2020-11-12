package com.vcfe.common;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.modexp.ModExpDoCalc;
import com.modexp.ModExpDoVerify;
import com.modexp.ModExpKey;
import com.modexp.ModExpKeyGen;
import com.vcfe.module.PublicParam;
import com.vcfe.module.VCFEEvaluateKey;
import com.vcfe.module.VCFESecretKey;
import com.vcfe.util.PairingSingleton;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.ElementPowPreProcessing;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;

public class VCFEKeyGen {
	private Polynomial poly;
	private PublicParam publicParam;
	private PseudoRandom pesudoRandom;
	private Element g;
	private Element u;
	private Element alpha;
	private Element gamme;
	private Element k;
	
	private VCFEEvaluateKey ek ;
	public VCFEEvaluateKey getEk() {
		return ek;
	}

	private VCFESecretKey sk;
	
	protected Pairing pairing;
	protected Field G1;
	protected Field G2;
	protected Field Zr;
	protected int degree;
	
	protected List<Element> Ui;
	protected List<Element> Falphai;
	/**
	 * @param p 外包的多项式
	 * @param pub 公共参数
	 */
	public VCFEKeyGen(Polynomial p,PublicParam pub)
	{
		this(p,pub,pub.getPairing().getG1().newRandomElement(),
				pub.getPairing().getG2().newRandomElement(),
				pub.getPairing().getZr().newRandomElement(),
				pub.getPairing().getZr().newRandomElement(),
				pub.getPairing().getZr().newRandomElement());
	}
	/**
	 * @param p  外包的多项式
	 * @param pub	公共参数
	 * @param g		群G1的生成元
	 * @param u		群G2生成元
	 * @param gamme	用户选取，用于扰乱计算
	 * @param k 	用户选取的 伪随机数生成器的私钥 默认 1
	 */
	public VCFEKeyGen(Polynomial p,PublicParam pub,Element g, Element u,Element alpha,Element gamme,Element k)
	{
		this.poly = p;
		this.publicParam = pub;
		this.pairing = pub.getPairing();
		this.G1 = pairing.getG1();
		this.G2 = pairing.getG2();
		this.Zr = pairing.getZr();
		this.g = g;
		this.u = u;
		this.alpha = alpha;
		this.gamme = gamme;
		this.k = k;
		this.degree = p.getDegree();
		this.pesudoRandom = new PseudoRandom(this.alpha,this.k, this.g);
		Ui = new ArrayList<Element>(2 * degree + 2);
		Falphai = new ArrayList<Element>(degree);
		this.ek = new VCFEEvaluateKey();
	}
	
	public ModExpKey modExpKeyGen()
	{
		ElementPowPreProcessing pppAlpha = alpha.getImmutable().getElementPowPreProcessing();
		ElementPowPreProcessing pppU = u.getImmutable().getElementPowPreProcessing();
		for(int i = 1;i <= 2 * degree + 2; i ++)
		{
			Element tmp = pppAlpha.pow(BigInteger.valueOf(i));
			this.Ui.add(pppU.powZn(tmp));
		}
		ek.setUi(Ui);
		ek.setPoly(poly);
		ek.setGemma(gamme);
		for (int i =1;i<=this.degree;i++)
		{
			this.Falphai.add(pesudoRandom.nextRandom(i));
		}
		Element[] base = new Element[degree];//(Element[])this.Falphai.toArray();
		BigInteger[] coeffs = new BigInteger[degree];
		List<Element> coeffsTmp = this.poly.getCoefficients();
		for(int i=0;i<degree;i++)
		{
			coeffs[i] = coeffsTmp.get(i).toBigInteger();
			base[i] = this.Falphai.get(i);
		}
		ModExpKeyGen mod = new ModExpKeyGen(base,coeffs,1,1,2);
		ModExpKey key = mod.genKey();
		return key;
	}
	/**
	 * @param sk 模幂运算中的私钥
	 * @param result	服务器返回的计算结果,包括结果和证据
	 * @return 若验证通过，则返回结果，否则返回null
	 */
	
	public VCFESecretKey restoreSecurityKey(ModExpKey.ModExpSKey sk,ModExpDoCalc.CalcResult result)
	{
		if(sk == null || result == null)
		{
			return null;
		}
		Element ret = ModExpDoVerify.doVerify(sk, result);
		if(ret != null)
		{
			Element tag = this.g.getImmutable().powZn(this.gamme);
			tag = tag.mul(ret);
			this.sk = new VCFESecretKey(pairing, alpha, tag, this.g, u);
			return this.sk;
		}
		return null;
	}
	private class PseudoRandom
	{
		private Element alpha;
		private Element k;
		private Element g;
		protected ElementPowPreProcessing pppAlpha;
		protected ElementPowPreProcessing pppG;
		/**
		 * 
		 * @param alpha	用户选取的私钥
		 
		 * @param k		用户选取
		 */
		public PseudoRandom(Element alpha,Element k,Element g)
		{
			this.alpha = alpha;
			this.k = k;
			this.g = g;
			this.pppAlpha = this.alpha.getElementPowPreProcessing();
			this.pppG = this.g.getElementPowPreProcessing();
		}
		public Element nextRandom(int index)
		{
			Element tmp = pppAlpha.pow(BigInteger.valueOf(index));
			tmp = tmp.mul(k);
			return this.pppG.powZn(tmp);
		}
	}
	
	public static void main(String[] args)
	{
		Pairing pairing = PairingSingleton.getInstance();
		Field Zr = pairing.getZr();
		List<Element> coeff = new ArrayList<Element>(2);
		coeff.add(Zr.newElement(2));
		coeff.add(Zr.newElement(5));
		Polynomial poly = new Polynomial(Zr,2,coeff);
		PublicParam pp =new PublicParam();
		pp.setPairing(PairingSingleton.getInstance());
		Element alpha = Zr.newRandomElement();
		Element gemma = Zr.newRandomElement();
		Element r = Zr.newRandomElement();
		Element g = pairing.getG1().newRandomElement();
		VCFEKeyGen keygen = new VCFEKeyGen(poly, pp,g,g,alpha,gemma,Zr.newOneElement());
//		keygen.keyGen();
	}
}
