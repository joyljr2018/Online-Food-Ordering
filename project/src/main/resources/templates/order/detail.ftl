<html>
<head>
    <meta charset="utf-8">
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">

    <title>Order Detail</title>

</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-4 column">
            <table class="table">
                <thead>
                <tr>
                    <th>
                        Order ID
                    </th>
                    <th>
                        Order total amount
                    </th>

                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                        ${orderDTO.orderId}
                    </td>
                    <td>
                        ${orderDTO.orderAmount}
                    </td>
                </tr>

                </tbody>
            </table>
        </div>
        <#--order detail-->
        <div class="col-md-12 column">
            <table class="table">
                <thead>
                <tr>
                    <th>
                        Product ID
                    </th>
                    <th>
                        Product Name
                    </th>
                    <th>
                        Price
                    </th>
                    <th>
                        Qty
                    </th>
                    <th>
                        Amount
                    </th>
                </tr>
                </thead>
                <tbody>
                <#list orderDTO.orderDetailList as orderDetail>
                    <tr>
                        <td></td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>