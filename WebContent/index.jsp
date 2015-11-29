<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<jsp:useBean id="now" class="java.util.Date" />
<html>
<head>
<meta charset="UTF-8">
<title>Endeca Query</title>
<link rel="stylesheet" href="./css/reset.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
<style>
.container {
	margin-right: 0px;
	margin-left: 0px;
}

.row-separator {
	border-bottom: solid #ccc;
	padding: 5px;
}

.indent {
	margin: 10px;
}
</style>

</head>
<body>
	<h1>Endeca Query</h1>
	<%-- 
	<div class="container">
		<form role="form">
			<div class="row">
				<div class="col-xs-3">
					<label for="ex1">server</label> <input class="form-control"
						id="ex1" type="text" value="localhost">
				</div>
				<div class="col-xs-2">
					<label for="ex2">port</label> <input class="form-control" id="ex2"
						type="text" value="15000">
				</div>
				<div class="col-xs-2">
					<button type="button" class="btn btn-default"
						aria-label="Left Align">
						<span class="glyphicon glyphicon-play" aria-hidden="true"></span>
					</button>
				</div>
			</div>
		</form>
	</div>
	--%>
	<h2>General</h2>
	<div class="container">
		<div class="row">
			<div class="col-sm-3 col-md-2 col-lg-2">Date:</div>
			<div class="col-sm-9 col-md-8 col-lg-7">
				<fmt:formatDate type="both" dateStyle="long" timeStyle="long"
					value="${now}" />
			</div>
		</div>
		<div class="row">
			<div class="col-sm-3 col-md-2 col-lg-2">URL Request:</div>
			<div class="col-sm-9 col-md-8 col-lg-7">
				<a href="${bean.requestUrl}?<c:out value="${bean.parameters}"/>"><c:out
						value="${bean.requestUrl}" />?<c:out value="${bean.parameters}" /></a>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-3 col-md-2 col-lg-2">Referring URL:</div>
			<div class="col-sm-9 col-md-8 col-lg-7">
				<a href="${bean.referringURL}"><c:out
						value="${bean.referringURL}" /></a>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-3 col-md-2 col-lg-2">Session Id:</div>
			<div class="col-sm-9 col-md-8 col-lg-7">
				<c:out value="${pageContext.session.id}" />
			</div>
		</div>
	</div>
	<div id="content">
		<ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
			<li class="active"><a href="#data" data-toggle="tab">Data</a></li>
			<li><a href="#query" data-toggle="tab">Query</a></li>
			<li><a href="#queryresults" data-toggle="tab">Query Results</a></li>
		</ul>
		<div id="my-tab-content" class="tab-content">
			<div id="my-tab-content" class="tab-content">
				<div class="tab-pane" id="query">
					<h2>Query Information</h2>
					<div class="container">
						<c:forEach var="entry" items="${bean.queryMethods}">
							<div class="row row-separator">
								<div class="col-sm-9 col-md-8 col-lg-7">${entry.key }</div>
								<div class="col-sm-3 col-md-4 col-lg-5">${entry.value }</div>
							</div>
						</c:forEach>
					</div>
				</div>
				<div class="tab-pane" id="queryresults">
					<h2>Query Results Information</h2>
					<div class="container">
						<c:forEach var="entry" items="${bean.queryResultsMethods}">
							<div class="row row-separator">
								<div class="col-sm-9 col-md-8 col-lg-7">${entry.key }</div>
								<div class="col-sm-3 col-md-4 col-lg-5">${entry.value }</div>
							</div>
						</c:forEach>
					</div>
				</div>
				<div class="tab-pane active" id="data">

					<div class="container">
						<div class="row">
							<div class="col-sm-5 col-md-5 col-lg-4">
								<h2>Dimension</h2>
							</div>
							<div class="col-sm-7 col-md-7 col-lg-8">
								<h2>Descriptor Dimensions</h2>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-5 col-md-5 col-lg-4">
								<c:forEach var="uidim" items="${bean.navigation}">
									<div class="row">
										<div class="col-sm-12 col-md-12 col-lg-12">
											<a
												href="${bean.requestUrl}?${bean.getQueryString('Ne', uidim.id) }"><c:out
													value="${uidim.name}" /></a><br>
											<ul class="indent">
												<c:forEach var="uidimref" items="${uidim.refinements }">
													<ol>
														<a
															href="${bean.requestUrl}?${bean.getQueryString('N', uidimref.id) }">${uidimref.name }</a>(${uidimref.count})
													</ol>
												</c:forEach>
											</ul>
										</div>
									</div>
								</c:forEach>
							</div>
							<div class="col-sm-7 col-md-7 col-lg-8">
								<div class="row">
									<div class="col-sm-12 col-md-12 col-lg-12">
										<c:forEach var="uidim" items="${bean.list1}">
											<div class="row">
												<div class="col-sm-12 col-md-12 col-lg-12">
													<a
														href="${bean.requestUrl}?${bean.getQueryString('N', uidim.refinements[fn:length(uidim.refinements)-1].id) }">Remove</a>
													<c:forEach var="uidimref" items="${uidim.refinements }"
														varStatus="status">
														${uidimref.name } (${uidimref.count})
														<c:if
															test="${status.index <  (fn:length(uidim.refinements) - 1) }"> > </c:if>

													</c:forEach>
												</div>
											</div>
										</c:forEach>
										<div class="row">
											<div class="col-sm-12 col-md-12 col-lg-12">
												<h2>Records</h2>
											</div>
										</div>
										<div class="row">
											<div class="col-sm-12 col-md-12 col-lg-12">
												Matching Records:
												<c:out value="${bean.recordCount }" />
											</div>
										</div>
										<div class="row">
											<div class="col-sm-12 col-md-12 col-lg-12">
												<br>
												<!-- SPACER -->
											</div>
										</div>
										<c:forEach var="record" items="${bean.recordsList}"
											varStatus="status">
											<div class="row">
												<div class="col-sm-12 col-md-12 col-lg-12">
													<h3>${record.spec }</h3>
													<ol>
														<c:forEach var="property" items="${record.properties}"
															varStatus="status">
															<ul>
																<font face="Arial" color="gray">${property.key}:</font>
																${property.value }
															</ul>
														</c:forEach>
													</ol>
													<h4>Associated Dimensions</h4>
													<ol>
														<c:forEach var="assocDimension" items="${record.dimValues}"
															varStatus="status">
															<ul>
																<font face="Arial" color="gray">${assocDimension[0].dimValue.dimensionName }:</font>
																${assocDimension[0].dimValue.name }
															</ul>
														</c:forEach>
													</ol>
												</div>
											</div>
										</c:forEach>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<script type="text/javascript">
				jQuery(document).ready(function($) {
					$('#tabs').tab();
				});
			</script>
			<script type="text/javascript" charset="utf-8" async
				src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>