<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <div class="panel-body">
        <form class="form-signin" name="login" method="post" action="<c:url value="login" />">
            <div class="form-group">
                <input id="login" placeholder="Nickname or email address" class="form-control input-sm" name="username" type="text">
            </div>
            <div class="form-group">
            <a href="/reset_password"><small>Forgot password?</small></a>
                <input class="form-control input-sm" placeholder="Password" name="password" id="inputPassword" type="password">
            </div>
            <div class="form-group">
                <input type="checkbox" value="remember-me"><small>Remember me</small>
            </div>
            <div class="form-group">
                <input class="btn btn-info btn-block" type="submit" value="Login" />
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <c:if test="${not empty param.error}">
                <div class="alert alert-danger" role="alert">error
                    : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
                </div>
            </c:if>
        </form>

<script type="text/javascript">
$(document).ready(function() {
    $(".submit-form").submit(function(event) {	
        var postData = $(this).serializeArray();
        var formURL = $(this).attr('action');
        var method = $(this).attr('method');
      $.ajax({
          type: method,
          url: formURL,
          data: postData,
          success: function(data, textStatus, jqXHR) {
              console.log('success');
              if(jqXHR.status === 200) {
                  $('.result').html('<div class="alert alert-success" role="alert">'+ jqXHR.responseText + '</div>');
                  window.setTimeout(function () {
                      $(".alert").fadeTo(500, 0).slideUp(500, function () {
                          $(this).remove();
                      });
                  }, 5000);
              }
            },
          error: function(jqXHR, textStatus, errorThrown) {
              console.log(jqXHR);
              $('.result').html('<div class="alert alert-danger" role="alert">' + jqXHR.responseText + '</div>');
              window.setTimeout(function () {
                  $(".alert").fadeTo(500, 0).slideUp(500, function () {
                      $(this).remove();
                  });
              }, 5000);
          }
        });
      return false;
    });
});
</script>
</div>