<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MBAS - Create User Page</title>
<s:head />
<sx:head />
</head>
<body>
	<h2>Create User Form</h2>
	<s:actionerror />
	<s:form action="validateClient">
		<s:hidden name="clientId" />
		<s:textfield name="firstName" key="label.first_name" required="true" />
		<s:textfield name="lastName" key="label.last_name" required="true" />
		<s:textfield name="username" key="label.username" required="true" />
		<s:password name="password" key="label.password" required="true" />
		<s:checkbox name="active" key="label.active" value="false" />
		<s:checkbox name="view" key="label.view" value="false" />
		<s:checkbox name="insert" key="label.insert" value="false" />
		<s:checkbox name="update" key="label.update" value="false" />
		<s:checkbox name="delete" key="label.delete" value="false" />
		<s:submit action="" key="label.copy_privileges" />
		<s:submit key="label.submit" name="submit" />
		<s:submit action="main" key="label.close" name="close" onclick="form.onsubmit=null"/>
	</s:form>
	
	<s:if test="clients.isEmpty()">
		<s:set name="clients" value="#session.clients" />
	</s:if>

	<sx:tabbedpanel id="tabContainer">
		<sx:div label="Client List">
			<s:if test="clients.size() > 0">
				<div class="content">
					<table class="clientTable">
						<tr class="even">
							<th>Name</th>
							<th>Username</th>
							<th>Password</th>
							<th>Edit</th>
							<th>Delete</th>
						</tr>
						<s:iterator value="clients" status="userStatus">
							<tr
								class="<s:if test="#userStatus.odd == true ">odd</s:if><s:else>even</s:else>">
								<td><s:property value="firstName + ' ' + lastName" /></td>
								<td><s:property value="username" /></td>
								<td>******</td>
								<td><s:url id="editURL" action="editClient">
										<s:param name="id" value="%{clientId}"></s:param>
									</s:url> <s:a href="%{editURL}">Edit</s:a></td>
								<td><s:url id="deleteURL" action="deleteClient">
										<s:param name="id" value="%{clientId}"></s:param>
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