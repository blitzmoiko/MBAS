<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<html>
<head>
<title>MBAS - Mail Type Page</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:head />
<sj:head />
<style type="text/css" title="currentStyle">
	@import "/MailToBusinessAssociates/styles/demo_page.css";
	@import "/MailToBusinessAssociates/styles/demo_table_jui.css";
	@import "/MailToBusinessAssociates/styles/base.css";

	#cont {overflow:hidden;}
	div#cont #main {float:left; background: silver; width:25%; padding-left: 20px}
	div#cont #sec {float:right; width:70%; padding-right: 20px}
	div#cont_2 #main {float:left; width:47%; padding-left: 20px}
	div#cont_2 #sec {float:right; width:47%; padding-right: 20px}
</style>
<script type="text/javascript" src="/MailToBusinessAssociates/js/jquery.js"></script>
<script type="text/javascript" src="/MailToBusinessAssociates/js/jquery.dataTables.js"></script>
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
	        "sPaginationType": "full_numbers",
		    "aaSorting": [[ 0, "asc" ]]
	    });

	    oTable3 = $('#mailTypeTable').dataTable({
	        "bJQueryUI": true,
	        "sPaginationType": "full_numbers"
	    });
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
</script>
</head>
<body>
	<div id="cont">
		<div id="main">
			<h2>Mail Type Form</h2>
			<br>
			<s:form action="validateMailType" namespace="/Application">
				<sj:textfield name="name" key="label.name" value="%{#session.modelMailType.name}" />
				<sj:textfield name="letterContentName" key="label.template_name" value="%{#session.modelMailType.letterContent.name}" />
				<sj:textfield name="letterTemplateName" key="label.image_name" value="%{#session.modelMailType.letterTemplate.name}" />
				<s:submit key="label.submit" name="submit" />
				<s:submit action="main" key="label.close" name="close" onclick="form.onsubmit=null" />
			</s:form>
		</div>
		<div id="sec">
			<s:if test="mailTypes.size() > 0">
				<div id="container3">
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
								<tr
									class="<s:if test="#userStatus.odd == true ">odd</s:if><s:else>even</s:else>">
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
	</div>
	<br>
	<div id="cont_2">
		<div id="main">
			<s:if test="letterContents.size() > 0">
				<div id="container1">
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
				</div>
			</s:if>
			<br>
			<a class="button" href="javascript:void(0);" onclick="childOpen('content/add_letter_content.jsp');">Add a new text template</a>
		</div>
		<div id="sec">
			<s:if test="letterTemplates.size() > 0">
				<div id="container2">
					<table id="letterTemplateTable" class="display">
						<thead>
							<tr class="even">
								<th>Name</th>
								<th>Image</th>
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
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
				<br>
				<a class="button" href="javascript:void(0);" onclick="childOpen('content/add_letter_template.jsp');">Add a new stylesheet image</a>
			</s:if>
			<br>
		</div>
	</div>
</body>
</html>