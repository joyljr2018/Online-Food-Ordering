<script>
    function onBridgeReady(){
        WeixinJSBridge.invoke(
                'getBrandWCPayRequest', {
                    "appId":"${payResponse.appId}",     //appId, passed by the seller
                    "timeStamp":"${payResponse.timeStamp}",         //second since 1970
                    "nonceStr":"${payResponse.nonceStr}", //random generated
                    "package":"${payResponse.packAge}",
                    "signType":"MD5",         //Wechat signature type
                    "paySign":"${payResponse.paySign}" //wechat signature
                },
                function(res){
//                    if(res.err_msg == "get_brand_wcpay_request:ok" ) {

//                    }     // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
                    location.href = "${returnUrl}";
                }
        );
    }
    if (typeof WeixinJSBridge == "undefined"){
        if( document.addEventListener ){
            document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
        }else if (document.attachEvent){
            document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
            document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
        }
    }else{
        onBridgeReady();
    }
</script>