package com.modexp;

import java.math.BigInteger;

import it.unisa.dia.gas.jpbc.Element;

/*
 * Task ģ��������ִ����֤
 */
public class ModExpDoVerify {

	/**
	 * @param sk ��֤��Կ
	 * @param result ���������صļ�����
	 * @return ����֤ͨ���򷵻ؼ����������򷵻�null
	 */
	public static Element doVerify(ModExpKey.ModExpSKey sk,ModExpDoCalc.CalcResult result)
	{
		Element gk3 = sk.getGk3();
		Element gk4 = sk.getGk4();
		
		Element mulOfw1Town = result.getW1w2w3wn();		//w1^x1 * w2^x2 * w3^x3 *...* wn^xn
		Element mulOfw1pTownp = result.getW1pw2pwnp();	//w1'^x1' * w2'^x2' * w3'^x3' *...* wn'^xn'
		
		Element mulOfh1Town = result.getH1w12wnexpy1();		//(h1w1w2..wn)^y1
		Element mulOfh2Town = result.getH2w1p2wnpexpy2();	//(h2w1'w2'..wn')^y2
		
		int t1 = sk.getT1();
		int t2 = sk.getT2();
		int r = sk.getR();
		
		Element tmp = gk3.mul(mulOfw1Town);
		Element tmp2 = mulOfh1Town.pow(BigInteger.valueOf(t1));
		Element res = tmp.mul(tmp2);
		
		tmp = gk4.mul(mulOfw1pTownp);
		tmp2 = mulOfh2Town.pow(BigInteger.valueOf(t2));
		Element proof = tmp.mul(tmp2);
		
		if(res.getImmutable().pow(BigInteger.valueOf(r)).isEqual(proof))
		{
		
			return res;
		}
		System.out.println("ModExp : doVerify Fail");
		return null;
	}
}
