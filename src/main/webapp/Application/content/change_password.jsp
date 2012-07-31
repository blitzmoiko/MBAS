<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div id="cont">
    <div id="main">
        <h4>Change Password</h4>
        <br>
        <s:actionerror />
        <s:form action="validatePassword">
            <s:password name="oldPassword" key="label.old_password" size="20" />
            <s:password name="newPassword" key="label.new_password" size="20" />
            <s:password name="reNewPassword" key="label.re_new_password" size="20" />
            <s:submit key="label.save" name="save" />
        </s:form>
    </div>
</div>
