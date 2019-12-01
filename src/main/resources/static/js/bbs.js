
function comment(){
    var parentId = $("#parentId").val();
    var content = $("#content").val();

    $.ajax({
        type:"post",
        url:"/comment",
        contentType:"application/json",
        data:JSON.stringify({
            "parentId":parentId,
            "content":content,
            "type":1
        }),
        success:function(data){
            if(data.code==200){
                $("#commentArea").hide();
            }else if(data.code==401){
                var isLogin = confirm("您还没有登录，是否确认登录？");
                if(isLogin){
                    window.open("https://github.com/login/oauth/authorize?client_id=00454ffc16e1687b1914&redirect_uri=http://localhost:8080/callback&scope=user&state=1");
                    window.localStorage.setItem("closable","true");
                }
            }else{
                alert(data.message);
            }
            console.log(data);
        },
        dataType:"json"

    });
}