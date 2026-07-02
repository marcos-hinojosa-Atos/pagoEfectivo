package com.wu.billersplus.connector.cashin.pago.efectivo.online.service;

import javax.ejb.Stateless;

import com.wu.billersplus.framework.cdi.qualifiers.BillerPlusServiceBean;
import com.wu.billersplus.framework.service.ProxyFactory;
import com.wu.billersplus.framework.service.ProxyService;

@Stateless
@BillerPlusServiceBean
public class ProxyFactoryImpl implements ProxyFactory {

	@Override
	public ProxyService createInstance() {
		return new ProxyServiceImpl();
	}

}
