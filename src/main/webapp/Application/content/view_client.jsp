<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Clients</title>
<s:head />
<sj:head />
<style type="text/css" title="currentStyle">
	@import "/MailToBusinessAssociates/styles/demo_page.css";
	@import "/MailToBusinessAssociates/styles/demo_table_jui.css";
	@import "/MailToBusinessAssociates/styles/base.css";
</style>
<script type="text/javascript" src="/MailToBusinessAssociates/js/jquery.js"></script>
<script type="text/javascript" src="/MailToBusinessAssociates/js/jquery.dataTables.js"></script>
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
</head>
<body>
	<h2>Clients</h2>
	<br>
	<s:if test="clients.size() > 0">
		<div class="content">
			<table id="clientTable" class="display">
				<thead>
					<tr class="even">
						<th>Name</th>
						<th>
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
							<td><input type="button" value="Submit Client" onclick="javascript:return regToClient(${clientId});" /></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
	</s:if>
</body>
</html>