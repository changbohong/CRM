package com.airwxtx.card.action;

public interface CardAction {

	/**
	 * �����ź�/���ֻ��Ų��һ�Ա��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String searchCard() throws Exception;

	/**
	 * ���봴��ҳ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String preSave() throws Exception;

	/**
	 * ������Ա��
	 * 
	 * @return
	 */
	public String saveCard() throws Exception;

	/**
	 * �����޸�ҳ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String preUpdate() throws Exception;

	/**
	 * �޸Ļ�Ա��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception;

}