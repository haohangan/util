var je = require('jsencrypt');
var $ = require("jquery");

var encrypt = new je.JSEncrypt();

encrypt.setPublicKey($('#pubkey').text());

$('#testme').click(function() {
	var encrypted = encrypt.encrypt($('#input').val());
	// $.ajax({
		// url:'http://localhost:81/decrypt',
		// type:'post',
		// cache:false,
		// data:{encrypt:encrypted},
		// success:function(data){
			// alert(data);
		// }
	// });'encrypt='+encrypted
	
	var form = new FormData();
	form.append('encrypt',encrypted);
	
	
	fetch("http://localhost:81/decrypt", {
	  method: "POST",
	  mode: 'cors',
	  body: form
	}).then(function(resp){
		return resp.text();
	}).then(function(r){
		alert(r);
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
