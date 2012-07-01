<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MBAS - Create Business Associate Page</title>
<s:head />
<sx:head />
</head>
<body onFocus="parent_disable();" onclick="parent_disable();">
	<h2>Create Business Associate Form</h2>
	<s:actionerror />
	<s:form action="validateBA" method="POST">
		<s:hidden name="businessAssociateId" value="%{#session.modelBusinessAssociate.clientId}" />
		<s:textfield name="firstName" key="label.first_name" required="true" value="%{#session.modelBusinessAssociate.firstName}" />
		<s:textfield name="middleName" key="label.middle_name" value="%{#session.modelBusinessAssociate.middleName}"/>
		<s:textfield name="lastName" key="label.last_name" required="true" value="%{#session.modelBusinessAssociate.lastName}" />
		<s:textfield name="email" key="label.email" required="true" value="%{#session.modelBusinessAssociate.email}" />
		<s:radio name="gender" list="{'Male','Female'}" key="label.gender" required="true" value="%{#session.modelBusinessAssociate.gender}"/>
		<s:textfield name="homePhone" key="label.home_phone" value="%{#session.modelBusinessAssociate.homePhone}" />
		<s:textfield name="workPhone" key="label.work_phone" value="%{#session.modelBusinessAssociate.workPhone}" />
		<s:textfield name="mobilePhone" key="label.mobile_phone" value="%{#session.modelBusinessAssociate.mobilePhone}" />
		<sx:datetimepicker name="birthDate" key="label.birth_date"
			displayFormat="dd-MM-yyyy" required="true" value="%{#session.modelBusinessAssociate.birthDate}" />
		<sx:datetimepicker name="anniversaryDate" key="label.anniversary_date"
			displayFormat="dd-MM-yyyy" value="%{#session.modelBusinessAssociate.anniversaryDate}" />

		<s:if test="#session.client.zuper == true">
			<s:textfield name="regToUser" key="label.reg_to_user" 
				value="%{#session.modelBusinessAssociate.client.firstName + ' ' + #session.modelBusinessAssociate.client.lastName}" />
			<s:submit action="" key="label.find_user" />
			<s:hidden name="regToUserId" value="%{#session.modelBusinessAssociate.client.clientId}" />
		</s:if>
		
		<s:else>
			<s:textfield name="regToUser" key="label.reg_to_user" disabled="true" value="%{#session.client.firstName + ' ' + #session.client.lastName}" />
			<s:hidden name="regToUserId" value="%{#session.client.clientId}" />
		</s:else>

		<s:checkbox label="Supplier" name="supplier" />
		<s:submit key="label.submit" name="submit" />
		<s:submit action="main" key="label.close" name="close" onclick="form.onsubmit=null" />
	</s:form>

	<s:if test="businessAssociates.size() > 0">
		<sx:tabbedpanel id="tabContainer">
			<sx:div label="Business Associate List">
				<div class="content">
					<table class="baTable">
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
						</tr>
						<s:iterator value="businessAssociates" status="userStatus">
							<tr
								class="<s:if test="#userStatus.odd == true ">odd</s:if><s:else>even</s:else>">
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
										<s:a href="%{editURL}">Edit</s:a>
								</td>
								<td><s:url id="deleteURL" action="deleteBA">
											<s:param name="id" value="%{businessAssociateId}" />
										</s:url> 
										<s:a href="%{deleteURL}">Delete</s:a>
								</td>
							</tr>
						</s:iterator>
					</table>
				</div>
			</sx:div>
		</sx:tabbedpanel>
	</s:if>
</body>
</html>