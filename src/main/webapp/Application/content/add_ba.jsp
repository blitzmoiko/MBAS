<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MBAS - Create Business Associate Page</title>
<s:head />
<sx:head />
</head>
<body>
	<h2>Create Business Associate Form</h2>
	<s:form action="saveOrUpdateBA">
		<s:push value="businessAssociate">
			<s:hidden name="businessAssociateId" />		
			<s:textfield name="firstName" key="label.first_name" />
			<s:textfield name="middleName" key="label.middle_name" />
			<s:textfield name="lastName" key="label.last_name" />
			<s:textfield name="email" key="label.email" />
			<s:radio name="gender" list="{'Male','Female'}" key="label.gender" />
			<s:textfield name="homePhone" key="label.home_phone" />
			<s:textfield name="workPhone" key="label.work_phone" />
			<s:textfield name="mobilePhone" key="label.mobile_phone" />
			<sx:datetimepicker name="birthDate" key="label.birth_date"
				displayFormat="dd-MMM-yyyy" value="%{'2000-01-01'}" />
			<sx:datetimepicker name="anniversaryDate" key="label.anniversary_date"
				displayFormat="dd-MMM-yyyy" value="%{'2000-01-01'}" />
			<s:textfield name="regToUser" key="label.reg_to_user" />
			<s:submit action="" key="label.find_user" />
			<s:checkbox label="Supplier" name="isSupplier" />
			<s:submit key="label.submit" name="submit"/>
			<s:submit action="main" key="label.close" name="close" onclick="form.onsubmit=null" />
		</s:push>
	</s:form>
	
	<sx:tabbedpanel id="tabContainer">
		<sx:div label="Business Associate List">
			<s:if test="businessAssociates.size() > 0">
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
								<td><s:property value="gender" /></td>
								<td><s:property value="homePhone" /></td>
								<td><s:property value="workPhone" /></td>
								<td><s:property value="mobilePhone" /></td>
								<td><s:property value="birthDate" /></td>
								<td><s:property value="anniversaryDate" /></td>
								<td><s:property value="isSupplier" /></td>
								<td><s:url id="editURL" action="editBA">
										<s:param name="id" value="%{businessAssociateId}"></s:param>
									</s:url> <s:a href="%{editURL}">Edit</s:a></td>
								<td><s:url id="deleteURL" action="deleteBA">
										<s:param name="id" value="%{businessAssociateId}"></s:param>
									</s:url> <s:a href="%{deleteURL}">Delete</s:a></td>
							</tr>
						</s:iterator>
					</table>
				</div>
			</s:if>
		</sx:div>
	</sx:tabbedpanel>
</body>
</html>