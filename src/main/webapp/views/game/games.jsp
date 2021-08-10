<%--
  Created by IntelliJ IDEA.
  User: CDS
  Date: 04/08/2021
  Time: 11:58 a. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>Listado de videojuegos</title>
    <link rel="stylesheet" href="${context}/assets/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${context}/assets/dist/css/styles.css">
    <link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
</head>
<body>
<br>
<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal"><i class="fas fa-plus"></i>
    Agregar juego
</button>
<br>
    <table id="container" class="table table-hover">
        <thead class="table-dark">
            <tr>
                <th>#</th>
                <th>Nombre</th>
                <th>Fecha</th>
                <th>Imagen</th>
                <th>Estado</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody></tbody>
    </table>
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Registrar juego</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="${context}/createGame" method="POST">
                    <input type="hidden" value="create" name="action">
                    <label>Nombre del juego</label>
                    <input class="form-control" type="text" name="nombre"/>
                    <br>
                    <label>Fecha</label>
                    <input class="form-control" type="text" name="fecha" />
                    <br>
                    <label>Imagen</label>
                    <input type="file" name="imagen">
                    <br><br>
                    <label>Estado</label>
                    <select class="form-select" name="estado">
                        <option value="1">Actvo</option>
                        <option value="0">Inactivo</option>
                    </select>
                    <br>
                    <button type="submit" class="btn btn-success"><i class="fas fa-plus"></i> Agregar</button>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
            </div>
        </div>
    </div>
</div>
    <script src="${context}/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
    <script src="${context}/assets/dist/js/main.js"></script>
</body>
</html>
