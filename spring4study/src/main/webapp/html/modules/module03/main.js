
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
	checkLogin();
	
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

function checkLogin(){
	var url = '/spring4study/getUserInfo.do';
	$.ajax({
  	   type: "GET",
  	   async :false,
  	   timeout : 20000,
       url : url,
       success: function(data){
    	   if(data.success)
    		   ;
    	   else{
    		  $(location).attr('href', '/spring4study/jsp/modules/user/login.jsp');
    	   }
       },
       error : function(err) {
    	   alert(err);
       }
    });
}

function makeMenu(){
	var sidebarMenuData = ajaxGetSidebarMenuData();
	//nodes 层次数必须>=2,与菜单展示形式有关。
	var nodes = sidebarMenuData.nodes;

	$('#sidebar-menu').append("<div class='menu_section' id='menu_section_01" + "'/>");
	var menuSection = $('#' + 'menu_section_01');
	menuSection.append("<h3>" + sidebarMenuData.title + "</h3>");
	menuSection.append("<ul class='nav side-menu' id='side_menu_01" + "'/>");
	var menuContain = $('#' + 'side_menu_01');	

	
	for(var i=0; i<nodes.length; i++){	
		menuContain.append("<li id='side-menu-li-" + nodes[i].id + "'/>");
		var topNode = $('#' + 'side-menu-li-' + nodes[i].id );
		topNode.append("<a><i class='fa fa-sitemap'></i>" +  nodes[i].name+ "<span class='fa fa-chevron-down'></span></a>");
		
		rescureMakeMenu(nodes[i], topNode);
	}
	init_sidebar();
}

function rescureMakeMenu(node, parentNode){
	var children = node.children;
	if(null != children && children != "undefined" && children.length > 0){
		parentNode.append("<ul class='nav child_menu' id='t_child_menu-" + node.id + "'/>");
		var child_menu = $('#' + 't_child_menu-' + node.id );
		for(var k=0; k<children.length; k++){
			var childrenChildren = children[k].children;
			if(null != childrenChildren && childrenChildren != "undefined" && childrenChildren.length > 0){
				child_menu.append("<li id='child-menu-li-" + children[k].id + "'/>");
				var child_child_menu = $('#' + 'child-menu-li-' + children[k].id );
				child_child_menu.append("<a>" +  children[k].name+ "<span class='fa fa-chevron-down'></span></a>");
				rescureMakeMenu(children[k], child_child_menu);
			}else{
				child_menu.append("<li><a href='javascript:void(0)' id='func_" + children[k].id + "'>" + children[k].name + "</a></li>");
				$('#' + 'func_' + children[k].id ).on("click", children[k], function(event){
					funcNodeClick(event);
				});
			}
		}
	}
}

function funcNodeClick(funcNode){
	alert(funcNode.data.name);
}

function ajaxGetSidebarMenuData(){
	var url = '/spring4study/html/modules/module03/menus.json';
	var sidebarMenuData = null;
	$.ajax({
  	   type: "GET",
  	   async :false,
  	   timeout : 20000,
       url : url,
       success: function(data){
    	   // alert(data);
    	   sidebarMenuData = data;
       },
       error : function(err) {
    	   // alert(err);
       }
    });
	return sidebarMenuData;
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
