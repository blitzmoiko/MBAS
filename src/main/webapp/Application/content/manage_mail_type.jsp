<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>

<script type="text/javascript" src="/MailToBusinessAssociates/js/jquery-ui-1.8.13.custom.min.js"></script>
<script type="text/javascript">
    function childOpen(childAction) {
        var popupWindow = window
            .open(
                childAction,
                "_blank",
                "close=no,directories=no,titlebar=no,toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=700,height=280,top=200,left=300");
    }

    var oTable;
    var oTable2;
    var oTable3;
    var giRedraw = false;

    $(document).ready(function() {
        /* Add a click handler to the rows - this could be used as a callback */
        $("#letterContentTable tbody").click(function(event) {
            $(oTable.fnSettings().aoData).each(function() {
                $(this.nTr).removeClass('row_selected');
            });
            $(event.target.parentNode).addClass('row_selected');
        });

        $("#letterTemplateTable tbody").click(function(event) {
            $(oTable2.fnSettings().aoData).each(function() {
                $(this.nTr).removeClass('row_selected');
            });
            $(event.target.parentNode).addClass('row_selected');
        });

        /* Init the table */
        oTable = $('#letterContentTable').dataTable({
                "bJQueryUI": true,
                "sPaginationType": "full_numbers",
                "aaSorting": [[ 0, "asc" ]]
        });

        oTable2 = $('#letterTemplateTable').dataTable({
            "bJQueryUI": true,
            "sScrollY": "300px",
            "sPaginationType": "full_numbers",
            "aaSorting": [[ 0, "asc" ]]
        });

        oTable3 = $('#mailTypeTable').dataTable({
            "bJQueryUI": true,
            "sPaginationType": "full_numbers"
        });
    });

    $(function() {
        $( "#tabs" ).tabs();
        $( ".tabs-bottom .ui-tabs-nav, .tabs-bottom .ui-tabs-nav > *" )
        .removeClass( "ui-corner-all ui-corner-top" )
        .addClass( "ui-corner-bottom" );
     });

    /* Get the rows which are currently selected */
    function fnGetSelected(oTableLocal) {
        var aReturn = new Array();
        var aTrs = oTableLocal.fnGetNodes();

        for ( var i = 0; i < aTrs.length; i++) {
            if ($(aTrs[i]).hasClass('row_selected')) {
                aReturn.push(aTrs[i]);
            }
        }
        alert(aReturn);
        return aReturn;
    }

    function popUpClosed() {
        window.location.reload();
    }
</script>

<div id="cont">
    <div id="main">
        <h4>Mail Type Form</h4>
        <br>
        <s:form action="validateMailType" namespace="/Application">
            <sj:textfield name="name" key="label.name" value="%{#session.modelMailType.name}" />
            <sj:textfield name="letterContentName" key="label.template_name" value="%{#session.modelMailType.letterContent.name}" />
            <sj:textfield name="letterTemplateName" key="label.image_name" value="%{#session.modelMailType.letterTemplate.name}" />
            <s:submit key="label.submit" name="submit" />
        </s:form>
    </div>
    <s:if test="mailTypes.size() > 0">
        <div id="sec">
            <table id="mailTypeTable" class="display">
                <thead>
                    <tr class="even">
                        <th>Name</th>
                        <th>Template</th>
                        <th>Stylesheet</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="mailTypes" status="userStatus">
                        <tr class="<s:if test="#userStatus.odd == true ">odd</s:if><s:else>even</s:else>">
                            <td><s:property value="name" /></td>
                            <td><s:property value="letterContent.name" /></td>
                            <td><s:property value="letterTemplate.name" /></td>
                            <td>
                                <s:url id="editURL" action="editMailType">
                                    <s:param name="id" value="%{mailTypeId}" />
                                </s:url>
                                <a id="edit-button" href="${editURL}">Edit</a>
                            </td>
                            <td>
                                <s:url id="deleteURL" action="deleteMailType">
                                    <s:param name="id" value="%{mailTypeId}" />
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

<br>

<div id="tabs" class="tabs-bottom">
    <ul>
       <li><a href="#tab-1">Letter Template</a></li>
       <li><a href="#tab-2">Image Template</a></li>
    </ul>
    <div id="tab-1">
        <a class="button" id="add-button" href="javascript:void(0);" onclick='childOpen("<s:url action="letterContent" />");'>Add a new text template</a>
        <br>
        <br>
        <s:if test="letterContents.size() > 0">
            <table id="letterContentTable" class="display">
                <thead>
                    <tr class="even">
                        <th>Name</th>
                        <th>Template</th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="letterContents" status="userStatus">
                        <tr class="<s:if test="#userStatus.odd == true">odd</s:if><s:else>even</s:else>">
                            <td><s:property value="name" /></td>
                            <td><s:property value="content" /></td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>
        </s:if>
    </div>

    <div id="tab-2">
        <a class="button" href="javascript:void(0);" onclick='childOpen("<s:url action="letterTemplate" />");'>Add a new stylesheet image</a>
        <br>
        <br>
        <s:if test="letterTemplates.size() > 0">
            <table id="letterTemplateTable" class="display">
                <thead>
                    <tr class="even">
                        <th>Name</th>
                        <th>Image</th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="letterTemplates" status="userStatus">
                        <tr
                            class="<s:if test="#userStatus.odd == true">odd</s:if><s:else>even</s:else>">
                            <td><s:property value="name" /></td>
                            <td><img
                                src='<s:url action="getDynamicImage">
                                            <s:param name="id" value="%{letterTemplateId}"></s:param>
                                     </s:url>'>
                            </td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>
        </s:if>
    </div>
</div>
