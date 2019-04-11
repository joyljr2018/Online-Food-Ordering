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
                                    Product ID
                                </th>
                                <th>
                                    Name
                                </th>
                                <th>
                                    Icon
                                </th>
                                <th>
                                    Price
                                </th>
                                <th>
                                    Stock
                                </th>
                                <th>
                                    Description
                                </th>
                                <th>
                                    Category
                                </th>
                                <th>
                                    Create time
                                </th>
                                <th>
                                    Update time
                                </th>
                                <th colspan="2">
                                    Process
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                    <#list productInfoPage.content as productInfo>




                    <tr>
                        <td>
                            ${productInfo.productId}
                        </td>
                        <td>
                            ${productInfo.productName}
                        </td>
                        <td>
                            <img heigh="100" width="100" src="${productInfo.productIcon}" alt="">

                        </td>
                        <td>
                            ${productInfo.productPrice}
                        </td>
                        <td>
                            ${productInfo.productStock}
                        </td>
                        <td>
                            ${productInfo.productDescription}
                        </td>
                        <td>
                            ${productInfo.categoryType}
                        </td>
                        <td>
                            ${productInfo.createTime}
                        </td>
                        <td>
                            ${productInfo.updateTime}
                        </td>
                        <td>
                            <a href="/sell/seller/product/index?productId=${productInfo.productId}">Edit</a></td>
                        <td>
                                <#if productInfo.getProductStatus()==   0>
                                <a href="/sell/seller/product/off_sale?productId=${productInfo.productId}">Down</a>
                                <#else>
                                <a href="/sell/seller/product/on_sale?productId=${productInfo.productId}">Up</a>
                                </#if>
                            </td>
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

                <#list 1..productInfoPage.getTotalPages() as index>
                    <#if currentPage==index>
                    <li class="disabled"><a href="#">${index}</a></li>
                    <#else>
                      <li><a href="/sell/seller/order/list?page=${index}&size=${productInfoPage.size}">${index}</a></li>
                    </#if>
                </#list>
                                <#if currentPage gte productInfoPage.getTotalPages()>
                                                    <li class="disabled">
                                                        <a href="#">Next</a>
                                                    </li>
                                <#else>
                                <li>
                                    <a href="/sell/seller/order/list?page=${currentPage+1}&size=${productInfoPage.size}">Next</a>
                                </li>
                                </#if>

                    </ul>
                </div>
            </div>
        </div>

    </div>

    </body>

<#--</html>-->
<#--<h1>${orderDTOPage.totalPages}</h1>-->

<#--<#list orderDTOPage.content as orderDTO>-->
    <#--${orderDTO.orderId}-->

<#--</#list>-->