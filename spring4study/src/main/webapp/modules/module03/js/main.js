
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
	
	$.extend( $.fn.dataTable.defaults, {
	    searching: false,
	    ordering:  false
	} );
	
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
    editTable2();
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
				dom : 'Blprtip',
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

function editTable2(){

	   	var editor = new $.fn.dataTable.Editor({
	   		ajax : "/spring4study/getUserInfoDemoData.do",
	   		table : "#example2",
	   		idSrc : "id",
	   		fields : [ {
	   			label : "nickName:",
				name  : "nickName",
				type  : "select"
	   		}, {
	   			label : "company:",
				name : "company"
	   		}, {
	   			label : "position:",
				name : "position"
	   		}, {
	   			label : "birthday:",
	   			name : "birthday"
	   		} ]
	   	});
	 
// Activate an inline edit on click of a table cell
	    $('#example2').on( 'click', 'tbody td:not(:first-child)', function (e) {
	        editor.inline( this, {
	            onBlur: 'submit'
	        } );
	    } );
	    var columsName = {
	            'nickName': '姓名',
	            'position': '位置',
	            'company': '公司',
	            'birthday': '生日',
	            'extn': '其他'
	        };
	    
	   	var checkbox =  {
	   			'title': '选择框',
                data: null,
                defaultContent: '',
                className: 'select-checkbox',
                orderable: false
            };
	   	var c1 = {'title': columsName["nickName"], data: "nickName" ,  "visible": true};
	   	var c2 = {'title': columsName["company"],  data: "company",  	render: $.fn.dataTable.render.text() };
	   	var c3 = {'title': columsName["position"], data: "position",  	render: $.fn.dataTable.render.text() };
	   	var c4 = {'title': columsName["birthday"], data: "birthday",  	render: $.fn.dataTable.render.text() };	  
	   	var c5 = { data: "id" ,  "visible": false};
	   	var columns = [];
	   	columns.push(checkbox);	columns.push(c1);	columns.push(c2);	columns.push(c3);	columns.push(c4);	columns.push(c5);
	   	
	    $('#example2').DataTable( {
	         dom			: 'lBrptip',
	        'paging'		: true,
	        'ordering'		: false,
	        'info'			: true,
	        'pagingType'	: 'full_numbers',
	        scrollY			: '50vh',
	        scrollCollapse	: true,
	        paging			: true,        
	        stateSave		: true,
	        'destroy'		: true,
	        ajax		: {
	            url: '/spring4study/getUserInfoDemoData.do',
	            type: 'POST'
	        },
	        columns		: columns,
            language	: {
                'oAria': {
                    'sSortAscending': ': 升序排列',
                    'sSortDescending': ': 降序排列'
                },
                'oPaginate': {
                    'sFirst': '首页',
                    'sLast': '末页',
                    'sNext': '下页',
                    'sPrevious': '上页'
                },
                'sEmptyTable': '没有相关记录',
                'sInfo': '第 _START_ 到 _END_ 条记录，共 _TOTAL_ 条',
                'sInfoEmpty': '第 0 到 0 条记录，共 0 条',
                'sInfoFiltered': '(从 _MAX_ 条记录中检索)',
                'sInfoPostFix': '',
                'sDecimal': '',
                'sThousands': ',',
                'sLengthMenu': '每页显示条数: _MENU_',
                'sLoadingRecords': '正在载入...',
                'sProcessing': '正在载入...',
                'sSearch': '搜索:',
                'sSearchPlaceholder': '',
                'sUrl': '',
                'sZeroRecords': '没有相关记录'                   	
            },      
	        order		: [ 1, 'asc' ],
	        select		: {
	            style	: 'os',
	            selector: 'td:first-child'
	        },
	        buttons		: [
	            { extend: "create", editor: editor },
	            { extend: "edit",   editor: editor },
	            { extend: "remove", editor: editor },
	            
	            { extend: "csv",		className: "btn-default" },
	            { extend: "excel",		className: "btn-default" },
	            { extend: "pdfHtml5",	className: "btn-default" },
	            { extend: "print", 		className: "btn-default" }
	        ]
	    } );
		
	}

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
        dom: 'lBrptip',
        'paging':   true,
        'ordering': false,
        'info':     true,
        'pagingType': 'full_numbers',
        scrollY:        '50vh',
        scrollCollapse: true,
        paging:         true,        
        stateSave:  true,
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
            { data: "users.last_name",  render: $.fn.dataTable.render.text() },
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
            { extend: "remove", editor: editor },
            
            { extend: "csv",		className: "btn-default" },
            { extend: "excel",		className: "btn-default" },
            { extend: "pdfHtml5",	className: "btn-default" },
            { extend: "print", 		className: "btn-default" }
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
    		  $(location).attr('href', '/spring4study/modules/user/jsp/login.jsp');
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
	var url = '/spring4study/modules/user/jsp/getCsrf.jsp';
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
