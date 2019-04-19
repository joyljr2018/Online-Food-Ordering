<html>
    <#include "../common/header.ftl">
    <body>
    <div id="wrapper" class="toggled">
        <#--sidebar-->
        <#include  "../common/nav.ftl">

        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>
                                    Order ID
                                </th>
                                <th>
                                    Name
                                </th>
                                <th>
                                    Phone number
                                </th>
                                <th>
                                    Address
                                </th>
                                <th>
                                    Pay Amount
                                </th>
                                <th>
                                    Order status
                                </th>
                                <th>
                                    Payment status
                                </th>
                                <th>
                                    Order create time
                                </th>
                                <th colspan="2">
                                    Process
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                    <#list orderDTOPage.content as orderDTO>




                    <tr>
                        <td>
                            ${orderDTO.getOrderId()}
                        </td>
                        <td>
                            ${orderDTO.buyerName}
                        </td>
                        <td>
                            ${orderDTO.buyerPhone}
                        </td>
                        <td>
                            ${orderDTO.buyerAddress}
                        </td>
                        <td>
                            ${orderDTO.orderAmount}
                        </td>
                        <td>
                            ${orderDTO.getOrderStatusEnum()}
                        </td>
                        <td>
                            ${orderDTO.getPaymentStatusEnum()}
                        </td>
                        <td>
                            ${orderDTO.createTime}
                        </td>

                        <td>
                            <a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}">Detail</a></td>
                        <td>
                            <#if orderDTO.getOrderStatusEnum().message=="New Order">
                                <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">Cancel</a>
                            </#if></td>
                    </tr>
                    </#list>
                            </tbody>
                        </table>
                    </div>
                </div>
            <#--pagination -->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                <#if currentPage lte 1>
                                                    <li class="disabled">
                                                        <a href="#">Prev</a>
                                                    </li>
                <#else>
                                <li>
                                    <a href="/sell/seller/order/list?page=${currentPage-1}&size=${orderDTOPage.size}">Prev</a>
                                </li>
                </#if>

                <#list 1..orderDTOPage.getTotalPages() as index>
                    <#if currentPage==index>
                    <li class="disabled"><a href="#">${index}</a></li>
                    <#else>
                      <li><a href="/sell/seller/order/list?page=${index}&size=${orderDTOPage.size}">${index}</a></li>
                    </#if>
                </#list>
                                <#if currentPage gte orderDTOPage.getTotalPages()>
                                                    <li class="disabled">
                                                        <a href="#">Next</a>
                                                    </li>
                                <#else>
                                <li>
                                    <a href="/sell/seller/order/list?page=${currentPage+1}&size=${orderDTOPage.size}">Next</a>
                                </li>
                                </#if>

                    </ul>
                </div>
            </div>
        </div>

    </div>
    <div class="modal fade" id="mymodal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title" id="myModalLabel">
                        New Message!
                    </h4>
                </div>
                <div class="modal-body">
                    You got new orders
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button> <button onclick="location.reload()" type="button" class="btn btn-primary">Check new order</button>
                </div>
            </div>

        </div>

    </div>
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <#--new order notification-->
    <script>
        var websocket =null;
        if('WebSocket' in window){
            websocket = new WebSocket('ws://10.0.0.199:8080/sell/webSocket');
        }
        else{
            alert('browser doesn support websocket');
        }
        websocket.onopen = function(event){
            console.log('host connect');
        }
        websocket.onclose=function(event){
            console.log('disconnected');
        }
        websocket.onmessage=function(event){
            console.log('new order'+ event.data);
            $("#mymodal").modal('show')
        }
        websocket.onerror=function(){
            alert('websocket error');
        }
        window.onbeforeunload=function () {
            websocket.close();

        }
    </script>
    </body>
</html>
<#--</html>-->
<#--<h1>${orderDTOPage.totalPages}</h1>-->

<#--<#list orderDTOPage.content as orderDTO>-->
    <#--${orderDTO.orderId}-->

<#--</#list>-->