function operateFormatter(value, row, index) {
	    return [
		 '<a href="javascript:void(0)" onclick="detailData(\''+row.tableName+'\');" title="详情">',
		 '详情',
		 '</a>  |',
         '<a href="javascript:void(0)" onclick="importData(\''+row.tableName+','+row.className+'\');" title="导入">',
            '上传',
         '</a>  |',
         '<a href="javascript:void(0)" onclick="exportData(\''+row.tableName+','+row.className+'\');"  title="下载">',
        	'下载',
         '</a>'
    ].join('');
}

//核查
function checkButton(){
	var classify = $('#classify').val();
	var vendor = $("#vendor").val();
	var deviceId = $("#deviceName").val();
	if(classify=='0'){
		alert('未选择业务，请选择');//.value = '未选择业务，请选择';
		return;
	}
	if(vendor=='0'){
		//$('errorVedor').value = '未选择厂家，请选择';
		alert('未选择厂家，请选择');
		return;
	}
	if(deviceId=='0'){
		//$('errorDeviceName').innerText = '未选择网元，请选择';
		alert('未选择网元，请选择');
		return;
	}
	debugger;
	$.ajax({
		url:"/check/"+classify+"/"+vendor+"/"+deviceId,
		dataType:"json",
		traditional: true,//属性在这里设置
		method:"post",
		async : false,
		success:function(data){
			document.getElementById("tipContent").innerText="删除成功";
			$("#Tip").modal('show');
			$("#dataGrid").bootstrapTable("refresh");
		},
		error:function(){
			document.getElementById("tipContent").innerText="删除失败";
			$("#Tip").modal('show');
		}
	});
}

function responseHandler(res) {
	    $.each(res.rows, function (i, row) {
	        row.state = $.inArray(row.id, selections) !== -1;
	    });
	    return res;
	}
	//点击取消后清空表单中已写信息
function resetAddModal(){
	document.getElementById("addTableForm").reset();
}
//新增标准数据
function addTable(){
	var param = $("#addTableForm").serializeArray();
	$("#conf").attr("onclick","addTable()");
	$.ajax({
		url:"/data/addTable/"+type,
		method:"post",
		data : {param: JSON.stringify(param)},
		dataType:"json",
		success:function(data){
			debugger;
			if(data.state=="success"){
				document.getElementById("tipContent").innerText="新增成功";
				$("#addEnd").modal('show');
				$("#addTableModal").modal('hide');
				$("#dataGrid").bootstrapTable('refresh');
			}else{
				var message = data.message;
				document.getElementById("tipContent").innerText="新增失败，"+message;
				$("#addEnd").modal('show');
			}
		},
		error:function(){
			debugger;
			document.getElementById("tipContent").innerText="新增失败";
			$("#addEnd").modal('show');
		}
	});
}

//修改标准数据
function editTable(){
	debugger;
	//获取选中行的数据
	var rows = $("#dataGrid").bootstrapTable('getSelections');
	if(rows.length!=1){
		document.getElementById("al").innerText="请选择一行数据";
		$("#updateEnd").modal('show');
	}
	else{
		var row = rows[0];
		$('#editId').val(row.id);
		$('#editTableName').val(row.tableName);
		$('#editDisplayName').val(row.displayName);
		$('#editClassify').val(row.classify);
		$('#editClassName').val(row.className);
		$('#editTableDesc').val(row.tableDesc);
		$("#editTable").modal("show");
	}
}
function updateTable(){
	var param = $("#editTabelForm").serializeArray();
	//设为disable则无法获取
	$.ajax({
		url:"/data/updateTable/"+type,
		method:"post",
		data: {param: JSON.stringify(param)},
		dataType:"json",
		success:function(data){
			if(data.state=="success"){
				$("#editTable").modal("hide");
				document.getElementById("al").innerText="修改成功";
				$("#updateEnd").modal('show');
				$("#dataGrid").bootstrapTable('refresh');
			}else{
				var message = data.message;
				document.getElementById("al").innerText="修改失败，"+message;
				$("#updateEnd").modal('show');
			}
		},
		error:function(data){
			document.getElementById("al").innerText="修改失败";
			$("#updateEnd").modal('show');
		}
	});
}
function deleteTable(){
	var rows = $("#dataGrid").bootstrapTable("getSelections");
	if(rows.length<1){
		document.getElementById("al").innerText="请选择数据";
		$("#updateEnd").modal('show');
	}
	var ids = [];
	var len = rows.length;
	debugger;
	for(var i=0;i<len;i++){
		ids.push(rows[i].id);
	}
	debugger;
	$.ajax({
		url:"/data/deleteTable",
		dataType:"json",
		traditional: true,//属性在这里设置
		method:"post",
		async : false,
		data:{
			"ids":JSON.stringify(ids)
		},
		success:function(data){
			document.getElementById("tipContent").innerText="删除成功";
			$("#Tip").modal('show');
			$("#dataGrid").bootstrapTable("refresh");
		},
		error:function(){
			document.getElementById("tipContent").innerText="删除失败";
			$("#Tip").modal('show');
		}
	});
}
function downLoadTable(){
	 window.location.href="/data/download/"+type;
}

//上传
function importData(tableName){
	$("#importDialog").modal("show");
	var uploadUrl="/excel/import/"+tableName;
	initFileInput('importFile',uploadUrl);
}

//下载
function exportData(tableName){
	//alert(tableName);
	window.location.href="/excel/export/"+tableName;
}

//详情
function detailData(tableName){
	//alert(tableName);
	if(type == '0'){
		window.location.href="/sdetail/"+tableName;
	}else{
		window.location.href="/adetail/"+tableName;
	}
}