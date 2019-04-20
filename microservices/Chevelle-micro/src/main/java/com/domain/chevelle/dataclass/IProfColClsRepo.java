/**********************************************************
 * class:		IProfColClsRepo
 * Description:	Interface to be implemented by the data class repository
 * History:		D. Grebenisan, Created
 * 
 **********************************************************/

package com.domain.chevelle.dataclass;

import java.util.List;
import java.sql.Timestamp;


public interface IProfColClsRepo {

	void saveOne(ProfColCls one_profColCls);
	void saveBatch(List<ProfColCls> list_profColCls);
}
