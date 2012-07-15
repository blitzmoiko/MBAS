<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<html>
<head>
<title>MBAS - Business Associate Page</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:head />
<sj:head />
<sx:head />
<style type="text/css" title="currentStyle">
	@import "/MailToBusinessAssociates/styles/demo_page.css";
	@import "/MailToBusinessAssociates/styles/demo_table_jui.css";
	@import "/MailToBusinessAssociates/styles/base.css";
</style>
<script type="text/javascript" src="/MailToBusinessAssociates/js/jquery.js"></script>
<script type="text/javascript" src="/MailToBusinessAssociates/js/jquery.dataTables.js"></script>
<script type="text/javascript">
	function childOpen(childAction) {
		var popupWindow = window
				.open(
						childAction,
						"_blank",
						"close=no,directories=no,titlebar=no,toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=700,height=300,top=200,left=300");
	}

	$(document).ready(function() {
		 oTable = $('#baTable').dataTable({
		        "bJQueryUI": true,
		        "sPaginationType": "full_numbers"
		 });
	} );	
</script>
</head>
<body>
	<div id="div1">
		<h2>Business Associate Form</h2>
		<br>
		<s:actionerror />
		<s:form action="validateBA">
			<s:hidden name="businessAssociateId" value="%{#session.modelBusinessAssociate.businessAssociateId}" />
			<s:textfield name="firstName" key="label.first_name" required="true" value="%{#session.modelBusinessAssociate.firstName}" />
			<s:textfield name="middleName" key="label.middle_name" value="%{#session.modelBusinessAssociate.middleName}" />
			<s:textfield name="lastName" key="label.last_name" required="true" value="%{#session.modelBusinessAssociate.lastName}" />
			<s:textfield name="email" key="label.email" required="true" value="%{#session.modelBusinessAssociate.email}" />
			<s:radio name="gender" list="#{'M':'Male', 'F':'Female'}" key="label.gender" required="true" value="%{#session.modelBusinessAssociate.sex}" />
			<s:textfield name="homePhone" key="label.home_phone" value="%{#session.modelBusinessAssociate.homePhone}" tooltip="Enter number in format (999-999-9999)" />
			<s:textfield name="workPhone" key="label.work_phone" value="%{#session.modelBusinessAssociate.workPhone}" tooltip="Enter number in format (999-999-9999)" />
			<s:textfield name="mobilePhone" key="label.mobile_phone" value="%{#session.modelBusinessAssociate.mobilePhone}" tooltip="Enter number in format (999-999-9999)" />
			<sx:datetimepicker name="birthDate" key="label.birth_date"
				displayFormat="dd-MM-yyyy" required="true" value="%{#session.modelBusinessAssociate.birthDate}" tooltip="Choose Birth Date (dd-mm-yyyy)" />
			<sx:datetimepicker name="anniversaryDate" key="label.anniversary_date"
				displayFormat="dd-MM-yyyy" value="%{#session.modelBusinessAssociate.anniversaryDate}" tooltip="Choose Anniversary Date (dd-mm-yyyy)" />
	
			<s:if test="#session.client.zuper == true">
				<sj:textfield id="regToUser" name="regToUser" key="label.reg_to_user" value="%{#session.modelBusinessAssociate.client.firstName + ' ' + #session.modelBusinessAssociate.client.lastName}" />
				<s:hidden id="regToUserId" name="regToUserId" value="%{#session.modelBusinessAssociate.client.clientId}" />
				<input type="button" value="Find Client" onclick="javascript:childOpen('viewClient.action')" />
			</s:if>
			<s:else>
				<sj:textfield name="regToUser" key="label.reg_to_user" disabled="true" value="%{#session.client.firstName + ' ' + #session.client.lastName}" />
				<s:hidden name="regToUserId" value="%{#session.client.clientId}" />
			</s:else>
	
			<s:checkbox key="label.supplier" name="supplier" value="%{#session.modelBusinessAssociate.supplier}" />
			<s:submit key="label.submit" name="submit" />
			<s:submit action="main" key="label.close" name="close" onclick="form.onsubmit=null" />
		</s:form>
	</div>
	<br>
	<div id="div2">
		<s:if test="businessAssociates.size() > 0">
			<div id="container">
				<table id="baTable" class="display">
					<thead>
						<tr class="even">
							<th>Name</th>
							<th>E-mail</th>
							<th>Gender</th>
							<th>Home Phone</th>
							<th>Work Phone</th>
							<th>Mobile Phone</th>
							<th>Birth Date</th>
							<th>Anniversary Date</th>
							<th>Supplier</th>
							<th />
							<th />
						</tr>
					</thead>
					<tbody>
						<s:iterator value="businessAssociates" status="userStatus">
							<tr class='<s:if test="#userStatus.odd == true ">odd</s:if><s:else>even</s:else>'>
								<td><s:property value="firstName + ' ' + lastName" /></td>
								<td><s:property value="email" /></td>
								<td><s:property value="sex" /></td>
								<td><s:property value="homePhone" /></td>
								<td><s:property value="workPhone" /></td>
								<td><s:property value="mobilePhone" /></td>
								<td><s:date name="birthDate" format="dd-MM-yyyy" /></td>
								<td><s:date name="anniversaryDate" format="dd-MM-yyyy" /></td>
								<td><input type="checkbox" disabled="disabled" ${supplier ? 'checked' : ''} /></td>
								<td><s:url id="editURL" action="editBA">
											<s:param name="id" value="%{businessAssociateId}" />
										</s:url> 
										<a class="button" href="${editURL}">Edit</a>
								</td>
								<td><s:url id="deleteURL" action="deleteBA">
											<s:param name="id" value="%{businessAssociateId}" />
										</s:url>
										<a class="button" href="${deleteURL}">Delete</a>
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</s:if>
	</div>
</body>
</html>