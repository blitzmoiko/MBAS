<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<script type="text/javascript">
    function childOpen(childAction) {
        var popupWindow = window
            .open(
                childAction,
                "_blank",
                "close=no,directories=no,titlebar=no,toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=700,height=300,top=200,left=300");
    }

    $(document).ready(function() {
        $('#clientTable').dataTable({
            "bJQueryUI": true,
            "sPaginationType": "full_numbers"
        });

        $('#copy_privileges').get(0).type = 'button';

        $('#delete-button').click(function() {
            return confirm("Are You sure?");
        });
    });
</script>

<div id="cont">
    <div id="main">
        <h4>Client Form</h4>
        <br>
        <s:actionerror />
        <s:form action="validateClient">
            <s:hidden name="clientId" value="%{#session.modelClient.clientId}"/>
            <s:textfield name="firstName" key="label.first_name" required="true" value="%{#session.modelClient.firstName}" />
            <s:textfield name="lastName" key="label.last_name" required="true" value="%{#session.modelClient.lastName}" />
            <s:textfield name="username" key="label.username" required="true" value="%{#session.modelClient.username}" />

            <!-- Test password exists for client  -->
            <s:if test="client.clientId != null">
                <s:password name="password" key="label.password" required="true" value="%{#session.modelClient.password}" showPassword="true" disabled="true" />
            </s:if>
            <s:else>
                <s:password name="password" key="label.password" required="true" />
            </s:else>

            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <div id="privilege">
                    <s:checkbox id="viewId" name="view" key="label.view" value="%{#session.modelClient.clientPrivilege.view}" />
                    <s:checkbox id="insertId" name="insert" key="label.insert" value="%{#session.modelClient.clientPrivilege.insert}" />
                    <s:checkbox id="updateId" name="update" key="label.update" value="%{#session.modelClient.clientPrivilege.update}" />
                    <s:checkbox id="deleteId" name="delete" key="label.delete" value="%{#session.modelClient.clientPrivilege.delete}" />
                    <s:submit id="copy_privileges" type="button" key="label.copy_privileges" onClick="childOpen('listClientPrivilege.action');" />
                </div>
                <s:checkbox name="active" key="label.active" value="%{#session.modelClient.active}" />
            </sec:authorize>

            <s:submit key="label.submit" name="submit" />
        </s:form>
    </div>

    <s:if test="clients.size() > 0">
        <div id="sec">
            <table id="clientTable" class="display">
                <thead>
                    <tr class="even">
                        <th>Name</th>
                        <th>Username</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="clients" status="userStatus">
                        <tr
                            class="<s:if test="#userStatus.odd == true">odd</s:if><s:else>even</s:else>">
                            <td><s:property value="firstName + ' ' + lastName" /></td>
                            <td><s:property value="username" /></td>
                            <td>
                                <s:url id="editURL" action="editClient">
                                    <s:param name="id" value="%{clientId}" />
                                </s:url>
                                <a id="edit-button" href="${editURL}">Edit</a>
                            </td>
                            <td>
                                <s:url id="deleteURL" action="deleteClient">
                                    <s:param name="id" value="%{clientId}" />
                                </s:url>
                                <a id="delete-button" href="${deleteURL}">Delete</a>
                            </td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>
        </div>
    </s:if>
</div>
