<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<script type="text/javascript">
    function childOpen(childAction) {
        var popupWindow = window
            .open(
                childAction,
                "_blank",
                "close=no,directories=no,titlebar=no,toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=500,height=300,top=200,left=400");
    }

    $(document).ready(function() {
        oTable = $('#baTable').dataTable({
            "bJQueryUI" : true,
            "sPaginationType" : "full_numbers",
            "sScrollX": "65%",
            "bScrollCollapse": true
        });

        $('#findClient').get(0).type = 'button';


        $('#delete-button').click(function() {
            return confirm("Are You sure?");
        });
    });
</script>

<div id="cont">
    <div id="main">
        <h4>Business Associate Form</h4>
        <br>
        <s:actionerror />
        <s:form action="validateBA" tooltipConfig="#{'tooltipDelay':'500', 'tooltipIcon='/MailToBusinessAssociates/images/info.png'}">
            <s:hidden name="businessAssociateId" value="%{#session.modelBusinessAssociate.businessAssociateId}" />
            <s:textfield name="firstName" key="label.first_name" required="true" value="%{#session.modelBusinessAssociate.firstName}" />
            <s:textfield name="middleName" key="label.middle_name" value="%{#session.modelBusinessAssociate.middleName}" />
            <s:textfield name="lastName" key="label.last_name" required="true" value="%{#session.modelBusinessAssociate.lastName}" />
            <s:textfield name="email" key="label.email" value="%{#session.modelBusinessAssociate.email}" />
            <s:radio name="gender" list="#{'M':'Male', 'F':'Female'}" key="label.gender" required="true" value="%{#session.modelBusinessAssociate.sex}" />
            <s:textfield name="homePhone" key="label.home_phone" value="%{#session.modelBusinessAssociate.homePhone}" tooltip="Phone number format (999-999-9999)" />
            <s:textfield name="workPhone" key="label.work_phone" value="%{#session.modelBusinessAssociate.workPhone}" tooltip="Phone number format (999-999-9999)" />
            <s:textfield name="mobilePhone" key="label.mobile_phone" value="%{#session.modelBusinessAssociate.mobilePhone}" tooltip="Phone number format (999-999-9999)" />
            <sx:datetimepicker name="birthDate" key="label.birth_date"
                displayFormat="dd-MM-yyyy" value="%{#session.modelBusinessAssociate.birthDate}" tooltip="Date format (dd-mm-yyyy)" />
            <sx:datetimepicker name="anniversaryDate" key="label.anniversary_date"
                displayFormat="dd-MM-yyyy" value="%{#session.modelBusinessAssociate.anniversaryDate}" tooltip="Date format (dd-mm-yyyy)" />

            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <sj:textfield id="regToUser" name="regToUser" key="label.reg_to_user" disabled="true" required="true" value="%{#session.modelBusinessAssociate.client.firstName + ' ' + #session.modelBusinessAssociate.client.lastName}" />
                <s:hidden id="regToUserId" name="regToUserId" value="%{#session.modelBusinessAssociate.client.clientId}" />
                <s:submit id="findClient" type="button" key="label.find_user" onClick="childOpen('viewClient.action');" />
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_USER')">
                <sj:textfield name="regToUser" key="label.reg_to_user" disabled="true" required="true" value="%{#session.client.firstName + ' ' + #session.client.lastName}" />
                <s:hidden name="regToUserId" value="%{#session.client.clientId}" />
            </sec:authorize>


            <s:checkbox key="label.supplier" name="supplier" value="%{#session.modelBusinessAssociate.supplier}" />
            <s:submit key="label.submit" name="submit" />
        </s:form>
    </div>

    <s:if test="businessAssociates.size() > 0">
        <div id="sec">
            <table id="baTable" class="display">
                <thead>
                    <tr class="even">
                        <th>Name</th>
                        <th>E-mail</th>
                        <th>Gender</th>
                        <th>Home Phone</th>
                        <th>Work Phone</th>
                        <th>Mobile Phone</th>
                        <th>Birth Date</th>
                        <th>Anniversary Date</th>
                        <th>Supplier</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="businessAssociates" status="userStatus">
                        <tr class='<s:if test="#userStatus.odd == true ">odd</s:if><s:else>even</s:else>'>
                            <td><s:property value="firstName + ' ' + lastName" /></td>
                            <td><s:property value="email" /></td>
                            <td><s:property value="sex" /></td>
                            <td><s:property value="homePhone" /></td>
                            <td><s:property value="workPhone" /></td>
                            <td><s:property value="mobilePhone" /></td>
                            <td><s:date name="birthDate" format="dd-MM-yyyy" /></td>
                            <td><s:date name="anniversaryDate" format="dd-MM-yyyy" /></td>
                            <td><input type="checkbox" disabled="disabled" ${supplier?'checked':''} /></td>
                            <td><s:url id="editURL" action="editBA">
                                    <s:param name="id" value="%{businessAssociateId}" />
                                </s:url>
                                <a id="edit-button" href="${editURL}">Edit</a>
                            </td>
                            <td><s:url id="deleteURL" action="deleteBA">
                                    <s:param name="id" value="%{businessAssociateId}" />
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
