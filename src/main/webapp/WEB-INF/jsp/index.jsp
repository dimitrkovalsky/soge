<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Api Explorer</title>
</head>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.0/css/font-awesome.min.css">
<link rel="stylesheet" href="<c:url value="resources/css/tether.min.css"/>">
<link rel="stylesheet" href="<c:url value="resources/css/bootstrap.min.css"/>">
<link rel="stylesheet" href="<c:url value="resources/css/mdb.min.css"/>">
<link rel="stylesheet" href="<c:url value="resources/css/style.css"/>">

<script src="<c:url value="resources/lib/jquery-2.2.3.js"/>"></script>
<script src="<c:url value="resources/lib/tether.min.js"/>"></script>
<script src="<c:url value="resources/lib/bootstrap.min.js"/>"></script>
<script src="<c:url value="resources/lib/mdb.min.js"/>"></script>

<script src="<c:url value="resources/js/http.js"/>"></script>
<script src="<c:url value="resources/lib/sockjs.js"/>"></script>
<script src="<c:url value="resources/lib/stomp.js"/>"></script>
<script src="<c:url value="resources/js/connect.js"/>"></script>


<body>

<div class="container col-md-12" style="margin-top:10px">
    <div class="col-md-2 pull-left">
    <span class="list-group col-md-10" id="requestList">
        <a href="#" class="list-group-item active" style="border-color: #4c9662; background-color: #4c9662;">
            Available requests
        </a>
    </span>
        <div class="md-form">
            <input type="text" class="form-control col-md-10" id="modelNameInput" value="">
            <button class="btn btn-success waves-effect col-md-10" onclick="generateModel()">create model</button>
            <input type="text" class="form-control col-md-10" id="repositoryNameInput" value="">
            <button class="btn btn-success waves-effect col-md-10" onclick="generateRepository()">create repository
            </button>

            <div class="form-group">
            <input type="text" class="form-control-inline col-md-8" id="actionNameInput" value="">
            <input type="text" class="form-control-inline col-md-8" id="messageTypeInput" value="100500">
       
            <button class="btn btn-success waves-effect col-md-10" onclick="generateAction()">create action
            </button>
            </div>
        </div>

    </div>
    <div class="col-md-10 pull-right">
        <div class="card col-md-8">
            <div class="card-header primary-color white-text">
                SOGE Http
            </div>

            </div>
            <form class="form-signin" name="login" method="post" action="<c:url value='login' />">
                <input type="text" id="login" class="form-control" name="j_username" value="test" />
                <input type="password" id="password" class="form-control" name="j_password" value="test" />
                <span class="input-group-btn">
                    <input class="btn btn-success waves-effect" type="submit" value="login" />" />
                </span>
            </form>
            <script type="text/javascript">
            $(document).ready(function() {
                $(".form-signin").submit(function(event) {
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

            <h5>Request</h5>
            <div class="md-form">
               <textarea type="text" id="requestData"
                         class="md-textarea col-md-10">{"messageType":1,"data":null}</textarea>
                <button class="btn btn-info pull-right waves-effect" id="send_btn" onclick="sendRequest()">Send</button>
            </div>
        </div>

        <div class="card col-md-8">
            <div class="card-header blue-grey white-text">Received messages</div>
            <div class="card-block" style="height: 350px">
                <ul id="logList" style="height: 300px; overflow: auto">
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>