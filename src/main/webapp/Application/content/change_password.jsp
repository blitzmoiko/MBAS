<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MBAS - Change Password</title>
<s:head />
<style type="text/css" title="currentStyle">
	@import "/MailToBusinessAssociates/styles/demo_page.css";
	@import "/MailToBusinessAssociates/styles/demo_table.css";
	@import "/MailToBusinessAssociates/styles/base.css";
</style>
</head>
<body>
	<div id="div1">
		<h2>Change Password</h2>
		<br>
		<s:actionerror />
		<s:form action="changePassword" namespace="/Application">
			<s:password name="oldPassword" key="label.old_password" size="20" />
			<s:password name="newPassword" key="label.new_password" size="20" />
			<s:password name="reNewPassword" key="label.re_new_password" size="20" />
			<s:submit key="label.save" name="save" />
			<s:submit action="main" key="label.cancel" name="cancel" onclick="form.onsubmit=null"/>
		</s:form>
	</div>
</body>
</html>