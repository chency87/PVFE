package com.vcfe.common;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.vcfe.icommon.IPolynomial;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.plaf.jpbc.field.poly.PolyElement;
import it.unisa.dia.gas.plaf.jpbc.field.poly.PolyField;

public class Polynomial implements IPolynomial{
	private int degree;
	private List<Element> coefficients = null;
	private Field<Element> field;
	
	public Polynomial(Field<Element> field)
	{
		this(field,new Random(155).nextInt(155),new ArrayList<Element>());
	}
	public Polynomial(Field<Element> field,int degree)
	{
		this(field,degree,new ArrayList<Element>());
	}
	public Polynomial(Field<Element> field,int degree,List<Element> coefficients)
	{
		this.field = field;
		
		this.coefficients = coefficients;
		if(degree == coefficients.size())
		{
			this.degree = degree;
		}else
		{
			this.degree = coefficients.size();
		}
		
	}
	@Override
	public void setToRandom()
	{
		for(int i=0;i<this.degree;i++)
		{
			this.coefficients.add(i, field.newRandomElement());
		}
	}
	public Field<Element> getField() {
		return this.field;
	}
	public int getDegree() {
		return this.degree;
	}
	public void setDegree(int degree)
	{
		this.degree = degree;
	}
	public List<Element> getCoefficients() {
		return this.coefficients;
	}
	public Element getCoefficient(int index) {
		return this.coefficients.get(index);
	}
	public void setCoefficients(List<Element> coeff)
	{
		this.coefficients = coeff;
		this.degree = coeff.size();
	}
	public void setCoefficient(Element e,int index)
	{
		if (index > this.degree)
			throw  new IllegalStateException("Index Out Of Range");
		else
			this.coefficients.set(index, e);
		this.degree = coefficients.size();
	}
	@Override
	public void print()
	{
		System.out.println("Polynomial Info:");
		System.out.println("\tDegree: " + this.getDegree());
		System.out.print("\tCoefficients: [" );
		for(Element e : coefficients)
		{
			System.out.print(e+" ");
		}
		System.out.println("]");
	}
	//*********************************·Ö½çÏß******************************************//

	@Override
	public Element EvalAt(Element x) {
		int n = this.getDegree();
		Element result = this.field.newZeroElement();
		for(int i = 0; i< n;  i++)
		{
			Element e = x.getImmutable().pow(BigInteger.valueOf(i));
			result = result.add(coefficients.get(i).getImmutable().mul(e));
		}
		return result;
	}
	@Override
	public Polynomial makeMonic() {
		int n = this.coefficients.size();
	    if (n == 0)
	    	return this;
        Element e0 = coefficients.get(n - 1);
        e0.invert();
        for (int i = 0; i < n - 1; i++) {
            coefficients.get(i).mul(e0);
        }
        e0.setToOne();
        return this;		
	}
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer("[");

        for (Element e : coefficients) {
            buffer.append(e).append(" ");
        }
        buffer.append("]");
        return buffer.toString();
	}
	@Override
	public Polynomial add(Polynomial b) {
		throw  new IllegalStateException("Method not support");
	}
	@Override
	public Polynomial sub(Polynomial b) {
		throw  new IllegalStateException("Method not support");
	}
	@Override
	public Polynomial mul(Polynomial b) {
		throw  new IllegalStateException("Method not support");
	}
	@Override
	public Polynomial div(Polynomial b) {
		throw  new IllegalStateException("Method not support");
	}
	@Override
	public Polynomial remainder(Polynomial b) {
		throw  new IllegalStateException("Method not support");
	}
	
	
	
	
}
