package com.vcfe.common;

import java.math.BigInteger;
import java.util.List;

import com.vcfe.module.PublicParam;
import com.vcfe.module.VCFEEvaluateKey;
import com.vcfe.module.VCFESecretKey;
import com.vcfe.util.Timer;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;

/**
 * �������ʽ�����������֤�㷨
 * @author Administrator
 *
 */
public class VCFEDoVerify {

	/**
	 * @param pp		��������
	 * @param ek		��ֵ��Կ
	 * @param sk		��֤˽Կ
	 * @param x			��������
	 * @param result	��������֤��
	 * @return			�Ƿ�ͨ����֤
	 */
	 
	public static boolean doVerify(PublicParam pp,VCFEEvaluateKey ek,VCFESecretKey sk,Element x,VCFEDoCompute.ResultAndProof result)
	{
		Pairing pairing = pp.getPairing();
		Element tag = sk.getTag();
		Element uixi = pairing.getG2().newOneElement();
		List<Element> ui = ek.getUi();
		int n = ek.getPoly().getDegree();
		for(int i=1;i<=n;i++)
		{
			uixi = uixi.mul(ui.get(n-i).getImmutable().powZn(x.getImmutable().pow(BigInteger.valueOf(i-1))));
		}
		Element g1 = sk.getG().getImmutable().powZn(sk.getAlpha());
		Element un = ui.get(n-1);
		System.out.println("g1 " + g1);
		Timer timer = new Timer();
		timer.start(7);
		Element left = pairing.pairing(tag, uixi);
		Element right = pairing.pairing(g1, un).powZn(result.getResult());
		Element right2 = pairing.pairing(sk.getG(), result.getProof());
		right.mul(right2);
		System.out.println(timer.stop(7));
		if(left.isEqual(right2))
		{
			System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
			return true;
		}else {
			System.out.println("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
			return false;
		}
	}
}
