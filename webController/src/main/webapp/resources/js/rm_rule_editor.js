/**
 * Created by saeed on 29/12/2015.
 */
var myCodeMirror;
var textAreaId='ruleEditorTextArea';
$(document).ready(function()
{
    makeEditorCodeMirror();
    console.log('page is ready2');

});
function autocomplete(t,c)
{
    var cursor = myCodeMirror.getCursor();
    var token = myCodeMirror.getTokenTypeAt(cursor);
    console.log(cursor);
    console.log(t);
    console.log(c);
    console.log(token);
    CodeMirror.showHint(myCodeMirror,function(){return {from: myCodeMirror.getCursor(), to: myCodeMirror.getCursor(),list:["ryte","uyiu"]};});
    //console.log(token)
    if (token == "."){
        myCodeMirror.replaceSelection("=" , "end");
    }

}
function makeEditorCodeMirror()
{
    var myTextArea=$('#'+textAreaId)[0];
    var options=
    {
        mode:"text/x-java",
        theme:'eclipse',
        lineNumbers:'true',
        showCursorWhenSelecting:'true',
        lineWiseCopyCut:'true',
        extraKeys: {"Ctrl-Space": function(t,e){autocomplete(t,e);}}
    };

    myCodeMirror = CodeMirror.fromTextArea(myTextArea,options);
    console.log('code mirror called');


}