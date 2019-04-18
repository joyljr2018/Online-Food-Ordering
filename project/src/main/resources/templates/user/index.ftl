<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
<head th:replace="~{fragments/header :: header}">
</head>
<body>

<div class="container blog-content-container">
    <form role="form" method="post" action="/sell/seller/login">
        <div class="form-group">
            <label>Username</label>
            <input name="username" type="text" class="form-control" value=""/>
        </div>
        <div class="form-group">
            <label>Password</label>
            <input name="password" type="password" class="form-control"  value=""/>
        </div>

        <button type="submit" class="btn btn-default">Log in</button>
    </form>
    <#--<form  th:action="/sell/seller/login" method="post">-->
        <#--<h2 >Please log in</h2>-->

        <#--<div class="form-group col-md-5">-->
            <#--<label for="username" class="col-form-label">Username</label>-->
            <#--<input type="text" class="form-control" id="username" name="username" maxlength="50" placeholder="Please enter username">-->

        <#--</div>-->
        <#--<div class="form-group col-md-5">-->
            <#--<label for="password" class="col-form-label">Password</label>-->
            <#--<input type="password" class="form-control" id="password" name="password" maxlength="30" placeholder="Please enter password" >-->
        <#--</div>-->
        <#--<div class="form-group col-md-5">-->
            <#--<input type="checkbox" name="remember-me"> Remember me-->
        <#--</div>-->
        <#--<div class="form-group col-md-5">-->
            <#--<button type="submit" class="btn btn-primary">Log in</button>-->
        <#--</div>-->
        <#--<div class=" col-md-5" th:if="">-->
            <#--<p class="blog-label-error" th:text=""></p>-->
        <#--</div>-->
    <#--</form>-->
</div> <!-- /container -->


</body>
</html>