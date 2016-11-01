<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="container text-center">
	<form class="form-inline" action="/CRM/card/searchCardAction" method="post"
		 style="padding-top:10px;padding-bottom:10px;">
		<div class="form-group">
			<label for="cardNo">卡号：</label>
			<input class="form-control" name="cardNo" type="text" value="<s:property value='cardNo' />">
		</div>
		<div class="form-group">
			<label for="phone">手机号：</label>
			<input class="form-control" name="phone" type="text" value="<s:property value='phone' />">
		</div>
		<button class="btn btn-primary" type="submit">
			<span class="glyphicon glyphicon-search"></span> 搜索
		</button>
	</form>
	<table class="table table-bordered table-striped table-hover">
		<thead>
			<tr>
				<th class="text-center">#</th>
				<th class="text-center">卡号</th>
				<th class="text-center">状态</th>
				<th class="text-center">手机号</th>
				<th class="text-center">卡校验码</th>
				<th class="text-center">余额（元）</th>
				<th class="text-center">操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="cards" status="st">
				<tr>
					<td><s:property value="#st.index + 1" /></td>
					<td><s:property value="cardNo" /></td>
					<td><s:property value="status" /></td>
					<td><s:property value="phone" /></td>
					<td><s:property value="checkCode" /></td>
					<td>
						<s:text name="global.format.money">
							<s:param value="balance" />
						</s:text>
					</td>
					<td>
						<a href="/CRM/card/cardDetailsAction?cardId=<s:property value='id' />" target="_blank">详情</a>
						<s:if test="status == @com.airwxtx.card.entity.CardStatus@NORMAL">
							&nbsp;/&nbsp;<a href="/CRM/card/freezeCardAction?cardId=<s:property value='id' />">冻结</a>
							&nbsp;/&nbsp;<a href="/CRM/card/cardChargeAction?cardId=<s:property value='id' />">充值</a>
							&nbsp;/&nbsp;<a href="/CRM/card/carPayAction?cardId=<s:property value='id' />" target="_blank">扣款</a>
						</s:if>
						<s:else>
							&nbsp;/&nbsp;<a href="/CRM/user/unfreezeCardAction?cardId=<s:property value='id' />">解冻</a>
						</s:else>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<div class="pagination" style="margin-top:-10px;">
		<a href="#" class="first" data-action="first">&laquo;</a> 
		<a href="#" class="previous" data-action="previous">&lsaquo;</a>
		<input type="text" readonly="readonly" data-max-page="<s:property value='maxPage' />">
		<a href="#" class="next" data-action="next">&rsaquo;</a>
		<a href="#" class="last" data-action="last">&raquo;</a>
	</div>
	<script type="text/javascript">
		$(function(){
			// 拦截搜索动作
			$("form").submit(function(){
				var url = $(this).attr("action");
				var params = {
					cardNo: $("[name='cardNo']").val(),
					phone: $("[name='phone']").val()
				};
				onsearch(url, params);
						
				// 阻止表单默认提交
				return false;
			});
		});
	</script>
</div>