/**
 * Created by saeed on 29/12/2015.
 */
var myCodeMirror;
var textAreaId = 'ruleEditorTextArea';
var importBtnId='importBtn';
$(document).ready(function () {
    makeEditorCodeMirror();
    console.log('page is ready2');
    $(document).keypress(onKeyPress);

});
function onKeyPress(e) {
    //on press ENter
    if (e.keyCode == 13) {
        var focused = document.activeElement;
        //if active element is selected class
        if (focused.id =='classes_input') {
            $('#' + importBtnId).click();
            return false;
        }
    }
}
function getClasses() {
    var str = $('#classList').val();
    console.log('classes:');
    console.log(str);
    return JSON.parse(str);

}
function getClassMethod(tokenstr) {
    var str = $('#methodList').val();
    console.log('methods');
    console.log(str);
    var allMethods = JSON.parse(str);
    var allClasses=getClasses();

    if (str == '.')return allMethods;
    var filteredMethod = [];
    $.each(allMethods, function (n, val) {
        if (val.toLowerCase().indexOf(tokenstr.toLowerCase()) > -1) {
            filteredMethod.push(val);
        }
    });
    $.each(allClasses, function (n, val) {
        if (val.toLowerCase().indexOf(tokenstr.toLowerCase()) > -1) {
            filteredMethod.push(val);
        }
    });
    return filteredMethod;
}
function autocomplete(t, c) {
    var hintList = getClasses();
    var cursor = myCodeMirror.getCursor();
    var token = myCodeMirror.getTokenAt(cursor);
    console.log(cursor);
    console.log('token : ' + JSON.stringify(token));
    //debugger;
    var newFrom = myCodeMirror.getCursor();
    var newTo = myCodeMirror.getCursor();
    if (true || token.string.trim() != '') {
        debugger;
        var c2 = cursor;
        //c2.ch=cursor.ch-1;
        token = myCodeMirror.getTokenAt(c2);
        hintList = getClassMethod(token.string.trim());
        //cursor.ch++;
        if (hintList.length > 0 && token.string != ".")
            newFrom.ch = newFrom.ch - token.string.trim().length;
    }
    var hint = {};

    newFrom.ch = newFrom.ch;
    hint.from = newFrom;
    hint.to = myCodeMirror.getCursor();
    hint.list = hintList;

    CodeMirror.showHint(myCodeMirror, function () {
        console.log('hint called..');
        console.log(JSON.stringify(hint));
        return hint;
    });
    //console.log(token)


}
function makeEditorCodeMirror(btnid) {
    var myTextArea = $('#' + textAreaId)[0];
    var options =
    {
        mode: "text/x-java",
        theme: 'eclipse',
        lineNumbers: 'true',
        showCursorWhenSelecting: 'true',
        lineWiseCopyCut: 'true',
        extraKeys: {
            "Ctrl-Space": function (t, e) {
                autocomplete(t, e);
            }
        }
    };

    myCodeMirror = CodeMirror.fromTextArea(myTextArea, options);
    console.log('code mirror called');
}
