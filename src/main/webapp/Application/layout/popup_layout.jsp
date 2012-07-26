<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<%@ taglib prefix="sjr" uri="/struts-jquery-richtext-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:head />
<sx:head />
<sj:head />
<style type="text/css" media="screen" title="currentStyle">
    @import "/MailToBusinessAssociates/styles/demo_page.css";
    @import "/MailToBusinessAssociates/styles/demo_table_jui.css";
    @import "/MailToBusinessAssociates/styles/superfish.css";
    @import "/MailToBusinessAssociates/styles/superfish-vertical.css";
    @import "/MailToBusinessAssociates/styles/base.css";
</style>
<script type="text/javascript" src="/MailToBusinessAssociates/js/jquery.js"></script>
<script type="text/javascript" src="/MailToBusinessAssociates/js/jquery.dataTables.js"></script>
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
<title>MBAS - <tiles:getAsString name="title" /></title>
</head>
<body>
    <div id="container">
        <div id="popup_body"><tiles:insertAttribute name="body" /></div>
    </div>
</body>
</html>
