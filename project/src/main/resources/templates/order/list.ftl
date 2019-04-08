<html>
    <head>
        <meta charset="=utf-8">
        <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">

    </head>
    <body>
    <div class="container">
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

                        <td>detail</td>
                        <td>
                            <#if orderDTO.getOrderStatusEnum().message!="Canceled Order">
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
    </body>

<#--</html>-->
<#--<h1>${orderDTOPage.totalPages}</h1>-->

<#--<#list orderDTOPage.content as orderDTO>-->
    <#--${orderDTO.orderId}-->

<#--</#list>-->