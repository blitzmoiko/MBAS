<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MBAS - Change Password</title>
<s:head />
<sx:head />
</head>
<body>
	<s:actionerror />
	<s:form action="changePassword" method="POST" namespace="/Application" >
		<s:password name="oldPassword" key="label.old_password" size="20" />
		<s:password name="newPassword" key="label.new_password" size="20" />
		<s:password name="reNewPassword" key="label.re_new_password" size="20" />
		<s:submit key="label.save" name="save" />
		<s:submit action="main" key="label.cancel" name="cancel" onclick="form.onsubmit=null"/>
	</s:form>
</body>
</html>