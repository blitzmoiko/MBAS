<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<script type="text/javascript">
    $(document).ready(function() {
        $('#letterContentTable').dataTable({
            "bJQueryUI": true,
            "sPaginationType": "full_numbers",
            "aaSorting": [[ 0, "asc" ]]
        });

        $('#delete-button').click(function() {
            return confirm("Are You sure?");
        });
    });
</script>

<div id="cont">
    <div id="main">
        <s:actionmessage/>
        <s:form action="validateLetterContent">
            <s:textfield name="name" key="label.name" value="%{#session.modelLetterContent.name}" />
            <s:textarea name="content" key="label.message" value="%{#session.modelLetterContent.content}" />
            <s:submit name="save" key="label.save" />
        </s:form>
    </div>

    <s:if test="letterContents.size() > 0">
        <div id="sec">
            <table id="letterContentTable" class="display">
                <thead>
                    <tr class="even">
                        <th>Name</th>
                        <th>Template</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="letterContents" status="userStatus">
                        <tr class="<s:if test="#userStatus.odd == true">odd</s:if><s:else>even</s:else>">
                            <td><s:property value="name" /></td>
                            <td><s:property value="content" /></td>
                            <td>
                                <s:url id="editURL" action="editLetterContent">
                                    <s:param name="id" value="%{letterContentId}" />
                                </s:url>
                                <a id="edit-button" href="${editURL}">Edit</a>
                            </td>
                            <td>
                                <s:url id="deleteURL" action="deleteLetterContent">
                                    <s:param name="id" value="%{letterContentId}" />
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
