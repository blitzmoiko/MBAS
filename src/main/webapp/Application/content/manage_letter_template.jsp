<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<script type="text/javascript">
    $(document).ready(function() {
        $('#letterTemplateTable').dataTable({
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
        <h4>Stylesheet Form</h4>
        <br>
        <s:actionmessage/>
        <s:form action="validateLetterTemplate" method="POST" enctype="multipart/form-data">
            <s:textfield name="name" key="label.name" value="%{#session.modelLetterTemplate.name}" />
            <s:file name="templateImage" key="label.image_name"/>
            <s:submit name="save" key="label.save" />
        </s:form>
    </div>

    <s:if test="letterTemplates.size() > 0">
        <div id="sec">
            <table id="letterTemplateTable" class="display">
                <thead>
                    <tr class="even">
                        <th>Name</th>
                        <th>Image</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="letterTemplates" status="userStatus">
                        <tr class="<s:if test="#userStatus.odd == true">odd</s:if><s:else>even</s:else>">
                            <td><s:property value="name" /></td>
                            <td><img src='<s:url action="getDynamicImage">
                                            <s:param name="id" value="%{letterTemplateId}"></s:param>
                                          </s:url>'>
                            </td>
                            <td>
                                <s:url id="editURL" action="editLetterTemplate">
                                    <s:param name="id" value="%{letterTemplateId}" />
                                </s:url>
                                <a id="edit-button" href="${editURL}">Edit</a>
                            </td>
                            <td>
                                <s:url id="deleteURL" action="deleteLetterTemplate">
                                    <s:param name="id" value="%{letterTemplateId}" />
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
