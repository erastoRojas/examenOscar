<%-- 
    Document   : pintarUsers
    Created on : 06-mar-2018, 8:43:06
    Author     : DAW
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

        <title>Users</title>
        
    </head>
    <body>

            <div class="container">
                <div class="col-xs-8 col-xs-offset-2">
                    <h1>Usuarios</h1>
                
                    <form action="${pageContext.request.contextPath}/UsersCajas?op=op" id="formulario" name="formulario" method="get">
                        <input type="text" id="nombre" name="nombre" placeholder="nombre Usuario"/>
                        <input type="text" id="pass" name="pass" placeholder="pass de usuario" size="12"/>
                        
                        
                        
                        <input type="text" id="nombreCaja" name="nombreCaja" placeholder="nombreCaja"/>
                        <input type="text" id="nombreCosa" name="nombreCosa" placeholder="nombreCosa" size="12"/>
                        <input type="text" id="cantidadCosa" name="cantidadCosa" placeholder="cantidadCosa" size="12"/>
                        
                        <br>
                        <br>
                        <button name="op" id="op" value="ListarCajas">Listar Cajas</button>
                        <button name="op" id="op" value="AnadirCaja">AnadirCaja</button>
                        <button name="op" id="op" value="AnadirCosasCaja">AnadirCosasCaja</button>
                        <button name="op" id="op" value="ListarCosas">Listar Cosas</button>
                        <button name="op" id="op" value="AnadirCantidadCosas">Añadir Cantidad Cosas</button>
                    </form>
                    <br>
                    <c:if test="${mensajeUser == null}">
                        <table id="tabla" class="table table-striped">
                            <tr style="font-weight: bold">
                                <td>Nombre Cajas</td>
                                <td>Cosas</td>

                            </tr>
                            <c:forEach items="${users}" var="caja">  
                                <tr>
                                    
                                    <td>${caja.nombre}</td>
                                    
                                    <c:forEach items="${caja.cosas}" var="cosa"> 
                                    <td>${cosa.nombre}:${cosa.cantidad}</td>
                                    </c:forEach>
                                </tr>
                            </c:forEach>                   
                        </table>
                    </c:if>
                    <h1 id="mensajeUser">${mensajeUser}</h1>
                    <h1 id="error">${error}</h1>
                </div>
            </div>
    </body>
</html>
