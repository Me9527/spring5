
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

var sidebarMenuData;

$(document).ready(function() {
	checkLogin();
	
	$('#myTree').treeview({
		data: getTree(),
		  levels: 10,
		  color : 'black',
		  backColor: 'SteelBlue'
	});
	
//    $('#exampleDatatable').DataTable( {
//        "ajax": 'arrays.txt'
//    } );
//    
    init_DataTables();
    editTable();
//  $("#doLoginBtn").click(function(event){
//	  ajaxLogin();
//  })
//  
//  $("#logoutBtn").click(function(event){
//	  ajaxLogout();
//  })
  

  
})

			
function init_DataTables() {

	console.log('run_datatables');
	if (typeof ($.fn.DataTable) === 'undefined') {
		return;
	}
	console.log('init_DataTables');

	var handleDataTableButtons = function() {
		if ($("#exampleDatatable").length) {
			$("#exampleDatatable").DataTable({
				dom : 'Blrtip',
				"ajax" : 'arrays.txt',
				buttons : [ {
					extend : "copy",
					className : "btn-sm"
				}, {
					extend : "csv",
					className : "btn-sm"
				}, {
					extend : "excel",
					className : "btn-sm"
				}, {
					extend : "pdfHtml5",
					className : "btn-sm"
				}, {
					extend : "print",
					className : "btn-sm"
				}, ],
				responsive : true
			});
		}
	};

	TableManageButtons = function() {
		"use strict";
		return {
			init : function() {
				handleDataTableButtons();
			}
		};
	}();

//	$('#datatable').dataTable();
//
//	$('#datatable-keytable').DataTable({
//		keys : true
//	});

//	$('#datatable-responsive').DataTable();

//	$('#datatable-scroller').DataTable({
//		ajax : "js/datatables/json/scroller-demo.json",
//		deferRender : true,
//		scrollY : 380,
//		scrollCollapse : true,
//		scroller : true
//	});

//	$('#datatable-fixed-header').DataTable({
//		fixedHeader : true
//	});

//	var $datatable = $('#datatable-checkbox');
//
//	$datatable.dataTable({
//		'order' : [ [ 1, 'asc' ] ],
//		'columnDefs' : [ {
//			orderable : false,
//			targets : [ 0 ]
//		} ]
//	});
//	$datatable.on('draw.dt', function() {
//		$('checkbox input').iCheck({
//			checkboxClass : 'icheckbox_flat-green'
//		});
//	});

	TableManageButtons.init();

};

function editTable(){

   var editor = new $.fn.dataTable.Editor( {
        ajax: "/spring4study/html/modules/module03/user.json",
        table: "#example",
        fields: [ {
                label: "First name:",
                name: "users.first_name"
            }, {
                label: "Last name:",
                name: "users.last_name"
            }, {
                label: "Phone #:",
                name: "users.phone"
            }, {
                label: "Site:",
                name: "users.site",
                type: "select"
            }
        ]
    } );
 
    // Activate an inline edit on click of a table cell
    $('#example').on( 'click', 'tbody td:not(:first-child)', function (e) {
        editor.inline( this, {
            onBlur: 'submit'
        } );
    } );
 
    $('#example').DataTable( {
        dom: 'Bfrtip',
        'paging':   true,
        'ordering': false,
        'info':     true,        
        ajax: {
            url: "/spring4study/html/modules/module03/user.json",
            type: 'POST'
        },
        columns: [
            {
                data: null,
                defaultContent: '',
                className: 'select-checkbox',
                orderable: false
            },
            { data: "users.first_name" ,  "visible": true},
            { data: "users.last_name" },
            { data: "users.phone" },
            { data: "sites.name", editField: "users.site" }
        ],
        order: [ 1, 'asc' ],
        select: {
            style:    'os',
            selector: 'td:first-child'
        },
        buttons: [
            { extend: "create", editor: editor },
            { extend: "edit",   editor: editor },
            { extend: "remove", editor: editor }
        ]
    } );
	
}


function logout(){
	var url = '/spring4study/modules/user/logout';
	$.ajax({
  	   type: "POST",
  	   dataType   : 'json',
  	   async :false,
  	   timeout : 20000,
       url : url,
       success: function(data){
    	   if(data.success){
    		   $(location).attr('href', '/spring4study');
    	   }else{
    		   alert(data.messsage);
    	   }
       },
       error : function(err) {
    	   alert(err);
       }
    });
}

function checkLogin(){
	var url = '/spring4study/getUserInfo.do';
	$.ajax({
  	   type: "GET",
  	   async :false,
  	   timeout : 20000,
       url : url,
       success: function(data){
    	   if(data.success){
    		   sidebarMenuData = data.data.menus;
    		   makeMenu();
    	   }else{
    		  $(location).attr('href', '/spring4study/jsp/modules/user/login.jsp');
    	   }
       },
       error : function(err) {
    	   alert(err);
       }
    });
}

function makeMenu(){
//	var sidebarMenuData = ajaxGetSidebarMenuData();
	//nodes 层次数必须>=2,与菜单展示形式有关。
	var sidebarMenuDataTitle = "功能菜单";
	var nodes = sidebarMenuData;

	$('#sidebar-menu').append("<div class='menu_section' id='menu_section_01" + "'/>");
	var menuSection = $('#' + 'menu_section_01');
	menuSection.append("<h3>" + sidebarMenuDataTitle + "</h3>");
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
