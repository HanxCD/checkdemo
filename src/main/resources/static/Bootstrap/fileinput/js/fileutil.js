//初始化fileinput控件（第一次初始化）
function initFileInput(ctrlName, uploadUrl) {    
	//alert("111");
    var control = $('#' + ctrlName); 

    //初始化上传控件的样式
    control.fileinput({
        language: 'zh', //设置语言
        uploadUrl: uploadUrl, //上传的地址
        allowedFileExtensions: ['xlsx', 'txt'],//接收的文件后缀
        //showUpload: false,  
        //showCaption: false,//是否显示标题
        //uploadAsync : true,
        //showCancel : false,
        showClose: true,  //显示上传按钮
        showUpload: true, //是否上传文件
        showRemove: true,
        dropZoneEnabled: false,//是否显示拖拽区域
        browseClass: "btn btn-primary", //按钮样式  
        maxFileCount: 10, //表示允许同时上传的最大文件个数
        enctype: 'multipart/form-data'
    });
    
    //导入文件上传完成之后的事件
     control.on("fileuploaded", function (event, data, previewId, index) {
    	 console.log("Upload success");
    });
    
    control.on('fileerror', function(event, data, msg) {//异步上传失败结果处理
    	console.log("Upload failed")
    });
}