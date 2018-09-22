package com.example.check.constants;

import java.util.HashMap;
import java.util.Map;

public final class CommonContants {
	
	public static final Map<String,String> hwCscf = new HashMap<String,String>();
	public static final Map<String,String> zteAdjhost = new HashMap<String,String>();
	public static final Map<String,String> ztePool = new HashMap<String,String>();

	public static final int hwCscfCount = 40;
	public static final int zteAdjhostCount = 51;
	public static final int ztePoolCount = 138;

	static{
		hwCscf.put("addressType", "0");
		hwCscf.put("sipuri", "1");
		hwCscf.put("csValue", "7");
	}
	static{
		zteAdjhost.put("hostGroupId", "3");
		zteAdjhost.put("hostGroupName", "4");
		zteAdjhost.put("heartBeat", "10");
		zteAdjhost.put("unusable", "11");
		zteAdjhost.put("usable", "12");
		zteAdjhost.put("unusableFailed", "13");
		zteAdjhost.put("usableSuccess", "14");
		
		ztePool.put("poolId", "5");
		ztePool.put("csValue", "6");
		ztePool.put("scscf1", "7");
	}

}
