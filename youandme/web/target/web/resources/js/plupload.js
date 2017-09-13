$(function(){
    // Initialize the widget when the DOM is ready
    var uploader = $("#uploader").pluploadQueue({
        // General settings
        runtimes: 'html5,flash,silverlight,html4',
        url: "../pluploadUpload",

        // Maximum file size
        max_file_size: '10000mb',

        chunk_size: '1mb',


        // Resize images on clientside if we can
        resize: {
            width: 200,
            height: 200,
            quality: 90,
            crop: true // crop to exact dimensions
        },

        // Specify what files to browse for
        filters: [
            {title: "Image files", extensions: "jpg,gif,png"},
            {title: "Vedio files", extensions: "mp4,mkv"},
            {title: "Zip files", extensions: "zip,avi"}
        ],

        // Rename files by clicking on their titles
        rename: true,

        // Sort files
        sortable: true,

        // Enable ability to drag'n'drop files onto the widget (currently only HTML5 supports that)
        dragdrop: true,

        // Views to activate
        views: {
            list: true,
            thumbs: true, // Show thumbs
            active: 'thumbs'
        },

        // Flash settings
        flash_swf_url: '../plupload/js/Moxie.swf',

        // Silverlight settings
        silverlight_xap_url: '../plupload/js/Moxie.xap'
    });

    $("#toStop").on('click', function () {
        uploader.stop();
    });

    $("#toStart").on('click', function () {
        uploader.start();
    });

    Date.prototype.Format = function (fmt) { //author: meizz
        var o = {
            "M+": this.getMonth() + 1, //月份
            "d+": this.getDate(), //日
            "h+": this.getHours(), //小时
            "m+": this.getMinutes(), //分
            "s+": this.getSeconds(), //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds() //毫秒
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    };

    $.ajax({
        url:"showUploadFileOfUser",
        type:"POST",
        success:function(result){
            var fileItems = result["data"];
            console.log(fileItems);
            if(!fileItems.length>0){
                $(".myShareContent_tips_container").fadeIn(1);
                return;
            }
            for(var i=0;i<fileItems.length;i++){
                $(".myShareContent").append('<div class="shareFileDiv" id="shareFileDiv'+fileItems[i]["id"]+'"></div>');
                $("#shareFileDiv"+fileItems[i]["id"]).append('<span class="shareFileName">'+fileItems[i]["fileName"]+'</span>');
                $("#shareFileDiv"+fileItems[i]["id"]).append('<span class="shareFileUser">by : '+fileItems[i]["uploadUsername"]+'</span>');
                $("#shareFileDiv"+fileItems[i]["id"]).append('<span class="shareFileTime">'+new Date(fileItems[i]['uploadTime']).Format("yyyy-MM-dd hh:mm:ss")+'</span>');
                $("#shareFileDiv"+fileItems[i]["id"]).append('<a href="'+basePath+'/youandme/downloadFile/'+fileItems[i]["id"]+'" class="shareFileDownload">'+"下载"+'</a>');
                $("#shareFileDiv"+fileItems[i]["id"]).append('<a href="" onclick="deleteFile('+fileItems[i]["id"]+')" class="shareFileDelete">'+"删除"+'</a>');
            }
        },
        error:function(){
            alert("System inner error");
        }
    });
});

var deleteFile = function(id){
    $.ajax({
        url:"deleteFile",
        type:"POST",
        data:{id:id},
        success:function(){

        },
        error:function(){

        }
    });
};