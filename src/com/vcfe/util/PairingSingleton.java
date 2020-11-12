package com.vcfe.util;

import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

public class PairingSingleton {
	
	/*
	 * 工程公用同一个pairing
	 * Singleton模式
	 */
	private static Pairing pairing = null;
	private PairingSingleton()
	{
	}
	public static Pairing getInstance()
	{
		if(pairing==null)
		{
			synchronized(PairingSingleton.class)
			{
				if(pairing == null)
				{
					pairing = PairingFactory.getPairing("src/com/vcfe/properties/a_181_603.properties");
				}
			}
			
		}
		return pairing;
		
	}

}
