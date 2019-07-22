


function validarLogin(forma){
	let valor = true;
	let valorfocus = '';
	
	if (document.getElementById('usuario').value == '') {
		document.getElementById('alerta').style.display = '';
		valor = false;
		valorfocus = '1'; 
	}
	
	if (document.getElementById('password').value == '') {
		document.getElementById('alerta').style.display = ''
		valor = false;
		valorfocus += '2';
	}
	
	if (document.getElementById('database').value == '') {
		document.getElementById('alerta').style.display = '';
		valor = false;
		valorfocus += '3';
	}

	//console.log(valorfocus);

	if (valorfocus == '3'){
		document.getElementById('database').focus();
		document.getElementById('alerta').innerHTML  = 'Campo requerido * : Base de datos';
	}
	if (valorfocus == '2' || valorfocus == '23'  ){
		document.getElementById('alerta').innerHTML  = 'Campo requerido * : Contrase&ntilde;a<br>' ;
		document.getElementById('password').focus();
	}
	if (valorfocus == '1' || valorfocus == '12' || valorfocus == '123'  ){
		document.getElementById('alerta').innerHTML  = 'Campo requerido * : Usuario <br>';
		document.getElementById('usuario').focus();
	}


	return valor;
}


					



	