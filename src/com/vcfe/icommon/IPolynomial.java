package com.vcfe.icommon;

import com.vcfe.common.Polynomial;

import it.unisa.dia.gas.jpbc.Element;

public interface IPolynomial {
	// return a + b
	Polynomial add(Polynomial b);
	//return a - b
	Polynomial sub(Polynomial b);
	//return a * b
	Polynomial mul(Polynomial b);
	//return a / b
	Polynomial div(Polynomial b);
	//return a % b
	Polynomial remainder(Polynomial b);
	//Evaluate Poly in x  return F(x)
	Element EvalAt(Element x);
	// make monic首一多项式
	Polynomial makeMonic();
	
	public void print();
	public void setToRandom();
	

}
