$!

	Template for the top menu bar in the header
	
	see PageParameters class for documentation of full set of arguments
!$
<div class="platform topBar">
    <div id="topnav">
        <ul>
            <li class="butt_admin_tools shift"><a href="$contextPath$/resource/Admin:Import" id="Import" class="menuitem">Import</a></li>
            <li class="butt_admin_tools shift"><a href="$contextPath$/resource/Admin:Export" id="Export" class="menuitem">Export</a></li>
            <li class="butt_admin_tools shift"><a href="$contextPath$/resource/Admin:Upload" id="Upload" class="menuitem">Upload</a></li>
            <li class="butt_admin_tools shift"><a href="$contextPath$/resource/Admin:Admin?view=wiki" id="Admin" class="menuitem">Settings</a></li>
            <li class="butt_help shift"><a href="$contextPath$/resource/Help:Help" id="Help" class="menuitem">Help</a></li>
            $if(!loggedIn)$
            	<li class="butt_login shift"><a href="#" onclick="\$r('$loginLink$')" class="menuitem">Login</a></li>
            $else$
            	<li class="butt_logout shift"><a href="$contextPath$/logout.jsp">Logout</a></li>
            $endif$
            <li class="search">
                $searchfield$
            </li>
        </ul>
    </div>
    <script language="JavaScript">
	// apply the ACTIVE class to the toolbar button that mathes the page name
    \$(".menuitem").filter(function(){ return "$pageName$" == this.id; }).addClass("active");
    </script>
    <div class="headerLogo">
        <a href="$contextPath$/">
            <img src="$contextPath$/images/iwb_logo_margin.png" alt="Information Workbench">
        </a>
    </div>

</div>