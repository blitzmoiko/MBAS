<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MBAS - View Client Privilege Page</title>
<s:head />
<sx:head />

<script type="text/javascript">
    function copyPrivilegeToClient(index) {
        window.opener.document.getElementById('viewId').checked = (document.getElementById('view' + index).checked ? true: false);
        window.opener.document.getElementById('insertId').checked = (document.getElementById('insert' + index).checked ? true: false);
        window.opener.document.getElementById('updateId').checked = (document.getElementById('update' + index).checked ? true: false);
        window.opener.document.getElementById('deleteId').checked = (document.getElementById('delete' + index).checked ? true: false);
        window.close();
        return false;
</script>
</head>
<body>
	<s:if test="clientPrivileges.size() > 0">
		<sx:div label="Client Privileges List">
			<div class="content">
				<table class="clientPrivilegeTable">
					<tr class="even">
						<th>Name</th>
						<th>Username</th>
						<th>View</th>
						<th>Insert</th>
						<th>Update</th>
						<th>Delete</th>
					</tr>
					<s:iterator value="clientPrivileges" status="idStatus">
						<tr class="<s:if test="#idStatus.odd == true ">odd</s:if><s:else>even</s:else>">
							<td><s:property value="client.firstName + ' ' + client.lastName" /></td>
							<td><s:property value="client.username" /></td>
							<td><input id="view${clientId}" type="checkbox" disabled="disabled"  ${view ? 'checked' : ''} /></td>
							<td><input id="insert${clientId}" type="checkbox" disabled="disabled"  ${insert ? 'checked' : ''} /></td>
							<td><input id="update${clientId}" type="checkbox" disabled="disabled" ${update ? 'checked' : ''} /></td>
							<td><input id="delete${clientId}" type="checkbox" disabled="disabled" ${delete ? 'checked' : ''} /></td>
							<td><input id="btn1" type="button" onclick="javascript:return copyPrivilegeToClient(${clientId});" value="Copy Privilege" /></td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</sx:div>
	</s:if>
</body>
</html>