<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <%@ include file="head.jsp"%>
</head>
<body>
<%@ include file="header.jsp"%>

    <div class="container-fluid detail">
        <div class="row detail-header">
            <div class="col-5 detail-img">
                <img src="${document.photo.fileToBase64String}">
            </div>
            <div class="col-7">
                <div class="detail-info">
                    <h2>${document.title}</h2>
                    <hr>
                    <p>Auteur : ${document.author.firstName} ${document.author.lastName}</p>
                    <p>Éditeur : ${document.publisher.name}</p>
                    <p>Isbn : ${document.isbn}</p>
                    <p>Date de publication : ${document.publicationDate}</p>
                </div>
            </div>
        </div>

        <div class="card detail-description">
            <div class="card-body">
                <h3 class="card-title">Description</h3>
                <hr>
                <p class="card-text">${document.description}</p>
            </div>
        </div>

        <div class="card detail-exemplars">
            <div class="card-body">
                <h3 class="card-title">Exemplaires</h3>
                <hr>
                <ul class="list-group">
                    <c:forEach items="${exemplars}" var="exemplar">
                        <li class="list-group-item">
                            ${exemplar.reference} - ${exemplar.library.name}
                            <c:choose>
                                <c:when test="${exemplar.loanEndDate == null}">
                                    <span class="badge badge-success">Disponible</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="badge badge-danger">Indisponible jusqu'au ${exemplar.loanEndDate}</span>
                                </c:otherwise>
                            </c:choose>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>

<%@ include file="footer.jsp"%>
</body>
</html>
