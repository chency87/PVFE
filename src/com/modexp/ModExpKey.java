package com.modexp;

import java.io.Serializable;
import java.util.Arrays;

import it.unisa.dia.gas.jpbc.Element;

public class ModExpKey implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ModExpSKey getSk() {
		return sk;
	}
	public ModExpCalcKey getCk() {
		return ck;
	}
	private ModExpSKey sk;
	private ModExpCalcKey ck;
	public ModExpKey()
	{
		this.sk = new ModExpSKey();
		this.ck = new ModExpCalcKey();
	}
	
	public void print()
	{
		System.out.println(this.sk.toString());
		System.out.println(this.ck.toString());
	}
	
	public class ModExpSKey
	{
		/*
		 */
		@Override
		public String toString() {
			return "ModExpSKey [gk3=" + gk3 + ", t1=" + t1 + ", gk4=" + gk4 + ", t2=" + t2 + ", r=" + r + "]";
		}
		
		public Element getGk3() {
			return gk3;
		}
		public void setGk3(Element gk3) {
			this.gk3 = gk3;
		}
		public int getT1() {
			return t1;
		}
		public void setT1(int t1) {
			this.t1 = t1;
		}
		public Element getGk4() {
			return gk4;
		}
		public void setGk4(Element gk4) {
			this.gk4 = gk4;
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
		private Element gk3;
		private int t1;
		private Element gk4;
		private int t2;
		private int r;
//		public ModExpSKey getModExpSKey()
//		{
//			return new ModExpSKey();
//		}
	}
	public class ModExpCalcKey 
	{
		public Element getY1() {
			return y1;
		}
		public void setY1(Element y1) {
			this.y1 = y1;
		}
		public Element getH1w1() {
			return h1w1;
		}
		public void setH1w1(Element h1w1) {
			this.h1w1 = h1w1;
		}
		public Element getY2() {
			return y2;
		}
		public void setY2(Element y2) {
			this.y2 = y2;
		}
		public Element getH2w2() {
			return h2w2;
		}
		public void setH2w2(Element h2w2) {
			this.h2w2 = h2w2;
		}
		public Element[] getW1() {
			return w1;
		}
		public void setW1(Element[] w1) {
			this.w1 = w1;
		}
		public Element[] getX1() {
			return x1;
		}
		public void setX1(Element[] x1) {
			this.x1 = x1;
		}
		public Element[] getW2() {
			return w2;
		}
		public void setW2(Element[] w2) {
			this.w2 = w2;
		}
		public Element[] getX2() {
			return x2;
		}
		public void setX2(Element[] x2) {
			this.x2 = x2;
		}
		private Element[] w1;
		private Element[] x1;
		private Element[] w2;
		private Element[] x2;
		private Element y1;
		private Element h1w1;
		private Element y2;
		private Element h2w2;
		@Override
		public String toString() {
			return "ModExpCalcKey [w1=" + Arrays.toString(w1) + ", x1=" + Arrays.toString(x1) + ", w2="
					+ Arrays.toString(w2) + ", x2=" + Arrays.toString(x2) + ", y1=" + y1 + ", h1w1=" + h1w1 + ", y2="
					+ y2 + ", h2w2=" + h2w2 + "]";
		}
		
		
	}
}
