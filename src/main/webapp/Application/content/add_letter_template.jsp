<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<html>
<head>
<title>MBAS - Add Letter Template</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:head />
<sj:head />
<style type="text/css" title="currentStyle">
	@import "/MailToBusinessAssociates/styles/demo_page.css";
	@import "/MailToBusinessAssociates/styles/demo_table_jui.css";
	@import "/MailToBusinessAssociates/styles/base.css";
</style>
<script type="text/javascript" src="/MailToBusinessAssociates/js/jquery.js"></script>
<script type="text/javascript" src="/MailToBusinessAssociates/js/jquery.dataTables.js"></script>
</head>
<body>
	<s:form action="validateLetterTemplate" namespace="/Application" method="POST" enctype="multipart/form-data">
		<s:textfield name="name" key="label.name" />
		<s:file name="templateImage" key="label.image_name"/>
		<s:submit name="save" key="label.save" />
		<s:submit name="close" key="label.close" onclick="self.close()"/>
	</s:form>
</body>
</html>