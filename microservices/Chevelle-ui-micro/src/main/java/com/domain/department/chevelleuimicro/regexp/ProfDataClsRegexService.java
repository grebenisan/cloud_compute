package com.domain.department.chevelleuimicro.regexp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;


@Service
public class ProfDataClsRegexService {
	
	@Autowired
	private ProfDataClsRegexRepo profDataClsRegexRepo;

	public List<ProfDataClsRegex> getAllProfDataClsRegex()
	{
		return profDataClsRegexRepo.getAllProfDataClsRegex();
	}
	
	public List<DcNameThold> getDcNamesTholds()
	{
		List<ProfDataClsRegex> list_profDataClsRegex = profDataClsRegexRepo.getAllProfDataClsRegex();
		List<DcNameThold> list_dcNameThold = new ArrayList<DcNameThold>();
		
		for (ProfDataClsRegex dc_regex : list_profDataClsRegex)
		{
			list_dcNameThold.add(new DcNameThold(dc_regex));
		}
		
		return list_dcNameThold;
	}
	
	
	public List<DcName> getDcNames()
	{
		List<ProfDataClsRegex> list_profDataClsRegex = profDataClsRegexRepo.getAllProfDataClsRegex();
		List<DcName> list_dcName = new ArrayList<DcName>();
		
		for (ProfDataClsRegex dc_regex : list_profDataClsRegex)
		{
			list_dcName.add(new DcName(dc_regex));
		}
		
		return list_dcName;		
	}
	
	
}
