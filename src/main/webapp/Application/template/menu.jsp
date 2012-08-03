<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

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
            <li><a href='<s:url action="changePassword" />'>Change Password</a>
            </li>
        </ul>
    </li>

    <li><a href="#">Mail System</a>
        <ul>
            <li>
                <!-- Mail System: send mail, manage letter content, manage letter template, manage mail type -->
                <a href='<s:url action="sendMail" />'>Send Mail</a>
            </li>
            <li>
                <!-- Manage mail type -->
                <a href='<s:url action="tableMailType" />'>Mail Type</a>
            </li>
            <li>
                <!-- Manage mail type -->
                <a href='<s:url action="listLetterContent" />'>Letter Template</a>
            </li>
            <li>
                <!-- Manage mail type -->
                <a href='<s:url action="listLetterTemplate" />'>Stylesheet Template</a>
            </li>
        </ul>
    </li>

    <li><a href="#">Records</a>
        <ul>
            <li>
                <!-- Record: print record, create log file -->
                <a href='<s:url action="tableMailType" />'>Logs</a>
            </li>
        </ul>
    </li>
</ul>