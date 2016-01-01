/**
 * Created by saeed on 29/12/2015.
 */
var textAreaId='ruleEditorTextArea';
$(document).ready(function()
{
    console.log('page is ready');

});
function makeEditorCodeMirror()
{
    var myTextArea=$('#'+textAreaId)[0];
    var options=
    {
        mode:"text/x-java",
        theme:'eclipse'
    };
    var myCodeMirror = CodeMirror.fromTextArea(myTextArea,options);

}