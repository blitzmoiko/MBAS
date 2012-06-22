<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MBAS - View Business Associate Page</title>
<sx:head />
</head>
<body>
	<h3>Welcome!</h3>
	<s:form action="sendMail">
		<s:submit key="label.send" />
		<s:submit action="" key="label.to" />
		<s:textfield name="to" size="40" />
		<s:textfield name="subject" key="label.subject" size="40" />
		<s:submit action="" key="label.apply_mail_type" />
		<s:textarea key="label.message" />
	</s:form>
</body>
</html>