<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MBAS - View Mail Type Page</title>
<sx:head />
</head>
<body>
	<s:form action="viewLetterContent">
		<sx:autocompleter />
		<s:submit key="label.go" />
		<s:if test="userList.size() > 0">
			<div class="content">
				<table class="userTable">
					<tr class="even">
						<th>Name</th>
						<th>Gender</th>
						<th>Country</th>
						<th>About You</th>
						<th>Mailing List</th>
						<th>Edit</th>
						<th>Delete</th>
					</tr>
					<s:iterator value="userList" status="userStatus">
						<tr
							class="<s:if test="#userStatus.odd == true ">odd</s:if> <s:else>even</s:else>">
							<td><s:property value="name" /></td>
							<td><s:property value="gender" /></td>
							<td><s:property value="country" /></td>
							<td><s:property value="aboutYou" /></td>
							<td><s:property value="mailingList" /></td>
							<td><s:url id="editURL" action="editUser">
									<s:param name="id" value="%{id}"></s:param>
								</s:url> <s:a href="%{editURL}">Edit</s:a></td>
							<td><s:url id="deleteURL" action="deleteUser">
									<s:param name="id" value="%{id}"></s:param>
								</s:url> <s:a href="%{deleteURL}">Delete</s:a></td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</s:if>
	</s:form>
	<s:submit key="label.select" />
</body>
</html>