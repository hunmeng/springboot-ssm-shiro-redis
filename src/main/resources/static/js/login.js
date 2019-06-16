$(document).ready(function() {
    $(function () { $("[data-toggle='tooltip']").tooltip(); });
     var windowWidth =  $(window).width();
     var left = "350px";
     var height = "500px";
     var width = "380px";
    if(windowWidth<375){
        width = "316px";
    }

    //打开会员登录
    $("#Login_start_").click(function() {
        $("#regist_container").hide();
        $("#login_phone").hide();
        $("#forget_password").hide();
        $("#forget_account").hide();
        $("#_close").show();
        $("#_start").animate({
            left: left,
            height: height,
            width: width
        }, 500, function() {
            $("#login_container").show(500);
            $("#_close").animate({
                height: '40px',
                width: '40px'
            }, 500);
        });
    });
    //打开会员注册
    $("#Regist_start_").click(function() {
        $("#login_container").hide();
        $("#login_phone").hide();
        $("#forget_password").hide();
        $("#forget_account").hide();
        $("#_close").show();
        $("#_start").animate({
            left: left,
            height: height,
            width: width
        }, 500, function() {
            $("#regist_container").show(500);
            $("#_close").animate({
                height: '40px',
                width: '40px'
            }, 500);
        });
    });
    //关闭
    // $("#_close").click(function() {
    //     $("#_close").animate({
    //         height: '0px',
    //         width: '0px'
    //     }, 500, function() {
    //         $("#_close").hide(500);
    //         $("#login_phone").hide();
    //         $("#forget_password").hide();
    //         $("#forget_account").hide();
    //         $("#login_container").hide(500);
    //         $("#regist_container").hide(500);
    //         $("#_start").animate({
    //             left: '0px',
    //             height: '0px',
    //             width: '0px'
    //         }, 500);
    //     });
    // });
    // 账号去注册
    $("#toRegist").click(function(){
        $("#login_container").hide(500);
        $("#regist_container").show(500);
    });
    //短信去 注册phoneToRegist
    $("#phoneToRegist").click(function(){
        $("#login_phone").hide(500);
        $("#regist_container").show(500);
    });
    //去 账号登录
    $("#toLogin").click(function(){
        $("#regist_container").hide(500);
        $("#login_container").show(500);
        $("#login_phone").hide(500);
    });
    //忘记密码
    $("#rememberOrfindPwd").click(function(){
        $("#forget_password").show(500);
        $("#login_container").hide(500);
        $("#_start").animate({
            height: '320px'
        })
    });
    $("#pwdToAccount").click(function(){
        $("#login_container").show(500);
        $("#forget_password").hide(500);
        $("#_start").animate({
            height: '500px'
        })
    });
    //忘记账号
    $("#findToAccount").click(function(){
        $("#forget_account").show(500);
        $("#forget_password").hide(500);
        $("#_start").animate({
            height: '320px'
        })
    });
    $("#accountToLogin").click(function(){
        $("#login_container").show(500);
        $("#forget_account").hide(500);
        $("#_start").animate({
            height: '500px'
        })
    });
    $("#accountToEmail").click(function(){
        $("#forget_password").show(500);
        $("#forget_account").hide(500);
    });
    //切换
    $("#toLoginPhone").click(function(){
        $("#login_container").hide(500);
        $("#login_phone").show(500);
    });
    $("#toLoginAccount").click(function(){
        $("#login_phone").hide(500);
        $("#login_container").show(500);
    });

});

function onsub() {
    var account = $("#login_number").val();
    var password =  $("#login_password").val();
    var yzm =  $("#regist_vcode1").val();

    var doReture = true;
    var reTurnString = "";
    //判断是否为空
    if(""==account){
        reTurnString ="请输入账号！！！";
        doTooltip("login_number",reTurnString);
        doReture =  false;
    }else if(""==password){
        reTurnString ="请输入密码！！！";
        doTooltip("login_password",reTurnString);
        doReture = false;
    }else if(""==yzm){
        reTurnString = "请输入验证码";
        doTooltip("regist_vcode1",reTurnString);
        doReture =  false;
    }else{
        //判断是否是英文
        if(isChn(account)==false){
            reTurnString ="请输入正确的账号(不能存在汉字)";
            doTooltip("login_number",reTurnString);
            doReture = false;
        }
        // else if(isEmail(account)==false){
        //     //检查email邮箱
        //     reTurnString ="请输入正确的邮箱账号";
        //     doTooltip("login_number",reTurnString);
        //     doReture = false;
        // }
        //判断是否是英文
        if(isChn(password)==false){
            reTurnString ="请输入正确的密码(不能存在汉字)";
            doTooltip("login_password",reTurnString);
            doReture = false;
        }else if(password.length<6){
            reTurnString ="请输入6位以上的密码！！！";
            doTooltip("login_password",reTurnString);
            doReture = false;
        }
    }
    if(doReture){
        login(account,password,yzm);
    }
}

//检查email邮箱
function isEmail(str){
    var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
    return reg.test(str);
}

//检查phone号码
function isPhone(str){
    if (""==str){
        return false;
    }
    var reg =/^1[3456789]\d{9}$/;
    return reg.test(str);
}

// 检查是否为中文
function isChn(str){
    var reg = /.*[\u4e00-\u9fa5]+.*$/;
    if(reg.test(str)){
        return false;
    }
    return true;
}
//声明一个保存Tooltip的全局变量
var arr = new Array();
function doTooltip(id,string) {
    //保存之前tooltip的数据
    var getTitle = $("#"+id).attr('data-original-title');
    arr.push(id+","+getTitle);
    //弹出红色边框，修改提示title
    document.getElementById(id).style.borderColor="#ff0300";
    $("#"+id).attr('title',string).tooltip('fixTitle');
    $("#"+id).tooltip('toggle');
}

//清除输入框颜色
function doBorder(obj) {
    obj.style.borderColor="";
    var arrlength= arr.length;
    //恢复之前的提示
    if (arrlength>0){
        for(var i = 0;i<arrlength;i++){
            var arrs =  arr[i].split(",");
            $("#"+arrs[0]).attr('title',arrs[1]).tooltip('fixTitle');
        }
        arr = new Array();
    }
}
//定义显示/隐藏函数。
function hideModal(){
    $('#myModal').modal('hide');
}

function showModal(){
    var h = $('#_start').css("height");
    //parseInt强转px为可计算数字
    document.getElementById("myModal").style.marginTop=parseInt(h)/2-50+"px";
    $('#myModal').modal({backdrop:'static',keyboard:false});
}

//账号登录
function login(account,password,yzm) {
    //定义json对象
    var json = {
        "userAccount" : account,
        "userPassword" : password,
        "yzm":yzm
    };
    // Jquery Ajax请求
    $.ajax({
        url : "user/login",
        type : "POST",
        async : true,
        data : json,
        dataType : 'text',
        beforeSend:function (){
            showModal();
        },
        success : function(data) {
            //alert(data)
            if (data == "0") {
                doTooltip("regist_vcode1","请输入正确的验证码");
                document.getElementById("regist_vcode1").value="";
                onImage(document.getElementById("getVCode"));
                hideModal();
            } else if(data=="1") {
                hideModal();
                alert("登录成功...");
                location.reload();
            }else{
                document.getElementById("regist_vcode1").value="";
                onImage(document.getElementById("getVCode"));
                hideModal();
                alert(data);
            }
        }
    });
}

var clock = '';
var nums = 120;
var btn;
//短信发送验证码
function sendCode(thisBtn,id) {
    var phone = $("#"+id).val();
    if(isPhone(phone)){
        // Jquery Ajax请求
        $.ajax({
            url : "user/doPhoneYzm",
            type : "POST",
            async : true,
            data : {phone:phone},
            dataType : 'json',
            beforeSend:function (){
                showModal();
            },
            success : function(data) {
                if (data.code == 100200) {
                    alert("发送成功...");
                    btn = thisBtn;
                    btn.disabled = true; //将按钮置为不可点击
                    btn.value = '重新获取（' + nums + '）';
                    clock = setInterval(doLoop, 1000); //一秒执行一次
                    hideModal();
                } else{
                    alert("发送失败...");
                    hideModal();
                }
            }
        });
    }else{
        doTooltip(id,"请输入正确的电话号码！");
    }
}

function doLoop() {
    nums--;
    if (nums > 0) {
        btn.value = '重新获取（' + nums + '）';
    } else {
        clearInterval(clock); //清除js定时器
        btn.disabled = false;
        btn.value = '发送验证码';
        nums = 10; //重置时间
    }
}

// ajax提交数据的时候，通常是提交一个表单，所以，序列化表单数据就非常有用，如：$("form").serialize()
$("#phone_form").on("submit",function(){

    var phone = $("#login_number2").val();
    if(""==phone){
        doTooltip("login_number2","请输入电话号码！");
        return false;
    }else if(phone.length<11){
        doTooltip("login_number2","请输入正确的电话号码！");
        return false;
    }
    var yzm = $("#regist_vcode2").val();
    if(""==yzm){
        doTooltip("regist_vcode2","请输入验证码！");
        return false;
    }else if(yzm.length<6){
        doTooltip("regist_vcode2","请输入6位的验证码！");
        return false;
    }
    var url = this.action;   //可以直接取到表单的action
    var formData = $(this).serialize();
    // Jquery Ajax请求
    $.ajax({
        url : url,
        type : "POST",
        async : true,
        data : formData,
        dataType : 'json',
        beforeSend:function (){
            showModal();
        },
        success : function(data) {
            if (data.code == 100200) {
                hideModal();
                alert(data.datas);
                location.reload();
            }else if(data.code == "no"){
                doTooltip("regist_vcode2","登录失败！验证码错误");
                hideModal();
            }else if(data.code == 100204){
                alert("登录失败！账号不存在");
                document.getElementById("regist_vcode2").value="";
                hideModal();
            }
            else{
                alert("登录失败！错误码:"+data.code);
                document.getElementById("regist_vcode2").value="";
                hideModal();
            }
        }
    });

    //阻止表单默认提交行为
    return false

})

// ajax提交数据的时候，通常是提交一个表单，所以，序列化表单数据就非常有用，如：$("form").serialize()
$("#regist_submit").on("submit",function(){

    var account = $("#regist_account").val();
    if(""==account){
        doTooltip("regist_account","请输注册的账号！");
        return false;
    }else if(account.length>50){
        doTooltip("regist_account","您输入的账号位数过长！！");
        return false;
    }
    var password1 = $("#regist_password1").val();
    var password2 = $("#regist_password2").val();
    if(""==password1){
        doTooltip("regist_password1","请输入密码！！");
        return false;
    }else if(password1.length<6){
        doTooltip("regist_password1","请输入6位或6位以上的密码！！！");
        return false;
    }else if(password1!=password2){
        $("#regist_password2").val("");
        doTooltip("regist_password2","两次输入的密码不一致！！");
        return false;
    }
    var yzm = $("#regist_vcode5").val();
    if(""==yzm){
        doTooltip("regist_vcode5","请输入验证码！");
        return false;
    }else if(yzm.length<4){
        doTooltip("regist_vcode5","请输入6位的验证码！");
        return false;
    }
    var url = this.action;   //可以直接取到表单的action
    var formData = $(this).serialize();
    // Jquery Ajax请求
    $.ajax({
        url : url,
        type : "POST",
        async : true,
        data : formData,
        dataType : 'text',
        beforeSend:function (){
            showModal();
        },
        success : function(data) {
            if (data>0) {
                hideModal();
                alert("注册成功...");
                location.reload();
            }else if("yzmNo"==data){
                hideModal();
                $("#regist_vcode5").val("");
                doTooltip("regist_vcode5","请输入正确的验证码！");
            }
            else{
                hideModal();
                alert("注册失败！");
                document.getElementById("regist_vcode5").value="";
            }
        }
    });

    //阻止表单默认提交行为
    return false

})



