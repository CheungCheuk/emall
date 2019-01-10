/**
 * 判断表单是否为空 
 * */

function isEmpty(value, content){
    if(value == null || value.length == 0){
        alert(content+"不能为空");
        return false;
    }
    return true;
}

/**
 * 获取 url 中的参数
 * @param {*} id 
 */
function getUrlParms(para) {
    //  页面URL的查询部分字符串，例如：edit_category?id=85
    var queryString = location.search;  //   queryString = "?id=85"

    //  数组元素存储包含 参数名和参数值的字符串，“key=value"
    var arrayPara = new Array();

    //  存储查找到的参数值
    var arrayValue = new Array();

    if( queryString != "" ){
        var index = 0;
        //  去除查询匹配 “？“   substr() 方法返回一个字符串，从指定位置开始到指定字符数的字符
        queryString = queryString.substr(1);    //  queryString = "id=85"
        //  根据 “&” 分割获取多个参数
        arrayPara = queryString.split("&"); //  arrayPara: ["id=85"]

        for (let i in arrayPara) {  //const i funcking wrong
            var paraPrefix = para+"=";  //  paraPrefix:"id=", para:"id"
            if( arrayPara[i].indexOf(paraPrefix)==0 
            && paraPrefix.length < arrayPara[i].length ){
                //  解码URI，避免出现乱码
                // arrayPara[i].substr(paraPrefix.lenght),将 "id=85"，裁剪成 “85”
                // decodeURI() 将已编码成 URI 的所有能识别的转义序列，转换成原字符 
                arrayValue[index] = decodeURI(arrayPara[i].substr(paraPrefix.length));    
                // arrayValue[index] = arrayPara[i].substr(paraPrefix.lenght);
                // 这句话出 bug 了 ，写了 == 判断语句，而不是 = 赋值语句，length 写成了 lenght
                // 导致 java.lang.NumberFormatException: For input string: "id=85"
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
function isDeleteLink(){
    var  confirmDelete = confirm("确认要删除");
    if(confirmDelete){
        return false;
    }
    return true;
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