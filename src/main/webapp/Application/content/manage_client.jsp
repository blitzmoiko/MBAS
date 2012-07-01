<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MBAS - Create User Page</title>
<s:head />
<sx:head />

<script type="text/javascript">
	function childOpen(childAction) {
		var popupWindow = window
				.open(
						childAction,
						"_blank",
						"close=no,directories=no,titlebar=no,toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=500,height=280,top=200,left=300");
	}

	function parentDisable() {
		if (popupWindow && !popupWindow.closed)
			popupWindow.focus();
	}
	
</script>

</head>
<body onFocus="parentDisable();" onclick="parentDisable();">
	<h2>Create User Form</h2>
	<s:actionerror />
	<s:form action="validateClient" method="POST">
		<s:hidden name="clientId" value="%{#session.modelClient.clientId}"/>
		<s:textfield name="firstName" key="label.first_name" required="true" value="%{#session.modelClient.firstName}" />
		<s:textfield name="lastName" key="label.last_name" required="true" value="%{#session.modelClient.lastName}" />
		<s:textfield name="username" key="label.username" required="true" value="%{#session.modelClient.username}" />
		
		<!-- Test password exists for client  -->		
		<s:if test="client.clientId != null">
			<s:password name="password" key="label.password" required="true" value="******" showPassword="true" disabled="true" />		
		</s:if>
		<s:else>
			<s:password name="password" key="label.password" required="true" />
		</s:else>
		
		<s:checkbox name="active" key="label.active" value="%{#session.modelClient.active}"  />
		
		<s:if test="#session.client.zuper == true">
 			<s:checkbox id="viewId" name="view" key="label.view" value="%{#session.modelClient.clientPrivilege.view}" />
			<s:checkbox id="insertId" name="insert" key="label.insert" value="%{#session.modelClient.clientPrivilege.insert}" />
			<s:checkbox id="updateId" name="update" key="label.update" value="%{#session.modelClient.clientPrivilege.update}" />
			<s:checkbox id="deleteId" name="delete" key="label.delete" value="%{#session.modelClient.clientPrivilege.delete}" />
			<input type="button" value="Copy Privileges from..." onClick="javascript:childOpen('listClientPrivilege.action')" /> 
		</s:if>

		<s:submit key="label.submit" name="submit" />
		<s:submit action="main" key="label.close" name="close" onclick="form.onsubmit=null"/>
	</s:form>

	<s:if test="clients.size() > 0">
		<sx:tabbedpanel id="tabContainer">
			<sx:div label="Client List">
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
										</s:url> <s:a href="%{editURL}">Edit</s:a>
								</td>
								<td><s:url id="deleteURL" action="deleteClient">
											<s:param name="id" value="%{clientId}"></s:param>
										</s:url> <s:a href="%{deleteURL}">Delete</s:a>
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