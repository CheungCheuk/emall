/**
 * 获取 url 中的参数，例如：?good_id=5，
 * 获得id的值：5，作为 RESTful 传递的url参数：”good/5“
 * @param {*}  
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
        //  去除字符 “？“
        queryString = queryString.substr(1);    //  queryString: "id=85"
        //  根据 “&” 分割获取多个参数
        arrayPara = queryString.split("&"); //  arrayPara: ["id=85"]

        for (let i in arrayPara) {  //const i funcking wrong
            var paraPrefix = para+"=";  //  paraPrefix:"id=", para:"id"
            if( arrayPara[i].indexOf(paraPrefix)==0 
            && paraPrefix.length < arrayPara[i].length ){
                //  解码URI，避免出现乱码
                // arrayPara[i].substr(paraPrefix.lenght),将 "id=85"，切割成 “85”
                // decodeURI() 将已编码成 URI 的所有能识别的转义序列，转换成原字符 
                arrayValue[index] = decodeURI(arrayPara[i].substr(paraPrefix.length));    
                // arrayValue[index] = arrayPara[i].substr(paraPrefix.lenght);
                // 这句话出 bug 了 ，写了 == 判断语句，而不是 = 赋值语句，length 写成了 lenght
                // 导致 java.lang.NumberFormatException: For input string: "id=85"
                index++;
            }
        }
    }
    if( arrayValue.length == 1 ){
        return arrayValue[0];
    }else if ( arrayValue.length==0 ) {
        return null
    }else {
        return arrayValue;
    }
}

/**
 * 格式化 money
 */

function formatMoney(integer) {
    //  价格：整数部分
    integer = new String(integer);
    integer = integer.toString().replace(/\$|\,/g,'');
    if (isNaN(integer)) {
        integer = "0";
    }
    //  价格：符号位
    if (integer == Math.abs(integer)) {
        sign = true;
    }else{
        sign = false;
    }

    integer = Math.floor(integer*100 + 0.50000000001);
    decimal = integer % 100;
    for (let i = 0; i < Math.floor(( integer.length - (1+i)) / 3);  i++) {
        integer = integer.substring( 0,integer.length-(4*i+3)) + "," +
        integer.substring(integer.length-(4*i+3));
    }
    return (((sign)?'':'-') + integer + '.' + decimal);
}




