$(document).ready(function() {
    $.fn.fnOpenModaRepo = function () {
        $(this).unbind("click").on("click", function(e)
        {
            var ctxt = $(this).data("ctxt");
            var repo = $(this).data("repo");
            var prms = $(this).data("prms");
            var repoURL = ctxt + "/RepoServ?nombRepo=" + repo + "&" + prms;
            var options = {
                pdfOpenParams: {
                        navpanes: 0,
                        toolbar: 0,
                        statusbar: 0,
                        view: "FitV",
                        pagemode: "none",
                        page: 1
                },
                forcePDFJS: true,
                PDFJS_URL: ctxt + "/resources/lib/pdfjs/web/viewer.html"
            };
            var myPDF = PDFObject.embed(repoURL, "#pdf", options);
            $("#ModaRepo").modal("show");
            return false;
        });
    };
    initObjeRepo();
});

function initObjeRepo()
{
    $("#testBton").fnOpenModaRepo();
    $("#testBtonParams").fnOpenModaRepo();
}
