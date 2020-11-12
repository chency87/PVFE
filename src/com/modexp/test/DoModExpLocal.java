package com.modexp.test;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.vcfe.common.Polynomial;
import com.vcfe.util.PairingSingleton;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.ElementPowPreProcessing;
import it.unisa.dia.gas.jpbc.Field;

public class DoModExpLocal {

	private Field Zr;
	private Field G2;
		protected List<Element> Ui;
		public DoModExpLocal(Polynomial poly,Element u,Element alpha)
		{
			this.Zr = PairingSingleton.getInstance().getZr();
			this.G2 = PairingSingleton.getInstance().getG2();
			int degree = poly.getDegree();
			Ui = new ArrayList<Element>();
			List<Element> coeff = poly.getCoefficients();
			ElementPowPreProcessing pppAlpha = alpha.getImmutable().getElementPowPreProcessing();
			ElementPowPreProcessing pppU = u.getImmutable().getElementPowPreProcessing();
			for(int i = 0;i < degree ; i ++)
			{
				Element tmp = pppAlpha.pow(BigInteger.valueOf(i));
				this.Ui.add(pppU.powZn(tmp));
			}
			Element sum = G2.newZeroElement();
			for(int i =0;i<this.Ui.size();i++) {
				Element tmp = this.Ui.get(i).getImmutable().powZn(coeff.get(i));
				sum = sum.getImmutable().add(tmp);
			}
		}

}
