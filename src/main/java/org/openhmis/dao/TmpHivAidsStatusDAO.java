package org.openhmis.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.openhmis.domain.TmpHivAidsStatus;

public class TmpHivAidsStatusDAO extends BaseDAO {

	public TmpHivAidsStatusDAO() {
	}

	public TmpHivAidsStatus getTmpHivAidsStatusById(Integer hivAidsStatusId)  {
		String queryString = "select hivAidsStatus " + 
			"from TmpHivAidsStatus as hivAidsStatus " + 
			"where hivAidsStatus.hivAidsStatusId =:hivAidsStatusId";

		Session session = getSession();
		Query queryObject = session.createQuery(queryString);
		queryObject.setParameter("hivAidsStatusId", hivAidsStatusId);
		queryObject.setMaxResults(1);
		
		List<TmpHivAidsStatus> results = queryObject.list();
		session.close();
		
		if(results.size() > 0)
			return (TmpHivAidsStatus)results.get(0);
		else
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<TmpHivAidsStatus> getTmpHivAidsStatusesByEnrollmentId(Integer enrollmentId) {
		String queryString = "select hivAidsStatus " + 
				"from TmpHivAidsStatus as hivAidsStatus " + 
				"where hivAidsStatus.enrollmentId =:enrollmentId";

		Session session = getSession();
		Query queryObject = session.createQuery(queryString);
		queryObject.setParameter("enrollmentId", enrollmentId);
		List<TmpHivAidsStatus> results = queryObject.list();
		session.close();
		return results;
	}
}