<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjr" uri="/struts-jquery-richtext-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MBAS - Send Mail Page</title>
<s:head />
<sj:head />
<style type="text/css" title="currentStyle">
	@import "/MailToBusinessAssociates/styles/demo_page.css";
	@import "/MailToBusinessAssociates/styles/demo_table.css";
	@import "/MailToBusinessAssociates/styles/base.css";
</style>
</head>
<body>
	<h2>Compose E-mail</h2>
	<br>
	<s:actionerror />
	<s:form action="sendMail" theme="xhtml" namespace="/Application">
<%-- 		<s:textfield name="from" key="label.from" value=%{#client.username}/> --%>
		<sj:textfield name="from" key="label.from" value="%{#session.client.username}" disabled="true" />
		<sj:textfield id="toId" name="to" key="label.to" value="neil.juan@cityproperties.com" />
		<sj:submit  key="label.add_recipients" onclick=" " />
		<sj:textfield name="subject" key="label.subject" value="TEST" />
		<sjr:tinymce
			id="richtextEditor" 
			name="message" 
			rows="10" 
			cols="80" 
			width="800"
			height="500"
			editorTheme="advanced"
			toolbarAlign="left"
            toolbarLocation="top"
			plugins="safari,spellchecker,pagebreak,style,layer,table,save,advhr,advimage,advlink,iespell,inlinepopups,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template"
            toolbarButtonsRow1="save,newdocument,|,bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,|,styleselect,formatselect,fontselect,fontsizeselect"
            toolbarButtonsRow2="cut,copy,paste,pastetext,pasteword,|,search,replace,|,bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,link,unlink,anchor,image,cleanup,help,code,|,insertdate,inserttime,preview,|,forecolor,backcolor"
			onFocusTopics="focusRichtext"
			onBlurTopics="blurRichtext"
			onChangeTopics="highlightRichtext"
			onSaveTopics="submitRichtextForm"
            value="Cras dictum. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aenean lacinia mauris vel est. Suspendisse eu nisl. Nullam ut libero. Integer dignissim consequat lectus. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos."
          />
		<s:hidden id="templateNameId" name="templateName" />
		<s:submit key="label.apply_mail_type" onclick=" " />
		<s:submit key="label.send" />
		<s:submit action="main" key="label.close" name="close" onclick="form.onsubmit=null" />
	</s:form>
</body>
</html>