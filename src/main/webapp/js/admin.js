/**
 * 判断表单是否为空 
 * */

function checkEmpty(value, content){
    if(value == null || value.length == 0){
        alert(content+"不能为空");
        return false;
    }
    return true;
}
function getUrlParms(para) {
    //  页面URL的查询部分字符串
    var queryString = location.search;

    //  数组元素存储包含 参数名和参数值的字符串，“key=value"
    var arrayPara = new Array();

    //  存储查找到的参数值
    var arrayValue = new Array();

    if( queryString!=""){
        var index = 0;
        //  去除查询匹配 “？“
        queryString = queryString.substr(1);
        //  根据 “&” 分割获取多个参数
        arrayPara = queryString.split("&");

        for (const i in arrayPara) {
            var paraPrefix = para+"=";
            if(arrayPara[i].indexOf(paraPrefix)==0 
            && paraPrefix.length<arrayPara[i].length){
                //  解码URI
                arrayValue[index]==decodeURI(arrayPara[i].substr(paraPrefix.lenght))
                index++;
            }
        }
    }
    if(arrayValue.length==1){
        return arrayValue[0];
    }else if (arrayValue.length==0) {
        return null
    }else {
        return arrayValue;
    }
}

/**
 * 判断是否数字
 */
function checkNumber(value,content){
    if(value.length == 0){
        alert(content+"不能为空");
        return false;
    }
    if(isNaN(value)){
        alert(content+"必须输入数字");
        return false;
    }
    return true;
}

/**
 * 判断是否是整数
 */
function checkInt(value,content){
    if(value.length == 0){
        alert(content+"不能为空");
        return false;
    }
    if(isNaN(value)){
        alert(content+"必须输入整数");
        return false;
    }
    return true;
}

/**
 * 判断是否删除
 */
function checkDeleteLink(){
    var  confirmDelete = confirm("确认要删除");
    if(confirmDelete){return true;}
    return false;
}


/**
 * 页面分页跳转
 * 跳转到第一页、最后一页或者前一页、后一页
 */
function jump(page,vue){
    if('first'==page && ! vue.pagination.first){
        vue.list(0);
    }
    else if ('previous' == page && vue.pagination.hasPrevious) {
        vue.list(vue.pagination.number-1);
    }
    else if ('next' == page && vue.pagination.hasNext) {
        vue.list(vue.vue.pagination.number+1);
    }
    else if ('last' == page && vue.pagination.last){
        vue.list(vue.pagination.totalPages-1);
    }
}

/**
 * 跳转到指定页面
 */
function jumpByNumber(start, vue){
    if(start !== vue.pagination.number){
        vue.list(start);
    }
}