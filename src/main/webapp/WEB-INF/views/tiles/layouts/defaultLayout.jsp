<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>

<head>
<title><tiles:getAsString name="title" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<link rel="stylesheet" href="<c:url value="resources/css/bootstrap.min.css"/>">
<link rel="stylesheet" href="<c:url value="resources/css/sb-admin.css"/>">
<link rel="stylesheet" href="<c:url value="resources/css/plugins/morris.css"/>">
<link rel="stylesheet" href="<c:url value="resources/css/style.css"/>">
<link rel="stylesheet" href="<c:url value="resources/font-awesome/css/font-awesome.min.css"/>">

<script type="text/javascript" src="<c:url value="resources/js/http.js"/>"></script>
<script type="text/javascript" src="<c:url value="resources/lib/sockjs.js"/>"></script>
<script type="text/javascript" src="<c:url value="resources/lib/stomp.js"/>"></script>
<script type="text/javascript" src="<c:url value="resources/js/connect.js"/>"></script>


</head>

<body>
<div id="wrapper">
    <tiles:insertAttribute name="header" />
    <tiles:insertAttribute name="sidebar" />
    <div id="page-wrapper">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">
                    Dashboard <small>Api Explorer</small>
                </h1>
                <ol class="breadcrumb">
                    <li class="active">
                        <i class="fa fa-dashboard"></i> Dashboard
                    </li>
                </ol>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <tiles:insertAttribute name="body" />
            </div>
            </div>
    </div>
</div>
</div>
    <script type="text/javascript" src="<c:url value="resources/js/jquery.js"/>"></script>

    <!-- Bootstrap Core JavaScript -->
    <script type="text/javascript" src="<c:url value="resources/js/bootstrap.min.js"/>"></script>

    <!-- Morris Charts JavaScript -->
    <script type="text/javascript" src="<c:url value="resources/js/plugins/morris/raphael.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="resources/js/plugins/morris/morris.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="resources/js/plugins/morris/morris-data.js"/>"></script>


</body>
</html>
