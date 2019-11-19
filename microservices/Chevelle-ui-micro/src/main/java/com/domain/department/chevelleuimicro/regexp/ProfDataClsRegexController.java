package com.domain.department.chevelleuimicro.regexp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/regex")
@CrossOrigin(origins = { "http://localhost", "http://localhost:4200", "https://chevelle-ui-dev.apps.host.domain.com", "https://chevelle-ui-dev.host.domain.com", "https://chevelle-ui-test.apps.host.domain.com", "https://chevelle-ui-test.host.domain.com", "https://chevelle-ui-qa.apps.host.domain.com", "https://chevelle-ui-qa.host.domain.com", "https://chevelle-ui.apps.pcfmi.gm.com", "https://chevelle-ui.cpi.gm.com" }, exposedHeaders = { "ETag", "ETAG", "dg_pending_cnt", "dg_total_cnt" }, methods= { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class ProfDataClsRegexController {

	@Autowired
	public ProfDataClsRegexService profDataClsRegexService;
	
	
	@RequestMapping(method = RequestMethod.GET, path="/getall", produces="application/json")
	public List<ProfDataClsRegex> getAllProfDataClsRegex()
	{
		return profDataClsRegexService.getAllProfDataClsRegex();
	}
	
	
	@RequestMapping(method = RequestMethod.GET, path="/gettholds", produces="application/json")
	public List<DcNameThold> getDcNamesTholds()
	{
		return profDataClsRegexService.getDcNamesTholds();
	}

	@RequestMapping(method = RequestMethod.GET, path="/getnames", produces="application/json")
	public List<DcName> getDcNames()
	{
		return profDataClsRegexService.getDcNames();
	}
	
}
