<%-- 
    Document   : carrito
    Created on : 11-mar-2018, 14:12:06
    Author     : Eduardo DAW
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/carrito?op=op" method="get">
            <label>Objeto</label>
            <label><b>id:</b></label>
            <input type="text" id="id" name="id"/>
            <label><b>nombre:</b></label>
            <input type="text" id="nombre" name="nombre"/>
            <label><b>tipo:</b></label>
            <input type="checkbox" id="tipo" name="tipo"><br>
            <div style="border-bottom: 2px solid;padding: 15px"></div>
            <div style="padding: 15px;margin: 15px;">
                <button name="op" id="op" value="ANADIR">Añadir cesta</button>
                <button name="op" id="op" value="INSERT">Insertar</button>
                <button name="op" id="op" value="UPDATE">Actualizar</button>
                <button name="op" id="op" value="DELETE">Borrar</button>
            </div>
        </form>
            
    </body>
</html>
