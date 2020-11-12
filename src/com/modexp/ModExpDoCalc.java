package com.modexp;

import com.vcfe.util.PairingSingleton;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;

/**
 * 服务器执行，根据输入文件执行幂运算
 * @author Administrator
 * @param ModExpCalcKey 用户的求值公钥
 * 
 */
public class ModExpDoCalc {
	protected Field G1;
	private ModExpKey.ModExpCalcKey ck;
	public ModExpDoCalc(ModExpKey.ModExpCalcKey k)
	{
		this.ck = k;
		this.G1 = PairingSingleton.getInstance().getG1();
	}
	/**
	 * 服务器执行计算
	 * @return CalcResult 返回服务器的计算结果和证据
	 * @see CalcResult
	 */
	public CalcResult doCalc()
	{
		CalcResult res = new CalcResult();
		Element h1wiy1 = ck.getH1w1().getImmutable().powZn(ck.getY1());	//(h1w1w2..wn)^y1
		Element h2wiy2 = ck.getH2w2().getImmutable().powZn(ck.getY2());	//(h2w1'w2'..wn')^y2
		
		Element mulOfw12wn = G1.newOneElement();					//w1^x1 * w2^x2 * w3^x3 *...* wn^xn
		Element mulOfw1p2wnp = G1.newOneElement();					//w1'^x1' * w2'^x2' * w3'^x3' *...* wn'^xn'
		
		for(int i =0;i<ck.getW1().length;i++)
		{
			Element tmp = ck.getW1()[i].getImmutable().powZn(ck.getX1()[i]);
			mulOfw12wn = mulOfw12wn.mul(tmp);
			tmp = ck.getW2()[i].getImmutable().powZn(ck.getX2()[i]);
			mulOfw1p2wnp = mulOfw1p2wnp.mul(tmp);
		}
		res.setH1w12wnexpy1(h1wiy1);
		res.setH2w1p2wnpexpy2(h2wiy2);
		res.setW1w2w3wn(mulOfw12wn);
		res.setW1pw2pwnp(mulOfw1p2wnp);
		return res;
	}
	/**
	 * 模幂运算结果
	 * <p>包含服务器的计算结果和对结果的正确性证明的证据</p>
	 * @author Administrator
	 *
	 */
	public class CalcResult
	{
		public Element getW1w2w3wn() {
			return w1w2w3wn;
		}

		public void setW1w2w3wn(Element w1w2w3wn) {
			this.w1w2w3wn = w1w2w3wn;
		}

		public Element getW1pw2pwnp() {
			return w1pw2pwnp;
		}

		public void setW1pw2pwnp(Element ww1pw2pwnp) {
			this.w1pw2pwnp = ww1pw2pwnp;
		}

		public Element getH1w12wnexpy1() {
			return h1w12wnexpy1;
		}

		public void setH1w12wnexpy1(Element h1w12wnexpy1) {
			this.h1w12wnexpy1 = h1w12wnexpy1;
		}

		public Element getH2w1p2wnpexpy2() {
			return h2w1p2wnpexpy2;
		}

		public void setH2w1p2wnpexpy2(Element h2w1p2wnpexpy2) {
			this.h2w1p2wnpexpy2 = h2w1p2wnpexpy2;
		}

		private Element w1w2w3wn;
		private Element w1pw2pwnp;
		private Element h1w12wnexpy1;
		private Element h2w1p2wnpexpy2;
		
		public CalcResult getInstance()
		{
			return new CalcResult();
		}
	}
	


}
