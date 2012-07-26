<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>jQuery Tabs</title>
<link type="text/css" href="/MailToBusinessAssociates/styles/jquery-ui-1.8.13.custom.css" rel="stylesheet" />
<script type="text/javascript" src="/MailToBusinessAssociates/js/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="/MailToBusinessAssociates/js/jquery-ui-1.8.13.custom.min.js"></script>
<script>
    $(function() {
        $("#tabs").tabs();
        $(".tabs-bottom .ui-tabs-nav, .tabs-bottom .ui-tabs-nav > *")
                .removeClass("ui-corner-all ui-corner-top").addClass(
                        "ui-corner-bottom");
    });
</script>
<style>
#tabs {
    height: 200px;
}

.tabs-bottom {
    position: relative;
}

.tabs-bottom .ui-tabs-panel {
    height: 140px;
    overflow: auto;
}

.tabs-bottom .ui-tabs-nav {
    position: absolute !important;
    left: 0;
    bottom: 0;
    right: 0;
    padding: 0 0.2em 0.2em 0;
}

.tabs-bottom .ui-tabs-nav li {
    margin-top: -2px !important;
    margin-bottom: 1px !important;
    border-top: none;
    border-bottom-width: 1px;
}

.ui-tabs-selected {
    margin-top: -3px !important;
}
</style>
</head>
<body>
    <h1>Welcome to jQuery Tabs</h1>
    <!-- Tabs -->
    <h2 class="demoHeaders">Tabs</h2>
    <div style="width: 500px; height: auto; font-size: small;">
        <div id="tabs" class="tabs-bottom">
            <ul>
                <li><a href="#tabs-1">Tab 1</a></li>
                <li><a href="#tabs-2">Tab 2</a></li>
                <li><a href="#tabs-3">Tab 3</a></li>
            </ul>
            <div id="tabs-1">Content for Tab 1</div>
            <div id="tabs-2">Content for Tab 2</div>
            <div id="tabs-3">Content for Tab 3</div>
        </div>
    </div>
</body>
</html>