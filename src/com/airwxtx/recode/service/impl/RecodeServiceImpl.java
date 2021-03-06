package com.airwxtx.recode.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airwxtx.card.dao.CardDao;
import com.airwxtx.recode.dao.RecodeDao;
import com.airwxtx.recode.entity.Recode;
import com.airwxtx.recode.service.RecodeService;
import com.airwxtx.utils.ExportExcelUtil;

@Service
public class RecodeServiceImpl implements RecodeService {

	@Autowired
	private RecodeDao recodeDao;

	@Autowired
	private CardDao cardDao;

	@Override
	@Transactional(readOnly = true)
	public List<Recode> findRecodeByFltNoOrRouteWithPage(String fltNo, String route, int page, int pageSize) {
		return recodeDao.findRecodeByFltNoOrRouteWithPage(fltNo, route, page, pageSize);
	}

	@Override
	@Transactional(readOnly = true)
	public int countRecodeWithFltNoOrRoute(String fltNo, String route) {
		return recodeDao.countRecodeWithFltNoOrRoute(fltNo, route);
	}

	@Override
	@Transactional(readOnly = true)
	public Recode loadRecode(Integer recodeId) {
		return recodeDao.loadRecode(recodeId);
	}

	@Override
	@Transactional
	public void deleteRecode(Recode recode) {
		recode = recodeDao.loadRecode(recode.getRecodeId());
		// 恢复对应会员卡的余额
		cardDao.updateCardBalance(recode.getCard(), recode.getConsumption());
		// 删除消费记录
		recodeDao.deleteRecode(recode);
	}

	@Override
	@Transactional(readOnly = true)
	public void exportXlsx(OutputStream out) throws IOException {
		List<Recode> recodes = recodeDao.loadAllRecodes();
		List<String[]> table = new ArrayList<>();
		table.add(
				new String[] { "航班号", "行程", "航班日期", "订票日期", "消费金额（元）", "备注", "会员单位", "会员中文名", "会员英文名", "会员卡号", "操作员" });
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		DecimalFormat df = new DecimalFormat("#,##0.00");
		for (Recode recode : recodes) {
			table.add(new String[] { recode.getFltNo(), recode.getRoute(), sdf.format(recode.getFlightDate()),
					sdf.format(recode.getBookingDate()), df.format(recode.getConsumption()), recode.getComment(),
					recode.getCard().getClient().getCompany(), recode.getCard().getClient().getClientName(),
					recode.getCard().getClient().getClientEnglishName(), recode.getCard().getCardNo(),
					recode.getUser().getUsername() });
		}
		ExportExcelUtil.xlsx(out, table);
	}

	public RecodeDao getRecodeDao() {
		return recodeDao;
	}

	public void setRecodeDao(RecodeDao recodeDao) {
		this.recodeDao = recodeDao;
	}

	public CardDao getCardDao() {
		return cardDao;
	}

	public void setCardDao(CardDao cardDao) {
		this.cardDao = cardDao;
	}

}
