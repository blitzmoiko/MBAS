<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.security.Principal" %>

<div id="content">
    <h1>MBAS</h1>
    <h3>City Properties Real Estate LLC.</h3>
    <br>

    <c:if test='${not empty param.error}'>
        <font color='red'>
            Your login attempt was not successful, try again.<br>
            Reason : ${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}
        </font>
    </c:if>
    <form method='POST' action='<c:url value='/j_spring_security_check' />'>
        <table class="wwFormTable">
            <tr>
                <td class="tdLabel"><label for="validateLogin_username" class="label">Username:</label></td>
                <td><input type='text' name='j_username' /></td>
            </tr>
            <tr>
                <td class="tdLabel"><label for="validateLogin_password" class="label">Password:</label></td>
                <td><input type='password' name='j_password' /></td>
            </tr>
            <tr>
                <td class="tdLabel" colspan='2' align='right'>
                    <input type="checkbox" name="_spring_security_remember_me" /> Remember me on this computer.
                </td>
            </tr>
            <tr>
                <td colspan='2' align='right'><input type='submit' value='Login' /></td>
            </tr>
        </table>
    </form>

</div>
