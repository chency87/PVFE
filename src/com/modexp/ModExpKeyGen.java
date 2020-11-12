package com.modexp;

import java.math.BigInteger;
import java.util.Random;

import com.vcfe.util.PairingSingleton;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;

/*
 * 模幂运算中的生成密钥算法
 * 生成六个元祖(k1, gk1), (k2, gk2), (k3, gk3), (k4, gk4), (k5, gk5)and (k6, gk6)
 * 计算base^exp 的结果，需要生成相应的key
 */

public class ModExpKeyGen {
	
	private ElementPair[] tuple;   //已知是6个元祖，所以直接数组存储
	private Element[] base;				// base^exp <==> u^a
	private BigInteger[] exp = null;				// base^exp <==> u^a

	private Field Zr;
	private Field G1;
	private int n;
	private int t1;
	private int t2;
	private int r;
	
	public ModExpKeyGen(Element[] base,BigInteger[] exp)
	{
		this.Zr = PairingSingleton.getInstance().getZr();
		this.G1 = PairingSingleton.getInstance().getG1();
		this.base = base;
		this.exp = exp;
		this.n = this.exp.length;
		this.t1 = 1;
		this.t2 = 1;
		this.r = 2;
		initTuple();
	}
	
	public ModExpKeyGen(Element[] base,BigInteger[] exp,int r)
	{
		this.Zr = PairingSingleton.getInstance().getZr();
		this.G1 = PairingSingleton.getInstance().getG1();
		this.base = base;
		this.exp = exp;
		this.n = this.exp.length;
		this.t1 = 1;
		this.t2 = 1;
		this.r = r;
		initTuple();
	}
	/**
	 * 模幂运算 密钥生成算法
	 * @param base	底数
	 * @param exp	指数
	 * @param t1	用户选取的扰乱数字  默认 1
	 * @param t2   	用户选取的扰乱数字   默认1
	 * @param r		用户选取的验证数字  默认2
	 */
	public ModExpKeyGen(Element[] base,BigInteger[] exp,int t1,int t2,int r)
	{
		this.Zr = PairingSingleton.getInstance().getZr();
		this.G1 = PairingSingleton.getInstance().getG1();
		this.base = base;
		this.exp = exp;
		this.n = this.exp.length;
		this.t1 = t1;
		this.t2 = t2;
		this.r = r;
		initTuple();
	}
	/**
	 * 返回生成的模幂运算的公私钥
	 * @return    模幂运算的公私钥
	 */
	public ModExpKey genKey()
	{
		ModExpKey key = new ModExpKey();
		ModExpKey.ModExpSKey sk = key.getSk();
		ModExpKey.ModExpCalcKey ck = key.getCk();
		
		Element[] wi = new Element[this.n]; 
		Element[] xi = new Element[this.n];
		Element[] wip = new Element[this.n];
		Element[] xip = new Element[this.n];
		Element v1 = this.tuple[4].getBaseexp().getImmutable();
		Element v2 = this.tuple[5].getBaseexp().getImmutable();
		Element gk3 = this.tuple[2].getBaseexp().getImmutable();
		Element gk4 = this.tuple[3].getBaseexp().getImmutable();
		
		BigInteger k1 = this.tuple[0].getExp();
		BigInteger k3 = this.tuple[2].getExp();
		BigInteger k5 = this.tuple[4].getExp();
		
		BigInteger k2 = this.tuple[1].getExp();
		BigInteger k4 = this.tuple[3].getExp();
		BigInteger k6 = this.tuple[5].getExp();
		
		BigInteger sumOfexp = BigInteger.ZERO;
		for(int i =0;i<n;i++)
		{
			wi[i] = this.base[i].getImmutable().div(v1);
			sumOfexp = sumOfexp.add(this.exp[i]);				//a1 + a2 +...+an
			wip[i] = this.base[i].getImmutable().div(v2);
		}
		//k3 = k5(a1+a2+a3+...+an) - k1t1y1
		BigInteger tmp = k5.multiply(sumOfexp);
		BigInteger k1t1y1 = tmp.subtract(k3);
		Element t1y1 = Zr.newElement(k1t1y1).div(Zr.newElement(k1));
		Element y1 = t1y1.div(Zr.newElement(t1));
		//k4 = k6r(a1+a2+a3+...+an) - k2t2y2
		tmp = k6.multiply(sumOfexp).multiply(BigInteger.valueOf(this.r));
		BigInteger k2t2y2 = tmp.subtract(k4);
		Element t2y2 = Zr.newElement(k2t2y2).div(Zr.newElement(k2));
		Element y2 = t2y2.div(Zr.newElement(t2));
		for(int i =0;i<n;i++)
		{
			xi[i] = Zr.newElement(this.exp[i]).sub(t1y1);
			tmp = this.exp[i].multiply(BigInteger.valueOf(r));
			xip[i] = Zr.newElement(tmp).sub(t2y2);
		}
		sk.setGk3(gk3);
		sk.setGk4(gk4);
		sk.setR(this.r);
		sk.setT1(this.t1);
		sk.setT2(this.t2);
		
		//calc h1 * w1 * w2 *...*wn
		Element mulOfh1w12wn = this.tuple[0].getBaseexp();		//h1
		//calc h2 * w1p * w2p *...*wnp
		Element mulOfh2w1p2wnp = this.tuple[1].getBaseexp();	//h2
		for(int i =0;i<n;i++)
		{
			mulOfh1w12wn = mulOfh1w12wn.mul(wi[i]);
			mulOfh2w1p2wnp = mulOfh2w1p2wnp.mul(wip[i]);
		}
		ck.setH1w1(mulOfh1w12wn);
		ck.setH2w2(mulOfh2w1p2wnp);
		ck.setY1(y1);
		ck.setY2(y2);
		ck.setW1(wi);
		ck.setX1(xi);
		ck.setW2(wip);
		ck.setX2(xip);
		return key;
	}
	private void initTuple()
	{
		Element g = this.G1.newRandomElement().getImmutable();
		Random rnd = new Random(9514376);
		BigInteger exp;
		if(this.tuple == null)
		{
			this.tuple = new ElementPair[6];
		}
		for(int i = 0; i < 6; i ++)
		{
			exp = new BigInteger(rnd.nextInt(15) + 1, new Random(123456789));
			tuple[i] = new ElementPair(g,exp);
		}
	}
	public void print()
	{
		for(int i = 0;i < 6; i ++)
		{
			tuple[i].print();
		}
	}
	private class ElementPair {
		private BigInteger exp;
		public BigInteger getExp() {
			return exp;
		}
		public Element getBaseexp() {
			return baseexp;
		}
		protected Element base;
		private Element baseexp;
		public ElementPair(Element g,BigInteger exp)
		{
			this.exp = exp;
			this.base = g.getImmutable();
			this.baseexp = this.base.pow(exp).getImmutable();
		}
		public void print()
		{
			System.out.println("**************ElementPair**************");
			System.out.println("g  : " + this.base);
			System.out.println("exp: " + this.exp);
			System.out.println("res: " + this.baseexp);
			System.out.println("***************************************");
		}
	} 

	public int getT1() {
		return t1;
	}
	public void setT1(int t1) {
		this.t1 = t1;
	}
	public int getT2() {
		return t2;
	}
	public void setT2(int t2) {
		this.t2 = t2;
	}
	public int getR() {
		return r;
	}
	public void setR(int r) {
		this.r = r;
	}
}
