$(document).ready(function () {
    // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
    $('.modal').modal();

    $("#aic").click(function(){
        $("#te").toggleClass("red-text");
    });

    $('.chips-placeholder').material_chip({
        placeholder: ' Enter a player',
        secondaryPlaceholder: 'Enter player name',
    });

    $('#bbb').click(function () {
        this.value = '';
        alert("clear");
    });
});
//
// $(document).ready(function() {
//
// });
