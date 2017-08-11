var je = require('jsencrypt');
var $ = require("jquery");

var encrypt = new je.JSEncrypt();

encrypt.setPublicKey($('#pubkey').text());

$('#testme').click(function() {
	var encrypted = encrypt.encrypt($('#input').val());
	$.ajax({
		url:'http://localhost:81/decrypt',
		type:'post',
		cache:false,
		data:{encrypt:encrypted},
		success:function(data){
			alert(data);
		}
	});
});

// $('#testme').click(function{
	// $.ajax({
	// url:'http://localhost:81/decrypt',
	// data:{
		// encrypt:encrypted
	// },
	// type:'post',
    // cache:false,
	// success:function(data){
		// alert(data);
	// },
	// error:function(){
		// alert("异常！");
	// }
// });
