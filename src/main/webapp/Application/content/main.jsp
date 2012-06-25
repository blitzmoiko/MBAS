<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MBAS - Main Page</title>
<sx:head />
</head>
<body>

	<ul>
		<li>
			<!-- User: manage user--activate/block user, add/edit dynamic privi -->
			<a href='<s:url action="listClient" />' >Manage User</a>
		</li>
		<li>
			<!-- BA: manage ba  -->
			<a href='<s:url action="listBA" />' >Manage Business Associate</a>
		</li>
		<li>
			<!-- Configuration  -->
			<a href="content/change_password.jsp">Change Password</a>
		</li>
		<li>
			<!-- Mail System: send mail, manage letter content, manage letter template, manage mail type -->
			<s:a href="content/send_mail.jsp">Send Mail</s:a>
			<s:a href="content/view_mail_type.jsp">Mail Type</s:a>
			<s:a href="content/view_letter_content.jsp">Letter Content</s:a>
			<s:a href="content/view_letter_template.jsp">Letter Template</s:a>
		</li>
		<!--
		<li>
			<!-- Record: print record, create log file
			<s:a href="Application/content/send_mail.jsp">Send Mail</s:a>
		</li>
		  -->
	</ul>
	
</body>
</html>