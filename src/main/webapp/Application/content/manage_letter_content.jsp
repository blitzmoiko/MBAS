<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<script type="text/javascript">
    window.onunload = function() {
        if (window.opener && !window.opener.closed) {
            window.opener.popUpClosed();
        }
    };
</script>

<div id="popup">
    <s:actionmessage/>
    <s:form action="validateLetterContent" namespace="/Application" method="POST">
        <s:textfield name="name" key="label.name" />
        <s:textarea name="content" key="label.message" />
        <s:submit name="save" key="label.save" />
        <s:submit name="close" key="label.close" onclick="window.location.reload(); self.close(); "/>
    </s:form>
</div>
