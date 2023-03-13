

async function registrarUsuario(){
	

	console.log("entre a registrar usuarios")
	let datos = {};
	datos.nombre = document.getElementById('txtNombre').value;
	datos.apellido = document.getElementById('txtApellido').value;
	datos.email = document.getElementById('txtEmail').value;
	datos.telefono = document.getElementById('txtTelefono').value;
	datos.password = document.getElementById('txtPassword').value;
	
	
	console.log("Datos: ",datos)
	
	let repetirPassword = document.getElementById('txtRepetirPassword').value;
	
	if(repetirPassword !== datos.password ){
		
		alert('Las contraseñas no coinciden ')
		return;
	}
	
const respuesta = await fetch('api/usuarios',{
	
	method: 'POST',
	headers: {
		'Accept':'application/json',
		'Content-Type':'application/json'
		
	},
	body: JSON.stringify(datos)
});	


	
}