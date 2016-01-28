package org.openhmis.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.openhmis.domain.TmpReferral;

public class TmpReferralDAO extends BaseDAO {

	public TmpReferralDAO() {
	}

	public TmpReferral getTmpReferralById(Integer referralId)  {
		String queryString = "select referral " + 
			"from TmpReferral as referral " + 
			"where referral.referralId =:referralId";

		Session session = getSession();
		Query queryObject = session.createQuery(queryString);
		queryObject.setParameter("referralId", referralId);
		queryObject.setMaxResults(1);
		
		List<TmpReferral> results = queryObject.list();
		session.close();
		
		if(results.size() > 0)
			return (TmpReferral)results.get(0);
		else
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<TmpReferral> getTmpReferralsByEnrollmentId(Integer enrollmentId) {
		String queryString = "select referral " + 
				"from TmpReferral as referral " + 
				"where referral.enrollmentId =:enrollmentId";

		Session session = getSession();
		Query queryObject = session.createQuery(queryString);
		queryObject.setParameter("enrollmentId", enrollmentId);
		List<TmpReferral> results = queryObject.list();
		session.close();
		return results;
	}
}