/* Copyright (c) 2014 Pathways Community Network Institute
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.openhmis.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.openhmis.dao.EthnicityDAO;
import org.openhmis.domain.Ethnicity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EthnicityDAOImpl extends BaseDAOImpl implements EthnicityDAO 
{
	private static final Logger log = LoggerFactory.getLogger(EthnicityDAOImpl.class);
	
	// default constructor
	public EthnicityDAOImpl()
	{
		
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> findEthnicityCodes()
	{
		log.debug("finding all Ethnicity codes");
		try
		{
			String queryString = "select e.ethnicityKey from Ethnicity e";
			Query queryObject = getSession().createQuery(queryString);
			return (List<Object[]>)queryObject.list();
		}
		catch (RuntimeException re)
		{
			log.error("find Ethnicity codes failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Ethnicity> findEthnicities()
	{
		log.debug("finding all Ethnicities instances");
		try 
		{
			String queryString = "from Ethnicity";
			Query queryObject = getSession().createQuery(queryString);
			return (List<Ethnicity>)queryObject.list();
		}
		catch (RuntimeException re)
		{
			log.error("find Ethnicities failed", re);
			throw re;
		}
	}
}
