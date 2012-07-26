<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<script type="text/javascript">
    function copyPrivilegeToClient(index) {
        window.opener.document.getElementById('viewId').checked = (document.getElementById('view' + index).checked ? true: false);
        window.opener.document.getElementById('insertId').checked = (document.getElementById('insert' + index).checked ? true: false);
        window.opener.document.getElementById('updateId').checked = (document.getElementById('update' + index).checked ? true: false);
        window.opener.document.getElementById('deleteId').checked = (document.getElementById('delete' + index).checked ? true: false);
        window.close();
        return false;
    }

    $(document).ready(function() {
        oTable = $('#clientPrivilegeTable').dataTable({
            "bJQueryUI": true,
            "sPaginationType": "full_numbers"
        });
    } );
</script>

<s:if test="clientPrivileges.size() > 0">
    <h2>Client Privileges</h2>
    <br>
    <div class="content">
        <table id="clientPrivilegeTable" class="display">
            <thead>
                <tr class="even">
                    <th>Name</th>
                    <th>Username</th>
                    <th>View</th>
                    <th>Insert</th>
                    <th>Update</th>
                    <th>Delete</th>
                    <th>Copy</th>
                </tr>
            </thead>
            <tbody>
                <s:iterator value="clientPrivileges" status="idStatus">
                    <tr class="<s:if test="#idStatus.odd == true ">odd</s:if><s:else>even</s:else>">
                        <td><s:property value="client.firstName + ' ' + client.lastName" /></td>
                        <td><s:property value="client.username" /></td>
                        <td><input id="view${clientId}" type="checkbox" disabled="disabled"  ${view ? 'checked' : ''} /></td>
                        <td><input id="insert${clientId}" type="checkbox" disabled="disabled"  ${insert ? 'checked' : ''} /></td>
                        <td><input id="update${clientId}" type="checkbox" disabled="disabled" ${update ? 'checked' : ''} /></td>
                        <td><input id="delete${clientId}" type="checkbox" disabled="disabled" ${delete ? 'checked' : ''} /></td>
                        <td><a id="copy-button" href="javascript:void(0)" onclick="javascript:return copyPrivilegeToClient(${clientId});">Copy</a></td>
                    </tr>
                </s:iterator>
            </tbody>
        </table>
    </div>
</s:if>