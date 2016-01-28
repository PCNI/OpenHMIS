package org.openhmis.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.openhmis.code.ClientWhenDvOccurred;
import org.openhmis.code.ProjectAvailability;
import org.openhmis.code.ProjectBedType;
import org.openhmis.code.ProjectHouseholdType;
import org.openhmis.code.ProjectYouthAgeGroup;
import org.openhmis.code.YesNoReason;
import org.openhmis.dao.TmpDomesticAbuseDAO;
import org.openhmis.domain.TmpProject;
import org.openhmis.domain.TmpDomesticAbuse;
import org.openhmis.dto.CoCDTO;
import org.openhmis.dto.FunderDTO;
import org.openhmis.dto.DomesticAbuseDTO;

public class DomesticAbuseManager {
	private static final TmpDomesticAbuseDAO tmpDomesticAbuseDAO = new TmpDomesticAbuseDAO();

	public DomesticAbuseManager() {}

	public static DomesticAbuseDTO getDomesticAbuseById(String domesticAbuseId) {
		DomesticAbuseDTO domesticAbuseDTO = DomesticAbuseManager.generateDomesticAbuseDTO(tmpDomesticAbuseDAO.getTmpDomesticAbuseById(Integer.parseInt(domesticAbuseId)));
		return domesticAbuseDTO;
	}

	public static List<DomesticAbuseDTO> getDomesticAbusesByEnrollmentId(String enrollmentId) {
		List<DomesticAbuseDTO> domesticAbuseDTOs = new ArrayList<DomesticAbuseDTO>();

		// Collect the domesticAbuses
		List<TmpDomesticAbuse> tmpDomesticAbuses = tmpDomesticAbuseDAO.getTmpDomesticAbusesByEnrollmentId(Integer.parseInt(enrollmentId));

		// For each domesticAbuse, collect and map the data
		for (Iterator<TmpDomesticAbuse> iterator = tmpDomesticAbuses.iterator(); iterator.hasNext();) {
			TmpDomesticAbuse tmpDomesticAbuse = iterator.next();
			DomesticAbuseDTO domesticAbuseDTO = DomesticAbuseManager.generateDomesticAbuseDTO(tmpDomesticAbuse);
			domesticAbuseDTOs.add(domesticAbuseDTO);
		}
		return domesticAbuseDTOs;

	}
	
	public static DomesticAbuseDTO addDomesticAbuse(DomesticAbuseDTO inputDTO) {
		// Generate a PathClient from the input
		TmpDomesticAbuse tmpDomesticAbuse = DomesticAbuseManager.generateTmpDomesticAbuse(inputDTO);
		
		// Set Export fields
		tmpDomesticAbuse.setDateCreated(new Date());
		tmpDomesticAbuse.setDateUpdated(new Date());
		
		// Save the client to allow secondary object generation
		tmpDomesticAbuseDAO.save(tmpDomesticAbuse);
		inputDTO.setDomesticAbuseId(tmpDomesticAbuse.getDomesticAbuseId().toString());
		
		// Return the resulting VO
		return DomesticAbuseManager.generateDomesticAbuseDTO(tmpDomesticAbuse);
	}
	
	public static DomesticAbuseDTO updateDomesticAbuse(DomesticAbuseDTO inputDTO) {
		// Generate a DomesticAbuse from the input
		TmpDomesticAbuse tmpDomesticAbuse = DomesticAbuseManager.generateTmpDomesticAbuse(inputDTO);
		tmpDomesticAbuse.setDomesticAbuseId(Integer.parseInt(inputDTO.getDomesticAbuseId()));
		tmpDomesticAbuse.setDateUpdated(new Date());
		
		// Update the object
		tmpDomesticAbuseDAO.update(tmpDomesticAbuse);
		
		// Return the resulting VO
		return DomesticAbuseManager.generateDomesticAbuseDTO(tmpDomesticAbuse);

	}
	
	public static boolean deleteDomesticAbuse(String domesticAbuseId) {
		TmpDomesticAbuse tmpDomesticAbuse = tmpDomesticAbuseDAO.getTmpDomesticAbuseById(Integer.parseInt(domesticAbuseId));
		tmpDomesticAbuseDAO.delete(tmpDomesticAbuse);
		return true;
	}
	
	public static DomesticAbuseDTO generateDomesticAbuseDTO(TmpDomesticAbuse tmpDomesticAbuse) {
		DomesticAbuseDTO domesticAbuseDTO = new DomesticAbuseDTO();

		domesticAbuseDTO.setDomesticAbuseId(tmpDomesticAbuse.getDomesticAbuseId().toString());
		domesticAbuseDTO.setEnrollmentId(tmpDomesticAbuse.getEnrollmentId().toString());

		// Program Specific Data Standards: Domestic Abuse (2014, 4.11)
		domesticAbuseDTO.setInformationDate(tmpDomesticAbuse.getInformationDate());
		domesticAbuseDTO.setDomesticViolenceVictim(YesNoReason.valueByCode(tmpDomesticAbuse.getDomesticViolenceVictim()));
		domesticAbuseDTO.setWhenOccurred(ClientWhenDvOccurred.valueByCode(tmpDomesticAbuse.getWhenOccurred()));

		// Export Standard Fields
		domesticAbuseDTO.setDateCreated(tmpDomesticAbuse.getDateCreated());
		domesticAbuseDTO.setDateUpdated(tmpDomesticAbuse.getDateUpdated());
		
		return domesticAbuseDTO;
	}
	
	public static TmpDomesticAbuse generateTmpDomesticAbuse(DomesticAbuseDTO inputDTO) {
		TmpDomesticAbuse tmpDomesticAbuse = new TmpDomesticAbuse();
		
		tmpDomesticAbuse.setEnrollmentId(Integer.parseInt(inputDTO.getEnrollmentId()));

		// Program Specific Data Standards: Domestic Abuse (2014, 4.11)
		tmpDomesticAbuse.setInformationDate(inputDTO.getInformationDate());
		tmpDomesticAbuse.setDomesticViolenceVictim(inputDTO.getDomesticViolenceVictim().getCode());
		tmpDomesticAbuse.setWhenOccurred(inputDTO.getWhenOccurred().getCode());

		// Export Standard Fields
		tmpDomesticAbuse.setDateCreated(inputDTO.getDateCreated());
		tmpDomesticAbuse.setDateUpdated(inputDTO.getDateUpdated());
		
		return tmpDomesticAbuse;
	}
	
}