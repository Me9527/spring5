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
		  backColor: 'blue'
	});
});


//https://github.com/jonmiles/bootstrap-treeview


