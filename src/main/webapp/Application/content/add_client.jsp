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
	<s:form action="saveOrUpdateClient">
		<s:push value="client">
			<s:hidden name="clientId" />
			<s:textfield name="firstName" key="label.first_name" />
			<s:textfield name="lastName" key="label.last_name" />
			<s:textfield name="username" key="label.username" />
			<s:password name="password" key="label.password" />
			<s:checkbox label="Activate" name="active" value="false" />
			<s:checkboxlist list="{'View', 'Insert', 'Update', 'Delete'}" name="privileges" />
			<s:submit action="" key="label.copy_privileges" />
			<s:submit key="label.submit" name="submit" />
			<s:submit action="main" key="label.close" name="close" onclick="form.onsubmit=null"/>
			
			<!-- TODO 
			<s:if test="client.zuper == true">
				<p><s:property value="client.zuper" /></p>
				<p><s:property value="%{client.zuper}" /></p>
			</s:if>
			-->
		</s:push>
	</s:form>
	
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