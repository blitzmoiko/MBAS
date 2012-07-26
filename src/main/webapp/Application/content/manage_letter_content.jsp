<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<s:form action="validateLetterContent" namespace="/Application" method="POST">
    <s:textfield name="name" key="label.name" />
    <s:textarea name="content" key="label.message" />
    <s:submit name="save" key="label.save" />
    <s:submit name="close" key="label.close" onclick="self.close()"/>
</s:form>
