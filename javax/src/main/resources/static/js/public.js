/**
 * 在表单提交时放入一个token，服务端严整该token是否有效，只允许有效的token请求。
 */

$(function(){
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e,xhr,options) {
        xhr.setRequestHeader(header, token);
    })
});