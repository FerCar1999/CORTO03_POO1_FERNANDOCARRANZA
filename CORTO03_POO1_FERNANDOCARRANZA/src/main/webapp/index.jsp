<%-- 
    Document   : index
    Created on : 25-abr-2018, 16:40:02
    Author     : Fernando Carranza
--%>

<%@page import="com.sv.udb.controlador.ControladorPrestamo"%>
<%@page import="com.sv.udb.controlador.ControladorLibro"%>
<%@page import="com.sv.udb.controlador.ControladorUsuario"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Examen 3</title>
        <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
        <script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
        <script type="text/javascript" src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <style>
            /*
            PDFObject appends the classname "pdfobject-container" to the target element.
            This enables you to style the element differently depending on whether the embed was successful.
            In this example, a successful embed will result in a large box.
            A failed embed will not have dimensions specified, so you don't see an oddly large empty box.
            */

            .pdfobject-container {
                width: 100%;
                height: 600px;
                margin: 2em 0;
            }

            .pdfobject { border: solid 1px #666; }
        </style>
    </head>
    <body>
        <%
            boolean estaModi = Boolean.parseBoolean((String) request.getAttribute("estaModi"));
            String nombBton = estaModi ? "Nuevo" : "Prestar"; // Para el texto del botón
            String clssEditBton = estaModi ? "" : "display: none"; //Pra ocultar botones
        %>
        <div class="container">
            <p></p>
            <ul class="nav nav-tabs">
                <li><a data-toggle="tab" href="#prestamos">Prestamos</a></li>
                <li><a data-toggle="tab" href="#devoluciones">Desembolso</a></li>
                <li><a data-toggle="tab" href="#reporte">Reportes</a></li>
            </ul>

            <div class="tab-content">
                <div id="prestamos" class="tab-pane active">
                    <div class="row">
                        <div class="col-md-5">
                            <div class="panel panel-primary">
                                <div class="panel-heading">Informacion de Prestamos</div>
                                <div class="panel-body">
                                    <c:if test="${!resp}">
                                        <div class="alert alert-success">
                                            ${mensAlerJ}
                                        </div>
                                    </c:if>
                                    <c:if test="${resp}">
                                        <div class="alert alert-danger">
                                            ${mensAlerJ}
                                        </div>
                                    </c:if>
                                    <form method="POST" action="PresServ" name="Demo">
                                        <input type="hidden" name="codi" id="codi" value="${codi}"/>
                                        <div class="form-group">
                                            <label for="usuario">Usuarios:${nombUsua}</label>
                                            <select class="form-control" id="usuario" name="usuario">
                                                <c:forEach var="usua" items="<%= new ControladorUsuario().consTodo()%>">
                                                    <option value="${usua.getCodi_usua()}">${usua.getNomb_usua()}</option>
                                                </c:forEach>
                                            </select> 
                                        </div>
                                        <div class="form-group">
                                            <label for="libro">Libros:${nombLibr}</label>
                                            <select class="form-control" id="libro" name="libro">
                                                <c:forEach var="libr" items="<%= new ControladorLibro().consTodo()%>">
                                                    <option value="${libr.getCodi_libr()}">${libr.getNomb_libr()}</option>
                                                </c:forEach>
                                            </select> 
                                        </div>
                                        <div class="form-group">
                                            <label for="fechaP">Fecha Prestamo</label>
                                            <input disabled type="text" class="form-control" name="fechaP" id="fechaP" value="${fechaP}"/>
                                        </div>
                                        <input type="submit" class="btn btn-default" name="btonPres" value="<%= nombBton%>"/>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-7">
                            <div class="panel panel-primary">
                                <div class="panel-heading">Prestamos</div>
                                <div class="panel-body">
                                    <form method="POST" action="PresServ" name="Tabl">
                                        <display:table export="true" id="tabBode" name="<%= new ControladorPrestamo().consTodo()%>">
                                            <display:column title="Cons">
                                                <input type="radio" name="codiPresRadi" value="${tabBode.codi_pres}"/>
                                            </display:column>
                                            <display:column property="codi_usua" title="Usuario" sortable="true"/>
                                            <display:column property="codi_libr" title="Libro" sortable="true"/>
                                            <display:column property="fech_pres" title="Fecha Prestamo" sortable="true"/>
                                            <display:column property="fech_devo" title="Fecha Devolucion" sortable="true"/>
                                        </display:table>
                                        <input type="submit" class="btn btn-success" name="btonPres" value="Consultar"/>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="devoluciones" class="tab-pane">
                    <div class="row">
                        <div class="col-md-5">
                            <div class="panel panel-primary">
                                <div class="panel-heading">Informacion de Desembolso</div>
                                <div class="panel-body">
                                    <c:if test="${!respDevo}">
                                        <div class="alert alert-success">
                                            ${mensAlerJD}
                                        </div>
                                    </c:if>
                                    <c:if test="${respDevo}">
                                        <div class="alert alert-danger">
                                            ${mensAlerJD}
                                        </div>
                                    </c:if>
                                    <form method="POST" action="DevoServ" name="Demo">
                                        <input type="hidden" name="codiDevo" id="codiDevo" value="${codiDevo}"/>
                                        <div class="form-group">
                                            <label for="usuarioDevo">Usuarios:</label>
                                            <select class="form-control" id="usuarioDevo" name="usuarioDevo">
                                                <option value="${codiUsuaDevo}" selected disabled>${nombUsuaDevo}</option>
                                                <c:forEach var="usua" items="<%= new ControladorUsuario().consTodo()%>">
                                                    <option value="${usua.getCodi_usua()}">${usua.getNomb_usua()}</option>
                                                </c:forEach>
                                            </select> 
                                        </div>
                                        <div class="form-group">
                                            <label for="libroDevo">Libros:</label>
                                            <select class="form-control" id="libroDevo" name="libroDevo">
                                                <option value="${codiLibrDevo}" selected disabled>${nombLibrDevo}</option>
                                                <c:forEach var="libr" items="<%= new ControladorLibro().consTodo()%>">
                                                    <option value="${libr.getCodi_libr()}">${libr.getNomb_libr()}</option>
                                                </c:forEach>
                                            </select> 
                                        </div>
                                        <div class="form-group">
                                            <label for="fechaPDevo">Fecha Prestamo</label>
                                            <input disabled type="text" class="form-control" name="fechaPDevo" id="fechaPDevo" value="${fechaPDevo}"/>
                                        </div>
                                        <div class="form-group">
                                            <label for="fechaDDevo">Fecha Devolucion</label>
                                            <input disabled type="text" class="form-control" name="fechaDDevo" id="fechaDDevo" value="${fechaDDevo}"/>
                                        </div>

                                        <input type="submit" class="btn btn-default" name="btonDese" value="Devolver"/>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-7">
                            <div class="panel panel-primary">
                                <div class="panel-heading">Desembolsos</div>
                                <div class="panel-body">
                                    <form method="POST" action="DevoServ" name="Tabl">
                                        <display:table export="true" id="tabBode" name="<%= new ControladorPrestamo().consTodo()%>">
                                            <display:column title="Cons">
                                                <input type="radio" name="codiDevoRadi" value="${tabBode.codi_pres}"/>
                                            </display:column>
                                            <display:column property="codi_usua" title="Usuario" sortable="true"/>
                                            <display:column property="codi_libr" title="Libro" sortable="true"/>
                                            <display:column property="fech_pres" title="Fecha Prestamo" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}"/>
                                            <display:column property="fech_devo" title="Fecha Devolucion" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}"/>
                                        </display:table>
                                        <input type="submit" class="btn btn-success" name="btonDese" value="Consultar"/>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="reporte" class="tab-pane ">
                    <div class="row">   
                        <div class="col-md-12">
                            <form method="POST" action="BodegaServ" name="Tabl">
                                <div class="table-responsive">
                                    
                                </div>
                                <input type="submit" class="btn btn-success" name="btnBode" value="Consultar"/>
                                <a href="RepoServ" class="btn btn-primary" name="btnRepo" id="bodeRepo" data-ctxt="${pageContext.request.contextPath}" 
                                   data-repo="bodeRepo" data-prms="" target="_blank">Crear Reporte Libros</a>
                                   <a href="RepoServll" class="btn btn-primary" name="btnRepo" id="bodeRepo" data-ctxt="${pageContext.request.contextPath}" 
                                   data-repo="bodeRepo" data-prms="" target="_blank">Crear Reporte Prestamos</a>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            $(function () {
                $('#fecha').datetimepicker();
                $('#fechaDesde').datetimepicker();
                $('#fechaHasta').datetimepicker();
            });
        </script>
    </body>
</html>