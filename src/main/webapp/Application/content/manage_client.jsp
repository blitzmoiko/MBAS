<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MBAS - Client Page</title>
<s:head />
<sj:head />
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
						"close=no,directories=no,titlebar=no,toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=700,height=280,top=200,left=300");
	}

	$(document).ready(function() {
	    $('#clientTable').dataTable({
	        "bJQueryUI": true,
	        "sPaginationType": "full_numbers"
	    });
	});

</script>
</head>
<body>
	<div id="div1">
		<h2>Client Form</h2>
		<br>
		<s:actionerror />
		<s:form action="validateClient">
			<s:hidden name="clientId" value="%{#session.modelClient.clientId}"/>
			<s:textfield name="firstName" key="label.first_name" required="true" value="%{#session.modelClient.firstName}" />
			<s:textfield name="lastName" key="label.last_name" required="true" value="%{#session.modelClient.lastName}" />
			<s:textfield name="username" key="label.username" required="true" value="%{#session.modelClient.username}" />

			<!-- Test password exists for client  -->
			<s:if test="client.clientId != null">
				<s:password name="password" key="label.password" required="true" value="••••••••"  showPassword="true" disabled="true" />
<!-- 				<input type="button" value="Change Password" onclick="javascript:childOpen('content/change_password.jsp')" /> -->
			</s:if>
			<s:else>
				<s:password name="password" key="label.password" required="true" />
			</s:else>

			<s:if test="#session.client.zuper == true">
				<s:checkbox name="active" key="label.active" value="%{#session.modelClient.active}" />
				<div id="privilege">
		 			<s:checkbox id="viewId" name="view" key="label.view" value="%{#session.modelClient.clientPrivilege.view}" />
					<s:checkbox id="insertId" name="insert" key="label.insert" value="%{#session.modelClient.clientPrivilege.insert}" />
					<s:checkbox id="updateId" name="update" key="label.update" value="%{#session.modelClient.clientPrivilege.update}" />
					<s:checkbox id="deleteId" name="delete" key="label.delete" value="%{#session.modelClient.clientPrivilege.delete}" />
					<%-- <s:submit type="button" key="label.copy_privileges" onClick="childOpen('listClientPrivilege.action')" /> --%>
					<input type="button" value="Copy Privileges" onClick="childOpen('listClientPrivilege.action')" />
				</div>
			</s:if>

			<s:submit key="label.submit" name="submit" />
			<s:submit action="main" key="label.close" name="close" onclick="form.onsubmit=null" />
		</s:form>
	</div>
	<br>
	<s:if test="clients.size() > 0">
		<div id="container">
			<table id="clientTable" class="display">
				<thead>
					<tr class="even">
						<th>Name</th>
						<th>Username</th>
						<th>Password</th>
						<th>Edit</th>
						<th>Delete</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="clients" status="userStatus">
						<tr
							class="<s:if test="#userStatus.odd == true ">odd</s:if><s:else>even</s:else>">
							<td><s:property value="firstName + ' ' + lastName" /></td>
							<td><s:property value="username" /></td>
							<td>••••••••</td>
							<td>
								<s:url id="editURL" action="editClient">
									<s:param name="id" value="%{clientId}" />
								</s:url>
								<a id="edit-button" href="${editURL}">Edit</a>
							</td>
							<td>
								<s:url id="deleteURL" action="deleteClient">
									<s:param name="id" value="%{clientId}" />
								</s:url>
								<a id="delete-button" href="${deleteURL}">Delete</a>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
	</s:if>
</body>
</html>