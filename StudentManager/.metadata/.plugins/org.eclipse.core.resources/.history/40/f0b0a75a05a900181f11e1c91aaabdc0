package com.runsystem.datnt.daos.implementations;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.runsystem.datnt.daos.AbstractDao;
import com.runsystem.datnt.daos.interfaces.RecordDao;
import com.runsystem.datnt.entities.Records;

@Repository
public class RecordDaoImpl extends AbstractDao<Integer, Records> implements RecordDao {

	@SuppressWarnings("unchecked")
	public List<Records> selectBySex(String sex) {
		return getSession().getNamedQuery("selectRecordBySex").setParameter("sex", sex).list();
	}

}
