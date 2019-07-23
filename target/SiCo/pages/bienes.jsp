<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<%@ include file="/templates/plantilla.jsp" %> 

<h3>Bienes y servicios</h3>
<div class="container">
	<div class="row">
		<div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
			<div id="default-tree"></div>
		</div>
	</div>
</div>

<%@ include file="/templates/footer.jsp" %> 




<script type="text/javascript">

var myTree = [{
				text: "Item 1",
				nodes: [{
					text: "Item 1-1",
					nodes: [{
							text: "Item 1-1-1"
						},
						{
							text: "Item 1-1-2"
						}
						]
				},
				{
					text: "Item 1-2"
				}]
			},
			{
				text: "Item 2"
			},
			{
				text: "Item 3"
	    	}];


	$('#default-tree').treeview({
		data: myTree,
		//levels: 2,
		expandIcon: 'glyphicon glyphicon-plus',
		collapseIcon: 'glyphicon glyphicon-minus',
		emptyIcon: 'glyphicon',
		nodeIcon: '',
		selectedIcon: '',
		checkedIcon: 'glyphicon glyphicon-check',
		uncheckedIcon: 'glyphicon glyphicon-unchecked',
		color: undefined, // '#000000',
		backColor: undefined, // '#FFFFFF',
		borderColor: undefined, // '#dddddd',
		onhoverColor: '#F5F5F5',
		selectedColor: '#FFFFFF',
		selectedBackColor: '#428bca',
		searchResultColor: '#D9534F',
		searchResultBackColor: undefined, //'#FFFFFF',
		enableLinks: false,
		highlightSelected: true,
		highlightSearchResults: true,
		showBorder: true,
		showIcon: true,
		showCheckbox: false,
		showTags: false,
		multiSelect: false
	});


/*
var treeObject = [{
		text:"Parent 1", 
		checked:true, // Optional
		id:15,otherDatas:"Other Datas", // Other Datas Optional
		children:[ 
			{ text:"Child 1" , checked:true	},
			{ text:"Child 2" 	}
		]
	},
	{
		text:"Parent 2", 
		children:[
			{
				text:"Parent 3",
				children:[
					{text:"Child 3",checked:true},
					{text:"Child 4"}
				]
			}
		]
	}
]
	var tw = new TreeView(
		treeObject,
		{showAlwaysCheckBox:true,fold:false});
	//document.body.appendChild( tw.root	 )
	$("arbolbienes").append(tw.root)
*/
</script>