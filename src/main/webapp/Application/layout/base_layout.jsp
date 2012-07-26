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
<script type="text/javascript">
    $(document).ready(function() {
        $("ul.sf-menu").superfish({
            animation : {
                height : 'show'
            },
            delay : 1200
        });
    });

    tday = new Array("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday",
            "Friday", "Saturday");
    tmonth = new Array("January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December");

    function getClock() {
        d = new Date();
        nday = d.getDay();
        nmonth = d.getMonth();
        ndate = d.getDate();
        nyear = d.getYear();
        nhour = d.getHours();
        nmin = d.getMinutes();
        if (nyear < 1000)
            nyear = nyear + 1900;

        if (nhour == 0) {
            ap = " AM";
            nhour = 12;
        } else if (nhour <= 11) {
            ap = " AM";
        } else if (nhour == 12) {
            ap = " PM";
        } else if (nhour >= 13) {
            ap = " PM";
            nhour -= 12;
        }

        if (nmin <= 9) {
            nmin = "0" + nmin;
        }

        document.getElementById('clock').innerHTML = "Today is " + tday[nday] + ", "
                + tmonth[nmonth] + " " + ndate + ", " + nyear + " <br>" + nhour
                + ":" + nmin + ap + "";
        setTimeout("getClock()", 1000);
    }
    window.onload = getClock;
</script>

<title>MBAS - <tiles:getAsString name="title" /></title>
</head>
<body>
    <div id="container">
        <div id="header"><tiles:insertAttribute name="header" /></div>
        <div id="menu"><tiles:insertAttribute name="menu" /></div>
        <div id="body"><tiles:insertAttribute name="body" /></div>
        <div id="footer"><tiles:insertAttribute name="footer" /></div>
    </div>
</body>
</html>
