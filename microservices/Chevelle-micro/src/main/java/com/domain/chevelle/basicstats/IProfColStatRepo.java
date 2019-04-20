/**********************************************************
 * class:		IProfColStatRepo
 * Description:	Interface to be implemented by the basic stats repository
 * History:		D. Grebenisan, Created
 * 
 **********************************************************/

package com.domain.chevelle.basicstats;

import java.util.List;


import java.sql.Timestamp;

public interface IProfColStatRepo 
{

	void saveOne(ProfColStat one_profColStat);
	void saveBatch(List<ProfColStat> list_profColStat);
	
}
