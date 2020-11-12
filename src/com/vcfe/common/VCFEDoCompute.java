package com.vcfe.common;

import java.math.BigInteger;
import java.util.List;

import com.vcfe.module.PublicParam;
import com.vcfe.module.VCFEEvaluateKey;
import com.vcfe.util.PairingSingleton;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.ElementPowPreProcessing;
/**
 * �������ʽ�����У�������ִ�м�����
 * @param poly�û�����Ķ���ʽ
 * @param input�û�����
 * @param pp ��������
 * @param ek �û����ɵ���ֵ��Կ
 * @author Chency
 * <p>date 2017��4��4��20:07:50</p>
 */
public class VCFEDoCompute {

	private Polynomial poly;
	private PublicParam pp;
	private VCFEEvaluateKey ek;
	/**
	 * ���캯��
	 * @param pp	��������
	 * @param ek	��ֵ��Կ
	 */
	public VCFEDoCompute(PublicParam pp,VCFEEvaluateKey ek)
	{
		this.pp = pp;
		this.ek = ek;
		this.poly = ek.getPoly();
	}
	/**
	 * ִ�м���
	 * @param x	����ֵ x
	 * @return ���ؼ�����
	 */
	public ResultAndProof doCompute(Element x)
	{
		ElementPowPreProcessing ppp = x.getElementPowPreProcessing();
		Element[] xi = new Element[this.poly.getDegree()];
		for(int i =0;i<xi.length;i++)
		{
			xi[i] = ppp.pow(BigInteger.valueOf(i));
		}
		ResultAndProof ret = new ResultAndProof(this.poly.EvalAt(x),genProof(xi));
		return ret;
	}
	/**
	 * �������ʽ�����С��������֤�㷨
	 * @param x    (1,x,x^2,...x^n)
	 * @return ����֤��
	 */
	public Element genProof(Element[] x)
	{
		int n = this.poly.getDegree();
		List<Element> ci = this.poly.getCoefficients();
		List<Element> ui = ek.getUi();
		Element proof = PairingSingleton.getInstance().getG1().newOneElement();
		for(int i=1;i<=n;i++)
		{
			Element wi = ui.get(n - i).getImmutable().powZn(ek.getGemma());
			for(int j =1;j<=n;j++)
			{
				if(j == i)
				{
					continue;
				}else
				{
					Element tmp = ui.get(n+j-i);
					wi = wi.mul(tmp.getImmutable().powZn(ci.get(j-1)));
				}
			}
			proof = proof.getImmutable().mul(wi.powZn(x[i-1]));
		}
		return proof;
	}
	public class ResultAndProof{
		public Element getResult() {
			return result;
		}
		public Element getProof() {
			return proof;
		}
		private Element result;
		private Element proof;
		public ResultAndProof(Element result,Element proof)
		{
			this.result = result;
			this.proof = proof;
		}
		public void print()
		{
			System.out.println(this.toString());
		}
		@Override
		public String toString() {
			return "ResultAndProof [\n\tresult=" + result + "\n\t proof=" + proof + "]";
		}
		
	}
}
