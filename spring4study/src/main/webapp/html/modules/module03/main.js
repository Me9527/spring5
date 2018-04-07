
function getTree() {
	var tree = [
		  {
		    text: "Parent 1",
		    nodes: [
		      {
		        text: "Child 1",
		        nodes: [
		          {
		            text: "Grandchild 1"
		          },
		          {
		            text: "Grandchild 2",
			        nodes: [
				          {
				            text: "Grandchild 1"
				          },
				          {
				            text: "Grandchild 2"
				          }]
		          }
		        ]
		      },
		      {
		        text: "Child 2"
		      }
		    ]
		  },
		  {
		    text: "Parent 2"
		  },
		  {
		    text: "Parent 3"
		  },
		  {
		    text: "Parent 4"
		  },
		  {
		    text: "Parent 5"
		  }
		];

  return tree;
}


$(document).ready(function() {
	$('#myTree').treeview({
		data: getTree(),
		  levels: 10,
		  color : 'black',
		  backColor: 'SteelBlue'
	});
	
	makeMenu();
	
//  $("#doLoginBtn").click(function(event){
//	  ajaxLogin();
//  })
//  
//  $("#logoutBtn").click(function(event){
//	  ajaxLogout();
//  })
  

  
})

//            <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
//              <div class="menu_section" id="myMenu">
//                <h3>我的菜单</h3>
//                <ul class="nav side-menu">
//                  <li><a><i class="fa fa-table"></i> Tables <span class="fa fa-chevron-down"></span></a>
//                    <ul class="nav child_menu">
//                      <li><a href="tables.html">Tables</a></li>
//                      <li><a href="tables_dynamic.html">Table Dynamic</a></li>
//                    </ul>
//                  </li>
//                </ul>                
//              </div>
//           </div>
           
function makeMenu(){
	var menuSections = [];
	menuSections.push('动态菜单');// menuSections.push('动态菜单');
	
	var nodes = [];
	nodes.push('node1'); nodes.push('node2'); nodes.push('node3');
	
	for(var i=0; i<menuSections.length; i++){
		$('#sidebar-menu').append("<div class='menu_section' id='menu_section-" + menuSections[i] + i + "'/>");
		//var elem = "'#" + menuSections[i] + i + "'";
		var menuSection = $('#' + 'menu_section-' + menuSections[i] + i );
		menuSection.append("<h3>" + menuSections[i] + "_" + (i+1) + "</h3>");
		
		menuSection.append("<ul class='nav side-menu' id='side-menu-" + menuSections[i] + i + "'/>");
		var sideMenu = $('#' + 'side-menu-' + menuSections[i] + i );
		sideMenu.append("<li id='side-menu-li-" + menuSections[i] + i + "'/>");
		var sideMenuLi = $('#' + 'side-menu-li-' + menuSections[i] + i );
		sideMenuLi.append("<a><i class='fa fa-table'></i>" +  menuSections[i] + "_" + (i+1) + "<span class='fa fa-chevron-down'></span></a>");
		
		sideMenuLi.append("<ul class='nav child_menu' id='t_child_menu-" + menuSections[i] + i + "'/>");
		var child_menu = $('#' + 't_child_menu-' + menuSections[i] + i );
		for(var i=0; i<nodes.length; i++){
			child_menu.append("<li><a href='javascript:void(0)' onclick='clickOnNode(\"" + nodes[i] + "\")'>" + nodes[i] + "</a></li>");
			//var nodeElement = "<li><a href='tables_dynamic.html'>" + nodes[i] + "</a></li>";
		}
		
	}
	init_sidebar();

	
//	
//	$('#sidebar-menu').append("<li><a href='tables.html'>c1</a></li>");
//	
//	var menu = $('#myMenu');
//	$('#sidebar-menu').append("<div class='menu_section' id='ment1'> <h3>我的动态菜单</h3> ");
//	//$('#sidebar-menu').append("<h3>我的动态菜单</h3>");
//	$('#ment1').append("<ul class='nav side-menu' id='ment2'>");
//	
//	$('#ment2').append("<li id='ment3'><a><i class='fa fa-table'></i> aaa <span class='fa fa-chevron-down'></span></a> <ul class='nav child_menu' id='ment4'> <li><a href='tables.html'>b1</a></li> <li><a href='tables_dynamic.html'>b2</a></li> </ul> </li>");
//	
//	$('#ment2').append("<li id='ment3'><a><i class='fa fa-table'></i> aaa <span class='fa fa-chevron-down'></span></a>");
//	$('#ment3').append("<ul class='nav child_menu' id='ment4'>");
//	$('#ment4').append("<li><a href='tables.html'>b1</a></li>");
//	$('#ment4').append("<li><a href='tables_dynamic.html'>b2</a></li>");
//	$('#ment3').append("</ul>");
//	$('#ment2').append("</li>");
//
//	$('#ment2').append("<li id='ment5'><a><i class='fa fa-table'></i> bbb <span class='fa fa-chevron-down'></span></a>");
//	$('#ment5').append("<ul class='nav child_menu' id='ment6'>");
//	$('#ment6').append("<li><a href='tables.html'>c1</a></li>");
//	$('#ment6').append("<li><a href='tables_dynamic.html'>c2</a></li>");
//	$('#ment5').append("</ul>");
//	$('#ment2').append("</li>");
//	
//	$('#ment1').append("</ul>");
//	$('#sidebar-menu').append("</div>");
//	
}

function clickOnNode( value){
	alert(value);
}


// DO GET X-CSRF-TOKEN
function ajaxGetCsrfToken(){
	var url = '/spring4study/modules/user/getCsrf.jsp';
	var token = null;
	$.ajax({
  	   type: "GET",
  	   async :false,
  	   timeout : 20000,
       url : url,
       success: function(data){
    	   // alert(data);
    	   token = data;
       },
       error : function(err) {
    	   // alert(err);
       }
    });
	return token;
}
  
// 'application/json;charset=utf-8' application/json
function ajaxLogin(){
  var data = ajaxGetCsrfToken();
  if(null == data){
	  alert("Get CsrfToken Error!");
	  return;
  }
  var queryParam = {};
  // alert($("#username").val());
  queryParam['username'] = $("#inputUsername").val();
  queryParam['password'] = $("#inputPassword").val();	
  //queryParam['targetUrl'] = "/html/modules/module02/loginSuccess.json";
  //'Accept-Language': 'utf-8, iso-8859-1;q=0.5, *;q=0.1'
  // 'Accept-Charset': 'utf-8, iso-8859-1;q=0.5'
  var url = '/spring4study/modules/user/login';
  $.ajax({
 	contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
  	headers    : {
  	             'X-CSRF-TOKEN': data
  	 },
  	dataType   : 'json',
  	type       : "POST",
  	timeout    : 20000,
  	data       : queryParam,
    url        : url,
    success    : function(result){
        // fill data to Modal Body
        // fillData(data);
        // alert(data);
    	if(result.success == false){
    		alert(result.message);
    	}else if (result.success == true){
        	//$("#showLoginBtn").text("注销");
            $("#showUserName").text("欢迎" + result.data.nickName);
            //$("#showLoginBtn").css("nav-link disabled");
            $('#loginModal').modal('hide');
            //$("#showLoginBtn").click(function(event){
            //	ajaxLogout();
            //})
    	}

     },
     error     : function(e) {
          	// fillData(null);
       }
   }); 
}
  
  
function ajaxLogout(){
	  var data = ajaxGetCsrfToken();
	  if(null == data){
		  alert("Get CsrfToken Error!");
		  return;
	  }
	  var queryParam = {};
	  var url = '/spring4study//modules/user/logout';
	  $.ajax({
	 	contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
	  	headers    : {
	  	             'X-CSRF-TOKEN': data,
	  	 },
	  	dataType   : 'json',
	  	type       : "POST",
	  	timeout    : 20000,
	  	data       : queryParam,
	    url        : url,
	    success    : function(data){
	        // fill data to Modal Body
	        // fillData(data);
	        // alert(data);
	    	if(data.success == false){
	    		alert(data.message);
	    	}else if (data.success == true){
	    		$("#currentUser").text("您未登录");
	            alert(data.message);
	    	}

	     },
	     error     : function(e) {
	          	// fillData(null);
	       }
	   }); 
	}  
  
  
  
  
  
  
// //////////////////////////////////////////////////////////////////////////

function testAccessRefuse(){
  	var data = ajaxGetCsrfToken();
  	if(null == data){
		alert("Get CsrfToken Error!");
		return;
  	}
  	var queryParam = {};
  	// alert($("#username").val());
    // "/modules/module02/ActionTwo/aabbcc.do?${_csrf.parameterName}=${_csrf.token}";
  	var url = '/spring4study/modules/module02/ActionTwo/aabbcc.do';
  	$.ajax({
 		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
  		headers    : {
  	              	'X-CSRF-TOKEN': data,
  	    },
  	    dataType   : 'json',
  	    type       : "POST",
  	    timeout    : 20000,
  	    data       : queryParam,
        url        : url,
        success    : function(data){
        	if(data.success == false)
        		alert(data.message);
        },
        error     : function(err) {
        	alert(err);
        	if(err.success == false)
        		alert(err.message);
        }
    }); 
}
