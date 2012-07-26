<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<script type="text/javascript">
    function regToClient(index) {
        window.opener.document.getElementById('regToUser').value = document.getElementById('name' + index).value;
        window.opener.document.getElementById('regToUserId').value = document.getElementById('clientNo' + index).value;
        window.close();
        return false;
    }

    $(document).ready(function() {
         oTable = $('#clientTable').dataTable({
                "bJQueryUI": true,
                "sPaginationType": "full_numbers"
         });
    } );
</script>

<s:if test="clients.size() > 0">
    <h2>Clients</h2>
    <br>
    <div class="content">
        <table id="clientTable" class="display">
            <thead>
                <tr class="even">
                    <th>Name</th>
                    <th>Submit</th>
                </tr>
            </thead>
            <tbody>
                <s:iterator value="clients" status="idStatus">
                    <tr class="<s:if test="#idStatus.odd == true ">odd</s:if><s:else>even</s:else>">
                        <td>
                            <s:property  value="firstName + ' ' + lastName" />
                            <s:hidden id="name%{clientId}" value="%{firstName} %{lastName}" />
                            <s:hidden id="clientNo%{clientId}" value="%{clientId}"/>
                        </td>
                        <td><a id="ok-button" href="javascript:void(0)" onclick="javascript:return regToClient(${clientId});">Submit Client</a></td>
                    </tr>
                </s:iterator>
            </tbody>
        </table>
    </div>
</s:if>
