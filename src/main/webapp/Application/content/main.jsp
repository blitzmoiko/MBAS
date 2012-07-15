<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MBAS - Main Page</title>
<s:head />
<sj:head  />
<style type="text/css" media="screen" title="currentStyle">
	@import "/MailToBusinessAssociates/styles/demo_page.css";
	@import "/MailToBusinessAssociates/styles/demo_table_jui.css";
	@import "/MailToBusinessAssociates/styles/superfish.css";
	@import "/MailToBusinessAssociates/styles/superfish-vertical.css";
	@import "/MailToBusinessAssociates/styles/base.css";
</style>
<script type="text/javascript" src="/MailToBusinessAssociates/js/hoverIntent.js" ></script>
<script type="text/javascript" src="/MailToBusinessAssociates/js/superfish.js"></script>
<script> 
	$(document).ready(function(){ 
	    $("ul.sf-menu").superfish({ 
	        animation: {height:'show'},   // slide-down effect without fade-in 
	        delay:     1200               // 1.2 second delay on mouseout 
	    }); 
	}); 
</script>
</head>
<body>
	<ul class="sf-menu sf-vertical">
		<li><a href="#">Users</a>
			<ul>
				<li> 
					<!-- User: manage user--activate/block user, add/edit dynamic privi -->
					<a href='<s:url action="listClient" />'>Manage Client</a>
				</li>
				<li> 
					<!-- BA: manage ba  --> 
					<a href='<s:url action="listBA" />'>Manage Business Associate</a>
				</li>
			</ul>
		</li>

		<li><a href="#">User Configuration</a>
			<ul>
				<li>
					<s:a href="content/change_password.jsp">Change Password</s:a>
				</li>
			</ul>
		</li>
		
		<li><a href="#">Mail System</a>
			<ul>
				<li>
					<!-- Mail System: send mail, manage letter content, manage letter template, manage mail type -->
					<s:a href="content/send_mail.jsp">Send Mail</s:a>
				</li>
				<li>
					<!-- Manage mail type --> 
					<a href='<s:url action="tableMailType" />'>Mail Type</a>
				</li>
			</ul>
		</li>

		<li><a href="#">Records</a>
			<ul>
				<li>
						<!-- Record: print record, create log file -->
						<%-- <s:a href="Application/content/send_mail.jsp">Send Mail</s:a> --%>
				</li>
			</ul>
		</li>
	</ul>
</body>
</html>